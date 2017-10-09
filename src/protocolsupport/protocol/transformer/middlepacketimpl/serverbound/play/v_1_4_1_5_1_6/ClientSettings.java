/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleClientSettings;

public class ClientSettings
extends MiddleClientSettings {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.locale = serializer.readString(7);
        this.viewDist = serializer.readByte();
        byte chatState = serializer.readByte();
        this.chatMode = chatState & 7;
        this.chatColors = (chatState & 8) == 8;
        serializer.readByte();
        serializer.readBoolean();
        this.skinFlags = 255;
    }
}

