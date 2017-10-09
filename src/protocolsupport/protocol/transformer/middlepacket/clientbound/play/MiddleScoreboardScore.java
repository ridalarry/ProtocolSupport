/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleScoreboardScore<T>
extends ClientBoundMiddlePacket<T> {
    protected String name;
    protected int mode;
    protected String objectiveName;
    protected int value;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.name = serializer.readString(40);
        this.mode = serializer.readUnsignedByte();
        this.objectiveName = serializer.readString(16);
        if (this.mode != 1) {
            this.value = serializer.readVarInt();
        }
    }
}

