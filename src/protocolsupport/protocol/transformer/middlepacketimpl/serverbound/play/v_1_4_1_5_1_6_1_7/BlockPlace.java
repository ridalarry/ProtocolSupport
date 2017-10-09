/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleBlockPlace;

public class BlockPlace
extends MiddleBlockPlace {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.position = new BlockPosition(serializer.readInt(), (int)serializer.readUnsignedByte(), serializer.readInt());
        this.face = serializer.readUnsignedByte();
        this.itemstack = serializer.readItemStack();
        this.cX = serializer.readUnsignedByte();
        this.cY = serializer.readUnsignedByte();
        this.cZ = serializer.readUnsignedByte();
    }
}

