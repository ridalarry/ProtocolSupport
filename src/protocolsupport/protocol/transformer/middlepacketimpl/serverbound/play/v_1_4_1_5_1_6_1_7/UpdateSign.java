/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleUpdateSign;

public class UpdateSign
extends MiddleUpdateSign {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.position = new BlockPosition(serializer.readInt(), (int)serializer.readShort(), serializer.readInt());
        for (int i = 0; i < 4; ++i) {
            this.lines[i] = serializer.readString(15);
        }
    }
}

