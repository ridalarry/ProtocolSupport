/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleChunkMulti;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.ChunkTransformer;
import protocolsupport.utils.netty.Compressor;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ChunkMulti
extends MiddleChunkMulti<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHUNK_MULTI_ID, version);
        ByteArrayOutputStream stream = new ByteArrayOutputStream(23000);
        for (int i = 0; i < this.data.length; ++i) {
            stream.write(ChunkTransformer.toPre18Data(this.data[i], this.bitmap[i], version));
        }
        byte[] compressed = Compressor.compressStatic(stream.toByteArray());
        serializer.writeShort(this.data.length);
        serializer.writeInt(compressed.length);
        serializer.writeBoolean(this.hasSkyLight);
        serializer.writeBytes(compressed);
        for (int i = 0; i < this.data.length; ++i) {
            serializer.writeInt(this.chunkX[i]);
            serializer.writeInt(this.chunkZ[i]);
            serializer.writeShort(this.bitmap[i]);
            serializer.writeShort(0);
        }
        return RecyclableSingletonList.create(serializer);
    }
}

