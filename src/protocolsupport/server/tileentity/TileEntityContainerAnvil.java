/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockAnvil
 *  net.minecraft.server.v1_8_R3.BlockAnvil$TileEntityContainerAnvil
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Container
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.PlayerInventory
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.tileentity;

import net.minecraft.server.v1_8_R3.BlockAnvil;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.PlayerInventory;
import net.minecraft.server.v1_8_R3.World;
import protocolsupport.server.container.ContainerAnvil;

public class TileEntityContainerAnvil
extends BlockAnvil.TileEntityContainerAnvil {
    private final World world;
    private final BlockPosition position;

    public TileEntityContainerAnvil(World world, BlockPosition position) {
        super(world, position);
        this.world = world;
        this.position = position;
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerAnvil(playerinventory, this.world, this.position, entityhuman);
    }
}

