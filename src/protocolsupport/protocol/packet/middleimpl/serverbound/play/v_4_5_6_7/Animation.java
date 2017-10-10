package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleAnimation;

public class Animation extends MiddleAnimation {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		serializer.readInt();
		serializer.readByte();
	}

}
