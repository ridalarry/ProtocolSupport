package protocolsupport.protocol.packet.middleimpl.serverbound.login.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.login.MiddleEncryptionResponse;

public class EncryptionResponse extends MiddleEncryptionResponse {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		sharedSecret = serializer.readArray();
		verifyToken = serializer.readArray();
	}

}
