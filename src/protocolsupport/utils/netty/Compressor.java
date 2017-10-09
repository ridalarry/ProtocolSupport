/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.util.Recycler
 *  io.netty.util.Recycler$Handle
 */
package protocolsupport.utils.netty;

import io.netty.util.Recycler;
import java.util.Arrays;
import java.util.zip.Deflater;
import protocolsupport.ProtocolSupport;
import protocolsupport.utils.Utils;

public class Compressor {
    private static final int compressionLevel = Utils.getJavaPropertyValue("protocolsupport.compressionlevel", 3, Utils.Converter.STRING_TO_INT);
    private static final Recycler<Compressor> recycler = new Recycler<Compressor>(){

        protected Compressor newObject(Recycler.Handle handle) {
            return new Compressor(handle);
        }
    };
    private final Deflater deflater = new Deflater(compressionLevel);
    private final Recycler.Handle handle;

    public static void init() {
        ProtocolSupport.logInfo("Compression level: " + compressionLevel);
    }

    public static Compressor create() {
        return (Compressor)recycler.get();
    }

    protected Compressor(Recycler.Handle handle) {
        this.handle = handle;
    }

    public byte[] compress(byte[] input) {
        this.deflater.setInput(input);
        this.deflater.finish();
        byte[] compressedBuf = new byte[input.length * 11 / 10 + 6];
        int size = this.deflater.deflate(compressedBuf);
        this.deflater.reset();
        return Arrays.copyOf(compressedBuf, size);
    }

    public void recycle() {
        recycler.recycle(this, handle);
    }

    public static byte[] compressStatic(byte[] input) {
        Compressor compressor = Compressor.create();
        try {
            byte[] arrby = compressor.compress(input);
            return arrby;
        }
        finally {
            compressor.recycle();
        }
    }

}

