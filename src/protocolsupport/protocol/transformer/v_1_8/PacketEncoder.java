/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.util.Attribute
 *  io.netty.util.AttributeKey
 *  net.minecraft.server.v1_8_R3.EnumProtocol
 *  net.minecraft.server.v1_8_R3.EnumProtocolDirection
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketDataSerializer
 *  net.minecraft.server.v1_8_R3.PacketListener
 */
package protocolsupport.protocol.transformer.v_1_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketListener;
import protocolsupport.protocol.core.IPacketEncoder;

public class PacketEncoder
implements IPacketEncoder {
    private static final EnumProtocolDirection direction = EnumProtocolDirection.CLIENTBOUND;
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;

    @Override
    public void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        Channel channel = ctx.channel();
        EnumProtocol currentProtocol = (EnumProtocol)channel.attr(currentStateAttrKey).get();
        Integer packetId = currentProtocol.a(direction, packet);
        if (packetId == null) {
            throw new IOException("Can't serialize unregistered packet");
        }
        PacketDataSerializer serializer = new PacketDataSerializer(output);
        serializer.b(packetId.intValue());
        packet.b(serializer);
    }
}

