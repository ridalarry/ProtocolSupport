/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleChat;

public class Chat
extends MiddleChat {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.message = serializer.readString(32767);
    }
}

