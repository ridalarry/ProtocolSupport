/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.properties.Property
 *  gnu.trove.map.TIntObjectMap
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import com.mojang.authlib.properties.Property;
import gnu.trove.map.TIntObjectMap;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedPlayer;
import protocolsupport.utils.DataWatcherObject;
import protocolsupport.utils.DataWatcherSerializer;
import protocolsupport.utils.netty.ChannelUtils;

public abstract class MiddleSpawnNamed<T>
extends ClientBoundMiddlePacket<T> {
    protected int playerEntityId;
    protected UUID uuid;
    protected String name;
    protected int x;
    protected int y;
    protected int z;
    protected int yaw;
    protected int pitch;
    protected int itemId;
    protected List<Property> properties;
    protected WatchedEntity wplayer;
    protected TIntObjectMap<DataWatcherObject> metadata;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.playerEntityId = serializer.readVarInt();
        this.uuid = serializer.g();
        this.x = serializer.readInt();
        this.y = serializer.readInt();
        this.z = serializer.readInt();
        this.yaw = serializer.readUnsignedByte();
        this.pitch = serializer.readUnsignedByte();
        this.itemId = serializer.readUnsignedShort();
        this.metadata = DataWatcherSerializer.decodeData(ProtocolVersion.MINECRAFT_1_8, ChannelUtils.toArray((ByteBuf)serializer));
    }

    @Override
    public void handle() {
        this.wplayer = new WatchedPlayer(this.playerEntityId);
        this.storage.addWatchedEntity(this.wplayer);
        LocalStorage.PlayerListEntry entry = this.storage.getPlayerListEntry(this.uuid);
        if (entry != null) {
            this.name = entry.getUserName();
            this.properties = entry.getProperties().getAll(true);
        } else {
            this.name = "Unknown";
            this.properties = Collections.emptyList();
        }
    }
}

