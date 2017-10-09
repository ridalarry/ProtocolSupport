/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeskipper.id;

import java.util.EnumMap;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeskipper.id.SkippingTable;

public abstract class SkippingRegistry {
    private final EnumMap<ProtocolVersion, SkippingTable> registry = new EnumMap(ProtocolVersion.class);

    public SkippingRegistry() {
        for (ProtocolVersion version : ProtocolVersion.values()) {
            this.registry.put(version, this.createTable());
        }
    }

    public SkippingTable getTable(ProtocolVersion version) {
        return this.registry.get((Object)version);
    }

    protected abstract SkippingTable createTable();

    protected /* varargs */ void registerSkipEntry(int id, ProtocolVersion ... versions) {
        for (ProtocolVersion version : versions) {
            this.registry.get((Object)((Object)version)).setSkip(id);
        }
    }
}

