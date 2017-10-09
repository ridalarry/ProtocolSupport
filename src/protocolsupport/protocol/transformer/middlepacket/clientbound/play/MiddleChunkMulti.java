/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.transformer.utils.ChunkTransformer;
import protocolsupport.utils.netty.ChannelUtils;

public abstract class MiddleChunkMulti<T>
extends ClientBoundMiddlePacket<T> {
    protected boolean hasSkyLight;
    protected int[] chunkX;
    protected int[] chunkZ;
    protected int[] bitmap;
    protected byte[][] data;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        int i;
        this.hasSkyLight = serializer.readBoolean();
        int count = serializer.readVarInt();
        this.chunkX = new int[count];
        this.chunkZ = new int[count];
        this.bitmap = new int[count];
        this.data = new byte[count][];
        for (i = 0; i < count; ++i) {
            this.chunkX[i] = serializer.readInt();
            this.chunkZ[i] = serializer.readInt();
            this.bitmap[i] = serializer.readUnsignedShort();
        }
        for (i = 0; i < count; ++i) {
            this.data[i] = ChannelUtils.toArray(serializer.readBytes(ChunkTransformer.calcDataSize(Integer.bitCount(this.bitmap[i]), this.hasSkyLight, true)));
        }
    }
}

