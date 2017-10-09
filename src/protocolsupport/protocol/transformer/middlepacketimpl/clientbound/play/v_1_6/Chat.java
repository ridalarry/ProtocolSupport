/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_6;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleChat;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.protocol.transformer.v_1_6.utils.ChatEncoder;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Chat
extends MiddleChat<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CHAT_ID, version);
        serializer.writeString(ChatEncoder.encode(LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)this.chatJson))));
        return RecyclableSingletonList.create(serializer);
    }
}

