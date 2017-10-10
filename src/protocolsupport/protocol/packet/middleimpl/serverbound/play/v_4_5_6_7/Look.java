package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleLook;

public class Look extends MiddleLook {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		yaw = serializer.readFloat();
		pitch = serializer.readFloat();
		onGround = serializer.readBoolean();
	}

}
