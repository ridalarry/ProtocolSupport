/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleUseBed<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected BlockPosition bed;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readVarInt();
        this.bed = serializer.readPosition();
    }

    @Override
    public void handle() {
    }
}

