/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleMap;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.MapTransformer;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class Map
extends MiddleMap<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        RecyclableArrayList<PacketData> datas = RecyclableArrayList.create();
        PacketData scaledata = PacketData.create(ClientBoundPacket.PLAY_MAP_ID, version);
        scaledata.writeVarInt(this.itemData);
        scaledata.writeShort(2);
        scaledata.writeByte(2);
        scaledata.writeByte(this.scale);
        datas.add(scaledata);
        if (this.icons.length > 0) {
            PacketData iconsdata = PacketData.create(ClientBoundPacket.PLAY_MAP_ID, version);
            iconsdata.writeVarInt(this.itemData);
            iconsdata.writeShort(this.icons.length * 3 + 1);
            iconsdata.writeByte(1);
            for (MiddleMap.Icon icon : this.icons) {
                iconsdata.writeByte(icon.dirtype);
                iconsdata.writeByte(icon.x);
                iconsdata.writeByte(icon.z);
            }
            datas.add(iconsdata);
        }
        if (this.columns > 0) {
            MapTransformer maptransformer = new MapTransformer();
            maptransformer.loadFromNewMapData(this.columns, this.rows, this.xstart, this.zstart, this.data);
            for (MapTransformer.ColumnEntry entry : maptransformer.toPre18MapData()) {
                PacketData mapdata = PacketData.create(ClientBoundPacket.PLAY_MAP_ID, version);
                mapdata.writeVarInt(this.itemData);
                mapdata.writeShort(3 + entry.getColors().length);
                mapdata.writeByte(0);
                mapdata.writeByte(entry.getX());
                mapdata.writeByte(entry.getY());
                mapdata.writeBytes(entry.getColors());
                datas.add(mapdata);
            }
        }
        return datas;
    }
}

