/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleInventoryData<T>
extends ClientBoundMiddlePacket<T> {
    protected int windowId;
    protected int type;
    protected int value;

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        this.type = serializer.readShort();
        this.value = serializer.readShort();
    }
}

