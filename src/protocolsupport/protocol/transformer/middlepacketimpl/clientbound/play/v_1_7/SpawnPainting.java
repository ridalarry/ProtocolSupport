/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleSpawnPainting;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SpawnPainting
extends MiddleSpawnPainting<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        int x = this.position.getX();
        int z = this.position.getZ();
        switch (this.direction) {
            case 0: {
                --z;
                break;
            }
            case 1: {
                ++x;
                break;
            }
            case 2: {
                ++z;
                break;
            }
            case 3: {
                --x;
            }
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SPAWN_PAINTING_ID, version);
        serializer.writeVarInt(this.entityId);
        serializer.writeString(this.type);
        serializer.writeInt(x);
        serializer.writeInt(this.position.getY());
        serializer.writeInt(z);
        serializer.writeInt(this.direction);
        return RecyclableSingletonList.create(serializer);
    }
}

