/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleExplosion<T>
extends ClientBoundMiddlePacket<T> {
    protected float x;
    protected float y;
    protected float z;
    protected float radius;
    protected AffectedBlock[] blocks;
    protected float pMotX;
    protected float pMotY;
    protected float pMotZ;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.x = serializer.readFloat();
        this.y = serializer.readFloat();
        this.z = serializer.readFloat();
        this.radius = serializer.readFloat();
        this.blocks = new AffectedBlock[serializer.readInt()];
        for (int i = 0; i < this.blocks.length; ++i) {
            AffectedBlock block = new AffectedBlock();
            block.offX = serializer.readByte();
            block.offY = serializer.readByte();
            block.offZ = serializer.readByte();
            this.blocks[i] = block;
        }
        this.pMotX = serializer.readFloat();
        this.pMotY = serializer.readFloat();
        this.pMotZ = serializer.readFloat();
    }

    protected static class AffectedBlock {
        public int offX;
        public int offY;
        public int offZ;

        protected AffectedBlock() {
        }
    }

}

