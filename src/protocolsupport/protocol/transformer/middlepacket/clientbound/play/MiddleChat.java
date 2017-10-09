/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleChat<T>
extends ClientBoundMiddlePacket<T> {
    protected String chatJson;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.chatJson = serializer.readString(32767);
    }
}

