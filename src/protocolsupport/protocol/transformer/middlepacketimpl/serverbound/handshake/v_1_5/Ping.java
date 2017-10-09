/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.Packet
 *  org.bukkit.Bukkit
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_5;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class Ping
extends ServerBoundMiddlePacket {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        serializer.readUnsignedByte();
    }

    public RecyclableCollection<Packet<?>> toNative() throws Exception {
        RecyclableArrayList packets = RecyclableArrayList.create();
        PacketCreator hsscreator = PacketCreator.create(ServerBoundPacket.HANDSHAKE_START.get());
        hsscreator.writeVarInt(ProtocolVersion.getLatest().getId());
        hsscreator.writeString("");
        hsscreator.writeShort(Bukkit.getPort());
        hsscreator.writeVarInt(1);
        packets.add(hsscreator.create());
        packets.add(ServerBoundPacket.STATUS_REQUEST.get());
        return packets;
    }
}

