/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleMap<T>
extends ClientBoundMiddlePacket<T> {
    protected int itemData;
    protected int scale;
    protected Icon[] icons;
    protected int columns;
    protected int rows;
    protected int xstart;
    protected int zstart;
    protected byte[] data;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.itemData = serializer.readVarInt();
        this.scale = serializer.readUnsignedByte();
        this.icons = new Icon[serializer.readVarInt()];
        for (int i = 0; i < this.icons.length; ++i) {
            Icon icon = new Icon();
            icon.dirtype = serializer.readUnsignedByte();
            icon.x = serializer.readUnsignedByte();
            icon.z = serializer.readUnsignedByte();
            this.icons[i] = icon;
        }
        this.columns = serializer.readUnsignedByte();
        if (this.columns > 0) {
            this.rows = serializer.readUnsignedByte();
            this.xstart = serializer.readUnsignedByte();
            this.zstart = serializer.readUnsignedByte();
            this.data = serializer.readArray();
        }
    }

    protected static class Icon {
        public int dirtype;
        public int x;
        public int z;

        protected Icon() {
        }
    }

}

