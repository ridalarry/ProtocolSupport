/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.util.Recycler
 *  io.netty.util.Recycler$Handle
 */
package protocolsupport.protocol.transformer.middlepacketimpl;

import io.netty.buffer.ByteBuf;
import io.netty.util.Recycler;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.utils.netty.Allocator;

public class PacketData
extends PacketDataSerializer {
    private static final Recycler<PacketData> RECYCLER = new Recycler<PacketData>(){

        protected PacketData newObject(Recycler.Handle handle) {
            return new PacketData(handle);
        }
    };
    private final Recycler.Handle handle;
    private int packetId;

    public static PacketData create(int packetId, ProtocolVersion version) {
        PacketData packetdata = (PacketData)((Object)RECYCLER.get());
        packetdata.packetId = packetId;
        packetdata.setVersion(version);
        return packetdata;
    }

    private PacketData(Recycler.Handle handle) {
        super(Allocator.allocateUnpooledBuffer());
        this.handle = handle;
    }

    public void recycle() {
        this.packetId = 0;
        this.clear();
        RECYCLER.recycle(this, handle);
    }

    public int getPacketId() {
        return this.packetId;
    }

    protected void finalize() {
        this.release();
    }

}

