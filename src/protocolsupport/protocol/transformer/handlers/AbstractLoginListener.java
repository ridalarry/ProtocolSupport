/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.collect.Multimap
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.properties.PropertyMap
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  net.minecraft.server.v1_8_R3.ChatComponentText
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.LoginListener
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketLoginInEncryptionBegin
 *  net.minecraft.server.v1_8_R3.PacketLoginInStart
 *  net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect
 *  net.minecraft.server.v1_8_R3.PacketLoginOutEncryptionBegin
 *  net.minecraft.server.v1_8_R3.PacketLoginOutSetCompression
 *  net.minecraft.server.v1_8_R3.PacketLoginOutSuccess
 *  net.minecraft.server.v1_8_R3.PlayerList
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Event
 */
package protocolsupport.protocol.transformer.handlers;

import com.google.common.base.Charsets;
import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.LoginListener;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketLoginInEncryptionBegin;
import net.minecraft.server.v1_8_R3.PacketLoginInStart;
import net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect;
import net.minecraft.server.v1_8_R3.PacketLoginOutEncryptionBegin;
import net.minecraft.server.v1_8_R3.PacketLoginOutSetCompression;
import net.minecraft.server.v1_8_R3.PacketLoginOutSuccess;
import net.minecraft.server.v1_8_R3.PlayerList;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import protocolsupport.ProtocolSupport;
import protocolsupport.api.events.PlayerLoginStartEvent;
import protocolsupport.protocol.transformer.handlers.LoginState;
import protocolsupport.protocol.transformer.handlers.PacketCompressor;
import protocolsupport.protocol.transformer.handlers.PacketDecompressor;
import protocolsupport.protocol.transformer.handlers.PlayerLookupUUID;
import protocolsupport.utils.Utils;

