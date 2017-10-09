/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.utils;

import java.util.ArrayList;

public class MapTransformer {
    private byte[] colors = new byte[16384];
    private int columnStart;
    private int columnEnd;
    private int rowStart;
    private int rowEnd;

    public void loadFromNewMapData(int columns, int rows, int xstart, int ystart, byte[] data) {
        for (int column = 0; column < columns; ++column) {
            for (int row = 0; row < rows; ++row) {
                this.colors[xstart + column + (ystart + row) * 128] = data[column + row * columns];
            }
        }
        this.columnStart = xstart;
        this.columnEnd = xstart + columns;
        this.rowStart = ystart;
        this.rowEnd = ystart + rows;
    }

    public ArrayList<ColumnEntry> toPre18MapData() {
        ArrayList<ColumnEntry> entries = new ArrayList<ColumnEntry>();
        for (int column = this.columnStart; column < this.columnEnd; ++column) {
            ColumnEntry entry = new ColumnEntry(column, this.rowStart, this.rowEnd - this.rowStart);
            for (int row = this.rowStart; row < this.rowEnd; ++row) {
                ColumnEntry.access$000((ColumnEntry)entry)[row - this.rowStart] = this.colors[row * 128 + column];
            }
            entries.add(entry);
        }
        return entries;
    }

    public static class ColumnEntry {
        private int x;
        private int y;
        private byte[] colors;

        public ColumnEntry(int x, int y, int rows) {
            this.x = x;
            this.y = y;
            this.colors = new byte[rows];
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public byte[] getColors() {
            return this.colors;
        }

        static /* synthetic */ byte[] access$000(ColumnEntry x0) {
            return x0.colors;
        }
    }

}

