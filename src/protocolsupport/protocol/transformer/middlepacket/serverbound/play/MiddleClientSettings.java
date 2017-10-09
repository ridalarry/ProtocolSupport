/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleClientSettings
extends ServerBoundMiddlePacket {
    protected String locale;
    protected int viewDist;
    protected int chatMode;
    protected boolean chatColors;
    protected int skinFlags;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_SETTINGS.get());
        creator.writeString(this.locale);
        creator.writeByte(this.viewDist);
        creator.writeByte(this.chatMode);
        creator.writeBoolean(this.chatColors);
        creator.writeByte(this.skinFlags);
        return RecyclableSingletonList.create(creator.create());
    }
}

