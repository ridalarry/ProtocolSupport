/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.util.concurrent.GenericFutureListener
 *  net.minecraft.server.v1_8_R3.ChatComponentText
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketStatusInPing
 *  net.minecraft.server.v1_8_R3.PacketStatusInStart
 *  net.minecraft.server.v1_8_R3.PacketStatusListener
 *  net.minecraft.server.v1_8_R3.PacketStatusOutPong
 *  net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo
 *  net.minecraft.server.v1_8_R3.PlayerList
 *  net.minecraft.server.v1_8_R3.ServerPing
 *  net.minecraft.server.v1_8_R3.ServerPing$ServerData
 *  net.minecraft.server.v1_8_R3.ServerPing$ServerPingPlayerSample
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.CraftServer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.util.CraftIconCache
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.server.ServerListPingEvent
 *  org.bukkit.util.CachedServerIcon
 *  org.spigotmc.SpigotConfig
 */
package protocolsupport.protocol.transformer.handlers;

import com.mojang.authlib.GameProfile;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketStatusInPing;
import net.minecraft.server.v1_8_R3.PacketStatusInStart;
import net.minecraft.server.v1_8_R3.PacketStatusListener;
import net.minecraft.server.v1_8_R3.PacketStatusOutPong;
import net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo;
import net.minecraft.server.v1_8_R3.PlayerList;
import net.minecraft.server.v1_8_R3.ServerPing;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftIconCache;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;
import org.spigotmc.SpigotConfig;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.events.ServerPingResponseEvent;

public class StatusListener
extends PacketStatusListener {
    private MinecraftServer server;
    private NetworkManager nmanager;
    private static final UUID profileUUID = UUID.randomUUID();

    public StatusListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
        super(minecraftserver, networkmanager);
        this.server = minecraftserver;
        this.nmanager = networkmanager;
    }

    public void a(PacketStatusInStart packetstatusinstart) {
        InetSocketAddress addr = (InetSocketAddress)this.nmanager.getSocketAddress();
        ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>(this.server.getPlayerList().players);
        String motd = this.server.getMotd();
        int maxPlayers = this.server.getPlayerList().getMaxPlayers();
        InternalServerListPingEvent bevent = new InternalServerListPingEvent(addr.getAddress(), motd, maxPlayers, players);
        bevent.setServerIcon(Bukkit.getServerIcon());
        Bukkit.getPluginManager().callEvent((Event)bevent);
        String icon = bevent.getIcon() != null ? bevent.getIcon().value : null;
        motd = bevent.getMotd();
        maxPlayers = bevent.getMaxPlayers();
        List profiles = new ArrayList<String>(players.size());
        for (EntityPlayer player : players) {
            profiles.add((String)player.getProfile().getName());
        }
        ServerPingResponseEvent revent = new ServerPingResponseEvent(addr, new ServerPingResponseEvent.ProtocolInfo(ProtocolVersion.getLatest(), this.server.getServerModName() + " " + this.server.getVersion()), icon, motd, maxPlayers, profiles);
        Bukkit.getPluginManager().callEvent((Event)revent);
        profiles = revent.getPlayers();
        icon = revent.getIcon();
        motd = revent.getMotd();
        maxPlayers = revent.getMaxPlayers();
        ServerPingResponseEvent.ProtocolInfo info = revent.getProtocolInfo();
        ServerPing.ServerPingPlayerSample playerSample = new ServerPing.ServerPingPlayerSample(maxPlayers, profiles.size());
        Collections.shuffle(profiles);
        GameProfile[] gprofiles = new GameProfile[profiles.size()];
        for (int i = 0; i < profiles.size(); ++i) {
            gprofiles[i] = new GameProfile(profileUUID, (String)profiles.get(i));
        }
        gprofiles = Arrays.copyOfRange(gprofiles, 0, Math.min(gprofiles.length, SpigotConfig.playerSample));
        playerSample.a(gprofiles);
        ServerPing serverping = new ServerPing();
        serverping.setFavicon(icon);
        serverping.setMOTD((IChatBaseComponent)new ChatComponentText(motd));
        serverping.setPlayerSample(playerSample);
        serverping.setServerInfo(new ServerPing.ServerData(info.getName(), info.getId()));
        this.nmanager.handle((Packet)new PacketStatusOutServerInfo(serverping));
    }

    public void a(PacketStatusInPing packetstatusinping) {
        this.nmanager.a((Packet)new PacketStatusOutPong(packetstatusinping.a()), (GenericFutureListener)ChannelFutureListener.CLOSE, new GenericFutureListener[0]);
    }

    public static class InternalServerListPingEvent
    extends ServerListPingEvent {
        private List<EntityPlayer> players;
        protected CraftIconCache icon;

        protected InternalServerListPingEvent(InetAddress address, String motd, int maxPlayers, List<EntityPlayer> players) {
            super(address, motd, maxPlayers);
            this.players = players;
        }

        public CraftIconCache getIcon() {
            return this.icon;
        }

        public void setServerIcon(CachedServerIcon icon) {
            if (icon != null && !(icon instanceof CraftIconCache)) {
                throw new IllegalArgumentException((Object)icon + " was not created by " + CraftServer.class);
            }
            this.icon = (CraftIconCache)icon;
        }

        public Iterator<Player> iterator() throws UnsupportedOperationException {
            return new Iterator<Player>(){
                Iterator<EntityPlayer> iterator;

                @Override
                public boolean hasNext() {
                    return this.iterator.hasNext();
                }

                @Override
                public Player next() {
                    return this.iterator.next().getBukkitEntity();
                }

                @Override
                public void remove() {
                    this.iterator.remove();
                }
            };
        }

    }

}

