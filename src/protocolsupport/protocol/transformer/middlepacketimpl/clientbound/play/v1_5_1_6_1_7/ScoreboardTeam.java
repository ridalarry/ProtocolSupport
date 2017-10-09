/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleScoreboardTeam;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ScoreboardTeam
extends MiddleScoreboardTeam<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SCOREBOARD_TEAM_ID, version);
        serializer.writeString(this.name);
        serializer.writeByte(this.mode);
        if (this.mode == 0 || this.mode == 2) {
            serializer.writeString(this.displayName);
            serializer.writeString(this.prefix);
            serializer.writeString(this.suffix);
            serializer.writeByte(this.friendlyFire);
        }
        if (this.mode == 0 || this.mode == 3 || this.mode == 4) {
            serializer.writeShort(this.players.length);
            for (String player : this.players) {
                serializer.writeString(Utils.clampString(player, 16));
            }
        }
        return RecyclableSingletonList.create(serializer);
    }
}

