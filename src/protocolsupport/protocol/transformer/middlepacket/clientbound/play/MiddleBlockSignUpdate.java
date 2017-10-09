/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlock;

public abstract class MiddleBlockSignUpdate<T>
extends MiddleBlock<T> {
    protected String[] linesJson = new String[4];

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        for (int i = 0; i < this.linesJson.length; ++i) {
            this.linesJson[i] = serializer.readString(32767);
        }
    }
}

