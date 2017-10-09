/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockOpenSignEditor;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockOpenSignEditor
extends MiddleBlockOpenSignEditor<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        if (version == ProtocolVersion.MINECRAFT_1_6_1) {
            return RecyclableEmptyList.get();
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SIGN_EDITOR_ID, version);
        serializer.writeByte(0);
        serializer.writeInt(this.position.getX());
        serializer.writeInt(this.position.getY());
        serializer.writeInt(this.position.getZ());
        return RecyclableSingletonList.create(serializer);
    }
}

