/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddlePlayerInfo;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class PlayerInfo
extends MiddlePlayerInfo<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        RecyclableArrayList<PacketData> datas = RecyclableArrayList.create();
        block5 : for (MiddlePlayerInfo.Info info : this.infos) {
            switch (this.action) {
                case ADD: {
                    if (info.previousinfo != null) {
                        datas.add(PlayerInfo.createData(info.previousinfo.getName(), false, version));
                    }
                    datas.add(PlayerInfo.createData(info.getName(), true, version));
                    continue block5;
                }
                case REMOVE: {
                    if (info.previousinfo == null) continue block5;
                    datas.add(PlayerInfo.createData(info.previousinfo.getName(), false, version));
                    continue block5;
                }
                case DISPLAY_NAME: {
                    if (info.previousinfo == null) continue block5;
                    datas.add(PlayerInfo.createData(info.previousinfo.getName(), false, version));
                    if (info.displayNameJson != null) {
                        datas.add(PlayerInfo.createData(info.getName(), true, version));
                        continue block5;
                    }
                    datas.add(PlayerInfo.createData(info.previousinfo.getUserName(), true, version));
                    break;
                }
            }
        }
        return datas;
    }

    static PacketData createData(String name, boolean add, ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_PLAYER_INFO_ID, version);
        serializer.writeString(Utils.clampString(name, 16));
        serializer.writeBoolean(add);
        serializer.writeShort(0);
        return serializer;
    }

}

