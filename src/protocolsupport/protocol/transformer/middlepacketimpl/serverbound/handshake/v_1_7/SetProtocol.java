/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_7;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.handshake.MiddleSetProtocol;

public class SetProtocol
extends MiddleSetProtocol {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        serializer.readVarInt();
        this.hostname = serializer.readString(32767);
        this.port = serializer.readUnsignedShort();
        this.nextState = serializer.readVarInt();
    }
}

