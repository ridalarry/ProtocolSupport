package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleTabComplete;

public class TabComplete extends MiddleTabComplete {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		string = serializer.readString(Short.MAX_VALUE);
		position = null;
	}

}
