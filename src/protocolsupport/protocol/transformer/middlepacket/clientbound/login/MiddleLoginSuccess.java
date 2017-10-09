/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.login;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleLoginSuccess<T>
extends ClientBoundMiddlePacket<T> {
    protected String uuidstring;
    protected String name;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.uuidstring = serializer.readString(36);
        this.name = serializer.readString(16);
    }
}

