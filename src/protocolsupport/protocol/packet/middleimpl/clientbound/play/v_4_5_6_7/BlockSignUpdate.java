package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleBlockSignUpdate;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.utils.LegacyUtils;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockSignUpdate extends MiddleBlockSignUpdate<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_UPDATE_SIGN_ID, version);
		serializer.writeInt(position.getX());
		serializer.writeShort(position.getY());
		serializer.writeInt(position.getZ());
		for (String lineJson : linesJson) {
			serializer.writeString(Utils.clampString(LegacyUtils.toText(ChatSerializer.a(lineJson)), 15));
		}
		return RecyclableSingletonList.create(serializer);
	}

}
