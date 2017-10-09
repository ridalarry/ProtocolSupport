/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.api.events;

import java.net.InetSocketAddress;
import java.util.List;
import protocolsupport.api.events.ServerPingResponseEvent;

@Deprecated
public class LegacyServerPingResponseEvent
extends ServerPingResponseEvent {
    public LegacyServerPingResponseEvent(InetSocketAddress address, String motd, int maxPlayers) {
        super(address, null, null, motd, maxPlayers, null);
    }
}

