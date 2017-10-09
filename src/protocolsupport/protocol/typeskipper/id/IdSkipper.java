/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Enchantment
 *  net.minecraft.server.v1_8_R3.MobEffectList
 */
package protocolsupport.protocol.typeskipper.id;

import net.minecraft.server.v1_8_R3.Enchantment;
import net.minecraft.server.v1_8_R3.MobEffectList;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeskipper.id.SkippingRegistry;
import protocolsupport.protocol.typeskipper.id.SkippingTable;
import protocolsupport.utils.ProtocolVersionsHelper;

public class IdSkipper {
    public static final SkippingRegistry ENCHANT = new SkippingRegistry(){

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(256);
        }
    };
    public static final SkippingRegistry EFFECT = new SkippingRegistry(){

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(32);
        }
    };
    public static final SkippingRegistry INVENTORY = new SkippingRegistry(){

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(16);
        }
    };

}

