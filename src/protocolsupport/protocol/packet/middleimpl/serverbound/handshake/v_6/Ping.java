package protocolsupport.protocol.packet.middleimpl.serverbound.handshake.v_6;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class Ping extends ServerBoundMiddlePacket {

	protected String hostname;
	protected int port;

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		serializer.readUnsignedByte();
		serializer.readUnsignedByte();
		serializer.readString(Short.MAX_VALUE);
		serializer.readUnsignedShort();
		serializer.readUnsignedByte();
		hostname = serializer.readString(Short.MAX_VALUE);
		port = serializer.readInt();
	}

	@Override
	public RecyclableCollection<Packet<?>> toNative() throws Exception {
		RecyclableArrayList<Packet<?>> packets = RecyclableArrayList.create();
		PacketCreator hsscreator = PacketCreator.create(ServerBoundPacket.HANDSHAKE_START.get());
		hsscreator.writeVarInt(ProtocolVersion.getLatest().getId());
		hsscreator.writeString(hostname);
		hsscreator.writeShort(port);
		hsscreator.writeVarInt(1);
		packets.add(hsscreator.create());
		packets.add(ServerBoundPacket.STATUS_REQUEST.get());
		return packets;
	}

}
