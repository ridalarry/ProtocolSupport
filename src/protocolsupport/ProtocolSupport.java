package protocolsupport;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import protocolsupport.commands.CommandHandler;
import protocolsupport.injector.ServerInjector;
import protocolsupport.injector.network.NettyInjector;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.server.listeners.PlayerListener;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.Compressor;

public class ProtocolSupport
extends JavaPlugin {
    public void onLoad() {
        try {
            Allocator.init();
            Compressor.init();
            ServerBoundPacket.init();
            ClientBoundPacket.init();
            InitialPacketDecoder.init();
            AbstractLoginListener.init();
            NettyInjector.inject();
            ServerInjector.inject();
        }
        catch (Throwable t) {
            t.printStackTrace();
            Bukkit.shutdown();
        }
    }

    public void onEnable() {
        this.getCommand("protocolsupport").setExecutor((CommandExecutor)new CommandHandler());
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
    }

    public void onDisable() {
        Bukkit.shutdown();
    }

    public static void logWarning(String message) {
        ((ProtocolSupport)JavaPlugin.getPlugin(ProtocolSupport.class)).getLogger().warning(message);
    }

    public static void logInfo(String message) {
        ((ProtocolSupport)JavaPlugin.getPlugin(ProtocolSupport.class)).getLogger().info(message);
    }
}

