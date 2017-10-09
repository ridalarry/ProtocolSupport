/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleEntityDestroy<T>
extends ClientBoundMiddlePacket<T> {
    protected int[] entityIds;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.entityIds = new int[serializer.readVarInt()];
        for (int i = 0; i < this.entityIds.length; ++i) {
            this.entityIds[i] = serializer.readVarInt();
        }
    }

    @Override
    public void handle() {
        this.storage.removeWatchedEntities(this.entityIds);
    }
}

