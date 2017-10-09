/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.CorruptedFrameException
 */
package protocolsupport.protocol.transformer.v_1_7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import protocolsupport.protocol.core.IPacketSplitter;
import protocolsupport.utils.netty.ChannelUtils;

public class PacketSplitter
implements IPacketSplitter {
    private final byte[] array = new byte[3];

    @Override
    public void split(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        input.markReaderIndex();
        for (int i = 0; i < this.array.length; ++i) {
            if (!input.isReadable()) {
                input.resetReaderIndex();
                return;
            }
            this.array[i] = input.readByte();
            if (this.array[i] < 0) continue;
            int length = ChannelUtils.readVarInt(Unpooled.wrappedBuffer((byte[])this.array));
            if (input.readableBytes() < length) {
                input.resetReaderIndex();
                return;
            }
            list.add((Object)input.readBytes(length));
            return;
        }
        throw new CorruptedFrameException("length wider than 21-bit");
    }
}

