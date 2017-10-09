/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Container
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.PlayerInventory
 *  net.minecraft.server.v1_8_R3.TileEntityEnchantTable
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.tileentity;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.PlayerInventory;
import net.minecraft.server.v1_8_R3.World;
import protocolsupport.server.container.ContainerEnchantTable;

public class TileEntityEnchantTable
extends net.minecraft.server.v1_8_R3.TileEntityEnchantTable {
    public void c() {
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityHuman) {
        return new ContainerEnchantTable(playerinventory, this.world, this.position);
    }
}

