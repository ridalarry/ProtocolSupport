/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.utils.netty.ChannelUtils;

public abstract class MiddleCustomPayload<T>
extends ClientBoundMiddlePacket<T> {
    protected String tag;
    protected byte[] data;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.tag = serializer.readString(20);
        this.data = ChannelUtils.toArray((ByteBuf)serializer);
    }
}

