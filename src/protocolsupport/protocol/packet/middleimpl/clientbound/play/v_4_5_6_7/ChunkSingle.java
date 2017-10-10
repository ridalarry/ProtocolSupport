package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleChunkSingle;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.transformer.utils.ChunkTransformer;
import protocolsupport.utils.netty.Compressor;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ChunkSingle extends MiddleChunkSingle<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHUNK_SINGLE_ID, version);
		serializer.writeInt(chunkX);
		serializer.writeInt(chunkZ);
		serializer.writeBoolean(cont);
		serializer.writeShort(bitmask);
		serializer.writeShort(0);
		byte[] compressed = Compressor.compressStatic(ChunkTransformer.toPre18Data(data, bitmask, version));
		serializer.writeInt(compressed.length);
		serializer.writeBytes(compressed);
		return RecyclableSingletonList.create(serializer);
	}

}
