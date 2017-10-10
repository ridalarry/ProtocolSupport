package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddlePositionLook;

public class PositionLook extends MiddlePositionLook {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		 x = serializer.readDouble();
		 y = serializer.readDouble();
		 serializer.readDouble();
		 z = serializer.readDouble();
		 yaw = serializer.readFloat();
		 pitch = serializer.readFloat();
		 onGround = serializer.readBoolean();
	}

}
