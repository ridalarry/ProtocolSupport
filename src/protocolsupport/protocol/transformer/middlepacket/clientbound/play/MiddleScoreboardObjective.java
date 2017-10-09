/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleScoreboardObjective<T>
extends ClientBoundMiddlePacket<T> {
    protected String name;
    protected int mode;
    protected String value;
    protected String type;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.name = serializer.readString(16);
        this.mode = serializer.readUnsignedByte();
        if (this.mode != 1) {
            this.value = serializer.readString(32);
            this.type = serializer.readString(32);
        }
    }
}

