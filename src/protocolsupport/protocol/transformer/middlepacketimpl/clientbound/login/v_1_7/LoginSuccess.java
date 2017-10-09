/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.login.v_1_7;

import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.login.MiddleLoginSuccess;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class LoginSuccess
extends MiddleLoginSuccess<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        if (version == ProtocolVersion.MINECRAFT_1_7_5) {
            this.uuidstring = this.uuidstring.replace("-", "");
        }
        PacketData serializer = PacketData.create(ClientBoundPacket.LOGIN_SUCCESS_ID, version);
        serializer.writeString(this.uuidstring);
        serializer.writeString(this.name);
        return RecyclableSingletonList.create(serializer);
    }
}

