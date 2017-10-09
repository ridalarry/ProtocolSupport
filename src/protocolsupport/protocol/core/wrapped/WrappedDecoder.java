/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.PacketListener
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Event
 */
package protocolsupport.protocol.core.wrapped;

import com.mojang.authlib.GameProfile;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.net.InetSocketAddress;
import java.util.List;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketListener;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import protocolsupport.api.events.PlayerDisconnectEvent;
import protocolsupport.protocol.core.IPacketDecoder;
import protocolsupport.protocol.storage.ProtocolStorage;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.utils.netty.ChannelUtils;

public class WrappedDecoder
extends ByteToMessageDecoder {
    private IPacketDecoder realDecoder;

    public WrappedDecoder() {
        this.realDecoder = new IPacketDecoder(){

            @Override
            public void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
            }
        };
    }

    public void setRealDecoder(IPacketDecoder realDecoder) {
        this.realDecoder = realDecoder;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        this.realDecoder.decode(ctx, input, list);
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        block7 : {
            super.channelInactive(ctx);
            try {
                InetSocketAddress addr = (InetSocketAddress)ChannelUtils.getNetworkManagerSocketAddress(ctx.channel());
                String username = null;
                PacketListener listener = ChannelUtils.getNetworkManager(ctx.channel()).getPacketListener();
                if (listener instanceof AbstractLoginListener) {
                    GameProfile profile = ((AbstractLoginListener)listener).getProfile();
                    if (profile != null) {
                        username = profile.getName();
                    }
                } else if (listener instanceof PlayerConnection) {
                    username = ((PlayerConnection)listener).player.getProfile().getName();
                }
                if (username != null) {
                    PlayerDisconnectEvent event = new PlayerDisconnectEvent(addr, username);
                    Bukkit.getPluginManager().callEvent((Event)event);
                }
                ProtocolStorage.clearData(addr);
            }
            catch (Throwable t) {
                if (!MinecraftServer.getServer().isDebugging()) break block7;
                t.printStackTrace();
            }
        }
    }

}

