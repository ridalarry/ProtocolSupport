/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketDataSerializer
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  org.apache.commons.lang3.Validate
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Player
 */
package protocolsupport.api.tab;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.components.TextComponent;
import protocolsupport.protocol.RecyclablePacketDataSerializer;

public class TabAPI {
    private static int maxTabSize = Math.min(Bukkit.getMaxPlayers(), 60);
    private static BaseComponent currentHeader;
    private static BaseComponent currentFooter;
    private static final BaseComponent empty;

    public static int getMaxTabSize() {
        return maxTabSize;
    }

    public static void setMaxTabSize(int maxSize) {
        maxTabSize = maxSize;
    }

    public static void setDefaultHeader(BaseComponent header) {
        currentHeader = header;
    }

    public static void setDefaultFooter(BaseComponent footer) {
        currentFooter = footer;
    }

    public static BaseComponent getDefaultHeader() {
        return currentHeader;
    }

    public static BaseComponent getDefaultFooter() {
        return currentFooter;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void sendHeaderFooter(Player player, BaseComponent header, BaseComponent footer) {
        Validate.notNull((Object)player, (String)"Player can't be null", (Object[])new Object[0]);
        RecyclablePacketDataSerializer serializer = RecyclablePacketDataSerializer.create(ProtocolVersion.getLatest());
        try {
            serializer.writeString(ChatAPI.toJSON(header != null ? header : empty));
            serializer.writeString(ChatAPI.toJSON(footer != null ? footer : empty));
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
            try {
                packet.a((PacketDataSerializer)serializer);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
        }
        finally {
            serializer.release();
        }
    }

    static {
        empty = new TextComponent("");
    }
}

