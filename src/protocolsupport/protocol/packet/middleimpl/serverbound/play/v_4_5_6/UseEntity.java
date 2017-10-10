package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleUseEntity;

public class UseEntity extends MiddleUseEntity {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		serializer.readInt();
		entityId = serializer.readInt();
		action = serializer.readBoolean() ? 1 : 0;
	}

}
