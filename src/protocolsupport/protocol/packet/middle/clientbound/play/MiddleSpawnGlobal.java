package protocolsupport.protocol.packet.middle.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleSpawnGlobal<T> extends ClientBoundMiddlePacket<T> {

	protected int entityId;
	protected int type;
	protected int x;
	protected int y;
	protected int z;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) {
		entityId = serializer.readVarInt();
		type = serializer.readUnsignedByte();
		x = serializer.readInt();
		y = serializer.readInt();
		z = serializer.readInt();
	}

}
