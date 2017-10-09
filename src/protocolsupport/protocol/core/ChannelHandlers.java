/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 */
package protocolsupport.protocol.core;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import protocolsupport.protocol.core.wrapped.WrappedDecoder;
import protocolsupport.protocol.core.wrapped.WrappedEncoder;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;

public class ChannelHandlers {
    public static final String INITIAL_DECODER = "initial_decoder";
    public static final String LEGACY_KICK = "legacy_kick";
    public static final String TIMEOUT = "timeout";
    public static final String SPLITTER = "splitter";
    public static final String PREPENDER = "prepender";
    public static final String DECODER = "decoder";
    public static final String ENCODER = "encoder";
    public static final String NETWORK_MANAGER = "packet_handler";
    public static final String DECRYPT = "decrypt";

    public static WrappedDecoder getDecoder(ChannelPipeline pipeline) {
        return (WrappedDecoder)pipeline.get("decoder");
    }

    public static WrappedEncoder getEncoder(ChannelPipeline pipeline) {
        return (WrappedEncoder)pipeline.get("encoder");
    }

    public static WrappedSplitter getSplitter(ChannelPipeline pipeline) {
        return (WrappedSplitter)pipeline.get("splitter");
    }

    public static WrappedPrepender getPrepender(ChannelPipeline pipeline) {
        return (WrappedPrepender)pipeline.get("prepender");
    }
}

