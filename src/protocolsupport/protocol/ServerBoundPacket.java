/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  net.minecraft.server.v1_8_R3.EnumProtocol
 *  net.minecraft.server.v1_8_R3.EnumProtocolDirection
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol
 *  net.minecraft.server.v1_8_R3.PacketLoginInEncryptionBegin
 *  net.minecraft.server.v1_8_R3.PacketLoginInStart
 *  net.minecraft.server.v1_8_R3.PacketPlayInAbilities
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInChat
 *  net.minecraft.server.v1_8_R3.PacketPlayInClientCommand
 *  net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow
 *  net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload
 *  net.minecraft.server.v1_8_R3.PacketPlayInEnchantItem
 *  net.minecraft.server.v1_8_R3.PacketPlayInEntityAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInLook
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInPositionLook
 *  net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive
 *  net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayInSettings
 *  net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle
 *  net.minecraft.server.v1_8_R3.PacketPlayInTabComplete
 *  net.minecraft.server.v1_8_R3.PacketPlayInTransaction
 *  net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInWindowClick
 *  net.minecraft.server.v1_8_R3.PacketStatusInPing
 *  net.minecraft.server.v1_8_R3.PacketStatusInStart
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol;

import com.google.common.collect.BiMap;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol;
import net.minecraft.server.v1_8_R3.PacketLoginInEncryptionBegin;
import net.minecraft.server.v1_8_R3.PacketLoginInStart;
import net.minecraft.server.v1_8_R3.PacketPlayInAbilities;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInChat;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayInEnchantItem;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot;
import net.minecraft.server.v1_8_R3.PacketPlayInSettings;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInTabComplete;
import net.minecraft.server.v1_8_R3.PacketPlayInTransaction;
import net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import net.minecraft.server.v1_8_R3.PacketStatusInPing;
import net.minecraft.server.v1_8_R3.PacketStatusInStart;
import org.spigotmc.SneakyThrow;
import protocolsupport.utils.Utils;

public enum ServerBoundPacket {
    HANDSHAKE_START(PacketHandshakingInSetProtocol.class),
    STATUS_REQUEST(PacketStatusInStart.class),
    STATUS_PING(PacketStatusInPing.class),
    LOGIN_START(PacketLoginInStart.class),
    LOGIN_ENCRYPTION_BEGIN(PacketLoginInEncryptionBegin.class),
    PLAY_KEEP_ALIVE(PacketPlayInKeepAlive.class),
    PLAY_CHAT(PacketPlayInChat.class),
    PLAY_USE_ENTITY(PacketPlayInUseEntity.class),
    PLAY_PLAYER(PacketPlayInFlying.class),
    PLAY_POSITION(PacketPlayInFlying.PacketPlayInPosition.class),
    PLAY_LOOK(PacketPlayInFlying.PacketPlayInLook.class),
    PLAY_POSITION_LOOK(PacketPlayInFlying.PacketPlayInPositionLook.class),
    PLAY_BLOCK_DIG(PacketPlayInBlockDig.class),
    PLAY_BLOCK_PLACE(PacketPlayInBlockPlace.class),
    PLAY_HELD_SLOT(PacketPlayInHeldItemSlot.class),
    PLAY_ANIMATION(PacketPlayInArmAnimation.class),
    PLAY_ENTITY_ACTION(PacketPlayInEntityAction.class),
    PLAY_STEER_VEHICLE(PacketPlayInSteerVehicle.class),
    PLAY_WINDOW_CLOSE(PacketPlayInCloseWindow.class),
    PLAY_WINDOW_CLICK(PacketPlayInWindowClick.class),
    PLAY_WINDOW_TRANSACTION(PacketPlayInTransaction.class),
    PLAY_CREATIVE_SET_SLOT(PacketPlayInSetCreativeSlot.class),
    PLAY_ENCHANT_SELECT(PacketPlayInEnchantItem.class),
    PLAY_UPDATE_SIGN(PacketPlayInUpdateSign.class),
    PLAY_ABILITIES(PacketPlayInAbilities.class),
    PLAY_TAB_COMPLETE(PacketPlayInTabComplete.class),
    PLAY_SETTINGS(PacketPlayInSettings.class),
    PLAY_CLIENT_COMMAND(PacketPlayInClientCommand.class),
    PLAY_CUSTOM_PAYLOAD(PacketPlayInCustomPayload.class);
    
    private final int id;
    private final EnumProtocol protocol;

    public static void init() {
    }

    private ServerBoundPacket(Class<? extends Packet<?>> packetClass) {
        Map protocolMap = null;
        try {
            protocolMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("h")).get(null);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.protocol = (EnumProtocol)protocolMap.get(packetClass);
        Map idMap = null;
        try {
            idMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("j")).get((Object)this.protocol);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.id = (Integer)((BiMap)idMap.get((Object)EnumProtocolDirection.SERVERBOUND)).inverse().get(packetClass);
    }

    public Packet<?> get() throws IllegalAccessException, InstantiationException {
        return this.protocol.a(EnumProtocolDirection.SERVERBOUND, this.id);
    }

    public int getId() {
        return this.id;
    }
}

