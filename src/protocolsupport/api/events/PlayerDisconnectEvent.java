/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.HandlerList
 */
package protocolsupport.api.events;

import java.net.InetSocketAddress;
import org.bukkit.event.HandlerList;
import protocolsupport.api.events.PlayerEvent;

public class PlayerDisconnectEvent
extends PlayerEvent {
    private static final HandlerList list = new HandlerList();

    public PlayerDisconnectEvent(InetSocketAddress address, String username) {
        super(address, username);
    }

    public HandlerList getHandlers() {
        return list;
    }

    public static HandlerList getHandlerList() {
        return list;
    }
}

