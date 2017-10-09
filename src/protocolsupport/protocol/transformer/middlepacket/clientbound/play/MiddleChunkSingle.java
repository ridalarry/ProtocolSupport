/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleChunkSingle<T>
extends ClientBoundMiddlePacket<T> {
    protected int chunkX;
    protected int chunkZ;
    protected boolean cont;
    protected int bitmask;
    protected byte[] data;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.chunkX = serializer.readInt();
        this.chunkZ = serializer.readInt();
        this.cont = serializer.readBoolean();
        this.bitmask = serializer.readUnsignedShort();
        this.data = serializer.readArray();
    }
}

