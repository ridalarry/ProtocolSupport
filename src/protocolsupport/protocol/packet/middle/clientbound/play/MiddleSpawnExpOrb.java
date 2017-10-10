package protocolsupport.protocol.packet.middle.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleSpawnExpOrb<T> extends ClientBoundMiddlePacket<T> {

	protected int entityId;
	protected int x;
	protected int y;
	protected int z;
	protected int count;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) {
		entityId = serializer.readVarInt();
		x = serializer.readInt();
		y = serializer.readInt();
		z = serializer.readInt();
		count = serializer.readShort();
	}

}
