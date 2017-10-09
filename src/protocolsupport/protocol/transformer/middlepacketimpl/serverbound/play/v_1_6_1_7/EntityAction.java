/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleEntityAction;

public class EntityAction
extends MiddleEntityAction {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.entityId = serializer.readInt();
        this.actionId = serializer.readByte() - 1;
        this.jumpBoost = serializer.readInt();
    }
}

