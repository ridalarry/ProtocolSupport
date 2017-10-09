/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.login.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.login.MiddleEncryptionResponse;

public class EncryptionResponse
extends MiddleEncryptionResponse {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.sharedSecret = serializer.readArray();
        this.verifyToken = serializer.readArray();
    }
}

