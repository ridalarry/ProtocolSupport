/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleGameStateChange;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class GameStateChange
extends MiddleGameStateChange<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        switch (this.type) {
            case 1: {
                this.type = 2;
                break;
            }
            case 2: {
                this.type = 1;
                break;
            }
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_GAME_STATE_CHANGE_ID, version);
        serializer.writeByte(this.type);
        serializer.writeByte((int)this.value);
        return RecyclableSingletonList.create(serializer);
    }
}

