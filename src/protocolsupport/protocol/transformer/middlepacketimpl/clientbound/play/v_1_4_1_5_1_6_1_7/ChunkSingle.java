/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleChunkSingle;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.ChunkTransformer;
import protocolsupport.utils.netty.Compressor;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ChunkSingle
extends MiddleChunkSingle<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHUNK_SINGLE_ID, version);
        serializer.writeInt(this.chunkX);
        serializer.writeInt(this.chunkZ);
        serializer.writeBoolean(this.cont);
        serializer.writeShort(this.bitmask);
        serializer.writeShort(0);
        byte[] compressed = Compressor.compressStatic(ChunkTransformer.toPre18Data(this.data, this.bitmask, version));
        serializer.writeInt(compressed.length);
        serializer.writeBytes(compressed);
        return RecyclableSingletonList.create(serializer);
    }
}

