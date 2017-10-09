/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.BlockEnchantmentTable
 *  net.minecraft.server.v1_8_R3.TileEntity
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.block;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockEnchantmentTable;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.World;
import protocolsupport.server.tileentity.TileEntityEnchantTable;

public class BlockEnchantTable
extends BlockEnchantmentTable {
    public BlockEnchantTable() {
        this.c(5.0f);
        this.b(2000.0f);
        this.c("enchantmentTable");
    }

    public TileEntity a(World world, int n) {
        return new TileEntityEnchantTable();
    }
}

