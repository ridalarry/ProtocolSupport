/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.ItemStack
 */
package protocolsupport.protocol.typeremapper.nbt.custompayload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.v1_8_R3.ItemStack;

public class MerchantData {
    private int windowId;
    private List<TradeOffer> offers = new ArrayList<TradeOffer>(10);

    public MerchantData(int windowId) {
        this.windowId = windowId;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public void addOffer(TradeOffer offer) {
        this.offers.add(offer);
    }

    public List<TradeOffer> getOffers() {
        return Collections.unmodifiableList(this.offers);
    }

    public static class TradeOffer {
        private ItemStack itemstack1;
        private ItemStack itemstack2;
        private ItemStack result;
        private int uses;
        private int maxuses;

        public TradeOffer(ItemStack itemstack1, ItemStack itemstack2, ItemStack result, int uses, int maxuses) {
            this.itemstack1 = itemstack1;
            this.result = result;
            this.uses = uses;
            this.maxuses = maxuses;
            this.itemstack2 = itemstack2;
        }

        public ItemStack getItemStack1() {
            return this.itemstack1;
        }

        public boolean hasItemStack2() {
            return this.itemstack2 != null;
        }

        public ItemStack getItemStack2() {
            return this.itemstack2;
        }

        public ItemStack getResult() {
            return this.result;
        }

        public boolean isDisabled() {
            return this.uses >= this.maxuses;
        }

        public int getUses() {
            return this.uses;
        }

        public int getMaxUses() {
            return this.maxuses;
        }
    }

}

