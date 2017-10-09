/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.types;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;

public class WatchedObject
extends WatchedEntity {
    private final SpecificType stype;

    public WatchedObject(int id, int type) {
        super(id);
        this.stype = SpecificType.getObjectByTypeId(type);
    }

    @Override
    public SpecificType getType() {
        return this.stype;
    }
}

