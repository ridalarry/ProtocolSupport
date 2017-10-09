/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.ByteBufProcessor
 *  io.netty.buffer.SwappedByteBuf
 *  io.netty.util.ReferenceCounted
 *  io.netty.util.internal.StringUtil
 */
package protocolsupport.utils.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.SwappedByteBuf;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

public final class ReplayingDecoderBuffer
extends ByteBuf {
    public static final EOFSignal EOF = new EOFSignal();
    private ByteBuf buffer;
    private SwappedByteBuf swapped;

    public ReplayingDecoderBuffer() {
    }

    public ReplayingDecoderBuffer(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public void setCumulation(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public int capacity() {
        return Integer.MAX_VALUE;
    }

    public ByteBuf capacity(int newCapacity) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int maxCapacity() {
        return this.capacity();
    }

    public ByteBufAllocator alloc() {
        return this.buffer.alloc();
    }

    public boolean isDirect() {
        return this.buffer.isDirect();
    }

    public boolean hasArray() {
        return false;
    }

    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    public boolean hasMemoryAddress() {
        return false;
    }

    public long memoryAddress() {
        throw new UnsupportedOperationException();
    }

    public ByteBuf clear() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int compareTo(ByteBuf buffer) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf copy() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf copy(int index, int length) {
        this.checkIndex(index, length);
        return this.buffer.copy(index, length);
    }

    public ByteBuf discardReadBytes() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf ensureWritable(int writableBytes) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int ensureWritable(int minWritableBytes, boolean force) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf duplicate() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public boolean getBoolean(int index) {
        this.checkIndex(index, 1);
        return this.buffer.getBoolean(index);
    }

    public byte getByte(int index) {
        this.checkIndex(index, 1);
        return this.buffer.getByte(index);
    }

    public short getUnsignedByte(int index) {
        this.checkIndex(index, 1);
        return this.buffer.getUnsignedByte(index);
    }

    public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
        this.checkIndex(index, length);
        this.buffer.getBytes(index, dst, dstIndex, length);
        return this;
    }

    public ByteBuf getBytes(int index, byte[] dst) {
        this.checkIndex(index, dst.length);
        this.buffer.getBytes(index, dst);
        return this;
    }

    public ByteBuf getBytes(int index, ByteBuffer dst) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
        this.checkIndex(index, length);
        this.buffer.getBytes(index, dst, dstIndex, length);
        return this;
    }

    public ByteBuf getBytes(int index, ByteBuf dst, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf getBytes(int index, ByteBuf dst) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int getBytes(int index, GatheringByteChannel out, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf getBytes(int index, OutputStream out, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int getInt(int index) {
        this.checkIndex(index, 4);
        return this.buffer.getInt(index);
    }

    public long getUnsignedInt(int index) {
        this.checkIndex(index, 4);
        return this.buffer.getUnsignedInt(index);
    }

    public long getLong(int index) {
        this.checkIndex(index, 8);
        return this.buffer.getLong(index);
    }

    public int getMedium(int index) {
        this.checkIndex(index, 3);
        return this.buffer.getMedium(index);
    }

    public int getUnsignedMedium(int index) {
        this.checkIndex(index, 3);
        return this.buffer.getUnsignedMedium(index);
    }

    public short getShort(int index) {
        this.checkIndex(index, 2);
        return this.buffer.getShort(index);
    }

    public int getUnsignedShort(int index) {
        this.checkIndex(index, 2);
        return this.buffer.getUnsignedShort(index);
    }

    public char getChar(int index) {
        this.checkIndex(index, 2);
        return this.buffer.getChar(index);
    }

    public float getFloat(int index) {
        this.checkIndex(index, 4);
        return this.buffer.getFloat(index);
    }

    public double getDouble(int index) {
        this.checkIndex(index, 8);
        return this.buffer.getDouble(index);
    }

    public int hashCode() {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public int indexOf(int fromIndex, int toIndex, byte value) {
        if (fromIndex == toIndex) {
            return -1;
        }
        if (Math.max(fromIndex, toIndex) > this.buffer.writerIndex()) {
            throw EOF;
        }
        return this.buffer.indexOf(fromIndex, toIndex, value);
    }

    public int bytesBefore(byte value) {
        int bytes = this.buffer.bytesBefore(value);
        if (bytes < 0) {
            throw EOF;
        }
        return bytes;
    }

    public int bytesBefore(int length, byte value) {
        int readerIndex = this.buffer.readerIndex();
        return this.bytesBefore(readerIndex, this.buffer.writerIndex() - readerIndex, value);
    }

    public int bytesBefore(int index, int length, byte value) {
        int writerIndex = this.buffer.writerIndex();
        if (index >= writerIndex) {
            throw EOF;
        }
        if (index <= writerIndex - length) {
            return this.buffer.bytesBefore(index, length, value);
        }
        int res = this.buffer.bytesBefore(index, writerIndex - index, value);
        if (res < 0) {
            throw EOF;
        }
        return res;
    }

    public int forEachByte(ByteBufProcessor processor) {
        int ret = this.buffer.forEachByte(processor);
        if (ret < 0) {
            throw EOF;
        }
        return ret;
    }

    public int forEachByte(int index, int length, ByteBufProcessor processor) {
        int writerIndex = this.buffer.writerIndex();
        if (index >= writerIndex) {
            throw EOF;
        }
        if (index <= writerIndex - length) {
            return this.buffer.forEachByte(index, length, processor);
        }
        int ret = this.buffer.forEachByte(index, writerIndex - index, processor);
        if (ret < 0) {
            throw EOF;
        }
        return ret;
    }

    public int forEachByteDesc(ByteBufProcessor processor) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public int forEachByteDesc(int index, int length, ByteBufProcessor processor) {
        if (index + length > this.buffer.writerIndex()) {
            throw EOF;
        }
        return this.buffer.forEachByteDesc(index, length, processor);
    }

    public ByteBuf markReaderIndex() {
        this.buffer.markReaderIndex();
        return this;
    }

    public ByteBuf markWriterIndex() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteOrder order() {
        return this.buffer.order();
    }

    public ByteBuf order(ByteOrder endianness) {
        if (endianness == null) {
            throw new NullPointerException("endianness");
        }
        if (endianness == this.order()) {
            return this;
        }
        SwappedByteBuf swapped = this.swapped;
        if (swapped == null) {
            swapped = this.swapped = new SwappedByteBuf((ByteBuf)this);
        }
        return swapped;
    }

    public boolean isReadable() {
        return this.buffer.isReadable();
    }

    public boolean isReadable(int size) {
        return this.buffer.isReadable(size);
    }

    public int readableBytes() {
        return this.buffer.readableBytes();
    }

    public boolean readBoolean() {
        this.checkReadableBytes(1);
        return this.buffer.readBoolean();
    }

    public byte readByte() {
        this.checkReadableBytes(1);
        return this.buffer.readByte();
    }

    public short readUnsignedByte() {
        this.checkReadableBytes(1);
        return this.buffer.readUnsignedByte();
    }

    public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
        this.checkReadableBytes(length);
        this.buffer.readBytes(dst, dstIndex, length);
        return this;
    }

    public ByteBuf readBytes(byte[] dst) {
        this.checkReadableBytes(dst.length);
        this.buffer.readBytes(dst);
        return this;
    }

    public ByteBuf readBytes(ByteBuffer dst) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
        this.checkReadableBytes(length);
        this.buffer.readBytes(dst, dstIndex, length);
        return this;
    }

    public ByteBuf readBytes(ByteBuf dst, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf readBytes(ByteBuf dst) {
        this.checkReadableBytes(dst.writableBytes());
        this.buffer.readBytes(dst);
        return this;
    }

    public int readBytes(GatheringByteChannel out, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf readBytes(int length) {
        this.checkReadableBytes(length);
        return this.buffer.readBytes(length);
    }

    public ByteBuf readSlice(int length) {
        this.checkReadableBytes(length);
        return this.buffer.readSlice(length);
    }

    public ByteBuf readBytes(OutputStream out, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int readerIndex() {
        return this.buffer.readerIndex();
    }

    public ByteBuf readerIndex(int readerIndex) {
        this.buffer.readerIndex(readerIndex);
        return this;
    }

    public int readInt() {
        this.checkReadableBytes(4);
        return this.buffer.readInt();
    }

    public long readUnsignedInt() {
        this.checkReadableBytes(4);
        return this.buffer.readUnsignedInt();
    }

    public long readLong() {
        this.checkReadableBytes(8);
        return this.buffer.readLong();
    }

    public int readMedium() {
        this.checkReadableBytes(3);
        return this.buffer.readMedium();
    }

    public int readUnsignedMedium() {
        this.checkReadableBytes(3);
        return this.buffer.readUnsignedMedium();
    }

    public short readShort() {
        this.checkReadableBytes(2);
        return this.buffer.readShort();
    }

    public int readUnsignedShort() {
        this.checkReadableBytes(2);
        return this.buffer.readUnsignedShort();
    }

    public char readChar() {
        this.checkReadableBytes(2);
        return this.buffer.readChar();
    }

    public float readFloat() {
        this.checkReadableBytes(4);
        return this.buffer.readFloat();
    }

    public double readDouble() {
        this.checkReadableBytes(8);
        return this.buffer.readDouble();
    }

    public ByteBuf resetReaderIndex() {
        this.buffer.resetReaderIndex();
        return this;
    }

    public ByteBuf resetWriterIndex() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBoolean(int index, boolean value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setByte(int index, int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, byte[] src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, ByteBuffer src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, ByteBuf src, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setBytes(int index, ByteBuf src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int setBytes(int index, InputStream in, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf setZero(int index, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int setBytes(int index, ScatteringByteChannel in, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf setIndex(int readerIndex, int writerIndex) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setInt(int index, int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setLong(int index, long value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setMedium(int index, int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setShort(int index, int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setChar(int index, int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setFloat(int index, float value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf setDouble(int index, double value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf skipBytes(int length) {
        this.checkReadableBytes(length);
        this.buffer.skipBytes(length);
        return this;
    }

    public ByteBuf slice() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf slice(int index, int length) {
        this.checkIndex(index, length);
        return this.buffer.slice(index, length);
    }

    public int nioBufferCount() {
        return this.buffer.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        ReplayingDecoderBuffer.reject();
        return null;
    }

    public ByteBuffer nioBuffer(int index, int length) {
        this.checkIndex(index, length);
        return this.buffer.nioBuffer(index, length);
    }

    public ByteBuffer[] nioBuffers() {
        ReplayingDecoderBuffer.reject();
        return null;
    }

    public ByteBuffer[] nioBuffers(int index, int length) {
        this.checkIndex(index, length);
        return this.buffer.nioBuffers(index, length);
    }

    public ByteBuffer internalNioBuffer(int index, int length) {
        this.checkIndex(index, length);
        return this.buffer.internalNioBuffer(index, length);
    }

    public String toString(int index, int length, Charset charset) {
        this.checkIndex(index, length);
        return this.buffer.toString(index, length, charset);
    }

    public String toString(Charset charsetName) {
        ReplayingDecoderBuffer.reject();
        return null;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object)((Object)this)) + '(' + "ridx=" + this.readerIndex() + ", widx=" + this.writerIndex() + ')';
    }

    public boolean isWritable() {
        return false;
    }

    public boolean isWritable(int size) {
        return false;
    }

    public int writableBytes() {
        return 0;
    }

    public int maxWritableBytes() {
        return 0;
    }

    public ByteBuf writeBoolean(boolean value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeByte(int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(byte[] src, int srcIndex, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(byte[] src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(ByteBuffer src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(ByteBuf src, int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeBytes(ByteBuf src) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int writeBytes(InputStream in, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public int writeBytes(ScatteringByteChannel in, int length) {
        ReplayingDecoderBuffer.reject();
        return 0;
    }

    public ByteBuf writeInt(int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeLong(long value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeMedium(int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeZero(int length) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int writerIndex() {
        return this.buffer.writerIndex();
    }

    public ByteBuf writerIndex(int writerIndex) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeShort(int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeChar(int value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeFloat(float value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf writeDouble(double value) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    private void checkIndex(int index, int length) {
        if (index + length > this.buffer.writerIndex()) {
            throw EOF;
        }
    }

    private void checkReadableBytes(int readableBytes) {
        if (this.buffer.readableBytes() < readableBytes) {
            throw EOF;
        }
    }

    public ByteBuf discardSomeReadBytes() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public int refCnt() {
        return this.buffer.refCnt();
    }

    public ByteBuf retain() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public ByteBuf retain(int increment) {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    public boolean release() {
        ReplayingDecoderBuffer.reject();
        return false;
    }

    public boolean release(int decrement) {
        ReplayingDecoderBuffer.reject();
        return false;
    }

    public ByteBuf unwrap() {
        ReplayingDecoderBuffer.reject();
        return this;
    }

    private static void reject() {
        throw new UnsupportedOperationException("not a replayable operation");
    }

    public static final class EOFSignal
    extends RuntimeException {
        private static final long serialVersionUID = 1;

        @Override
        public EOFSignal fillInStackTrace() {
            return this;
        }
    }

}

