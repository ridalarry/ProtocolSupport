/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelConfig
 *  io.netty.channel.ChannelException
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelOption
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.v1_8_R3.EnumProtocolDirection
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.PacketListener
 */
package protocolsupport.injector.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import java.io.PrintStream;
import java.util.List;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.PacketListener;
import protocolsupport.protocol.core.FakePacketListener;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.core.timeout.SimpleReadTimeoutHandler;
import protocolsupport.protocol.core.wrapped.WrappedDecoder;
import protocolsupport.protocol.core.wrapped.WrappedEncoder;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;

public class ServerConnectionChannel
extends ChannelInitializer<Channel> {
    private final List<NetworkManager> networkManagers;
    private static final int IPTOS_THROUGHPUT = 8;
    private static final int IPTOS_LOWDELAY = 16;

    public ServerConnectionChannel(List<NetworkManager> networkManagers) {
        this.networkManagers = networkManagers;
    }

    protected void initChannel(Channel channel) {
        block5 : {
            block4 : {
                try {
                	channel.config().setOption(ChannelOption.IP_TOS, IPTOS_THROUGHPUT | IPTOS_LOWDELAY);
                }
                catch (ChannelException channelexception) {
                    if (!MinecraftServer.getServer().isDebugging()) break block4;
                    System.err.println("Unable to set IP_TOS option: " + channelexception.getMessage());
                }
            }
            try {
            	channel.config().setOption(ChannelOption.TCP_NODELAY, true);
            }
            catch (ChannelException channelexception) {
                if (!MinecraftServer.getServer().isDebugging()) break block5;
                System.err.println("Unable to set TCP_NODELAY option: " + channelexception.getMessage());
            }
        }
        channel.pipeline().addLast("timeout", (ChannelHandler)new SimpleReadTimeoutHandler(30)).addLast("initial_decoder", (ChannelHandler)new InitialPacketDecoder()).addLast("splitter", (ChannelHandler)new WrappedSplitter()).addLast("decoder", (ChannelHandler)new WrappedDecoder()).addLast("prepender", (ChannelHandler)new WrappedPrepender()).addLast("encoder", (ChannelHandler)new WrappedEncoder());
        NetworkManager networkmanager = new NetworkManager(EnumProtocolDirection.SERVERBOUND);
        networkmanager.a((PacketListener)new FakePacketListener());
        this.networkManagers.add(networkmanager);
        channel.pipeline().addLast("packet_handler", (ChannelHandler)networkmanager);
    }
}

