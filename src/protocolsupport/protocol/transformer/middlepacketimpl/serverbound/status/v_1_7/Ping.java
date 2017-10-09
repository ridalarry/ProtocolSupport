/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.status.v_1_7;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.status.MiddlePing;

public class Ping
extends MiddlePing {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.pingId = serializer.readLong();
    }
}

