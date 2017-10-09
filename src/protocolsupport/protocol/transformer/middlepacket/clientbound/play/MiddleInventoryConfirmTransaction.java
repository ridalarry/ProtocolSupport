/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleInventoryConfirmTransaction<T>
extends ClientBoundMiddlePacket<T> {
    protected int windowId;
    protected int actionNumber;
    protected boolean accepted;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        this.actionNumber = serializer.readShort();
        this.accepted = serializer.readBoolean();
    }
}

