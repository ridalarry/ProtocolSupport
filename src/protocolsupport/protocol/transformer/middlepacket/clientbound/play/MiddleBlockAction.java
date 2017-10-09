/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlock;

public abstract class MiddleBlockAction<T>
extends MiddleBlock<T> {
    protected int info1;
    protected int info2;
    protected int type;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.info1 = serializer.readUnsignedByte();
        this.info2 = serializer.readUnsignedByte();
        this.type = serializer.readVarInt();
    }
}

