package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleClientSettings;

public class ClientSettings extends MiddleClientSettings {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		locale = serializer.readString(7);
		viewDist = serializer.readByte();
		int chatState = serializer.readByte();
		chatMode = chatState & 7;
		chatColors = (chatState & 8) == 8;
		serializer.readByte();
		serializer.readBoolean();
		skinFlags = 255;
	}

}
