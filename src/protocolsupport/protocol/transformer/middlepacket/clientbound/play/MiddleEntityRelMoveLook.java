/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntityRelMoveLook<T>
extends MiddleEntity<T> {
    protected int relX;
    protected int relY;
    protected int relZ;
    protected int yaw;
    protected int pitch;
    protected boolean onGround;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.relX = serializer.readByte();
        this.relY = serializer.readByte();
        this.relZ = serializer.readByte();
        this.yaw = serializer.readByte();
        this.pitch = serializer.readByte();
        this.onGround = serializer.readBoolean();
    }
}

