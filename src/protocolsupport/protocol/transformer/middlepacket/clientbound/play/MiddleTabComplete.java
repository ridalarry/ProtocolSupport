/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleTabComplete<T>
extends ClientBoundMiddlePacket<T> {
    protected String[] matches;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.matches = new String[serializer.readVarInt()];
        for (int i = 0; i < this.matches.length; ++i) {
            this.matches[i] = serializer.readString(32767);
        }
    }
}

