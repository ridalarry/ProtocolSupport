/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddlePlayerAbilities<T>
extends ClientBoundMiddlePacket<T> {
    protected int flags;
    protected float flyspeed;
    protected float walkspeed;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.flags = serializer.readUnsignedByte();
        this.flyspeed = serializer.readFloat();
        this.walkspeed = serializer.readFloat();
    }
}

