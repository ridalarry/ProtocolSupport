/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.ByteBufProcessor
 *  io.netty.util.ReferenceCounted
 */
package protocolsupport.utils.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

public class WrappingBuffer
extends ByteBuf {
    protected ByteBuf buf;

    public void setBuf(ByteBuf buf) {
        this.buf = buf;
    }

    public int capacity() {
        return this.buf.capacity();
    }

    public ByteBuf capacity(int i) {
        return this.buf.capacity(i);
    }

    public int maxCapacity() {
        return this.buf.maxCapacity();
    }

    public ByteBufAllocator alloc() {
        return this.buf.alloc();
    }

    public ByteOrder order() {
        return this.buf.order();
    }

    public ByteBuf order(ByteOrder byteorder) {
        return this.buf.order(byteorder);
    }

    public ByteBuf unwrap() {
        return this.buf.unwrap();
    }

    public boolean isDirect() {
        return this.buf.isDirect();
    }

    public int readerIndex() {
        return this.buf.readerIndex();
    }

    public ByteBuf readerIndex(int i) {
        return this.buf.readerIndex(i);
    }

    public int writerIndex() {
        return this.buf.writerIndex();
    }

    public ByteBuf writerIndex(int writerIndex) {
        return this.buf.writerIndex(writerIndex);
    }

    public ByteBuf setIndex(int readerIndex, int writerIndex) {
        return this.buf.setIndex(readerIndex, writerIndex);
    }

    public int readableBytes() {
        return this.buf.readableBytes();
    }

    public int writableBytes() {
        return this.buf.writableBytes();
    }

    public int maxWritableBytes() {
        return this.buf.maxWritableBytes();
    }

    public boolean isReadable() {
        return this.buf.isReadable();
    }

    public boolean isReadable(int size) {
        return this.buf.isReadable(size);
    }

    public boolean isWritable() {
        return this.buf.isWritable();
    }

    public boolean isWritable(int i) {
        return this.buf.isWritable(i);
    }

    public ByteBuf clear() {
        return this.buf.clear();
    }

    public ByteBuf markReaderIndex() {
        return this.buf.markReaderIndex();
    }

    public ByteBuf resetReaderIndex() {
        return this.buf.resetReaderIndex();
    }

    public ByteBuf markWriterIndex() {
        return this.buf.markWriterIndex();
    }

    public ByteBuf resetWriterIndex() {
        return this.buf.resetWriterIndex();
    }

    public ByteBuf discardReadBytes() {
        return this.buf.discardReadBytes();
    }

    public ByteBuf discardSomeReadBytes() {
        return this.buf.discardSomeReadBytes();
    }

    public ByteBuf ensureWritable(int size) {
        return this.buf.ensureWritable(size);
    }

    public int ensureWritable(int size, boolean flag) {
        return this.buf.ensureWritable(size, flag);
    }

    public boolean getBoolean(int index) {
        return this.buf.getBoolean(index);
    }

    public byte getByte(int index) {
        return this.buf.getByte(index);
    }

    public short getUnsignedByte(int index) {
        return this.buf.getUnsignedByte(index);
    }

    public short getShort(int index) {
        return this.buf.getShort(index);
    }

    public int getUnsignedShort(int index) {
        return this.buf.getUnsignedShort(index);
    }

    public int getMedium(int index) {
        return this.buf.getMedium(index);
    }

    public int getUnsignedMedium(int index) {
        return this.buf.getUnsignedMedium(index);
    }

    public int getInt(int index) {
        return this.buf.getInt(index);
    }

    public long getUnsignedInt(int index) {
        return this.buf.getUnsignedInt(index);
    }

    public long getLong(int index) {
        return this.buf.getLong(index);
    }

    public char getChar(int index) {
        return this.buf.getChar(index);
    }

    public float getFloat(int index) {
        return this.buf.getFloat(index);
    }

    public double getDouble(int index) {
        return this.buf.getDouble(index);
    }

    public ByteBuf getBytes(int index, ByteBuf bytebuf) {
        return this.buf.getBytes(index, bytebuf);
    }

    public ByteBuf getBytes(int index, ByteBuf bytebuf, int length) {
        return this.buf.getBytes(index, bytebuf, length);
    }

    public ByteBuf getBytes(int index, ByteBuf bytebuf, int bytebufindex, int length) {
        return this.buf.getBytes(index, bytebuf, bytebufindex, length);
    }

    public ByteBuf getBytes(int index, byte[] abyte) {
        return this.buf.getBytes(index, abyte);
    }

    public ByteBuf getBytes(int index, byte[] abyte, int abyteindex, int length) {
        return this.buf.getBytes(index, abyte, abyteindex, length);
    }

    public ByteBuf getBytes(int index, ByteBuffer bytebuffer) {
        return this.buf.getBytes(index, bytebuffer);
    }

    public ByteBuf getBytes(int index, OutputStream outputstream, int length) throws IOException {
        return this.buf.getBytes(index, outputstream, length);
    }

    public int getBytes(int index, GatheringByteChannel gatheringbytechannel, int length) throws IOException {
        return this.buf.getBytes(index, gatheringbytechannel, length);
    }

    public ByteBuf setBoolean(int index, boolean b) {
        return this.buf.setBoolean(index, b);
    }

    public ByteBuf setByte(int index, int b) {
        return this.buf.setByte(index, b);
    }

    public ByteBuf setShort(int index, int s) {
        return this.buf.setShort(index, s);
    }

    public ByteBuf setMedium(int index, int m) {
        return this.buf.setMedium(index, m);
    }

    public ByteBuf setInt(int index, int i) {
        return this.buf.setInt(index, i);
    }

    public ByteBuf setLong(int index, long l) {
        return this.buf.setLong(index, l);
    }

    public ByteBuf setChar(int index, int c) {
        return this.buf.setChar(index, c);
    }

    public ByteBuf setFloat(int index, float f) {
        return this.buf.setFloat(index, f);
    }

    public ByteBuf setDouble(int index, double d) {
        return this.buf.setDouble(index, d);
    }

    public ByteBuf setBytes(int index, ByteBuf bytebuf) {
        return this.buf.setBytes(index, bytebuf);
    }

    public ByteBuf setBytes(int index, ByteBuf bytebuf, int length) {
        return this.buf.setBytes(index, bytebuf, length);
    }

    public ByteBuf setBytes(int index, ByteBuf bytebuf, int bytebufindex, int length) {
        return this.buf.setBytes(index, bytebuf, bytebufindex, length);
    }

    public ByteBuf setBytes(int index, byte[] abyte) {
        return this.buf.setBytes(index, abyte);
    }

    public ByteBuf setBytes(int index, byte[] abyte, int abyteindex, int length) {
        return this.buf.setBytes(index, abyte, abyteindex, length);
    }

    public ByteBuf setBytes(int index, ByteBuffer bytebuffer) {
        return this.buf.setBytes(index, bytebuffer);
    }

    public int setBytes(int index, InputStream inputstream, int length) throws IOException {
        return this.buf.setBytes(index, inputstream, length);
    }

    public int setBytes(int index, ScatteringByteChannel scatteringbytechannel, int length) throws IOException {
        return this.buf.setBytes(index, scatteringbytechannel, length);
    }

    public ByteBuf setZero(int index, int length) {
        return this.buf.setZero(index, length);
    }

    public boolean readBoolean() {
        return this.buf.readBoolean();
    }

    public byte readByte() {
        return this.buf.readByte();
    }

    public short readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public short readShort() {
        return this.buf.readShort();
    }

    public int readUnsignedShort() {
        return this.buf.readUnsignedShort();
    }

    public int readMedium() {
        return this.buf.readMedium();
    }

    public int readUnsignedMedium() {
        return this.buf.readUnsignedMedium();
    }

    public int readInt() {
        return this.buf.readInt();
    }

    public long readUnsignedInt() {
        return this.buf.readUnsignedInt();
    }

    public long readLong() {
        return this.buf.readLong();
    }

    public char readChar() {
        return this.buf.readChar();
    }

    public float readFloat() {
        return this.buf.readFloat();
    }

    public double readDouble() {
        return this.buf.readDouble();
    }

    public ByteBuf readBytes(int length) {
        return this.buf.readBytes(length);
    }

    public ByteBuf readSlice(int length) {
        return this.buf.readSlice(length);
    }

    public ByteBuf readBytes(ByteBuf bytebuf) {
        return this.buf.readBytes(bytebuf);
    }

    public ByteBuf readBytes(ByteBuf bytebuf, int length) {
        return this.buf.readBytes(bytebuf, length);
    }

    public ByteBuf readBytes(ByteBuf bytebuf, int index, int length) {
        return this.buf.readBytes(bytebuf, index, length);
    }

    public ByteBuf readBytes(byte[] abyte) {
        return this.buf.readBytes(abyte);
    }

    public ByteBuf readBytes(byte[] abyte, int index, int length) {
        return this.buf.readBytes(abyte, index, length);
    }

    public ByteBuf readBytes(ByteBuffer bytebuffer) {
        return this.buf.readBytes(bytebuffer);
    }

    public ByteBuf readBytes(OutputStream outputstream, int length) throws IOException {
        return this.buf.readBytes(outputstream, length);
    }

    public int readBytes(GatheringByteChannel gatheringbytechannel, int length) throws IOException {
        return this.buf.readBytes(gatheringbytechannel, length);
    }

    public ByteBuf skipBytes(int length) {
        return this.buf.skipBytes(length);
    }

    public ByteBuf writeBoolean(boolean b) {
        return this.buf.writeBoolean(b);
    }

    public ByteBuf writeByte(int b) {
        return this.buf.writeByte(b);
    }

    public ByteBuf writeShort(int s) {
        return this.buf.writeShort(s);
    }

    public ByteBuf writeMedium(int m) {
        return this.buf.writeMedium(m);
    }

    public ByteBuf writeInt(int i) {
        return this.buf.writeInt(i);
    }

    public ByteBuf writeLong(long l) {
        return this.buf.writeLong(l);
    }

    public ByteBuf writeChar(int c) {
        return this.buf.writeChar(c);
    }

    public ByteBuf writeFloat(float f) {
        return this.buf.writeFloat(f);
    }

    public ByteBuf writeDouble(double d) {
        return this.buf.writeDouble(d);
    }

    public ByteBuf writeBytes(ByteBuf bytebuf) {
        return this.buf.writeBytes(bytebuf);
    }

    public ByteBuf writeBytes(ByteBuf bytebuf, int length) {
        return this.buf.writeBytes(bytebuf, length);
    }

    public ByteBuf writeBytes(ByteBuf bytebuf, int index, int length) {
        return this.buf.writeBytes(bytebuf, index, length);
    }

    public ByteBuf writeBytes(byte[] abyte) {
        return this.buf.writeBytes(abyte);
    }

    public ByteBuf writeBytes(byte[] abyte, int index, int length) {
        return this.buf.writeBytes(abyte, index, length);
    }

    public ByteBuf writeBytes(ByteBuffer bytebuffer) {
        return this.buf.writeBytes(bytebuffer);
    }

    public int writeBytes(InputStream inputstream, int length) throws IOException {
        return this.buf.writeBytes(inputstream, length);
    }

    public int writeBytes(ScatteringByteChannel scatteringbytechannel, int length) throws IOException {
        return this.buf.writeBytes(scatteringbytechannel, length);
    }

    public ByteBuf writeZero(int length) {
        return this.buf.writeZero(length);
    }

    public int indexOf(int fromIndex, int toIndex, byte value) {
        return this.buf.indexOf(fromIndex, toIndex, value);
    }

    public int bytesBefore(byte value) {
        return this.buf.bytesBefore(value);
    }

    public int bytesBefore(int length, byte value) {
        return this.buf.bytesBefore(length, value);
    }

    public int bytesBefore(int index, int length, byte b0) {
        return this.buf.bytesBefore(index, length, b0);
    }

    public int forEachByte(ByteBufProcessor bytebufprocessor) {
        return this.buf.forEachByte(bytebufprocessor);
    }

    public int forEachByte(int index, int length, ByteBufProcessor bytebufprocessor) {
        return this.buf.forEachByte(index, length, bytebufprocessor);
    }

    public int forEachByteDesc(ByteBufProcessor bytebufprocessor) {
        return this.buf.forEachByteDesc(bytebufprocessor);
    }

    public int forEachByteDesc(int index, int length, ByteBufProcessor bytebufprocessor) {
        return this.buf.forEachByteDesc(index, length, bytebufprocessor);
    }

    public ByteBuf copy() {
        return this.buf.copy();
    }

    public ByteBuf copy(int index, int length) {
        return this.buf.copy(index, length);
    }

    public ByteBuf slice() {
        return this.buf.slice();
    }

    public ByteBuf slice(int index, int length) {
        return this.buf.slice(index, length);
    }

    public ByteBuf duplicate() {
        return this.buf.duplicate();
    }

    public int nioBufferCount() {
        return this.buf.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        return this.buf.nioBuffer();
    }

    public ByteBuffer nioBuffer(int index, int length) {
        return this.buf.nioBuffer(index, length);
    }

    public ByteBuffer internalNioBuffer(int index, int length) {
        return this.buf.internalNioBuffer(index, length);
    }

    public ByteBuffer[] nioBuffers() {
        return this.buf.nioBuffers();
    }

    public ByteBuffer[] nioBuffers(int index, int length) {
        return this.buf.nioBuffers(index, length);
    }

    public boolean hasArray() {
        return this.buf.hasArray();
    }

    public byte[] array() {
        return this.buf.array();
    }

    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    public boolean hasMemoryAddress() {
        return this.buf.hasMemoryAddress();
    }

    public long memoryAddress() {
        return this.buf.memoryAddress();
    }

    public String toString(Charset charset) {
        return this.buf.toString(charset);
    }

    public String toString(int index, int length, Charset charset) {
        return this.buf.toString(index, length, charset);
    }

    public int hashCode() {
        return this.buf.hashCode();
    }

    public boolean equals(Object object) {
        return this.buf.equals(object);
    }

    public int compareTo(ByteBuf bytebuf) {
        return this.buf.compareTo(bytebuf);
    }

    public String toString() {
        return this.buf.toString();
    }

    public ByteBuf retain(int amount) {
        return this.buf.retain(amount);
    }

    public ByteBuf retain() {
        return this.buf.retain();
    }

    public int refCnt() {
        return this.buf.refCnt();
    }

    public boolean release() {
        return this.buf.release();
    }

    public boolean release(int amount) {
        return this.buf.release(amount);
    }
}

