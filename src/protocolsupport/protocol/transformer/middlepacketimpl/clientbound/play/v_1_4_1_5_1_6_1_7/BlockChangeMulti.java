/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockChangeMulti;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockChangeMulti
extends MiddleBlockChangeMulti<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        RemappingTable remapper = IdRemapper.BLOCK.getTable(version);
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_BLOCK_CHANGE_MULTI_ID, version);
        serializer.writeInt(this.chunkX);
        serializer.writeInt(this.chunkZ);
        serializer.writeShort(this.records.length);
        serializer.writeInt(this.records.length * 4);
        for (MiddleBlockChangeMulti.Record record : this.records) {
            serializer.writeShort(record.coord);
            int id = record.id;
            serializer.writeShort(remapper.getRemap(id >> 4) << 4 | id & 15);
        }
        return RecyclableSingletonList.create(serializer);
    }
}

