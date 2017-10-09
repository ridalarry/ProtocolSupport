/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.Packet
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class PositionLook
extends ServerBoundMiddlePacket {
    protected double x;
    protected double y;
    protected double yhead;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;

    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.x = serializer.readDouble();
        this.y = serializer.readDouble();
        this.yhead = serializer.readDouble();
        this.z = serializer.readDouble();
        this.yaw = serializer.readFloat();
        this.pitch = serializer.readFloat();
        this.onGround = serializer.readBoolean();
    }

    public RecyclableCollection<Packet<?>> toNative() throws Exception {
        if (this.y == -999.0 && this.yhead == -999.0) {
            PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_LOOK.get());
            creator.writeFloat(this.yaw);
            creator.writeFloat(this.pitch);
            creator.writeBoolean(this.onGround);
            return RecyclableSingletonList.create(creator.create());
        }
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_POSITION_LOOK.get());
        creator.writeDouble(this.x);
        creator.writeDouble(this.y);
        creator.writeDouble(this.z);
        creator.writeFloat(this.yaw);
        creator.writeFloat(this.pitch);
        creator.writeBoolean(this.onGround);
        return RecyclableSingletonList.create(creator.create());
    }
}

