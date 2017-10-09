/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.AxisAlignedBB
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.Block$StepSound
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.BlockSnow
 *  net.minecraft.server.v1_8_R3.BlockStateInteger
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.EnumDirection
 *  net.minecraft.server.v1_8_R3.IBlockData
 *  net.minecraft.server.v1_8_R3.IBlockState
 *  net.minecraft.server.v1_8_R3.ItemSnow
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.item;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.BlockStateInteger;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.IBlockState;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.World;
import protocolsupport.server.block.BlockSnow;

public class ItemSnow
extends net.minecraft.server.v1_8_R3.ItemSnow {
    private BlockSnow blockSnow;

    public ItemSnow(BlockSnow blockSnow) {
        super((Block)blockSnow);
        this.blockSnow = blockSnow;
    }

    public boolean interactWith(ItemStack itemStack, EntityHuman entityHuman, World world, BlockPosition blockPosition, EnumDirection enumDirection, float n, float n2, float n3) {
        AxisAlignedBB a;
        int intValue;
        IBlockData set;
        if (itemStack.count == 0) {
            return false;
        }
        if (!entityHuman.a(blockPosition, enumDirection, itemStack)) {
            return false;
        }
        IBlockData blockData = world.getType(blockPosition);
        Block block = blockData.getBlock();
        BlockPosition shift = blockPosition;
        if (!(enumDirection == EnumDirection.UP && block == this.a || block.a(world, blockPosition))) {
            shift = blockPosition.shift(enumDirection);
            blockData = world.getType(shift);
            block = blockData.getBlock();
        }
        if (block == this.a && (intValue = ((Integer)blockData.get((IBlockState)net.minecraft.server.v1_8_R3.BlockSnow.LAYERS)).intValue()) <= 7 && (a = this.blockSnow.getRealBB(world, shift, set = blockData.set((IBlockState)net.minecraft.server.v1_8_R3.BlockSnow.LAYERS, (Comparable)Integer.valueOf(intValue + 1)))) != null && world.b(a) && world.setTypeAndData(shift, set, 2)) {
            world.makeSound((double)((float)shift.getX() + 0.5f), (double)((float)shift.getY() + 0.5f), (double)((float)shift.getZ() + 0.5f), this.a.stepSound.getPlaceSound(), (this.a.stepSound.getVolume1() + 1.0f) / 2.0f, this.a.stepSound.getVolume2() * 0.8f);
            --itemStack.count;
            return true;
        }
        return super.interactWith(itemStack, entityHuman, world, shift, enumDirection, n, n2, n3);
    }
}

