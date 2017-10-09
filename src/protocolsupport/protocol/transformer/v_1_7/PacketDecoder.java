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
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  org.bukkit.entity.Player
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol.transformer.v_1_7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import org.spigotmc.SneakyThrow;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.core.IPacketDecoder;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_7.SetProtocol;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.login.v_1_4_1_5_1_6_1_7.EncryptionResponse;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.login.v_1_7.LoginStart;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.Animation;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.BlockDig;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.BlockPlace;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.Chat;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.CreativeSetSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.Flying;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.HeldSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.InventoryClick;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.InventoryClose;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.InventoryEnchant;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.InventoryTransaction;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.KeepAlive;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.Look;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.Position;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.TabComplete;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7.UpdateSign;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7.EntityAction;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7.PlayerAbilities;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7.PositionLook;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_6_1_7.SteerVehicle;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7.ClientCommand;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7.ClientSettings;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7.CustomPayload;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_7.UseEntity;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.status.v_1_7.Ping;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.status.v_1_7.ServerInfoRequest;
import protocolsupport.protocol.transformer.utils.registry.MiddleTransformerRegistry;
import protocolsupport.utils.netty.ChannelUtils;
import protocolsupport.utils.netty.WrappingBuffer;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class PacketDecoder
implements IPacketDecoder {
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;
    private final MiddleTransformerRegistry<ServerBoundMiddlePacket> registry = new MiddleTransformerRegistry();
    private final WrappingBuffer buffer;
    private final PacketDataSerializer serializer;

    public PacketDecoder(ProtocolVersion version) {
        try {
            this.registry.register(EnumProtocol.HANDSHAKING, 0, SetProtocol.class);
            this.registry.register(EnumProtocol.LOGIN, 0, LoginStart.class);
            this.registry.register(EnumProtocol.LOGIN, 1, EncryptionResponse.class);
            this.registry.register(EnumProtocol.STATUS, 0, ServerInfoRequest.class);
            this.registry.register(EnumProtocol.STATUS, 1, Ping.class);
            this.registry.register(EnumProtocol.PLAY, 0, KeepAlive.class);
            this.registry.register(EnumProtocol.PLAY, 1, Chat.class);
            this.registry.register(EnumProtocol.PLAY, 2, UseEntity.class);
            this.registry.register(EnumProtocol.PLAY, 3, Flying.class);
            this.registry.register(EnumProtocol.PLAY, 4, Position.class);
            this.registry.register(EnumProtocol.PLAY, 5, Look.class);
            this.registry.register(EnumProtocol.PLAY, 6, PositionLook.class);
            this.registry.register(EnumProtocol.PLAY, 7, BlockDig.class);
            this.registry.register(EnumProtocol.PLAY, 8, BlockPlace.class);
            this.registry.register(EnumProtocol.PLAY, 9, HeldSlot.class);
            this.registry.register(EnumProtocol.PLAY, 10, Animation.class);
            this.registry.register(EnumProtocol.PLAY, 11, EntityAction.class);
            this.registry.register(EnumProtocol.PLAY, 12, SteerVehicle.class);
            this.registry.register(EnumProtocol.PLAY, 13, InventoryClose.class);
            this.registry.register(EnumProtocol.PLAY, 14, InventoryClick.class);
            this.registry.register(EnumProtocol.PLAY, 15, InventoryTransaction.class);
            this.registry.register(EnumProtocol.PLAY, 16, CreativeSetSlot.class);
            this.registry.register(EnumProtocol.PLAY, 17, InventoryEnchant.class);
            this.registry.register(EnumProtocol.PLAY, 18, UpdateSign.class);
            this.registry.register(EnumProtocol.PLAY, 19, PlayerAbilities.class);
            this.registry.register(EnumProtocol.PLAY, 20, TabComplete.class);
            this.registry.register(EnumProtocol.PLAY, 21, ClientSettings.class);
            this.registry.register(EnumProtocol.PLAY, 22, ClientCommand.class);
            this.registry.register(EnumProtocol.PLAY, 23, CustomPayload.class);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.buffer = new WrappingBuffer();
        this.serializer = new PacketDataSerializer(this.buffer, version);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        if (!input.isReadable()) {
            return;
        }
        Channel channel = ctx.channel();
        EnumProtocol currentProtocol = (EnumProtocol)channel.attr(currentStateAttrKey).get();
        this.buffer.setBuf(input);
        int packetId = this.serializer.readVarInt();
        ServerBoundMiddlePacket packetTransformer = this.registry.getTransformer(currentProtocol, packetId);
        if (packetTransformer != null) {
            if (packetTransformer.needsPlayer()) {
                packetTransformer.setPlayer(ChannelUtils.getBukkitPlayer(channel));
            }
            packetTransformer.readFromClientData(this.serializer);
            RecyclableCollection collection = packetTransformer.toNative();
            try {
                list.addAll(collection);
            }
            finally {
                collection.recycle();
            }
        }
    }
}

