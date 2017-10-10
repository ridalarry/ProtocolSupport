package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleChunkMulti;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.utils.ChunkTransformer;
import protocolsupport.utils.netty.Compressor;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ChunkMulti extends MiddleChunkMulti<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHUNK_MULTI_ID, version);
		ByteArrayOutputStream stream = new ByteArrayOutputStream(23000);
		for (int i = 0; i < data.length; i++) {
			stream.write(ChunkTransformer.toPre18Data(data[i], bitmap[i], version));
		}
		byte[] compressed = Compressor.compressStatic(stream.toByteArray());
		serializer.writeShort(data.length);
		serializer.writeInt(compressed.length);
		serializer.writeBoolean(hasSkyLight);
		serializer.writeBytes(compressed);
		for (int i = 0; i < data.length; i++) {
			serializer.writeInt(chunkX[i]);
			serializer.writeInt(chunkZ[i]);
			serializer.writeShort(bitmap[i]);
			serializer.writeShort(0);
		}
		return RecyclableSingletonList.create(serializer);
	}

}
