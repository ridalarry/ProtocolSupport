/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 */
package protocolsupport.protocol.transformer.middlepacket;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.Packet;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.MiddlePacket;
import protocolsupport.utils.recyclable.RecyclableCollection;

public abstract class ServerBoundMiddlePacket
extends MiddlePacket {
    public abstract void readFromClientData(PacketDataSerializer var1) throws IOException;

    public abstract RecyclableCollection<? extends Packet<?>> toNative() throws Exception;
}

