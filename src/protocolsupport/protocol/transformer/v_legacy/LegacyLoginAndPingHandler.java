/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelFutureListener
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPipeline
 *  io.netty.channel.SimpleChannelInboundHandler
 *  io.netty.handler.codec.DecoderException
 *  io.netty.util.concurrent.GenericFutureListener
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.event.Event
 *  org.bukkit.event.server.ServerListPingEvent
 *  org.bukkit.util.CachedServerIcon
 */
package protocolsupport.protocol.transformer.v_legacy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderException;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.events.LegacyServerPingResponseEvent;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.RecyclablePacketDataSerializer;
import protocolsupport.utils.netty.ChannelUtils;

@ChannelHandler.Sharable
public class LegacyLoginAndPingHandler
extends SimpleChannelInboundHandler<ByteBuf> {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf input) throws Exception {
        RecyclablePacketDataSerializer serializer = RecyclablePacketDataSerializer.create(ProtocolVersion.MINECRAFT_LEGACY);
        try {
            short packetId = input.readUnsignedByte();
            if (packetId == 254) {
                LegacyLoginAndPingHandler.writePing((InetSocketAddress)ChannelUtils.getNetworkManagerSocketAddress(ctx.channel()), serializer);
            } else if (packetId == 2) {
                LegacyLoginAndPingHandler.writeLoginKick(serializer);
            } else {
                throw new DecoderException("Unknown packet id " + packetId + " in legacy login and ping handler");
            }
            ctx.channel().pipeline().firstContext().writeAndFlush((Object)serializer).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
        }
        finally {
            serializer.release();
        }
    }

    private static void writeLoginKick(PacketDataSerializer serializer) {
        serializer.writeByte(255);
        serializer.writeString("Outdated client");
    }

    private static void writePing(InetSocketAddress remoteAddress, PacketDataSerializer serializer) {
        ServerListPingEvent bevent = new ServerListPingEvent(remoteAddress.getAddress(), Bukkit.getMotd(), Bukkit.getOnlinePlayers().size(), Bukkit.getMaxPlayers()){

            public void setServerIcon(CachedServerIcon icon) {
            }
        };
        Bukkit.getPluginManager().callEvent((Event)bevent);
        LegacyServerPingResponseEvent revent = new LegacyServerPingResponseEvent(remoteAddress, bevent.getMotd(), bevent.getMaxPlayers());
        Bukkit.getPluginManager().callEvent((Event)revent);
        String response = ChatColor.stripColor((String)revent.getMotd()) + "\u00a7" + bevent.getNumPlayers() + "\u00a7" + revent.getMaxPlayers();
        serializer.writeByte(255);
        serializer.writeString(response);
    }

}

