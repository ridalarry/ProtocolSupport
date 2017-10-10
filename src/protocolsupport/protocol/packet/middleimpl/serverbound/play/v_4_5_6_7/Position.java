package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddlePosition;

public class Position extends MiddlePosition {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		x = serializer.readDouble();
		y = serializer.readDouble();
		serializer.readDouble();
		z = serializer.readDouble();
		onGround = serializer.readBoolean();
	}

}
