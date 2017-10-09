/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleGameStateChange<T>
extends ClientBoundMiddlePacket<T> {
    protected int type;
    protected float value;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.type = serializer.readByte();
        this.value = serializer.readFloat();
    }
}

