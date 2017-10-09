/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleUseEntity;

public class UseEntity
extends MiddleUseEntity {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.entityId = serializer.readInt();
        this.action = serializer.readByte();
    }
}

