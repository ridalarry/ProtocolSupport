/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleBlockChangeMulti<T>
extends ClientBoundMiddlePacket<T> {
    protected int chunkX;
    protected int chunkZ;
    protected Record[] records;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.chunkX = serializer.readInt();
        this.chunkZ = serializer.readInt();
        this.records = new Record[serializer.readVarInt()];
        for (int i = 0; i < this.records.length; ++i) {
            Record record = new Record();
            record.coord = serializer.readUnsignedShort();
            record.id = serializer.readVarInt();
            this.records[i] = record;
        }
    }

    protected static class Record {
        public int coord;
        public int id;

        protected Record() {
        }
    }

}

