package protocolsupport.protocol.packet.middleimpl.serverbound.handshake.v_1_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.handshake.MiddleSetProtocol;

public class SetProtocol extends MiddleSetProtocol {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		serializer.readVarInt();
		hostname = serializer.readString(Short.MAX_VALUE);
		port = serializer.readUnsignedShort();
		nextState = serializer.readVarInt();
	}

}
