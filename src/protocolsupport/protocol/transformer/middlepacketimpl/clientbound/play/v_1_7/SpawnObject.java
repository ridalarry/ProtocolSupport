/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleSpawnObject;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SpawnObject
extends MiddleSpawnObject<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        if (this.type == 78) {
            return RecyclableEmptyList.get();
        }
        if (this.type == 71) {
            switch (this.objectdata) {
                case 0: {
                    this.z -= 32;
                    this.yaw = 128;
                    break;
                }
                case 1: {
                    this.x += 32;
                    this.yaw = 64;
                    break;
                }
                case 2: {
                    this.z += 32;
                    this.yaw = 0;
                    break;
                }
                case 3: {
                    this.x -= 32;
                    this.yaw = 192;
                }
            }
        }
        if (this.type == 70) {
            int id = IdRemapper.BLOCK.getTable(version).getRemap(this.objectdata & 4095);
            int data = this.objectdata >> 12 & 15;
            this.objectdata = id | data << 16;
        }
        if (this.type == 50 || this.type == 70 || this.type == 74) {
            this.y += 16;
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SPAWN_OBJECT_ID, version);
        serializer.writeVarInt(this.entityId);
        serializer.writeByte(this.type);
        serializer.writeInt(this.x);
        serializer.writeInt(this.y);
        serializer.writeInt(this.z);
        serializer.writeByte(this.pitch);
        serializer.writeByte(this.yaw);
        serializer.writeInt(this.objectdata);
        if (this.objectdata > 0) {
            serializer.writeShort(this.motX);
            serializer.writeShort(this.motY);
            serializer.writeShort(this.motZ);
        }
        return RecyclableSingletonList.create(serializer);
    }
}