public abstract class AbstractLoginListener
extends LoginListener {
    private static final int loginThreads = Utils.getJavaPropertyValue("protocolsupport.loginthreads", 8, Utils.Converter.STRING_TO_INT);
    private static final int loginThreadKeepAlive = Utils.getJavaPropertyValue("protocolsupport.loginthreadskeepalive", 60, Utils.Converter.STRING_TO_INT);
    private static final Executor loginprocessor = new ThreadPoolExecutor(1, loginThreads, (long)loginThreadKeepAlive, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory(){

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("LoginProcessingThread");
            return thread;
        }
    });
    protected static final Logger logger = LogManager.getLogger();
    protected static final Random random = new Random();
    protected final byte[] randomBytes = new byte[4];
    protected int loginTicks;
    protected SecretKey loginKey;
    protected volatile LoginState state = LoginState.HELLO;
    protected GameProfile profile;
    protected boolean isOnlineMode;
    protected boolean useOnlineModeUUID;
    protected UUID forcedUUID;

    public static void init() {
        ProtocolSupport.logInfo("Login threads max count: " + loginThreads + ", keep alive time: " + loginThreadKeepAlive);
    }

    public AbstractLoginListener(NetworkManager networkmanager) {
        super(MinecraftServer.getServer(), networkmanager);
        random.nextBytes(this.randomBytes);
        this.useOnlineModeUUID = this.isOnlineMode = MinecraftServer.getServer().getOnlineMode() && !this.networkManager.c();
        this.forcedUUID = null;
    }

    public void c() {
        if (this.state == LoginState.READY_TO_ACCEPT) {
            this.b();
        }
        if (this.loginTicks++ == 600) {
            this.disconnect("Took too long to log in");
        }
    }

    public void disconnect(String s) {
        try {
            logger.info("Disconnecting " + this.d() + ": " + s);
            ChatComponentText chatcomponenttext = new ChatComponentText(s);
            this.networkManager.handle((Packet)new PacketLoginOutDisconnect((IChatBaseComponent)chatcomponenttext));
            this.networkManager.close((IChatBaseComponent)chatcomponenttext);
        }
        catch (Exception exception) {
            logger.error("Error whilst disconnecting player", (Throwable)exception);
        }
    }

    public void initUUID() {
        this.profile = new GameProfile(this.networkManager.spoofedUUID != null ? this.networkManager.spoofedUUID : this.generateOffileModeUUID(), this.profile.getName());
        if (this.networkManager.spoofedProfile != null) {
            for (Property property : this.networkManager.spoofedProfile) {
				profile.getProperties().put(property.getName(), property);
				}
        }
    }

    protected UUID generateOffileModeUUID() {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.profile.getName()).getBytes(Charsets.UTF_8));
    }

    public void b() {
        EntityPlayer entityPlayer = MinecraftServer.getServer().getPlayerList().attemptLogin((LoginListener)this, this.profile, this.hostname);
        if (entityPlayer != null) {
            int threshold;
            this.state = LoginState.ACCEPTED;
            if (this.hasCompression() && (threshold = MinecraftServer.getServer().aK()) >= 0 && !this.networkManager.c()) {
                this.networkManager.a((Packet)new PacketLoginOutSetCompression(threshold), (GenericFutureListener)new ChannelFutureListener(){

                    public void operationComplete(ChannelFuture future) throws Exception {
                        AbstractLoginListener.this.enableCompresssion(threshold);
                    }
                }, new GenericFutureListener[0]);
            }
            this.networkManager.handle((Packet)new PacketLoginOutSuccess(this.profile));
            MinecraftServer.getServer().getPlayerList().a(this.networkManager, MinecraftServer.getServer().getPlayerList().processLogin(this.profile, entityPlayer));
        }
    }

    protected abstract boolean hasCompression();

    protected void enableCompresssion(int compressionLevel) {
        Channel channel = this.networkManager.channel;
        if (compressionLevel >= 0) {
            channel.pipeline().addBefore("decoder", "decompress", (ChannelHandler)new PacketDecompressor(compressionLevel));
            channel.pipeline().addBefore("encoder", "compress", (ChannelHandler)new PacketCompressor(compressionLevel));
        }
    }

    public void a(IChatBaseComponent ichatbasecomponent) {
        logger.info(this.d() + " lost connection: " + ichatbasecomponent.c());
    }

    public String d() {
        return this.profile != null ? (Object)this.profile + " (" + this.networkManager.getSocketAddress() + ")" : this.networkManager.getSocketAddress().toString();
    }

    public void a(final PacketLoginInStart packetlogininstart) {
        Validate.validState((boolean)(this.state == LoginState.HELLO), (String)"Unexpected hello packet", (Object[])new Object[0]);
        this.state = LoginState.ONLINEMODERESOLVE;
        loginprocessor.execute(new Runnable(){

            @Override
            public void run() {
                block5 : {
                    try {
                        AbstractLoginListener.this.profile = packetlogininstart.a();
                        PlayerLoginStartEvent event = new PlayerLoginStartEvent((InetSocketAddress)AbstractLoginListener.this.networkManager.getSocketAddress(), AbstractLoginListener.this.profile.getName(), AbstractLoginListener.this.isOnlineMode, AbstractLoginListener.this.useOnlineModeUUID, AbstractLoginListener.this.hostname);
                        Bukkit.getPluginManager().callEvent((Event)event);
                        if (event.isLoginDenied()) {
                            AbstractLoginListener.this.disconnect(event.getDenyLoginMessage());
                            return;
                        }
                        AbstractLoginListener.this.isOnlineMode = event.isOnlineMode();
                        AbstractLoginListener.this.useOnlineModeUUID = event.useOnlineModeUUID();
                        AbstractLoginListener.this.forcedUUID = event.getForcedUUID();
                        if (AbstractLoginListener.this.isOnlineMode) {
                            AbstractLoginListener.this.state = LoginState.KEY;
                            AbstractLoginListener.this.networkManager.handle((Packet)new PacketLoginOutEncryptionBegin("", MinecraftServer.getServer().Q().getPublic(), AbstractLoginListener.this.randomBytes));
                        } else {
                            new PlayerLookupUUID(AbstractLoginListener.this, AbstractLoginListener.this.isOnlineMode).run();
                        }
                    }
                    catch (Throwable t) {
                        AbstractLoginListener.this.disconnect("Error occured while logging in");
                        if (!MinecraftServer.getServer().isDebugging()) break block5;
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    public void a(final PacketLoginInEncryptionBegin packetlogininencryptionbegin) {
        Validate.validState((boolean)(this.state == LoginState.KEY), (String)"Unexpected key packet", (Object[])new Object[0]);
        this.state = LoginState.AUTHENTICATING;
        loginprocessor.execute(new Runnable(){

            @Override
            public void run() {
                block3 : {
                    try {
                        PrivateKey privatekey = MinecraftServer.getServer().Q().getPrivate();
                        if (!Arrays.equals(AbstractLoginListener.this.randomBytes, packetlogininencryptionbegin.b(privatekey))) {
                            throw new IllegalStateException("Invalid nonce!");
                        }
                        AbstractLoginListener.this.loginKey = packetlogininencryptionbegin.a(privatekey);
                        AbstractLoginListener.this.enableEncryption(AbstractLoginListener.this.loginKey);
                        new PlayerLookupUUID(AbstractLoginListener.this, AbstractLoginListener.this.isOnlineMode).run();
                    }
                    catch (Throwable t) {
                        AbstractLoginListener.this.disconnect("Error occured while logging in");
                        if (!MinecraftServer.getServer().isDebugging()) break block3;
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    protected abstract void enableEncryption(SecretKey var1);

    public Logger getLogger() {
        return logger;
    }

    public GameProfile getProfile() {
        return this.profile;
    }

    public void setProfile(GameProfile profile) {
        this.profile = profile;
    }

    public GameProfile generateOfflineProfile(GameProfile current) {
        return this.a(current);
    }

    public void setReadyToAccept() {
        UUID newUUID = null;
        if (this.isOnlineMode && !this.useOnlineModeUUID) {
            newUUID = this.generateOffileModeUUID();
        }
        if (this.forcedUUID != null) {
            newUUID = this.forcedUUID;
        }
        if (newUUID != null) {
            GameProfile newProfile = new GameProfile(newUUID, this.profile.getName());
            newProfile.getProperties().putAll((Multimap)this.profile.getProperties());
            this.profile = newProfile;
        }
        this.state = LoginState.READY_TO_ACCEPT;
    }

    public SecretKey getLoginKey() {
        return this.loginKey;
    }

    public NetworkManager getNetworkManager() {
        return this.networkManager;
    }

}

