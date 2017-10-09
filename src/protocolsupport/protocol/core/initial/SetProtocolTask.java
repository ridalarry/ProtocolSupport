/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPipeline
 */
package protocolsupport.protocol.core.initial;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;

public class SetProtocolTask
implements Runnable {
    private final InitialPacketDecoder initialDecoder;
    private final Channel channel;
    private final ProtocolVersion version;

    public SetProtocolTask(InitialPacketDecoder initialDecoder, Channel channel, ProtocolVersion version) {
        this.initialDecoder = initialDecoder;
        this.channel = channel;
        this.version = version;
    }

    @Override
    public void run() {
        try {
            this.initialDecoder.setProtocol(this.channel, this.version);
        }
        catch (Exception t) {
            this.channel.pipeline().firstContext().fireExceptionCaught((Throwable)t);
        }
    }
}

