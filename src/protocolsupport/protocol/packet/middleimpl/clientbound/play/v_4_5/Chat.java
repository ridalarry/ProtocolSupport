package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleChat;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.utils.LegacyUtils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Chat extends MiddleChat<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHAT_ID, version);
		serializer.writeString(LegacyUtils.toText(ChatSerializer.a(chatJson)));
		return RecyclableSingletonList.create(serializer);
	}

}
