package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleKeepAlive;

public class KeepAlive extends MiddleKeepAlive {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		keepAliveId = serializer.readInt();
	}

}
