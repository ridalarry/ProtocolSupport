/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Block
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Blocks
 *  net.minecraft.server.v1_8_R3.ContainerEnchantTable
 *  net.minecraft.server.v1_8_R3.Enchantment
 *  net.minecraft.server.v1_8_R3.EnchantmentManager
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  net.minecraft.server.v1_8_R3.IBlockData
 *  net.minecraft.server.v1_8_R3.IInventory
 *  net.minecraft.server.v1_8_R3.InventorySubcontainer
 *  net.minecraft.server.v1_8_R3.Item
 *  net.minecraft.server.v1_8_R3.ItemEnchantedBook
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Items
 *  net.minecraft.server.v1_8_R3.PlayerAbilities
 *  net.minecraft.server.v1_8_R3.PlayerInventory
 *  net.minecraft.server.v1_8_R3.Statistic
 *  net.minecraft.server.v1_8_R3.StatisticList
 *  net.minecraft.server.v1_8_R3.WeightedRandomEnchant
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.CraftServer
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryView
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.enchantment.EnchantItemEvent
 *  org.bukkit.event.enchantment.PrepareItemEnchantEvent
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.PluginManager
 */
package protocolsupport.server.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.EnchantmentManager;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.IInventory;
import net.minecraft.server.v1_8_R3.InventorySubcontainer;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemEnchantedBook;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.PlayerAbilities;
import net.minecraft.server.v1_8_R3.PlayerInventory;
import net.minecraft.server.v1_8_R3.Statistic;
import net.minecraft.server.v1_8_R3.StatisticList;
import net.minecraft.server.v1_8_R3.WeightedRandomEnchant;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class ContainerEnchantTable
extends net.minecraft.server.v1_8_R3.ContainerEnchantTable {
    private final Random random = new Random();
    private World world;
    private BlockPosition position;
    private Player player;

    public ContainerEnchantTable(PlayerInventory inv, World world, BlockPosition pos) {
        super(inv, world, pos);
        this.world = world;
        this.position = pos;
        this.player = (Player)inv.player.getBukkitEntity();
    }

    public void a(IInventory iinventory) {
        if (iinventory == this.enchantSlots) {
            net.minecraft.server.v1_8_R3.ItemStack itemstack = iinventory.getItem(0);
            if (itemstack != null) {
                int bookShelfs = 0;
                for (int z = -1; z <= 1; ++z) {
                    for (int x = -1; x <= 1; ++x) {
                        if (z == 0 && x == 0 || !this.world.isEmpty(this.position.a(x, 0, z)) || !this.world.isEmpty(this.position.a(x, 1, z))) continue;
                        if (this.world.getType(this.position.a(x * 2, 0, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x * 2, 1, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (x == 0 || z == 0) continue;
                        if (this.world.getType(this.position.a(x * 2, 0, z)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x * 2, 1, z)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x, 0, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x, 1, z * 2)).getBlock() != Blocks.BOOKSHELF) continue;
                        ++bookShelfs;
                    }
                }
                this.random.setSeed(this.f);
                for (int i = 0; i < 3; ++i) {
                    this.costs[i] = EnchantmentManager.a((Random)this.random, (int)i, (int)bookShelfs, (net.minecraft.server.v1_8_R3.ItemStack)itemstack);
                    this.h[i] = -1;
                    if (this.costs[i] >= i + 1) continue;
                    this.costs[i] = 0;
                }
                CraftItemStack item = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)itemstack);
                PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, (InventoryView)this.getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, this.costs, bookShelfs);
                event.setCancelled(!itemstack.v());
                this.world.getServer().getPluginManager().callEvent((Event)event);
                if (event.isCancelled()) {
                    for (bookShelfs = 0; bookShelfs < 3; ++bookShelfs) {
                        this.costs[bookShelfs] = 0;
                    }
                    return;
                }
                for (int i = 0; i < 3; ++i) {
                    List<WeightedRandomEnchant> list;
                    if (this.costs[i] <= 0 || (list = this.getEnchantments(itemstack, i, this.costs[i])) == null || list.isEmpty()) continue;
                    WeightedRandomEnchant weightedrandomenchant = list.get(this.random.nextInt(list.size()));
                    this.h[i] = weightedrandomenchant.enchantment.id | weightedrandomenchant.level << 8;
                }
                this.b();
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.costs[i] = 0;
                    this.h[i] = -1;
                }
            }
        }
    }

    public boolean a(EntityHuman entityhuman, int slot) {
        boolean supportsLapisSlot = ProtocolSupportAPI.getProtocolVersion((Player)entityhuman.getBukkitEntity()) == ProtocolVersion.MINECRAFT_1_8;
        net.minecraft.server.v1_8_R3.ItemStack itemstack = this.enchantSlots.getItem(0);
        net.minecraft.server.v1_8_R3.ItemStack lapis = this.enchantSlots.getItem(1);
        int cost = slot + 1;
        if (supportsLapisSlot && (lapis == null || lapis.count < cost) && !entityhuman.abilities.canInstantlyBuild) {
            return false;
        }
        if (this.costs[slot] > 0 && itemstack != null && (entityhuman.expLevel >= cost && entityhuman.expLevel >= this.costs[slot] || entityhuman.abilities.canInstantlyBuild)) {
            List<WeightedRandomEnchant> enchantments = this.getEnchantments(itemstack, slot, this.costs[slot]);
            if (enchantments == null) {
                enchantments = new ArrayList<WeightedRandomEnchant>();
            }
            boolean isBook = itemstack.getItem() == Items.BOOK;
            HashMap<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
            for (WeightedRandomEnchant enchantment : enchantments) {
                enchants.put(Enchantment.getById((int)enchantment.enchantment.id), enchantment.level);
            }
            CraftItemStack item = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)itemstack);
            EnchantItemEvent event = new EnchantItemEvent((Player)entityhuman.getBukkitEntity(), (InventoryView)this.getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, this.costs[slot], enchants, slot);
            this.world.getServer().getPluginManager().callEvent((Event)event);
            int level = event.getExpLevelCost();
            if (event.isCancelled() || level > entityhuman.expLevel && !entityhuman.abilities.canInstantlyBuild || event.getEnchantsToAdd().isEmpty()) {
                return false;
            }
            if (isBook) {
                itemstack.setItem((Item)Items.ENCHANTED_BOOK);
            }
            for (Map.Entry entry : event.getEnchantsToAdd().entrySet()) {
                try {
                    if (isBook) {
                        int enchantId = ((Enchantment)entry.getKey()).getId();
                        if (net.minecraft.server.v1_8_R3.Enchantment.getById((int)enchantId) == null) continue;
                        WeightedRandomEnchant enchantment = new WeightedRandomEnchant(net.minecraft.server.v1_8_R3.Enchantment.getById((int)enchantId), ((Integer)entry.getValue()).intValue());
                        Items.ENCHANTED_BOOK.a(itemstack, enchantment);
                        continue;
                    }
                    item.addUnsafeEnchantment((Enchantment)entry.getKey(), ((Integer)entry.getValue()).intValue());
                }
                catch (IllegalArgumentException enchantId) {}
            }
            entityhuman.enchantDone(supportsLapisSlot ? cost : this.costs[slot]);
            if (!entityhuman.abilities.canInstantlyBuild && supportsLapisSlot) {
                net.minecraft.server.v1_8_R3.ItemStack itemStack = lapis;
                itemStack.count -= cost;
                if (lapis.count <= 0) {
                    this.enchantSlots.setItem(1, null);
                }
            }
            entityhuman.b(StatisticList.W);
            this.enchantSlots.update();
            this.f = entityhuman.cj();
            this.a((IInventory)this.enchantSlots);
            return true;
        }
        return false;
    }

    private List<WeightedRandomEnchant> getEnchantments(net.minecraft.server.v1_8_R3.ItemStack itemstack, int slot, int cost) {
        this.random.setSeed(this.f + slot);
        List list = EnchantmentManager.b((Random)this.random, (net.minecraft.server.v1_8_R3.ItemStack)itemstack, (int)cost);
        if (itemstack.getItem() == Items.BOOK && list != null && list.size() > 1) {
            list.remove(this.random.nextInt(list.size()));
        }
        return list;
    }
}

