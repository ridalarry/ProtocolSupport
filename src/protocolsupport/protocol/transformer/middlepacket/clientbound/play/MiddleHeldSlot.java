/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleHeldSlot<T>
extends ClientBoundMiddlePacket<T> {
    protected int slot;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.slot = serializer.readUnsignedByte();
    }
}

