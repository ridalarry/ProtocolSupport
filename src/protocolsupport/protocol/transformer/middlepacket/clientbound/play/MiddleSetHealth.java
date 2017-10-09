/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleSetHealth<T>
extends ClientBoundMiddlePacket<T> {
    protected float health;
    protected int food;
    protected float saturation;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.health = serializer.readFloat();
        this.food = serializer.readVarInt();
        this.saturation = serializer.readFloat();
    }
}

