/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 */
package protocolsupport.protocol.core;

import io.netty.channel.Channel;
import protocolsupport.api.ProtocolVersion;

public interface IPipeLineBuilder {
    public void buildPipeLine(Channel var1, ProtocolVersion var2);
}

