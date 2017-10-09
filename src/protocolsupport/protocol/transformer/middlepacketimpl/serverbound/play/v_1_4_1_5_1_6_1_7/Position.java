/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddlePosition;

public class Position
extends MiddlePosition {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.x = serializer.readDouble();
        this.y = serializer.readDouble();
        serializer.readDouble();
        this.z = serializer.readDouble();
        this.onGround = serializer.readBoolean();
    }
}

