package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleWorldEvent;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class WorldEvent extends MiddleWorldEvent<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		if (effectId == 2001) {
			data = IdRemapper.BLOCK.getTable(version).getRemap(data & 0xFFF);
		}
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WORLD_EVENT_ID, version);
		serializer.writeInt(effectId);
		serializer.writeInt(position.getX());
		serializer.writeByte(position.getY());
		serializer.writeInt(position.getZ());
		serializer.writeInt(data);
		serializer.writeBoolean(disableRelative);
		return RecyclableSingletonList.create(serializer);
	}

}
