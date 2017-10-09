/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleWorldEvent;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class WorldEvent
extends MiddleWorldEvent<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        if (this.effectId == 2001) {
            this.data = IdRemapper.BLOCK.getTable(version).getRemap(this.data & 4095);
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WORLD_EVENT_ID, version);
        serializer.writeInt(this.effectId);
        serializer.writeInt(this.position.getX());
        serializer.writeByte(this.position.getY());
        serializer.writeInt(this.position.getZ());
        serializer.writeInt(this.data);
        serializer.writeBoolean(this.disableRelative);
        return RecyclableSingletonList.create(serializer);
    }
}

