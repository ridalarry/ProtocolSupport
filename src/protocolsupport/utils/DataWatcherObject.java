/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.utils;

import java.util.EnumMap;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.utils.ProtocolVersionsHelper;

public class DataWatcherObject {
    public ValueType type;
    public Object value;

    public DataWatcherObject(ValueType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public void toByte() {
        this.type = ValueType.BYTE;
        this.value = Byte.valueOf(((Number)this.value).byteValue());
    }

    public void toShort() {
        this.type = ValueType.SHORT;
        this.value = ((Number)this.value).shortValue();
    }

    public void toInt() {
        this.type = ValueType.INT;
        this.value = ((Number)this.value).intValue();
    }

    public void toFloat() {
        this.type = ValueType.FLOAT;
        this.value = Float.valueOf(((Number)this.value).floatValue());
    }

    public String toString() {
        return "type: " + (Object)((Object)this.type) + " " + "value: " + this.value;
    }

    public static enum ValueType {
        BYTE(new TypeToProtocolsMappingEntry(0, ProtocolVersionsHelper.ALL)),
        SHORT(new TypeToProtocolsMappingEntry(1, ProtocolVersionsHelper.ALL)),
        INT(new TypeToProtocolsMappingEntry(2, ProtocolVersionsHelper.ALL)),
        FLOAT(new TypeToProtocolsMappingEntry(3, ProtocolVersionsHelper.ALL)),
        STRING(new TypeToProtocolsMappingEntry(4, ProtocolVersionsHelper.ALL)),
        ITEMSTACK(new TypeToProtocolsMappingEntry(5, ProtocolVersionsHelper.ALL)),
        VECTOR3I(new TypeToProtocolsMappingEntry(6, ProtocolVersionsHelper.BEFORE_1_9)),
        VECTOR3F(new TypeToProtocolsMappingEntry(7, ProtocolVersion.MINECRAFT_1_8));
        
        private static final EnumMap<ProtocolVersion, ValueType[]> TYPE_BY_PROTOCOL_AND_ID;
        private final EnumMap<ProtocolVersion, Integer> types = new EnumMap(ProtocolVersion.class);

        private /* varargs */ ValueType(TypeToProtocolsMappingEntry ... mappings) {
            for (TypeToProtocolsMappingEntry mapping : mappings) {
                for (ProtocolVersion version : mapping.versions) {
                    this.types.put(version, mapping.type);
                }
            }
        }

        public int getId(ProtocolVersion version) {
            Integer type = this.types.get((Object)version);
            if (type == null) {
                throw new IllegalArgumentException("Type id for protocol version " + (Object)((Object)version) + " doesn't exist for datawatcher valuetype " + (Object)((Object)this));
            }
            return type;
        }

        public static ValueType fromId(ProtocolVersion version, int id) {
            ValueType vtype = TYPE_BY_PROTOCOL_AND_ID.get((Object)version)[id];
            if (vtype == null) {
                throw new IllegalArgumentException("Datawatcher valuetype doesn't exist for protocol version " + (Object)((Object)version) + " and type id " + id);
            }
            return vtype;
        }

        static {
            TYPE_BY_PROTOCOL_AND_ID = new EnumMap(ProtocolVersion.class);
            for (ProtocolVersion version : ProtocolVersion.values()) {
                ValueType[] byId = new ValueType[256];
                for (ValueType vtype : ValueType.values()) {
                    if (!vtype.types.containsKey((Object)version)) continue;
                    byId[vtype.types.get((Object)version).intValue()] = vtype;
                }
                TYPE_BY_PROTOCOL_AND_ID.put(version, byId);
            }
        }

        private static final class TypeToProtocolsMappingEntry {
            private int type;
            private ProtocolVersion[] versions;

            public /* varargs */ TypeToProtocolsMappingEntry(int type, ProtocolVersion ... versions) {
                this.type = type;
                this.versions = versions;
            }
        }

    }

}

