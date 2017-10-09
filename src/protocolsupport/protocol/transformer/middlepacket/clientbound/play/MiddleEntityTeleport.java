/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntityTeleport<T>
extends MiddleEntity<T> {
    protected int x;
    protected int y;
    protected int z;
    protected byte yaw;
    protected byte pitch;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.yaw = serializer.readByte();
        this.pitch = serializer.readByte();
    }
}

