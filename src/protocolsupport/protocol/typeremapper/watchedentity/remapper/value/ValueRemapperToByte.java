/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.remapper.value;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapper;
import protocolsupport.utils.DataWatcherObject;

public class ValueRemapperToByte
implements ValueRemapper {
    @Override
    public DataWatcherObject remap(DataWatcherObject object) {
        object.toByte();
        return object;
    }
}

