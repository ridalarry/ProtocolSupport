/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntityVelocity<T>
extends MiddleEntity<T> {
    protected int motX;
    protected int motY;
    protected int motZ;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.motX = serializer.readShort();
        this.motY = serializer.readShort();
        this.motZ = serializer.readShort();
    }
}

