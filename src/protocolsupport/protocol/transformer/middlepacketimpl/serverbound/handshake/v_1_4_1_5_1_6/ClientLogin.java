/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.Packet
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class ClientLogin
extends ServerBoundMiddlePacket {
    protected String username;
    protected String hostname;
    protected int port;

    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        serializer.readUnsignedByte();
        this.username = serializer.readString(16);
        this.hostname = serializer.readString(32767);
        this.port = serializer.readInt();
    }

    public RecyclableCollection<Packet<?>> toNative() throws Exception {
        RecyclableArrayList packets = RecyclableArrayList.create();
        PacketCreator hsscreator = PacketCreator.create(ServerBoundPacket.HANDSHAKE_START.get());
        hsscreator.writeVarInt(ProtocolVersion.getLatest().getId());
        hsscreator.writeString(this.hostname);
        hsscreator.writeShort(this.port);
        hsscreator.writeVarInt(2);
        packets.add(hsscreator.create());
        PacketCreator lscreator = PacketCreator.create(ServerBoundPacket.LOGIN_START.get());
        lscreator.writeString(this.username);
        packets.add(lscreator.create());
        return packets;
    }
}

