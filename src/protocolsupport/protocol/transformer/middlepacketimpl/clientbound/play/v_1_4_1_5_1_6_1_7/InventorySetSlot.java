/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.inventory.InventoryView
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleInventorySetSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class InventorySetSlot
extends MiddleInventorySetSlot<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        if (this.player.getOpenInventory().getType() == InventoryType.ENCHANTING) {
            if (this.slot == 1) {
                return RecyclableEmptyList.get();
            }
            if (this.slot > 0) {
                --this.slot;
            }
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WINDOW_SET_SLOT_ID, version);
        serializer.writeByte(this.windowId);
        serializer.writeShort(this.slot);
        serializer.writeItemStack(this.itemstack);
        return RecyclableSingletonList.create(serializer);
    }
}

