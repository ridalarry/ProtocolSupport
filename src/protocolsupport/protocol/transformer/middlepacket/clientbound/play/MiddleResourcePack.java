/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleResourcePack<T>
extends ClientBoundMiddlePacket<T> {
    protected String url;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.url = serializer.readString(32767);
    }
}

