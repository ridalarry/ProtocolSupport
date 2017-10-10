package protocolsupport.protocol.packet.middle.clientbound.play;

import java.io.IOException;

import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleBlock<T> extends ClientBoundMiddlePacket<T> {

	protected BlockPosition position;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {
		position = serializer.readPosition();
	}

}
