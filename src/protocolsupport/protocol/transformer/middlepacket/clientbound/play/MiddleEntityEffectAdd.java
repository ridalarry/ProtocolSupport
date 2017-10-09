/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntity;

public abstract class MiddleEntityEffectAdd<T>
extends MiddleEntity<T> {
    protected int effectId;
    protected int amplifier;
    protected int duration;
    protected boolean hideParticles;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.effectId = serializer.readByte();
        this.amplifier = serializer.readByte();
        this.duration = serializer.readVarInt();
        this.hideParticles = serializer.readBoolean();
    }
}

