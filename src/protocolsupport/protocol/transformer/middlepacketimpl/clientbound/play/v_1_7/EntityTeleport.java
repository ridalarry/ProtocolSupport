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
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntityTeleport;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class EntityTeleport
extends MiddleEntityTeleport<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        WatchedEntity wentity = this.storage.getWatchedEntity(this.entityId);
        if (wentity != null && (wentity.getType() == SpecificType.TNT || wentity.getType() == SpecificType.FALLING_OBJECT)) {
            this.y += 16;
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_ENTITY_TELEPORT_ID, version);
        serializer.writeInt(this.entityId);
        serializer.writeInt(this.x);
        serializer.writeInt(this.y);
        serializer.writeInt(this.z);
        serializer.writeByte((int)this.yaw);
        serializer.writeByte((int)this.pitch);
        return RecyclableSingletonList.create(serializer);
    }
}

