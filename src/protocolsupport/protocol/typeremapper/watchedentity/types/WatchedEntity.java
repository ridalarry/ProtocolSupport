/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.types;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;

public abstract class WatchedEntity {
    private int id;

    public WatchedEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public abstract SpecificType getType();

    public String toString() {
        return new StringBuilder(100).append("Id: ").append(this.getId()).append(", ").append("Type: ").append((Object)((Object)this.getType())).toString();
    }
}

