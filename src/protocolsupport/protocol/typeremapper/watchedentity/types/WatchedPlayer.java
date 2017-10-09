/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.types;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;

public class WatchedPlayer
extends WatchedEntity {
    public WatchedPlayer(int id) {
        super(id);
    }

    @Override
    public SpecificType getType() {
        return SpecificType.PLAYER;
    }
}

