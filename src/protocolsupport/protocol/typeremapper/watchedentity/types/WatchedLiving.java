/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.types;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;

public class WatchedLiving
extends WatchedEntity {
    private final SpecificType stype;

    public WatchedLiving(int id, int type) {
        super(id);
        this.stype = SpecificType.getMobByTypeId(type);
    }

    @Override
    public SpecificType getType() {
        return this.stype;
    }
}

