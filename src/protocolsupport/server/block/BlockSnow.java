/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.AxisAlignedBB
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.Block$StepSound
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.BlockSnow
 *  net.minecraft.server.v1_8_R3.IBlockData
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.block;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.World;

public class BlockSnow
extends net.minecraft.server.v1_8_R3.BlockSnow {
    public BlockSnow() {
        this.c(0.1f);
        this.a(Block.n);
        this.c("snow");
        this.e(0);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public AxisAlignedBB getRealBB(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return super.a(world, blockposition, iblockdata);
    }
}

