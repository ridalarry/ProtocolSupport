/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleSpawnGlobal<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int type;
    protected int x;
    protected int y;
    protected int z;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.entityId = serializer.readVarInt();
        this.type = serializer.readUnsignedByte();
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
    }
}

