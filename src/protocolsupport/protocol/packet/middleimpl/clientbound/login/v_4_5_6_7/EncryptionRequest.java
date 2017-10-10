package protocolsupport.protocol.packet.middleimpl.clientbound.login.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.login.MiddleEncryptionRequest;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class EncryptionRequest extends MiddleEncryptionRequest<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.LOGIN_ENCRYPTION_BEGIN_ID, version);
		serializer.writeString(serverId);
		serializer.writeArray(publicKey);
		serializer.writeArray(verifyToken);
		return RecyclableSingletonList.create(serializer);
	}

}
