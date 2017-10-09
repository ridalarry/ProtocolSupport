/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.HandlerList
 */
package protocolsupport.api.events;

import java.net.InetSocketAddress;
import java.util.UUID;
import org.bukkit.event.HandlerList;
import protocolsupport.api.events.PlayerEvent;

public class PlayerLoginStartEvent
extends PlayerEvent {
    private final String hostname;
    private boolean onlinemode;
    private boolean useonlinemodeuuid;
    private UUID uuid;
    private String denyLoginMessage;
    private static final HandlerList list = new HandlerList();

    public PlayerLoginStartEvent(InetSocketAddress address, String username, boolean onlinemode, boolean useonlinemodeuuid, String hostname) {
        super(address, username);
        this.onlinemode = onlinemode;
        this.useonlinemodeuuid = useonlinemodeuuid;
        this.hostname = hostname;
    }

    public String getHostname() {
        return this.hostname;
    }

    public boolean isOnlineMode() {
        return this.onlinemode;
    }

    public void setOnlineMode(boolean onlinemode) {
        this.onlinemode = onlinemode;
    }

    public boolean useOnlineModeUUID() {
        return this.useonlinemodeuuid;
    }

    public void setUseOnlineModeUUID(boolean useonlinemodeuuid) {
        this.useonlinemodeuuid = useonlinemodeuuid;
    }

    public boolean hasForcedUUID() {
        return this.uuid != null;
    }

    public void setForcedUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getForcedUUID() {
        return this.uuid;
    }

    public boolean isLoginDenied() {
        return this.denyLoginMessage != null;
    }

    public String getDenyLoginMessage() {
        return this.denyLoginMessage;
    }

    public void denyLogin(String message) {
        this.denyLoginMessage = message;
    }

    public HandlerList getHandlers() {
        return list;
    }

    public static HandlerList getHandlerList() {
        return list;
    }
}

