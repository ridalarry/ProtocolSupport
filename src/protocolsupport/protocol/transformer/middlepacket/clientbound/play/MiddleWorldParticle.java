/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import java.util.ArrayList;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleWorldParticle<T>
extends ClientBoundMiddlePacket<T> {
    protected int type;
    protected boolean longdist;
    protected float x;
    protected float y;
    protected float z;
    protected float offX;
    protected float offY;
    protected float offZ;
    protected float speed;
    protected int count;
    protected ArrayList<Integer> adddata = new ArrayList();

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.type = serializer.readInt();
        this.longdist = serializer.readBoolean();
        this.x = serializer.readFloat();
        this.y = serializer.readFloat();
        this.z = serializer.readFloat();
        this.offX = serializer.readFloat();
        this.offY = serializer.readFloat();
        this.offZ = serializer.readFloat();
        this.speed = serializer.readFloat();
        this.count = serializer.readInt();
        this.adddata.clear();
        while (serializer.isReadable()) {
            this.adddata.add(serializer.readVarInt());
        }
    }
}

