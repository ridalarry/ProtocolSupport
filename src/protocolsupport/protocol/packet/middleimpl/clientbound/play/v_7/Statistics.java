package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleStatistics;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Statistics extends MiddleStatistics<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_STATISTICS, version);
		serializer.writeVarInt(statistics.length);
		for (Statistic stat : statistics) {
			serializer.writeString(stat.name);
			serializer.writeVarInt(stat.value);
		}
		return RecyclableSingletonList.create(serializer);
	}

}