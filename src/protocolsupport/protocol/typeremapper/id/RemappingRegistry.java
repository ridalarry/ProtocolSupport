/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.id;

import java.util.EnumMap;
import java.util.Map;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.id.RemappingTable;

public abstract class RemappingRegistry {
    private final EnumMap<ProtocolVersion, RemappingTable> remappings = new EnumMap(ProtocolVersion.class);

    public RemappingRegistry() {
        for (ProtocolVersion version : ProtocolVersion.values()) {
            this.remappings.put(version, this.createTable());
        }
    }

    public RemappingTable getTable(ProtocolVersion version) {
        return this.remappings.get((Object)version);
    }

    protected void copy(RemappingRegistry other) {
        this.remappings.clear();
        this.remappings.putAll(other.remappings);
    }

    protected abstract RemappingTable createTable();

    protected /* varargs */ void registerRemapEntry(int from, int to, ProtocolVersion ... versions) {
        for (ProtocolVersion version : versions) {
            this.remappings.get((Object)((Object)version)).setRemap(from, to);
        }
    }
}

