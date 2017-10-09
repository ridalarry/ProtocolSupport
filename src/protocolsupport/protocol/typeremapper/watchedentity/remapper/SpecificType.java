/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.EntityType
 */
package protocolsupport.protocol.typeremapper.watchedentity.remapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.EntityType;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.RemappingEntry;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapper;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapperStringClamp;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapperToByte;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapperToInt;
import protocolsupport.utils.DataWatcherObject;
import protocolsupport.utils.ProtocolVersionsHelper;

public enum SpecificType {
    NONE(EType.NONE, -1, new RemappingEntriesForProtocols[0]),
    ENTITY(EType.NONE, -1, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(0, 1)).addProtocols(ProtocolVersionsHelper.ALL)),
    LIVING(EType.NONE, -1, ENTITY, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(2, 3)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(2, 10, new ValueRemapperStringClamp(64)), new RemappingEntry(3, 11)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry(2, 5, new ValueRemapperStringClamp(64)), new RemappingEntry(3, 6)).addProtocols(ProtocolVersionsHelper.BEFORE_1_6), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(6)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_8, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(7), new RemappingEntry.RemappingEntryCopyOriginal(8), new RemappingEntry.RemappingEntryCopyOriginal(9)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry(7, 8), new RemappingEntry(8, 9), new RemappingEntry(9, 10)).addProtocols(ProtocolVersionsHelper.BEFORE_1_6), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(15)).addProtocols(ProtocolVersion.MINECRAFT_1_8)),
    PLAYER(EType.NONE, -1, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(17, 18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(10)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_8, ProtocolVersion.MINECRAFT_1_6_1))),
    AGEABLE(EType.NONE, -1, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(12)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(12, 12, new ValueRemapperToInt())).addProtocols(ProtocolVersionsHelper.BEFORE_1_8)),
    TAMEABLE(EType.NONE, -1, AGEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16), new RemappingEntry.RemappingEntryCopyOriginal(17)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ARMOR_STAND(EType.NONE, -1, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(10, 11, 12, 13, 14, 15, 16)).addProtocols(ProtocolVersion.MINECRAFT_1_8)),
    COW(EType.MOB, EntityType.COW, AGEABLE, new RemappingEntriesForProtocols[0]),
    MUSHROOM_COW(EType.MOB, EntityType.MUSHROOM_COW, COW, new RemappingEntriesForProtocols[0]),
    CHICKEN(EType.MOB, EntityType.CHICKEN, AGEABLE, new RemappingEntriesForProtocols[0]),
    SQUID(EType.MOB, EntityType.SQUID, LIVING, new RemappingEntriesForProtocols[0]),
    HORSE(EType.MOB, EntityType.HORSE, AGEABLE, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(16, 19, 20, 21, 22)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    BAT(EType.MOB, EntityType.BAT, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    OCELOT(EType.MOB, EntityType.OCELOT, TAMEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    WOLF(EType.MOB, EntityType.WOLF, TAMEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(19)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(20)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(20, 20, new ValueRemapper(){

        @Override
        public DataWatcherObject remap(DataWatcherObject object) {
            object.value = Byte.valueOf((byte)(15 - ((Byte)object.value).byteValue()));
            return object;
        }
    })).addProtocols(ProtocolVersionsHelper.BEFORE_1_8), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(18)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_8, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry(18, 18, new ValueRemapperToInt())).addProtocols(ProtocolVersionsHelper.BEFORE_1_6)),
    PIG(EType.MOB, EntityType.PIG, AGEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    RABBIT(EType.MOB, EntityType.RABBIT, AGEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    SHEEP(EType.MOB, EntityType.SHEEP, AGEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    VILLAGER(EType.MOB, EntityType.VILLAGER, AGEABLE, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ENDERMAN(EType.MOB, EntityType.ENDERMAN, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(17, 18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(16, 16, new ValueRemapperToByte())).addProtocols(ProtocolVersionsHelper.BEFORE_1_8)),
    GIANT(EType.MOB, EntityType.GIANT, LIVING, new RemappingEntriesForProtocols[0]),
    SILVERFISH(EType.MOB, EntityType.SILVERFISH, LIVING, new RemappingEntriesForProtocols[0]),
    ENDERMITE(EType.MOB, EntityType.ENDERMITE, SILVERFISH, new RemappingEntriesForProtocols[0]),
    ENDER_DRAGON(EType.MOB, EntityType.ENDER_DRAGON, LIVING, new RemappingEntriesForProtocols[0]),
    SNOWMAN(EType.MOB, EntityType.SNOWMAN, LIVING, new RemappingEntriesForProtocols[0]),
    ZOMBIE(EType.MOB, EntityType.ZOMBIE, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(12, 13, 14)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ZOMBIE_PIGMAN(EType.MOB, EntityType.PIG_ZOMBIE, ZOMBIE, new RemappingEntriesForProtocols[0]),
    BLAZE(EType.MOB, EntityType.BLAZE, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    SPIDER(EType.MOB, EntityType.SPIDER, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    CAVE_SPIDER(EType.MOB, EntityType.CAVE_SPIDER, SPIDER, new RemappingEntriesForProtocols[0]),
    CREEPER(EType.MOB, EntityType.CREEPER, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(16, 17, 18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    GHAST(EType.MOB, EntityType.GHAST, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    SLIME(EType.MOB, EntityType.SLIME, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    MAGMA_CUBE(EType.MOB, EntityType.MAGMA_CUBE, SLIME, new RemappingEntriesForProtocols[0]),
    SKELETON(EType.MOB, EntityType.SKELETON, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(13)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    WITCH(EType.MOB, EntityType.WITCH, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(21)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    IRON_GOLEM(EType.MOB, EntityType.IRON_GOLEM, LIVING, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    WITHER(EType.MOB, EntityType.WITHER, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(17, 18, 19, 20)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    GUARDIAN(EType.MOB, EntityType.GUARDIAN, LIVING, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(16, 17)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ARMOR_STAND_MOB(EType.MOB, EntityType.ARMOR_STAND, ARMOR_STAND, new RemappingEntriesForProtocols[0]),
    BOAT(EType.OBJECT, 1, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(17, 18)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(19)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_8, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry(19, 19, new ValueRemapperToInt())).addProtocols(ProtocolVersionsHelper.BEFORE_1_6)),
    TNT(EType.OBJECT, 50, ENTITY, new RemappingEntriesForProtocols[0]),
    SNOWBALL(EType.OBJECT, 61, ENTITY, new RemappingEntriesForProtocols[0]),
    EGG(EType.OBJECT, 62, ENTITY, new RemappingEntriesForProtocols[0]),
    FIREBALL(EType.OBJECT, 63, ENTITY, new RemappingEntriesForProtocols[0]),
    FIRECHARGE(EType.OBJECT, 64, ENTITY, new RemappingEntriesForProtocols[0]),
    ENDERPEARL(EType.OBJECT, 65, ENTITY, new RemappingEntriesForProtocols[0]),
    WITHER_SKULL(EType.OBJECT, 66, FIREBALL, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(10)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    FALLING_OBJECT(EType.OBJECT, 70, ENTITY, new RemappingEntriesForProtocols[0]),
    ENDEREYE(EType.OBJECT, 72, ENTITY, new RemappingEntriesForProtocols[0]),
    POTION(EType.OBJECT, 73, ENTITY, new RemappingEntriesForProtocols[0]),
    DRAGON_EGG(EType.OBJECT, 74, ENTITY, new RemappingEntriesForProtocols[0]),
    EXP_BOTTLE(EType.OBJECT, 75, ENTITY, new RemappingEntriesForProtocols[0]),
    LEASH_KNOT(EType.OBJECT, 77, ENTITY, new RemappingEntriesForProtocols[0]),
    FISHING_FLOAT(EType.OBJECT, 90, ENTITY, new RemappingEntriesForProtocols[0]),
    ITEM(EType.OBJECT, 2, ENTITY, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(10)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    MINECART(EType.OBJECT, 10, ENTITY, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(16, 17, 18, 21, 22)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(19)).addProtocols(ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_8, ProtocolVersion.MINECRAFT_1_6_1)), new RemappingEntriesForProtocols(new RemappingEntry(19, 19, new ValueRemapperToInt())).addProtocols(ProtocolVersionsHelper.BEFORE_1_6), new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(20)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(20, 20, new ValueRemapper(){

        @Override
        public DataWatcherObject remap(DataWatcherObject object) {
            int value = (Integer)object.value;
            int id = value & 65535;
            int data = value >> 12;
            object.value = data << 16 | id;
            return object;
        }
    })).addProtocols(ProtocolVersionsHelper.BEFORE_1_6)),
    ARROW(EType.OBJECT, 60, ENTITY, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(16)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    FIREWORK(EType.OBJECT, 76, ENTITY, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(8)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ITEM_FRAME(EType.OBJECT, 71, ENTITY, new RemappingEntriesForProtocols(RemappingEntry.RemappingEntryCopyOriginal.of(8, 9)).addProtocols(ProtocolVersion.MINECRAFT_1_8), new RemappingEntriesForProtocols(new RemappingEntry(8, 2), new RemappingEntry(9, 3, new ValueRemapper(){

        @Override
        public DataWatcherObject remap(DataWatcherObject object) {
            int rotation = ((Byte)object.value).byteValue();
            object.value = Byte.valueOf((byte)(rotation >>= 1));
            return object;
        }
    })).addProtocols(ProtocolVersionsHelper.BEFORE_1_8)),
    ENDER_CRYSTAL(EType.OBJECT, 51, ENTITY, new RemappingEntriesForProtocols(new RemappingEntry.RemappingEntryCopyOriginal(8)).addProtocols(ProtocolVersionsHelper.BEFORE_1_9)),
    ARMOR_STAND_OBJECT(EType.OBJECT, 78, ARMOR_STAND, new RemappingEntriesForProtocols[0]);
    
    private static final SpecificType[] OBJECT_BY_TYPE_ID;
    private static final SpecificType[] MOB_BY_TYPE_ID;
    private final EType etype;
    private final int typeId;
    private final EnumMap<ProtocolVersion, ArrayList<RemappingEntry>> entries = new EnumMap(ProtocolVersion.class);

    public static SpecificType getObjectByTypeId(int objectTypeId) {
        return OBJECT_BY_TYPE_ID[objectTypeId];
    }

    public static SpecificType getMobByTypeId(int mobTypeId) {
        return MOB_BY_TYPE_ID[mobTypeId];
    }

    private /* varargs */ SpecificType(EType etype, EntityType type, RemappingEntriesForProtocols ... entries) {
        this(etype, (int)type.getTypeId(), entries);
    }

    private /* varargs */ SpecificType(EType etype, int typeId, RemappingEntriesForProtocols ... entries) {
        for (ProtocolVersion version : ProtocolVersion.values()) {
            this.entries.put(version, new ArrayList());
        }
        this.etype = etype;
        this.typeId = typeId;
        for (RemappingEntriesForProtocols rp : entries) {
            for (ProtocolVersion version : ((RemappingEntriesForProtocols)((Object)rp)).versions) {
                this.entries.get((Object)version).addAll(Arrays.asList(((RemappingEntriesForProtocols)((Object)rp)).entries));
            }
        }
    }

    private /* varargs */ SpecificType(EType etype, EntityType type, SpecificType superType, RemappingEntriesForProtocols ... entries) {
        this(etype, (int)type.getTypeId(), superType, entries);
    }

    private /* varargs */ SpecificType(EType etype, int typeId, SpecificType superType, RemappingEntriesForProtocols ... entries) {
        for (ProtocolVersion version : ProtocolVersion.values()) {
            this.entries.put(version, new ArrayList());
        }
        this.etype = etype;
        this.typeId = typeId;
        for (Map.Entry entry : superType.entries.entrySet()) {
            this.entries.get(entry.getKey()).addAll((Collection)entry.getValue());
        }
        for (RemappingEntriesForProtocols rp : entries) {
            for (ProtocolVersion version : rp.versions) {
                this.entries.get((Object)version).addAll(Arrays.asList(rp.entries));
            }
        }
    }

    public List<RemappingEntry> getRemaps(ProtocolVersion version) {
        return this.entries.get((Object)version);
    }

    static {
        OBJECT_BY_TYPE_ID = new SpecificType[256];
        MOB_BY_TYPE_ID = new SpecificType[256];
        Arrays.fill((Object[])OBJECT_BY_TYPE_ID, (Object)NONE);
        Arrays.fill((Object[])MOB_BY_TYPE_ID, (Object)NONE);
        block4 : for (SpecificType type : SpecificType.values()) {
            switch (type.etype) {
                case OBJECT: {
                    SpecificType.OBJECT_BY_TYPE_ID[type.typeId] = type;
                    continue block4;
                }
                case MOB: {
                    SpecificType.MOB_BY_TYPE_ID[type.typeId] = type;
                    break;
                }
            }
        }
    }

    private static class RemappingEntriesForProtocols {
        private ArrayList<ProtocolVersion> versions = new ArrayList();
        private RemappingEntry[] entries;

        protected /* varargs */ RemappingEntriesForProtocols(RemappingEntry ... entries) {
            this.entries = entries;
        }

        protected /* varargs */ RemappingEntriesForProtocols addProtocols(ProtocolVersion ... versions) {
            this.versions.addAll(Arrays.asList(versions));
            return this;
        }
    }

    private static enum EType {
        NONE,
        OBJECT,
        MOB;
        

        private EType() {
        }
    }

}

