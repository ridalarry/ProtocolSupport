package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleEntityAttach;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class EntityAttach extends MiddleEntityAttach<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		if (leash) {
			return RecyclableEmptyList.get();
		} else {
			PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_ENTITY_ATTACH_ID, version);
			serializer.writeInt(entityId);
			serializer.writeInt(vehicleId);
			return RecyclableSingletonList.create(serializer);
		}
	}

}