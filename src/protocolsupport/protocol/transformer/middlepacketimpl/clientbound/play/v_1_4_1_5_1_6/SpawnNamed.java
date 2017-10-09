/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  gnu.trove.map.TIntObjectMap
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import gnu.trove.map.TIntObjectMap;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleSpawnNamed;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.watchedentity.WatchedDataRemapper;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.utils.DataWatcherSerializer;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SpawnNamed
extends MiddleSpawnNamed<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SPAWN_NAMED_ID, version);
        serializer.writeInt(this.playerEntityId);
        serializer.writeString(this.name);
        serializer.writeInt(this.x);
        serializer.writeInt(this.y);
        serializer.writeInt(this.z);
        serializer.writeByte(this.yaw);
        serializer.writeByte(this.pitch);
        serializer.writeShort(this.itemId);
        serializer.writeBytes(DataWatcherSerializer.encodeData(version, WatchedDataRemapper.transform(this.wplayer, this.metadata, version)));
        return RecyclableSingletonList.create(serializer);
    }
}

