package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleLogin;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Login extends MiddleLogin<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_LOGIN_ID, version);
		serializer.writeInt(playerEntityId);
		serializer.writeString(leveltype);
		serializer.writeByte(gamemode);
		serializer.writeByte(dimension);
		serializer.writeByte(difficulty);
		serializer.writeByte(0);
		serializer.writeByte(Math.min(maxplayers, 60));
		return RecyclableSingletonList.create(serializer);
	}

}