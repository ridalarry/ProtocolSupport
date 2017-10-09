/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Container
 *  net.minecraft.server.v1_8_R3.ContainerAnvil
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.ICrafting
 *  net.minecraft.server.v1_8_R3.PlayerInventory
 *  net.minecraft.server.v1_8_R3.World
 */
package protocolsupport.server.container;

import java.util.List;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.ICrafting;
import net.minecraft.server.v1_8_R3.PlayerInventory;
import net.minecraft.server.v1_8_R3.World;

public class ContainerAnvil
extends net.minecraft.server.v1_8_R3.ContainerAnvil {
    public ContainerAnvil(PlayerInventory playerinventory, World world, BlockPosition blockposition, EntityHuman entityhuman) {
        super(playerinventory, world, blockposition, entityhuman);
    }

    public void b() {
        super.b();
        for (ICrafting listener : this.listeners) {
            listener.setContainerData((Container)this, 0, this.a);
        }
    }
}

