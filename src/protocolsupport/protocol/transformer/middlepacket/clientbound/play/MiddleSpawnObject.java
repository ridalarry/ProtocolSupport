/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedObject;

public abstract class MiddleSpawnObject<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int type;
    protected int x;
    protected int y;
    protected int z;
    protected int pitch;
    protected int yaw;
    protected int objectdata;
    protected int motX;
    protected int motY;
    protected int motZ;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readVarInt();
        this.type = serializer.readUnsignedByte();
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.pitch = serializer.readUnsignedByte();
        this.yaw = serializer.readUnsignedByte();
        this.objectdata = serializer.readInt();
        if (this.objectdata > 0) {
            this.motX = serializer.readShort();
            this.motY = serializer.readShort();
            this.motZ = serializer.readShort();
        }
    }

    @Override
    public void handle() {
        this.storage.addWatchedEntity(new WatchedObject(this.entityId, this.type));
    }
}

