/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 */
package protocolsupport.api.events;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import protocolsupport.api.ProtocolVersion;

public class ServerPingResponseEvent
extends Event {
    private final InetSocketAddress address;
    private ProtocolInfo info;
    private String motd;
    private String icon;
    private int maxPlayers;
    private List<String> players;
    private static final HandlerList list = new HandlerList();

    public ServerPingResponseEvent(InetSocketAddress address, ProtocolInfo info, String icon, String motd, int maxPlayers, List<String> players) {
        this.address = address;
        this.setProtocolInfo(info);
        this.setIcon(icon);
        this.setMotd(motd);
        this.setMaxPlayers(maxPlayers);
        this.setPlayers(players);
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public ProtocolInfo getProtocolInfo() {
        return this.info;
    }

    public void setProtocolInfo(ProtocolInfo info) {
        this.info = info != null ? info : new ProtocolInfo(-1, "ProtocolSupport");
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMotd() {
        return this.motd;
    }

    public void setMotd(String motd) {
        this.motd = motd != null ? motd : "A minecraft server (ProtocolSupport)";
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<String> getPlayers() {
        return new ArrayList<String>(this.players);
    }

    public void setPlayers(List<String> players) {
        this.players = players != null ? new ArrayList<String>(players) : new ArrayList();
    }

    public HandlerList getHandlers() {
        return list;
    }

    public static HandlerList getHandlerList() {
        return list;
    }

    public static String getServerModName() {
        return MinecraftServer.getServer().getServerModName();
    }

    public static String getServerVersionName() {
        return MinecraftServer.getServer().getVersion();
    }

    public static class ProtocolInfo {
        private int id;
        private String name;

        public ProtocolInfo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public ProtocolInfo(ProtocolVersion version, String name) {
            this(version.getId(), name);
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

}

