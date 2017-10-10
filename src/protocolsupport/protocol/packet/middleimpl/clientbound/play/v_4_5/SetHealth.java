package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5;

import net.minecraft.server.v1_8_R3.MathHelper;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleSetHealth;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SetHealth extends MiddleSetHealth<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_UPDATE_HEALTH_ID, version);
		serializer.writeShort(MathHelper.f(health));
		serializer.writeShort(food);
		serializer.writeFloat(saturation);
		return RecyclableSingletonList.create(serializer);
	}

}