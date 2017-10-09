/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.ByteToMessageDecoder
 */
package protocolsupport.protocol.transformer.v_1_6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class PacketDecrypter
extends ByteToMessageDecoder {
    private Cipher cipher;
    private byte[] buffer = new byte[0];

    public PacketDecrypter(Cipher cipher) {
        this.cipher = cipher;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> packet) throws Exception {
        packet.add((Object)this.decrypt(ctx, buf));
    }

    private byte[] readToBuffer(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (this.buffer.length < readableBytes) {
            this.buffer = new byte[readableBytes];
        }
        byteBuf.readBytes(this.buffer, 0, readableBytes);
        return this.buffer;
    }

    protected ByteBuf decrypt(ChannelHandlerContext ctx, ByteBuf input) throws ShortBufferException {
        int readableBytes = input.readableBytes();
        byte[] bytes = this.readToBuffer(input);
        ByteBuf heapBuffer = ctx.alloc().heapBuffer(this.cipher.getOutputSize(readableBytes));
        heapBuffer.writerIndex(this.cipher.update(bytes, 0, readableBytes, heapBuffer.array(), heapBuffer.arrayOffset()));
        return heapBuffer;
    }
}

