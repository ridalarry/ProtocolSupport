/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.EnumParticle
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.server.v1_8_R3.EnumParticle;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleWorldParticle;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class WorldParticle
extends MiddleWorldParticle<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WORLD_PARTICLES_ID, version);
        EnumParticle particle = EnumParticle.values()[this.type];
        String name = particle.b();
        switch (particle) {
            case ITEM_CRACK: {
                name = name + IdRemapper.ITEM.getTable(version).getRemap((Integer)this.adddata.get(0));
                break;
            }
            case BLOCK_CRACK: 
            case BLOCK_DUST: {
                int blockstateId = (Integer)this.adddata.get(0);
                name = name + IdRemapper.BLOCK.getTable(version).getRemap(blockstateId & 4095) + "_" + (blockstateId >> 12 & 15);
                break;
            }
        }
        serializer.writeString(name);
        serializer.writeFloat(this.x);
        serializer.writeFloat(this.y);
        serializer.writeFloat(this.z);
        serializer.writeFloat(this.offX);
        serializer.writeFloat(this.offY);
        serializer.writeFloat(this.offZ);
        serializer.writeFloat(this.speed);
        serializer.writeInt(this.count);
        return RecyclableSingletonList.create(serializer);
    }

}

