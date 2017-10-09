/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.watchedentity.remapper;

import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapper;

public class RemappingEntry {
    protected int from;
    protected int to;
    protected ValueRemapper vremap = ValueRemapper.NO_OP;

    protected RemappingEntry(int from, int to) {
        this.from = from;
        this.to = to;
    }

    protected RemappingEntry(int from, int to, ValueRemapper vremap) {
        this(from, to);
        this.vremap = vremap;
    }

    public int getIdFrom() {
        return this.from;
    }

    public int getIdTo() {
        return this.to;
    }

    public ValueRemapper getValueRemapper() {
        return this.vremap;
    }

    public static class RemappingEntryCopyOriginal
    extends RemappingEntry {
        public RemappingEntryCopyOriginal(int id) {
            super(id, id);
        }

        public static /* varargs */ RemappingEntryCopyOriginal[] of(int ... ids) {
            RemappingEntryCopyOriginal[] entries = new RemappingEntryCopyOriginal[ids.length];
            for (int i = 0; i < entries.length; ++i) {
                entries[i] = new RemappingEntryCopyOriginal(ids[i]);
            }
            return entries;
        }
    }

}

