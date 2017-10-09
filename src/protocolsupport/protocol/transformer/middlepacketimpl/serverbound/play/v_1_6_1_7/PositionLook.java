/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddlePositionLook;

public class PositionLook
extends MiddlePositionLook {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.x = serializer.readDouble();
        this.y = serializer.readDouble();
        serializer.readDouble();
        this.z = serializer.readDouble();
        this.yaw = serializer.readFloat();
        this.pitch = serializer.readFloat();
        this.onGround = serializer.readBoolean();
    }
}

