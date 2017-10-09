/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.properties.Property
 *  gnu.trove.map.hash.TIntObjectHashMap
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer
 */
package protocolsupport.protocol.storage;

import com.mojang.authlib.properties.Property;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedPlayer;

public class LocalStorage {
    private final TIntObjectHashMap<WatchedEntity> watchedEntities = new TIntObjectHashMap();
    private WatchedPlayer player;
    private final HashMap<UUID, PlayerListEntry> playerlist = new HashMap();

    public void addWatchedEntity(WatchedEntity entity) {
		watchedEntities.put(entity.getId(), entity);
    }

    public void addWatchedSelfPlayer(WatchedPlayer player) {
        this.player = player;
        this.addWatchedEntity(player);
    }

    private void readdSelfPlayer() {
        if (this.player != null) {
            this.addWatchedEntity(this.player);
        }
    }

    public WatchedEntity getWatchedEntity(int entityId) {
        return (WatchedEntity)this.watchedEntities.get(entityId);
    }

    public void removeWatchedEntities(int[] entityIds) {
        for (int entityId : entityIds) {
            this.watchedEntities.remove(entityId);
        }
        this.readdSelfPlayer();
    }

    public void clearWatchedEntities() {
        this.watchedEntities.clear();
        this.readdSelfPlayer();
    }

    public void addPlayerListEntry(UUID uuid, PlayerListEntry entry) {
        this.playerlist.put(uuid, entry);
    }

    public PlayerListEntry getPlayerListEntry(UUID uuid) {
        return this.playerlist.get(uuid);
    }

    public void removePlayerListEntry(UUID uuid) {
        this.playerlist.remove(uuid);
    }

    public static class PropertiesStorage {
        private final HashMap<String, Property> signed = new HashMap();
        private final HashMap<String, Property> unsigned = new HashMap();

        public void add(Property property) {
            if (property.hasSignature()) {
                this.signed.put(property.getName(), property);
            } else {
                this.unsigned.put(property.getName(), property);
            }
        }

        public List<Property> getAll(boolean signedOnly) {
            if (signedOnly) {
                return new ArrayList<Property>(this.signed.values());
            }
            ArrayList<Property> properties = new ArrayList<Property>();
            properties.addAll(this.signed.values());
            properties.addAll(this.unsigned.values());
            return properties;
        }
    }

    public static class PlayerListEntry
    implements Cloneable {
        private final String name;
        private String displayNameJson;
        private final PropertiesStorage propstorage = new PropertiesStorage();

        public PlayerListEntry(String name) {
            this.name = name;
        }

        public void setDisplayNameJson(String displayNameJson) {
            this.displayNameJson = displayNameJson;
        }

        public PropertiesStorage getProperties() {
            return this.propstorage;
        }

        public String getUserName() {
            return this.name;
        }

        public String getName() {
            return this.displayNameJson == null ? this.name : LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)this.displayNameJson));
        }

        public PlayerListEntry clone() {
            PlayerListEntry clone = new PlayerListEntry(this.name);
            clone.displayNameJson = this.displayNameJson;
            for (Property property : this.propstorage.getAll(false)) {
                clone.propstorage.add(property);
            }
            return clone;
        }
    }

}

