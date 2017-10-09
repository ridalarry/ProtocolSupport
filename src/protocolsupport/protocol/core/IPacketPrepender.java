/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package protocolsupport.protocol.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public interface IPacketPrepender {
    public void prepend(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) throws Exception;
}

