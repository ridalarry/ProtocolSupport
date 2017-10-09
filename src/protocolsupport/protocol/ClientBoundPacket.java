/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  net.minecraft.server.v1_8_R3.EnumProtocol
 *  net.minecraft.server.v1_8_R3.EnumProtocolDirection
 *  net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect
 *  net.minecraft.server.v1_8_R3.PacketLoginOutEncryptionBegin
 *  net.minecraft.server.v1_8_R3.PacketLoginOutSuccess
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAbilities
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutBed
 *  net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction
 *  net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange
 *  net.minecraft.server.v1_8_R3.PacketPlayOutChat
 *  net.minecraft.server.v1_8_R3.PacketPlayOutCloseWindow
 *  net.minecraft.server.v1_8_R3.PacketPlayOutCollect
 *  net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutEntityLook
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMove
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutExperience
 *  net.minecraft.server.v1_8_R3.PacketPlayOutExplosion
 *  net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange
 *  net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive
 *  net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect
 *  net.minecraft.server.v1_8_R3.PacketPlayOutLogin
 *  net.minecraft.server.v1_8_R3.PacketPlayOutMap
 *  net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk
 *  net.minecraft.server.v1_8_R3.PacketPlayOutMapChunkBulk
 *  net.minecraft.server.v1_8_R3.PacketPlayOutMultiBlockChange
 *  net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn
 *  net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect
 *  net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor
 *  net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect
 *  net.minecraft.server.v1_8_R3.PacketPlayOutResourcePackSend
 *  net.minecraft.server.v1_8_R3.PacketPlayOutRespawn
 *  net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective
 *  net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective
 *  net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore
 *  net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityExperienceOrb
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityPainting
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayOutStatistic
 *  net.minecraft.server.v1_8_R3.PacketPlayOutTabComplete
 *  net.minecraft.server.v1_8_R3.PacketPlayOutTileEntityData
 *  net.minecraft.server.v1_8_R3.PacketPlayOutTransaction
 *  net.minecraft.server.v1_8_R3.PacketPlayOutUpdateAttributes
 *  net.minecraft.server.v1_8_R3.PacketPlayOutUpdateHealth
 *  net.minecraft.server.v1_8_R3.PacketPlayOutUpdateSign
 *  net.minecraft.server.v1_8_R3.PacketPlayOutUpdateTime
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWindowData
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWindowItems
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent
 *  net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles
 *  net.minecraft.server.v1_8_R3.PacketStatusOutPong
 *  net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol;

import com.google.common.collect.BiMap;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect;
import net.minecraft.server.v1_8_R3.PacketLoginOutEncryptionBegin;
import net.minecraft.server.v1_8_R3.PacketLoginOutSuccess;
import net.minecraft.server.v1_8_R3.PacketPlayOutAbilities;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutBed;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_8_R3.PacketPlayOutCollect;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity;
import net.minecraft.server.v1_8_R3.PacketPlayOutExperience;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import net.minecraft.server.v1_8_R3.PacketPlayOutLogin;
import net.minecraft.server.v1_8_R3.PacketPlayOutMap;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunkBulk;
import net.minecraft.server.v1_8_R3.PacketPlayOutMultiBlockChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutResourcePackSend;
import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityExperienceOrb;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityPainting;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutStatistic;
import net.minecraft.server.v1_8_R3.PacketPlayOutTabComplete;
import net.minecraft.server.v1_8_R3.PacketPlayOutTileEntityData;
import net.minecraft.server.v1_8_R3.PacketPlayOutTransaction;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateAttributes;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateHealth;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateSign;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateTime;
import net.minecraft.server.v1_8_R3.PacketPlayOutWindowData;
import net.minecraft.server.v1_8_R3.PacketPlayOutWindowItems;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PacketStatusOutPong;
import net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo;
import org.spigotmc.SneakyThrow;
import protocolsupport.utils.Utils;

