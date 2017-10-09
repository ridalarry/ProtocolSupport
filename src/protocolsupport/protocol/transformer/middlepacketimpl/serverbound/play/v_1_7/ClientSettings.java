/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleClientSettings;

public class ClientSettings
extends MiddleClientSettings {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.locale = serializer.readString(7);
        this.viewDist = serializer.readByte();
        this.chatMode = serializer.readByte();
        this.chatColors = serializer.readBoolean();
        serializer.readByte();
        serializer.readBoolean();
        this.skinFlags = 255;
    }
}

