package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleSteerVehicle;

public class SteerVehicle extends MiddleSteerVehicle {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		sideForce = serializer.readFloat();
		forwardForce = serializer.readFloat();
		flags = (serializer.readBoolean() ? 1 : 0) + (serializer.readBoolean() ? 1 << 1 : 0);
	}

}
