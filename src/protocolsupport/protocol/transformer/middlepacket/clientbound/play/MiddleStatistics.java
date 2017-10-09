/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleStatistics<T>
extends ClientBoundMiddlePacket<T> {
    protected Statistic[] statistics;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.statistics = new Statistic[serializer.readVarInt()];
        for (int i = 0; i < this.statistics.length; ++i) {
            Statistic stat = new Statistic();
            stat.name = serializer.readString(32767);
            stat.value = serializer.readVarInt();
            this.statistics[i] = stat;
        }
    }

    protected static class Statistic {
        public String name;
        public int value;

        protected Statistic() {
        }
    }

}

