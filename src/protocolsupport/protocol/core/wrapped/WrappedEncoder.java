/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketListener
 */
package protocolsupport.protocol.core.wrapped;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListener;
import protocolsupport.protocol.core.IPacketEncoder;

public class WrappedEncoder
extends MessageToByteEncoder<Packet<PacketListener>> {
    private IPacketEncoder realEncoder;

    public WrappedEncoder() {
        super(true);
        this.realEncoder = new IPacketEncoder(){

            @Override
            public void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
            }
        };
    }

    public void setRealEncoder(IPacketEncoder realEncoder) {
        this.realEncoder = realEncoder;
    }

    protected void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        this.realEncoder.encode(ctx, packet, output);
    }

}

