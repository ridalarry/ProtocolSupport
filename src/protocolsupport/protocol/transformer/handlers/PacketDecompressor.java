/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.server.v1_8_R3.PacketDecompressor
 */
package protocolsupport.protocol.transformer.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.zip.Inflater;
import protocolsupport.utils.netty.ChannelUtils;

public class PacketDecompressor
extends net.minecraft.server.v1_8_R3.PacketDecompressor {
    private final Inflater inflater = new Inflater();

    public PacketDecompressor(int threshold) {
        super(threshold);
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        this.inflater.end();
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf from, List<Object> list) throws Exception {
        if (!from.isReadable()) {
            return;
        }
        int uncompressedlength = ChannelUtils.readVarInt(from);
        if (uncompressedlength == 0) {
            list.add((Object)from.readBytes(from.readableBytes()));
        } else {
            this.inflater.setInput(ChannelUtils.toArray(from));
            byte[] uncompressed = new byte[uncompressedlength];
            this.inflater.inflate(uncompressed);
            list.add((Object)Unpooled.wrappedBuffer((byte[])uncompressed));
            this.inflater.reset();
        }
    }
}

