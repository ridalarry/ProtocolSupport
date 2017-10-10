package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_6_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleEntityAction;

public class EntityAction extends MiddleEntityAction {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		entityId = serializer.readInt();
		actionId = serializer.readByte() - 1;
		jumpBoost = serializer.readInt();
	}

}
