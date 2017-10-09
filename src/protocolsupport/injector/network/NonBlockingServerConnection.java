/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.bootstrap.AbstractBootstrap
 *  io.netty.bootstrap.ServerBootstrap
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.epoll.Epoll
 *  io.netty.channel.epoll.EpollServerSocketChannel
 *  io.netty.channel.socket.nio.NioServerSocketChannel
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  net.minecraft.server.v1_8_R3.ChatComponentText
 *  net.minecraft.server.v1_8_R3.CrashReport
 *  net.minecraft.server.v1_8_R3.CrashReportSystemDetails
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.LazyInitVar
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect
 *  net.minecraft.server.v1_8_R3.ReportedException
 *  net.minecraft.server.v1_8_R3.ServerConnection
 *  org.apache.logging.log4j.Logger
 *  org.spigotmc.SpigotConfig
 */
package protocolsupport.injector.network;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.CrashReport;
import net.minecraft.server.v1_8_R3.CrashReportSystemDetails;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.LazyInitVar;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import net.minecraft.server.v1_8_R3.ReportedException;
import net.minecraft.server.v1_8_R3.ServerConnection;
import org.apache.logging.log4j.Logger;
import org.spigotmc.SpigotConfig;
import protocolsupport.injector.network.ServerConnectionChannel;
import protocolsupport.utils.Utils;

public class NonBlockingServerConnection
extends ServerConnection {
    private static Logger e;
    private List<ChannelFuture> g;
    private List<NetworkManager> h;

    static List<NetworkManager> access$0(ServerConnection connection) {
        return ((NonBlockingServerConnection)connection).h;
    }

    static List<NetworkManager> access$0(NonBlockingServerConnection connection) {
        return connection.h;
    }

    static MinecraftServer access$1(ServerConnection connection) {
        return MinecraftServer.getServer();
    }

    static MinecraftServer access$1(NonBlockingServerConnection connection) {
        return MinecraftServer.getServer();
    }

    public NonBlockingServerConnection() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        super(MinecraftServer.getServer());
        e = (Logger)Utils.setAccessible(ServerConnection.class.getDeclaredField("e")).get(null);
        this.g = (List)Utils.setAccessible(ServerConnection.class.getDeclaredField("g")).get((Object)this);
        this.h = new ConcurrentLinkedQueueFakeListImpl<NetworkManager>();
        Utils.setAccessible(ServerConnection.class.getDeclaredField("h")).set((Object)this, this.h);
    }

    public static void inject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Utils.setAccessible(MinecraftServer.class.getDeclaredField("q")).set((Object)MinecraftServer.getServer(), (Object)new NonBlockingServerConnection());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(InetAddress inetaddress, int port) throws IOException {
		synchronized (this.g) {
			Class<? extends ServerSocketChannel> oclass;
			LazyInitVar<? extends EventLoopGroup> lazyinitvar;
			if (Epoll.isAvailable() && MinecraftServer.getServer().ai()) {
				oclass = EpollServerSocketChannel.class;
				lazyinitvar = ServerConnection.b;
				e.info("Using epoll channel type");
			} else {
				oclass = NioServerSocketChannel.class;
				lazyinitvar = ServerConnection.a;
				e.info("Using default channel type");
			}
			this.g.add(
				new ServerBootstrap()
				.channel(oclass)
				.childHandler(new ServerConnectionChannel(h))
				.group(lazyinitvar.c())
				.localAddress(inetaddress, port)
				.bind().syncUninterruptibly()
			);
		}
	}

    public void c() {
        if (SpigotConfig.playerShuffle > 0 && MinecraftServer.currentTick % SpigotConfig.playerShuffle == 0) {
            Collections.shuffle(this.h);
        }
        Iterator<NetworkManager> iterator = this.h.iterator();
        while (iterator.hasNext()) {
            final NetworkManager networkmanager = iterator.next();
            if (networkmanager.h()) continue;
            if (!networkmanager.g()) {
                if (networkmanager.preparing) continue;
                iterator.remove();
                networkmanager.l();
                continue;
            }
            try {
                networkmanager.a();
            }
            catch (Exception exception) {
                if (networkmanager.c()) {
                    CrashReport crashreport = CrashReport.a((Throwable)exception, (String)"Ticking memory connection");
                    CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");
                    crashreportsystemdetails.a("Connection", (Callable)new Callable<String>(){

                        @Override
                        public String call() throws Exception {
                            return networkmanager.toString();
                        }
                    });
                    throw new ReportedException(crashreport);
                }
                e.warn("Failed to handle packet for " + networkmanager.getSocketAddress(), (Throwable)exception);
                final ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");
                networkmanager.a((Packet)new PacketPlayOutKickDisconnect((IChatBaseComponent)chatcomponenttext), (GenericFutureListener)new GenericFutureListener<Future<? super Void>>(){

                    public void operationComplete(Future<? super Void> future) throws Exception {
                        networkmanager.close((IChatBaseComponent)chatcomponenttext);
                    }
                }, new GenericFutureListener[0]);
                networkmanager.k();
            }
        }
    }

    public static class ConcurrentLinkedQueueFakeListImpl<E>
    extends ConcurrentLinkedQueue<E>
    implements List<E> {
        private static final long serialVersionUID = -8466302736959675653L;

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E get(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E set(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int indexOf(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int lastIndexOf(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListIterator<E> listIterator() {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException();
        }
    }

}

