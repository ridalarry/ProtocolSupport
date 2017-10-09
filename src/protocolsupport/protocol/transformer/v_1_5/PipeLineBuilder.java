/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.PacketListener
 */
package protocolsupport.protocol.transformer.v_1_5;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.PacketListener;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPacketDecoder;
import protocolsupport.protocol.core.IPacketEncoder;
import protocolsupport.protocol.core.IPacketPrepender;
import protocolsupport.protocol.core.IPacketSplitter;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.transformer.v_1_5.HandshakeListener;
import protocolsupport.protocol.transformer.v_1_5.PacketDecoder;
import protocolsupport.protocol.transformer.v_1_5.PacketEncoder;
import protocolsupport.protocol.transformer.v_1_5.PacketPrepender;
import protocolsupport.protocol.transformer.v_1_5.PacketSplitter;

public class PipeLineBuilder
implements IPipeLineBuilder {
    @Override
    public void buildPipeLine(Channel channel, ProtocolVersion version) {
        ChannelPipeline pipeline = channel.pipeline();
        NetworkManager networkmanager = (NetworkManager)pipeline.get("packet_handler");
        networkmanager.a((PacketListener)new HandshakeListener(networkmanager));
        ChannelHandlers.getSplitter(pipeline).setRealSplitter(new PacketSplitter());
        ChannelHandlers.getPrepender(pipeline).setRealPrepender(new PacketPrepender());
        ChannelHandlers.getDecoder(pipeline).setRealDecoder(new PacketDecoder(version));
        ChannelHandlers.getEncoder(pipeline).setRealEncoder(new PacketEncoder(version));
    }
}

