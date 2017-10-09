/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.inventory.InventoryView
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleInventoryClick;

public class InventoryClick
extends MiddleInventoryClick {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        this.slot = serializer.readShort();
        if (this.player.getOpenInventory().getType() == InventoryType.ENCHANTING && this.slot > 0) {
            ++this.slot;
        }
        this.button = serializer.readUnsignedByte();
        this.actionNumber = serializer.readShort();
        this.mode = serializer.readUnsignedByte();
        this.itemstack = serializer.readItemStack();
    }
}

