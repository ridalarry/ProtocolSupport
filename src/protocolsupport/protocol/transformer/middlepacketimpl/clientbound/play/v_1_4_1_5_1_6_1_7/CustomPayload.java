/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleCustomPayload;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.nbt.custompayload.CustomPayloadSerializer;
import protocolsupport.protocol.typeremapper.nbt.custompayload.MerchantData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class CustomPayload
extends MiddleCustomPayload<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, version);
        serializer.writeString(this.tag);
        CustomPayloadSerializer serverdata = new CustomPayloadSerializer(new PacketDataSerializer(Unpooled.wrappedBuffer((byte[])this.data), ProtocolVersion.getLatest()));
        CustomPayloadSerializer clientdata = new CustomPayloadSerializer(version);
        if (this.tag.equals("MC|TrList")) {
            clientdata.writeMerchantData(serverdata.readMerchantData());
        } else {
            clientdata.copyAll(serverdata);
        }
        serializer.writeArray(clientdata.toData());
        return RecyclableSingletonList.create(serializer);
    }
}

