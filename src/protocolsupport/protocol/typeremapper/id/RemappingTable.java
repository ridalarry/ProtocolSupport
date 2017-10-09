/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.id;

public class RemappingTable {
    protected final int[] table;

    public RemappingTable(int size) {
        this.table = new int[size];
        int i = 0;
        while (i < this.table.length) {
            this.table[i] = i++;
        }
    }

    public void setRemap(int from, int to) {
        this.table[from] = to;
    }

    public int getRemap(int id) {
        return this.table[id];
    }
}

