/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5;

import java.io.IOException;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddlePlayerAbilities;

public class PlayerAbilities
extends MiddlePlayerAbilities {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.flags = serializer.readUnsignedByte();
        this.flySpeed = (float)serializer.readUnsignedByte() / 255.0f;
        this.walkSpeed = (float)serializer.readUnsignedByte() / 255.0f;
    }
}

