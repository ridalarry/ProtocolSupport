/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 */
package protocolsupport.protocol.core.wrapped;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import protocolsupport.protocol.core.IPacketSplitter;

public class WrappedSplitter
extends ByteToMessageDecoder {
    private IPacketSplitter realSplitter;

    public WrappedSplitter() {
        this.realSplitter = new IPacketSplitter(){

            @Override
            public void split(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
            }
        };
    }

    public void setRealSplitter(IPacketSplitter realSplitter) {
        this.realSplitter = realSplitter;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        if (!input.isReadable()) {
            return;
        }
        this.realSplitter.split(ctx, input, list);
    }

}

