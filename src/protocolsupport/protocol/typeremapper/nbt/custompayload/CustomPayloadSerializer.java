/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.v1_8_R3.ItemStack
 */
package protocolsupport.protocol.typeremapper.nbt.custompayload;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.List;
import net.minecraft.server.v1_8_R3.ItemStack;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.RecyclablePacketDataSerializer;
import protocolsupport.protocol.typeremapper.nbt.custompayload.MerchantData;
import protocolsupport.utils.netty.ChannelUtils;

public class CustomPayloadSerializer {
    private final PacketDataSerializer serializer;

    public CustomPayloadSerializer(PacketDataSerializer serializer) {
        this.serializer = serializer;
    }

    public CustomPayloadSerializer(ProtocolVersion version) {
        this.serializer = RecyclablePacketDataSerializer.create(version);
    }

    public void copyAll(CustomPayloadSerializer another) {
        this.serializer.writeBytes((ByteBuf)another.serializer);
    }

    public MerchantData readMerchantData() throws IOException {
        MerchantData merchdata = new MerchantData(this.serializer.readInt());
        int count = this.serializer.readUnsignedByte();
        for (int i = 0; i < count; ++i) {
            ItemStack itemstack1 = this.serializer.readItemStack();
            ItemStack result = this.serializer.readItemStack();
            ItemStack itemstack2 = null;
            if (this.serializer.readBoolean()) {
                itemstack2 = this.serializer.readItemStack();
            }
            boolean disabled = this.serializer.readBoolean();
            int uses = 0;
            int maxuses = 7;
            if (this.serializer.getVersion() == ProtocolVersion.MINECRAFT_1_8) {
                uses = this.serializer.readInt();
                maxuses = this.serializer.readInt();
            }
            merchdata.addOffer(new MerchantData.TradeOffer(itemstack1, itemstack2, result, disabled ? maxuses : uses, maxuses));
        }
        return merchdata;
    }

    public void writeMerchantData(MerchantData merchdata) {
        this.serializer.writeInt(merchdata.getWindowId());
        this.serializer.writeByte(merchdata.getOffers().size());
        for (MerchantData.TradeOffer offer : merchdata.getOffers()) {
            this.serializer.writeItemStack(offer.getItemStack1());
            this.serializer.writeItemStack(offer.getResult());
            this.serializer.writeBoolean(offer.hasItemStack2());
            if (offer.hasItemStack2()) {
                this.serializer.writeItemStack(offer.getItemStack2());
            }
            this.serializer.writeBoolean(offer.isDisabled());
            if (this.serializer.getVersion() != ProtocolVersion.MINECRAFT_1_8) continue;
            this.serializer.writeInt(offer.getUses());
            this.serializer.writeInt(offer.getMaxUses());
        }
    }

    public byte[] toData() {
        try {
            byte[] arrby = ChannelUtils.toArray((ByteBuf)this.serializer);
            return arrby;
        }
        finally {
            this.serializer.release();
        }
    }
}

