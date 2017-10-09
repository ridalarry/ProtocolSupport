/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeskipper.id;

public class SkippingTable {
    protected final boolean[] table;

    public SkippingTable(int size) {
        this.table = new boolean[size];
        for (int i = 0; i < this.table.length; ++i) {
            this.table[i] = false;
        }
    }

    public void setSkip(int id) {
        this.table[id] = true;
    }

    public boolean shouldSkip(int id) {
        return this.table[id];
    }
}

