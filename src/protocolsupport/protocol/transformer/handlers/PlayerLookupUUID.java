/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.exceptions.AuthenticationUnavailableException
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.properties.PropertyMap
 *  net.minecraft.server.v1_8_R3.MinecraftEncryption
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  org.apache.logging.log4j.Logger
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.CraftServer
 *  org.bukkit.craftbukkit.v1_8_R3.util.Waitable
 *  org.bukkit.event.Event
 *  org.bukkit.event.player.AsyncPlayerPreLoginEvent
 *  org.bukkit.event.player.AsyncPlayerPreLoginEvent$Result
 *  org.bukkit.event.player.PlayerPreLoginEvent
 *  org.bukkit.event.player.PlayerPreLoginEvent$Result
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.RegisteredListener
 */
package protocolsupport.protocol.transformer.handlers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import net.minecraft.server.v1_8_R3.MinecraftEncryption;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.util.Waitable;
import org.bukkit.event.Event;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import protocolsupport.api.events.PlayerPropertiesResolveEvent;
import protocolsupport.api.events.PlayerPropertiesResolveEvent.ProfileProperty;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;

public class PlayerLookupUUID {
    private final AbstractLoginListener listener;
    private final boolean isOnlineMode;

    public PlayerLookupUUID(AbstractLoginListener listener, boolean isOnlineMode) {
        this.listener = listener;
        this.isOnlineMode = isOnlineMode;
    }

    public void run() {
        GameProfile gameprofile = this.listener.getProfile();
        try {
            if (!this.isOnlineMode) {
                this.listener.initUUID();
                this.fireLoginEvents();
                return;
            }
            String hash = new BigInteger(MinecraftEncryption.a((String)"", (PublicKey)MinecraftServer.getServer().Q().getPublic(), (SecretKey)this.listener.getLoginKey())).toString(16);
            this.listener.setProfile(MinecraftServer.getServer().aD().hasJoinedServer(new GameProfile(null, gameprofile.getName()), hash));
            if (this.listener.getProfile() != null) {
                this.fireLoginEvents();
            } else {
                this.listener.disconnect("Failed to verify username!");
                this.listener.getLogger().error("Username '" + gameprofile.getName() + "' tried to join with an invalid session");
            }
        }
        catch (AuthenticationUnavailableException authenticationunavailableexception) {
            this.listener.disconnect("Authentication servers are down. Please try again later, sorry!");
            this.listener.getLogger().error("Couldn't verify username because servers are unavailable");
        }
        catch (Exception exception) {
            this.listener.disconnect("Failed to verify username!");
            MinecraftServer.getServer().server.getLogger().log(Level.WARNING, "Exception verifying " + gameprofile.getName(), exception);
        }
    }

    private void fireLoginEvents() throws Exception {
        if (!this.listener.getNetworkManager().g()) {
            return;
        }
        String playerName = this.listener.getProfile().getName();
        InetSocketAddress saddress = (InetSocketAddress)this.listener.getNetworkManager().getSocketAddress();
        InetAddress address = saddress.getAddress();
        ArrayList<PlayerPropertiesResolveEvent.ProfileProperty> properties = new ArrayList<PlayerPropertiesResolveEvent.ProfileProperty>();
        PropertyMap propertymap = this.listener.getProfile().getProperties();
        for (Property property : propertymap.values()) {
			properties.add(new ProfileProperty(property.getName(), property.getValue(), property.getSignature()));
		}
        PlayerPropertiesResolveEvent propResolve = new PlayerPropertiesResolveEvent(saddress, playerName, properties);
        Bukkit.getPluginManager().callEvent((Event)propResolve);
        propertymap.clear();
        for (PlayerPropertiesResolveEvent.ProfileProperty profileproperty : propResolve.getProperties().values()) {
			propertymap.put(profileproperty.getName(), new Property(profileproperty.getName(), profileproperty.getValue(), profileproperty.getSignature()));
        }
        UUID uniqueId = this.listener.getProfile().getId();
        final CraftServer server = MinecraftServer.getServer().server;
        AsyncPlayerPreLoginEvent asyncEvent = new AsyncPlayerPreLoginEvent(playerName, address, uniqueId);
        server.getPluginManager().callEvent((Event)asyncEvent);
        if (PlayerPreLoginEvent.getHandlerList().getRegisteredListeners().length != 0) {
            final PlayerPreLoginEvent event = new PlayerPreLoginEvent(playerName, address, uniqueId);
            if (asyncEvent.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
                event.disallow(asyncEvent.getResult(), asyncEvent.getKickMessage());
            }
            Waitable<PlayerPreLoginEvent.Result> waitable = new Waitable<PlayerPreLoginEvent.Result>(){

                protected PlayerPreLoginEvent.Result evaluate() {
                    server.getPluginManager().callEvent((Event)event);
                    return event.getResult();
                }
            };
            MinecraftServer.getServer().processQueue.add(waitable);
            if (waitable.get() != PlayerPreLoginEvent.Result.ALLOWED) {
                this.listener.disconnect(event.getKickMessage());
                return;
            }
        } else if (asyncEvent.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            this.listener.disconnect(asyncEvent.getKickMessage());
            return;
        }
        this.listener.getLogger().info("UUID of player " + this.listener.getProfile().getName() + " is " + this.listener.getProfile().getId());
        this.listener.setReadyToAccept();
    }

}

