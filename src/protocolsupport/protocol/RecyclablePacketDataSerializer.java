/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.util.Recycler
 *  io.netty.util.Recycler$Handle
 */
package protocolsupport.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.util.Recycler;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.utils.netty.Allocator;

public class RecyclablePacketDataSerializer
extends PacketDataSerializer {
    private static final Recycler<RecyclablePacketDataSerializer> RECYCLER = new Recycler<RecyclablePacketDataSerializer>(){

        protected RecyclablePacketDataSerializer newObject(Recycler.Handle handle) {
            return new RecyclablePacketDataSerializer(handle);
        }
    };
    private final Recycler.Handle handle;

    public static RecyclablePacketDataSerializer create(ProtocolVersion version) {
        RecyclablePacketDataSerializer serializer = (RecyclablePacketDataSerializer)((Object)RECYCLER.get());
        serializer.setVersion(version);
        return serializer;
    }

    public static RecyclablePacketDataSerializer create(ProtocolVersion version, byte[] data) {
        RecyclablePacketDataSerializer serializer = RecyclablePacketDataSerializer.create(version);
        serializer.writeBytes(data);
        return serializer;
    }

    private RecyclablePacketDataSerializer(Recycler.Handle handle) {
        super(Allocator.allocateUnpooledBuffer());
        this.handle = handle;
    }

    public boolean release(int count) {
        return this.release();
    }

    public boolean release() {
        this.recycle();
        return true;
    }

    private void recycle() {
        this.clear();
        RECYCLER.recycle(this, handle);
    }

    protected void finalize() {
        super.release();
    }

}

