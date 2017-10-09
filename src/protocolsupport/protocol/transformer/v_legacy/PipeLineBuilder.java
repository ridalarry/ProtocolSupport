/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 */
package protocolsupport.protocol.transformer.v_legacy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.transformer.v_legacy.LegacyLoginAndPingHandler;

public class PipeLineBuilder
implements IPipeLineBuilder {
    private static final LegacyLoginAndPingHandler legacyHandler = new LegacyLoginAndPingHandler();

    @Override
    public void buildPipeLine(Channel channel, ProtocolVersion version) {
        channel.pipeline().addAfter("timeout", "legacy_kick", (ChannelHandler)legacyHandler);
    }
}