public class ClientBoundPacket {
    public static final int LOGIN_DISCONNECT_ID = ClientBoundPacket.getId(PacketLoginOutDisconnect.class);
    public static final int LOGIN_ENCRYPTION_BEGIN_ID = ClientBoundPacket.getId(PacketLoginOutEncryptionBegin.class);
    public static final int LOGIN_SUCCESS_ID = ClientBoundPacket.getId(PacketLoginOutSuccess.class);
    public static final int STATUS_SERVER_INFO_ID = ClientBoundPacket.getId(PacketStatusOutServerInfo.class);
    public static final int STATUS_PONG_ID = ClientBoundPacket.getId(PacketStatusOutPong.class);
    public static final int PLAY_KEEP_ALIVE_ID = ClientBoundPacket.getId(PacketPlayOutKeepAlive.class);
    public static final int PLAY_LOGIN_ID = ClientBoundPacket.getId(PacketPlayOutLogin.class);
    public static final int PLAY_CHAT_ID = ClientBoundPacket.getId(PacketPlayOutChat.class);
    public static final int PLAY_UPDATE_TIME_ID = ClientBoundPacket.getId(PacketPlayOutUpdateTime.class);
    public static final int PLAY_ENTITY_EQUIPMENT_ID = ClientBoundPacket.getId(PacketPlayOutEntityEquipment.class);
    public static final int PLAY_SPAWN_POSITION_ID = ClientBoundPacket.getId(PacketPlayOutSpawnPosition.class);
    public static final int PLAY_UPDATE_HEALTH_ID = ClientBoundPacket.getId(PacketPlayOutUpdateHealth.class);
    public static final int PLAY_RESPAWN_ID = ClientBoundPacket.getId(PacketPlayOutRespawn.class);
    public static final int PLAY_POSITION_ID = ClientBoundPacket.getId(PacketPlayOutPosition.class);
    public static final int PLAY_HELD_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutHeldItemSlot.class);
    public static final int PLAY_BED_ID = ClientBoundPacket.getId(PacketPlayOutBed.class);
    public static final int PLAY_ANIMATION_ID = ClientBoundPacket.getId(PacketPlayOutAnimation.class);
    public static final int PLAY_SPAWN_NAMED_ID = ClientBoundPacket.getId(PacketPlayOutNamedEntitySpawn.class);
    public static final int PLAY_COLLECT_EFFECT_ID = ClientBoundPacket.getId(PacketPlayOutCollect.class);
    public static final int PLAY_SPAWN_OBJECT_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntity.class);
    public static final int PLAY_SPAWN_LIVING_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityLiving.class);
    public static final int PLAY_SPAWN_PAINTING_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityPainting.class);
    public static final int PLAY_SPAWN_EXP_ORB_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityExperienceOrb.class);
    public static final int PLAY_ENTITY_VELOCITY_ID = ClientBoundPacket.getId(PacketPlayOutEntityVelocity.class);
    public static final int PLAY_ENTITY_DESTROY_ID = ClientBoundPacket.getId(PacketPlayOutEntityDestroy.class);
    public static final int PLAY_ENTITY_ID = ClientBoundPacket.getId(PacketPlayOutEntity.class);
    public static final int PLAY_ENTITY_REL_MOVE_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutRelEntityMove.class);
    public static final int PLAY_ENTITY_LOOK_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutEntityLook.class);
    public static final int PLAY_ENTITY_REL_MOVE_LOOK_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class);
    public static final int PLAY_ENTITY_TELEPORT_ID = ClientBoundPacket.getId(PacketPlayOutEntityTeleport.class);
    public static final int PLAY_ENTITY_HEAD_ROTATION_ID = ClientBoundPacket.getId(PacketPlayOutEntityHeadRotation.class);
    public static final int PLAY_ENTITY_STATUS_ID = ClientBoundPacket.getId(PacketPlayOutEntityStatus.class);
    public static final int PLAY_ENTITY_ATTACH_ID = ClientBoundPacket.getId(PacketPlayOutAttachEntity.class);
    public static final int PLAY_ENTITY_METADATA_ID = ClientBoundPacket.getId(PacketPlayOutEntityMetadata.class);
    public static final int PLAY_ENTITY_EFFECT_ADD_ID = ClientBoundPacket.getId(PacketPlayOutEntityEffect.class);
    public static final int PLAY_ENTITY_EFFECT_REMOVE_ID = ClientBoundPacket.getId(PacketPlayOutRemoveEntityEffect.class);
    public static final int PLAY_EXPERIENCE_ID = ClientBoundPacket.getId(PacketPlayOutExperience.class);
    public static final int PLAY_ENTITY_ATTRIBUTES_ID = ClientBoundPacket.getId(PacketPlayOutUpdateAttributes.class);
    public static final int PLAY_CHUNK_SINGLE_ID = ClientBoundPacket.getId(PacketPlayOutMapChunk.class);
    public static final int PLAY_BLOCK_CHANGE_MULTI_ID = ClientBoundPacket.getId(PacketPlayOutMultiBlockChange.class);
    public static final int PLAY_BLOCK_CHANGE_SINGLE_ID = ClientBoundPacket.getId(PacketPlayOutBlockChange.class);
    public static final int PLAY_BLOCK_ACTION_ID = ClientBoundPacket.getId(PacketPlayOutBlockAction.class);
    public static final int PLAY_BLOCK_BREAK_ANIMATION_ID = ClientBoundPacket.getId(PacketPlayOutBlockBreakAnimation.class);
    public static final int PLAY_CHUNK_MULTI_ID = ClientBoundPacket.getId(PacketPlayOutMapChunkBulk.class);
    public static final int PLAY_EXPLOSION_ID = ClientBoundPacket.getId(PacketPlayOutExplosion.class);
    public static final int PLAY_WORLD_EVENT_ID = ClientBoundPacket.getId(PacketPlayOutWorldEvent.class);
    public static final int PLAY_WORLD_SOUND_ID = ClientBoundPacket.getId(PacketPlayOutNamedSoundEffect.class);
    public static final int PLAY_WORLD_PARTICLES_ID = ClientBoundPacket.getId(PacketPlayOutWorldParticles.class);
    public static final int PLAY_GAME_STATE_CHANGE_ID = ClientBoundPacket.getId(PacketPlayOutGameStateChange.class);
    public static final int PLAY_SPAWN_WEATHER_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityWeather.class);
    public static final int PLAY_WINDOW_OPEN_ID = ClientBoundPacket.getId(PacketPlayOutOpenWindow.class);
    public static final int PLAY_WINDOW_CLOSE_ID = ClientBoundPacket.getId(PacketPlayOutCloseWindow.class);
    public static final int PLAY_WINDOW_SET_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutSetSlot.class);
    public static final int PLAY_WINDOW_SET_ITEMS_ID = ClientBoundPacket.getId(PacketPlayOutWindowItems.class);
    public static final int PLAY_WINDOW_DATA_ID = ClientBoundPacket.getId(PacketPlayOutWindowData.class);
    public static final int PLAY_WINDOW_TRANSACTION_ID = ClientBoundPacket.getId(PacketPlayOutTransaction.class);
    public static final int PLAY_UPDATE_SIGN_ID = ClientBoundPacket.getId(PacketPlayOutUpdateSign.class);
    public static final int PLAY_MAP_ID = ClientBoundPacket.getId(PacketPlayOutMap.class);
    public static final int PLAY_UPDATE_TILE_ID = ClientBoundPacket.getId(PacketPlayOutTileEntityData.class);
    public static final int PLAY_SIGN_EDITOR_ID = ClientBoundPacket.getId(PacketPlayOutOpenSignEditor.class);
    public static final int PLAY_STATISTICS = ClientBoundPacket.getId(PacketPlayOutStatistic.class);
    public static final int PLAY_PLAYER_INFO_ID = ClientBoundPacket.getId(PacketPlayOutPlayerInfo.class);
    public static final int PLAY_ABILITIES_ID = ClientBoundPacket.getId(PacketPlayOutAbilities.class);
    public static final int PLAY_TAB_COMPLETE_ID = ClientBoundPacket.getId(PacketPlayOutTabComplete.class);
    public static final int PLAY_SCOREBOARD_OBJECTIVE_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardObjective.class);
    public static final int PLAY_SCOREBOARD_SCORE_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardScore.class);
    public static final int PLAY_SCOREBOARD_DISPLAY_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardDisplayObjective.class);
    public static final int PLAY_SCOREBOARD_TEAM_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardTeam.class);
    public static final int PLAY_CUSTOM_PAYLOAD_ID = ClientBoundPacket.getId(PacketPlayOutCustomPayload.class);
    public static final int PLAY_KICK_DISCONNECT_ID = ClientBoundPacket.getId(PacketPlayOutKickDisconnect.class);
    public static final int PLAY_RESOURCE_PACK_ID = ClientBoundPacket.getId(PacketPlayOutResourcePackSend.class);

    public static void init() {
    }

    private static final int getId(Class<?> packetClass) {
        Map protocolMap = null;
        try {
            protocolMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("h")).get(null);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        EnumProtocol protocol = (EnumProtocol)protocolMap.get(packetClass);
        Map idMap = null;
        try {
            idMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("j")).get((Object)protocol);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        return (Integer)((BiMap)idMap.get((Object)EnumProtocolDirection.CLIENTBOUND)).inverse().get(packetClass);
    }
}

