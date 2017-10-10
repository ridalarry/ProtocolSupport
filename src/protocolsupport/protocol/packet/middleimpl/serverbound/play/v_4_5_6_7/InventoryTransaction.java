package protocolsupport.protocol.packet.middleimpl.serverbound.play.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.serverbound.play.MiddleInventoryTransaction;

public class InventoryTransaction extends MiddleInventoryTransaction {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		windowId = serializer.readUnsignedByte();
		actionNumber = serializer.readShort();
		accepted = serializer.readBoolean();
	}

}
