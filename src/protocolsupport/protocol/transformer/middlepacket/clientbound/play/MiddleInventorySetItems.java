/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.server.v1_8_R3.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleInventorySetItems<T>
extends ClientBoundMiddlePacket<T> {
    protected int windowId;
    protected ArrayList<ItemStack> itemstacks = new ArrayList();

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        int count = serializer.readShort();
        this.itemstacks.clear();
        for (int i = 0; i < count; ++i) {
            this.itemstacks.add(serializer.readItemStack());
        }
    }
}

