package protocolsupport.protocol.packet.middle.clientbound.play;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleSpawnPainting<T> extends ClientBoundMiddlePacket<T> {

	protected int entityId;
	protected String type;
	protected BlockPosition position;
	protected int direction;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {
		entityId = serializer.readVarInt();
		type = serializer.readString(13);
		position = serializer.readPosition();
		direction = serializer.readByte();
	}

}
