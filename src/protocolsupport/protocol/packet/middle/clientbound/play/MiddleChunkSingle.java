package protocolsupport.protocol.packet.middle.clientbound.play;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;

public abstract class MiddleChunkSingle<T> extends ClientBoundMiddlePacket<T> {

	protected int chunkX;
	protected int chunkZ;
	protected boolean cont;
	protected int bitmask;
	protected byte[] data;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {
		chunkX = serializer.readInt();
		chunkZ = serializer.readInt();
		cont = serializer.readBoolean();
		bitmask = serializer.readUnsignedShort();
		data = serializer.readArray();
	}

}
