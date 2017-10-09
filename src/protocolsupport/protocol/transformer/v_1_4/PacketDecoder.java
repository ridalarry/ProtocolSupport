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
package protocolsupport.protocol.transformer.v_1_4;

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
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_4_1_5_1_6.ClientLogin;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.login.v_1_4_1_5_1_6_1_7.EncryptionResponse;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5.EntityAction;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5.PlayerAbilities;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5.PositionLook;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6.ClientCommand;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6.ClientSettings;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6.CustomPayload;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6.KickDisconnect;
import protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6.UseEntity;
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
import protocolsupport.protocol.transformer.utils.registry.MiddleTransformerRegistry;
import protocolsupport.utils.netty.ChannelUtils;
import protocolsupport.utils.netty.ReplayingDecoderBuffer;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class PacketDecoder
implements IPacketDecoder {
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;
    private final MiddleTransformerRegistry<ServerBoundMiddlePacket> dataRemapperRegistry = new MiddleTransformerRegistry();
    private final ReplayingDecoderBuffer buffer;
    private final PacketDataSerializer serializer;

    public PacketDecoder() {
        try {
            this.dataRemapperRegistry.register(EnumProtocol.HANDSHAKING, 2, ClientLogin.class);
            this.dataRemapperRegistry.register(EnumProtocol.LOGIN, 252, EncryptionResponse.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 0, KeepAlive.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 3, Chat.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 7, UseEntity.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 10, Flying.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 11, Position.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 12, Look.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 13, PositionLook.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 14, BlockDig.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 15, BlockPlace.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 16, HeldSlot.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 18, Animation.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 19, EntityAction.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 101, InventoryClose.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 102, InventoryClick.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 106, InventoryTransaction.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 107, CreativeSetSlot.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 108, InventoryEnchant.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 130, UpdateSign.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 203, TabComplete.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 202, PlayerAbilities.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 204, ClientSettings.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 205, ClientCommand.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 250, CustomPayload.class);
            this.dataRemapperRegistry.register(EnumProtocol.PLAY, 255, KickDisconnect.class);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.buffer = new ReplayingDecoderBuffer();
        this.serializer = new PacketDataSerializer(this.buffer, ProtocolVersion.MINECRAFT_1_4_7);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        block7 : {
            if (!input.isReadable()) {
                return;
            }
            this.buffer.setCumulation(input);
            this.serializer.markReaderIndex();
            Channel channel = ctx.channel();
            EnumProtocol currentProtocol = (EnumProtocol)channel.attr(currentStateAttrKey).get();
            try {
                short packetId = this.serializer.readUnsignedByte();
                ServerBoundMiddlePacket packetTransformer = this.dataRemapperRegistry.getTransformer(currentProtocol, packetId);
                if (packetTransformer == null) break block7;
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
            catch (ReplayingDecoderBuffer.EOFSignal ex) {
                this.serializer.resetReaderIndex();
            }
        }
    }
}

