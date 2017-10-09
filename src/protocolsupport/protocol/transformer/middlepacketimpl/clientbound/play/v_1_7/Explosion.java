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
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleExplosion;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Explosion
extends MiddleExplosion<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_EXPLOSION_ID, version);
        serializer.writeFloat(this.x);
        serializer.writeFloat(this.y);
        serializer.writeFloat(this.z);
        serializer.writeFloat(this.radius);
        serializer.writeInt(this.blocks.length);
        for (MiddleExplosion.AffectedBlock block : this.blocks) {
            serializer.writeByte(block.offX);
            serializer.writeByte(block.offY);
            serializer.writeByte(block.offZ);
        }
        serializer.writeFloat(this.pMotX);
        serializer.writeFloat(this.pMotY);
        serializer.writeFloat(this.pMotZ);
        return RecyclableSingletonList.create(serializer);
    }
}

