/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.v1_8_R3.ChatComponentText
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.ServerConnection
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.injector.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import java.lang.reflect.Field;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.ServerConnection;
import org.spigotmc.SneakyThrow;
import protocolsupport.injector.network.ServerConnectionChannel;
import protocolsupport.utils.Utils;

public class BasicInjector {
    public static void inject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        ServerConnection serverConnection = MinecraftServer.getServer().aq();
        Field connectionsListField = Utils.setAccessible(ServerConnection.class.getDeclaredField("g"));
        ChannelInjectList connectionsList = new ChannelInjectList((List)Utils.setAccessible(ServerConnection.class.getDeclaredField("h")).get((Object)serverConnection), (List)connectionsListField.get((Object)serverConnection));
        connectionsListField.set((Object)serverConnection, connectionsList);
        connectionsList.injectExisting();
    }

    public static class ChannelInjectList
    implements List<ChannelFuture> {
        private final List<NetworkManager> networkManagersList;
        private final List<ChannelFuture> originalList;

        public ChannelInjectList(List<NetworkManager> networkManagerList, List<ChannelFuture> originalList) {
            this.originalList = originalList;
            this.networkManagersList = networkManagerList;
        }

        public void injectExisting() {
            for (ChannelFuture future : this.originalList) {
                this.inject(future);
            }
        }

        @Override
        public boolean add(ChannelFuture e) {
            boolean result = this.originalList.add(e);
            this.inject(e);
            return result;
        }

        @Override
        public void add(int index, ChannelFuture element) {
            this.originalList.add(index, element);
            this.inject(element);
        }

        @Override
        public ChannelFuture set(int index, ChannelFuture element) {
            ChannelFuture result = this.originalList.set(index, element);
            this.inject(element);
            return result;
        }

        @Override
        public boolean addAll(Collection<? extends ChannelFuture> c) {
            boolean result = this.originalList.addAll(c);
            for (ChannelFuture future : c) {
                this.inject(future);
            }
            return result;
        }

        @Override
        public boolean addAll(int index, Collection<? extends ChannelFuture> c) {
            boolean result = this.originalList.addAll(index, c);
            for (ChannelFuture future : c) {
                this.inject(future);
            }
            return result;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        protected void inject(ChannelFuture future) {
            Channel channel = future.channel();
            try {
                Object serverMainHandler = null;
                for (ChannelHandler handler : channel.pipeline().toMap().values()) {
                    if (!handler.getClass().getName().equals("io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor")) continue;
                    serverMainHandler = handler;
                    break;
                }
                if (serverMainHandler == null) {
                    throw new IllegalStateException("Unable to find default netty channel initializer");
                }
                Utils.setAccessible(serverMainHandler.getClass().getDeclaredField("childHandler")).set(serverMainHandler, (Object)new ServerConnectionChannel(this.networkManagersList));
            }
            catch (Exception e2) {
                SneakyThrow.sneaky((Throwable)e2);
            }
            List<NetworkManager> e2 = this.networkManagersList;
            synchronized (e2) {
                for (NetworkManager nm : this.networkManagersList) {
                    if (!nm.channel.localAddress().equals(channel.localAddress())) continue;
                    nm.close((IChatBaseComponent)new ChatComponentText("ProtocolSupport channel reset"));
                }
            }
        }

        @Override
        public int size() {
            return this.originalList.size();
        }

        @Override
        public boolean isEmpty() {
            return this.originalList.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return this.originalList.contains(o);
        }

        @Override
        public Iterator<ChannelFuture> iterator() {
            return this.originalList.iterator();
        }

        @Override
        public Object[] toArray() {
            return this.originalList.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return this.originalList.toArray(a);
        }

        @Override
        public boolean remove(Object o) {
            return this.originalList.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return this.originalList.containsAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return this.originalList.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return this.originalList.retainAll(c);
        }

        @Override
        public void clear() {
            this.originalList.clear();
        }

        @Override
        public ChannelFuture get(int index) {
            return this.originalList.get(index);
        }

        @Override
        public ChannelFuture remove(int index) {
            return this.originalList.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            return this.originalList.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.originalList.lastIndexOf(o);
        }

        @Override
        public ListIterator<ChannelFuture> listIterator() {
            return this.originalList.listIterator();
        }

        @Override
        public ListIterator<ChannelFuture> listIterator(int index) {
            return this.originalList.listIterator(index);
        }

        @Override
        public List<ChannelFuture> subList(int fromIndex, int toIndex) {
            return this.originalList.subList(fromIndex, toIndex);
        }
    }

}

