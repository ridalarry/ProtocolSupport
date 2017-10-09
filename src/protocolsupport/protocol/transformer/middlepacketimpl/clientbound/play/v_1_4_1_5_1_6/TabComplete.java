/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringUtils
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleTabComplete;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class TabComplete
extends MiddleTabComplete<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        if (this.matches.length == 0) {
            return RecyclableEmptyList.get();
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_TAB_COMPLETE_ID, version);
        serializer.writeString(Utils.clampString(StringUtils.join((Object[])this.matches, (char)'\u0000'), 32767));
        return RecyclableSingletonList.create(serializer);
    }
}

