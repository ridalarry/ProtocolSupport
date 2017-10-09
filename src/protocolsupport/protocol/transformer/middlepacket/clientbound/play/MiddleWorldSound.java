/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleWorldSound<T>
extends ClientBoundMiddlePacket<T> {
    protected String name;
    protected int x;
    protected int y;
    protected int z;
    protected float volume;
    protected int pitch;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.name = serializer.readString(32767);
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.volume = serializer.readFloat();
        this.pitch = serializer.readUnsignedByte();
    }
}

