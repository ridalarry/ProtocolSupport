/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.server.v1_8_R3.PacketDataSerializer
 */
package protocolsupport.protocol.transformer.v_1_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import protocolsupport.protocol.core.IPacketPrepender;
import protocolsupport.utils.netty.ChannelUtils;

public class PacketPrepender
implements IPacketPrepender {
    @Override
    public void prepend(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) throws Exception {
        int readableBytes = input.readableBytes();
        int varIntLength = PacketDataSerializer.a((int)readableBytes);
        if (varIntLength > 3) {
            throw new IllegalArgumentException("unable to fit " + readableBytes + " into " + 3);
        }
        output.ensureWritable(varIntLength + readableBytes);
        ChannelUtils.writeVarInt(output, readableBytes);
        output.writeBytes(input, input.readerIndex(), readableBytes);
    }
}

