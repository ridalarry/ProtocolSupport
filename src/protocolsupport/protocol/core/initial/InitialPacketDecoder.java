/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPipeline
 *  io.netty.channel.SimpleChannelInboundHandler
 *  io.netty.util.concurrent.EventExecutor
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.ScheduledFuture
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 */
package protocolsupport.protocol.core.initial;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.concurrent.TimeUnit;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import protocolsupport.ProtocolSupport;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.core.initial.ProtocolUtils;
import protocolsupport.protocol.core.initial.SetProtocolTask;
import protocolsupport.protocol.storage.ProtocolStorage;
import protocolsupport.protocol.transformer.v_1_5.PipeLineBuilder;
import protocolsupport.utils.Utils;
import protocolsupport.utils.netty.ChannelUtils;
import protocolsupport.utils.netty.ReplayingDecoderBuffer;

public class InitialPacketDecoder
extends SimpleChannelInboundHandler<ByteBuf> {
    private static final int ping152delay = Utils.getJavaPropertyValue("protocolsupport.ping152delay", 100, Utils.Converter.STRING_TO_INT);
    private static final int pingLegacyDelay = Utils.getJavaPropertyValue("protocolsupport.pinglegacydelay", 200, Utils.Converter.STRING_TO_INT);
    private static final EnumMap<ProtocolVersion, IPipeLineBuilder> pipelineBuilders = new EnumMap(ProtocolVersion.class);
    protected final ByteBuf receivedData = Unpooled.buffer();
    protected final ReplayingDecoderBuffer replayingBuffer = new ReplayingDecoderBuffer(this.receivedData);
    protected Future<?> responseTask;

    public static void init() {
        ProtocolSupport.logInfo("Assume 1.5.2 ping delay: " + ping152delay);
        ProtocolSupport.logInfo("Assume legacy ping dealy: " + pingLegacyDelay);
    }

    protected void scheduleTask(ChannelHandlerContext ctx, Runnable task, long delay, TimeUnit tu) {
        this.responseTask = ctx.executor().schedule(task, delay, tu);
    }

    protected void cancelTask() {
        if (this.responseTask != null) {
            this.responseTask.cancel(true);
            this.responseTask = null;
        }
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.cancelTask();
        super.channelInactive(ctx);
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        this.cancelTask();
        super.handlerRemoved(ctx);
    }

    public void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        if (!buf.isReadable()) {
            return;
        }
        this.receivedData.writeBytes(buf);
        this.receivedData.readerIndex(0);
        this.decode(ctx);
    }

    private void decode(ChannelHandlerContext ctx) throws Exception {
        this.cancelTask();
        Channel channel = ctx.channel();
        short firstbyte = this.replayingBuffer.readUnsignedByte();
        try {
            ProtocolVersion handshakeversion = null;
            switch (firstbyte) {
                case 254: {
                    if (this.replayingBuffer.readableBytes() == 0) {
                        this.scheduleTask(ctx, new SetProtocolTask(this, channel, ProtocolVersion.MINECRAFT_LEGACY), pingLegacyDelay, TimeUnit.MILLISECONDS);
                        break;
                    }
                    if (this.replayingBuffer.readUnsignedByte() == 1) {
                        if (this.replayingBuffer.readableBytes() == 0) {
                            this.scheduleTask(ctx, new SetProtocolTask(this, channel, ProtocolVersion.MINECRAFT_1_5_2), ping152delay, TimeUnit.MILLISECONDS);
                            break;
                        }
                        if (this.replayingBuffer.readUnsignedByte() == 250 && "MC|PingHost".equals(new String(ChannelUtils.toArray(this.replayingBuffer.readBytes(this.replayingBuffer.readUnsignedShort() * 2)), StandardCharsets.UTF_16BE))) {
                            this.replayingBuffer.readUnsignedShort();
                            handshakeversion = ProtocolUtils.get16PingVersion(this.replayingBuffer.readUnsignedByte());
                            break;
                        }
                        handshakeversion = InitialPacketDecoder.attemptDecodeNettyHandshake(this.replayingBuffer);
                        break;
                    }
                    handshakeversion = InitialPacketDecoder.attemptDecodeNettyHandshake(this.replayingBuffer);
                    break;
                }
                case 2: {
                    handshakeversion = ProtocolUtils.readOldHandshake(this.replayingBuffer);
                    break;
                }
                default: {
                    handshakeversion = InitialPacketDecoder.attemptDecodeNettyHandshake(this.replayingBuffer);
                }
            }
            if (handshakeversion != null) {
                this.setProtocol(channel, handshakeversion);
            }
        }
        catch (ReplayingDecoderBuffer.EOFSignal handshakeversion) {
            // empty catch block
        }
    }

    protected void setProtocol(Channel channel, ProtocolVersion version) throws Exception {
        if (MinecraftServer.getServer().isDebugging()) {
            System.out.println(ChannelUtils.getNetworkManagerSocketAddress(channel) + " connected with protocol version " + (Object)((Object)version));
        }
        ProtocolStorage.setProtocolVersion(ChannelUtils.getNetworkManagerSocketAddress(channel), version);
        channel.pipeline().remove("initial_decoder");
        pipelineBuilders.get((Object)version).buildPipeLine(channel, version);
        this.receivedData.readerIndex(0);
        channel.pipeline().firstContext().fireChannelRead((Object)this.receivedData);
    }

    private static ProtocolVersion attemptDecodeNettyHandshake(ByteBuf bytebuf) {
        bytebuf.readerIndex(0);
        return ProtocolUtils.readNettyHandshake(bytebuf.readSlice(ChannelUtils.readVarInt(bytebuf)));
    }

    static {
        protocolsupport.protocol.transformer.v_1_8.PipeLineBuilder builder = new protocolsupport.protocol.transformer.v_1_8.PipeLineBuilder();
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_FUTURE, builder);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_8, builder);
        protocolsupport.protocol.transformer.v_1_7.PipeLineBuilder builder17 = new protocolsupport.protocol.transformer.v_1_7.PipeLineBuilder();
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_7_10, builder17);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_7_5, builder17);
        protocolsupport.protocol.transformer.v_1_6.PipeLineBuilder builder16 = new protocolsupport.protocol.transformer.v_1_6.PipeLineBuilder();
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_6_4, builder16);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_6_2, builder16);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_6_1, builder16);
        PipeLineBuilder builder15 = new PipeLineBuilder();
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_5_2, builder15);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_5_1, builder15);
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_1_4_7, new protocolsupport.protocol.transformer.v_1_4.PipeLineBuilder());
        pipelineBuilders.put(ProtocolVersion.MINECRAFT_LEGACY, new protocolsupport.protocol.transformer.v_legacy.PipeLineBuilder());
    }
}

