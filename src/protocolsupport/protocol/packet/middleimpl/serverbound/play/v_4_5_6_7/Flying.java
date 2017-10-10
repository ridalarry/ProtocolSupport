package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleFlying;

public class Flying extends MiddleFlying {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		onGround = serializer.readBoolean();
	}

}
