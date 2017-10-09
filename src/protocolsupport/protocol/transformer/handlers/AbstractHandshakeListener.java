/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.authlib.properties.Property
 *  com.mojang.util.UUIDTypeAdapter
 *  net.minecraft.server.v1_8_R3.ChatComponentText
 *  net.minecraft.server.v1_8_R3.EnumProtocol
 *  net.minecraft.server.v1_8_R3.HandshakeListener
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.LoginListener
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol
 *  net.minecraft.server.v1_8_R3.PacketListener
 *  net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect
 *  org.apache.logging.log4j.LogManager
 *  org.spigotmc.SpigotConfig
 */
package protocolsupport.protocol.transformer.handlers;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.HandshakeListener;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.LoginListener;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol;
import net.minecraft.server.v1_8_R3.PacketListener;
import net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect;
import org.apache.logging.log4j.LogManager;
import org.spigotmc.SpigotConfig;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.storage.ProtocolStorage;
import protocolsupport.protocol.storage.ThrottleTracker;
import protocolsupport.protocol.transformer.handlers.StatusListener;

public abstract class AbstractHandshakeListener
extends HandshakeListener {
    private static final Gson gson = new Gson();
    private final NetworkManager networkManager;

    public AbstractHandshakeListener(NetworkManager networkmanager) {
        super(MinecraftServer.getServer(), networkmanager);
        this.networkManager = networkmanager;
    }

    public void a(PacketHandshakingInSetProtocol packethandshakinginsetprotocol) {
        switch (packethandshakinginsetprotocol.a()) {
            case LOGIN: {
                this.networkManager.a(EnumProtocol.LOGIN);
                try {
                    InetAddress address = ((InetSocketAddress)this.networkManager.getSocketAddress()).getAddress();
                    if (ThrottleTracker.isEnabled() && !SpigotConfig.bungee) {
                        if (ThrottleTracker.isThrottled(address)) {
                            ChatComponentText chatcomponenttext = new ChatComponentText("Connection throttled! Please wait before reconnecting.");
                            this.networkManager.handle((Packet)new PacketLoginOutDisconnect((IChatBaseComponent)chatcomponenttext));
                            this.networkManager.close((IChatBaseComponent)chatcomponenttext);
                            return;
                        }
                        ThrottleTracker.track(address, System.currentTimeMillis());
                    }
                }
                catch (Throwable t) {
                    LogManager.getLogger().debug("Failed to check connection throttle", t);
                }
                if (packethandshakinginsetprotocol.b() != ProtocolVersion.getLatest().getId()) {
                    ChatComponentText chatcomponenttext = new ChatComponentText("Unsupported protocol version " + packethandshakinginsetprotocol.b());
                    this.networkManager.handle((Packet)new PacketLoginOutDisconnect((IChatBaseComponent)chatcomponenttext));
                    this.networkManager.close((IChatBaseComponent)chatcomponenttext);
                    break;
                }
                this.networkManager.a((PacketListener)this.getLoginListener(this.networkManager));
                if (SpigotConfig.bungee) {
                    String[] split = packethandshakinginsetprotocol.hostname.split("\u0000");
                    if (split.length != 3 && split.length != 4) {
                        ChatComponentText chatcomponenttext = new ChatComponentText("If you wish to use IP forwarding, please enable it in your BungeeCord config as well!");
                        this.networkManager.handle((Packet)new PacketLoginOutDisconnect((IChatBaseComponent)chatcomponenttext));
                        this.networkManager.close((IChatBaseComponent)chatcomponenttext);
                        return;
                    }
                    packethandshakinginsetprotocol.hostname = split[0];
                    SocketAddress oldaddress = this.networkManager.getSocketAddress();
                    ProtocolVersion version = ProtocolStorage.getProtocolVersion(oldaddress);
                    ProtocolStorage.clearData(oldaddress);
                    InetSocketAddress newaddress = new InetSocketAddress(split[1], ((InetSocketAddress)oldaddress).getPort());
                    this.networkManager.l = newaddress;
                    ProtocolStorage.setProtocolVersion(newaddress, version);
                    this.networkManager.spoofedUUID = UUIDTypeAdapter.fromString((String)split[2]);
                    if (split.length == 4) {
                        this.networkManager.spoofedProfile = (Property[])gson.fromJson(split[3], Property[].class);
                    }
                }
                ((LoginListener)this.networkManager.getPacketListener()).hostname = packethandshakinginsetprotocol.hostname + ":" + packethandshakinginsetprotocol.port;
                break;
            }
            case STATUS: {
                this.networkManager.a(EnumProtocol.STATUS);
                this.networkManager.a((PacketListener)new StatusListener(MinecraftServer.getServer(), this.networkManager));
                break;
            }
            default: {
                throw new UnsupportedOperationException("Invalid intention " + (Object)packethandshakinginsetprotocol.a());
            }
        }
    }

    public void a(IChatBaseComponent ichatbasecomponent) {
    }

    public abstract LoginListener getLoginListener(NetworkManager var1);

}

