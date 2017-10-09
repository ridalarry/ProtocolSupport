/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  gnu.trove.map.TIntObjectMap
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import gnu.trove.map.TIntObjectMap;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedLiving;
import protocolsupport.utils.DataWatcherObject;
import protocolsupport.utils.DataWatcherSerializer;
import protocolsupport.utils.netty.ChannelUtils;

public abstract class MiddleSpawnLiving<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int type;
    protected int x;
    protected int y;
    protected int z;
    protected int yaw;
    protected int pitch;
    protected int headPitch;
    protected int motX;
    protected int motY;
    protected int motZ;
    protected WatchedEntity wentity;
    protected TIntObjectMap<DataWatcherObject> metadata;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readVarInt();
        this.type = serializer.readUnsignedByte();
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.yaw = serializer.readUnsignedByte();
        this.pitch = serializer.readUnsignedByte();
        this.headPitch = serializer.readUnsignedByte();
        this.motX = serializer.readShort();
        this.motY = serializer.readShort();
        this.motZ = serializer.readShort();
        this.metadata = DataWatcherSerializer.decodeData(ProtocolVersion.MINECRAFT_1_8, ChannelUtils.toArray((ByteBuf)serializer));
    }

    @Override
    public void handle() {
        this.wentity = new WatchedLiving(this.entityId, this.type);
        this.storage.addWatchedEntity(this.wentity);
    }
}

