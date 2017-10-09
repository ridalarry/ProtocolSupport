/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlock;

public abstract class MiddleBlockBreakAnimation<T>
extends MiddleBlock<T> {
    protected int entityId;
    protected int stage;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readVarInt();
        super.readFromServerData(serializer);
        this.stage = serializer.readByte();
    }
}

