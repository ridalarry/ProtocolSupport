/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.login;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleLoginDisconnect<T>
extends ClientBoundMiddlePacket<T> {
    protected String messageJson;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.messageJson = serializer.readString(32767);
    }
}

