/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleUseEntity;

public class UseEntity
extends MiddleUseEntity {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        serializer.readInt();
        this.entityId = serializer.readInt();
        this.action = serializer.readBoolean() ? 1 : 0;
    }
}

