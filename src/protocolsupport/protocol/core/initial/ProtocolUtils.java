/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.handler.codec.DecoderException
 */
package protocolsupport.protocol.core.initial;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.utils.netty.ChannelUtils;

public class ProtocolUtils {
    protected static ProtocolVersion get16PingVersion(int protocolId) {
        switch (protocolId) {
            case 78: {
                return ProtocolVersion.MINECRAFT_1_6_4;
            }
            case 74: {
                return ProtocolVersion.MINECRAFT_1_6_2;
            }
            case 73: {
                return ProtocolVersion.MINECRAFT_1_6_1;
            }
        }
        return ProtocolVersion.MINECRAFT_1_6_4;
    }

    protected static ProtocolVersion readOldHandshake(ByteBuf data) {
        ProtocolVersion version = ProtocolVersion.fromId(data.readUnsignedByte());
        return version != ProtocolVersion.UNKNOWN ? version : ProtocolVersion.MINECRAFT_LEGACY;
    }

    protected static ProtocolVersion readNettyHandshake(ByteBuf data) {
        int packetId = ChannelUtils.readVarInt(data);
        if (packetId == 0) {
            ProtocolVersion version = ProtocolVersion.fromId(ChannelUtils.readVarInt(data));
            return version != ProtocolVersion.UNKNOWN ? version : ProtocolVersion.MINECRAFT_FUTURE;
        }
        throw new DecoderException("" + packetId + "is not a valid packet id");
    }
}

