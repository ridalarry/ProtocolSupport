/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.PooledByteBufAllocator
 *  io.netty.buffer.UnpooledByteBufAllocator
 */
package protocolsupport.utils.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import protocolsupport.ProtocolSupport;
import protocolsupport.utils.Utils;

public class Allocator {
    private static final boolean direct = Utils.getJavaPropertyValue("protocolsupport.buffer", true, new Utils.Converter<String, Boolean>(){

        @Override
        public Boolean convert(String t) {
            switch (t.toLowerCase()) {
                case "direct": {
                    return true;
                }
                case "heap": {
                    return false;
                }
            }
            throw new RuntimeException("Invalid buffer type " + t.toLowerCase() + ", should be either heap or direct");
        }
    });
    private static final ByteBufAllocator allocator = (ByteBufAllocator)Utils.getJavaPropertyValue("protocolsupport.allocator", PooledByteBufAllocator.DEFAULT, new Utils.Converter<String, ByteBufAllocator>(){

        @Override
        public ByteBufAllocator convert(String t) {
            switch (t.toLowerCase()) {
                case "pooled": {
                    return PooledByteBufAllocator.DEFAULT;
                }
                case "unpooled": {
                    return UnpooledByteBufAllocator.DEFAULT;
                }
            }
            throw new IllegalArgumentException("Invalid allocator type " + t.toLowerCase() + ", should be either pooled or unpooled");
        }
    });

    public static void init() {
        ProtocolSupport.logInfo("Allocator: " + (Object)allocator + ", direct: " + direct);
    }

    public static ByteBuf allocateBuffer() {
        if (direct) {
            return allocator.directBuffer();
        }
        return allocator.heapBuffer();
    }

    public static ByteBuf allocateUnpooledBuffer() {
        if (direct) {
            return UnpooledByteBufAllocator.DEFAULT.directBuffer();
        }
        return UnpooledByteBufAllocator.DEFAULT.heapBuffer();
    }

}

