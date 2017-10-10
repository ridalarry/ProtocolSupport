package protocolsupport.protocol.packet.middleimpl.serverbound.login.v_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.login.MiddleLoginStart;

public class LoginStart extends MiddleLoginStart {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		name = serializer.readString(16);
	}

}
