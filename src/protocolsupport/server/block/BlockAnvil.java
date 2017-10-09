/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.Block$StepSound
 *  net.minecraft.server.v1_8_R3.BlockAnvil
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.EnumDirection
 *  net.minecraft.server.v1_8_R3.IBlockData
 *  net.minecraft.server.v1_8_R3.ITileEntityContainer
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.block;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.ITileEntityContainer;
import net.minecraft.server.v1_8_R3.World;
import protocolsupport.server.tileentity.TileEntityContainerAnvil;

public class BlockAnvil
extends net.minecraft.server.v1_8_R3.BlockAnvil {
    public BlockAnvil() {
        this.c(5.0f);
        this.a(Block.p);
        this.b(2000.0f);
        this.c("anvil");
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        entityhuman.openTileEntity((ITileEntityContainer)new TileEntityContainerAnvil(world, blockposition));
        return true;
    }
}

