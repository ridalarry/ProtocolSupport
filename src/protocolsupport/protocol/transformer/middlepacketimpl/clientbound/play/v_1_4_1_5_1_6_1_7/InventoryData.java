/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.inventory.InventoryView
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleInventoryData;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class InventoryData
extends MiddleInventoryData<RecyclableCollection<PacketData>> {
    private static final int[] furTypeTr = new int[]{1, 2, 0};

    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        if (this.player.getOpenInventory().getType() == InventoryType.FURNACE && this.type < furTypeTr.length) {
            this.type = furTypeTr[this.type];
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WINDOW_DATA_ID, version);
        serializer.writeByte(this.windowId);
        serializer.writeShort(this.type);
        serializer.writeShort(this.value);
        return RecyclableSingletonList.create(serializer);
    }
}

