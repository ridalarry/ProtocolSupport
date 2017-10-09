/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleCollectEffect<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected int collectorId;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.entityId = serializer.readVarInt();
        this.collectorId = serializer.readVarInt();
    }
}

