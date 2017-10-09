/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 */
package protocolsupport.protocol.core.wrapped;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocolsupport.protocol.core.IPacketPrepender;

public class WrappedPrepender
extends MessageToByteEncoder<ByteBuf> {
    private IPacketPrepender realPrepender;

    public WrappedPrepender() {
        super(true);
        this.realPrepender = new IPacketPrepender(){

            @Override
            public void prepend(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) throws Exception {
            }
        };
    }

    public void setRealPrepender(IPacketPrepender realEncoder) {
        this.realPrepender = realEncoder;
    }

    protected void encode(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) throws Exception {
        if (!input.isReadable()) {
            return;
        }
        this.realPrepender.prepend(ctx, input, output);
    }

}

