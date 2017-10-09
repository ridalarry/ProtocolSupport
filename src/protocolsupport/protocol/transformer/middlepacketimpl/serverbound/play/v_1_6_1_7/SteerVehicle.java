/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleSteerVehicle;

public class SteerVehicle
extends MiddleSteerVehicle {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.sideForce = serializer.readFloat();
        this.forwardForce = serializer.readFloat();
        this.flags = (serializer.readBoolean() ? 1 : 0) + (serializer.readBoolean() ? 2 : 0);
    }
}

