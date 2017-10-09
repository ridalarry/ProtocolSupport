/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package protocolsupport.protocol.transformer.v_1_6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import protocolsupport.protocol.core.IPacketSplitter;

public class PacketSplitter
implements IPacketSplitter {
    @Override
    public void split(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        list.add((Object)input.readBytes(input.readableBytes()));
    }
}

