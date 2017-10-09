/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleInventoryOpen<T>
extends ClientBoundMiddlePacket<T> {
    protected int windowId;
    protected String invname;
    protected String titleJson;
    protected int slots;
    protected int horseId;

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.windowId = serializer.readUnsignedByte();
        this.invname = serializer.readString(32);
        this.titleJson = serializer.readString(32767);
        this.slots = serializer.readUnsignedByte();
        if (this.invname.equals("EntityHorse")) {
            this.horseId = serializer.readInt();
        }
    }
}

