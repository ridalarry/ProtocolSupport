/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 *  org.bukkit.inventory.ItemStack
 */
package protocolsupport.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import protocolsupport.api.ProtocolVersion;

public abstract class ItemStackWriteEvent
extends Event {
    private final ProtocolVersion version;
    private final ItemStack original;
    private static final HandlerList list = new HandlerList();

    public ItemStackWriteEvent(ProtocolVersion version, ItemStack original) {
        this.version = version;
        this.original = original.clone();
    }

    public ItemStack getOriginal() {
        return this.original.clone();
    }

    public ProtocolVersion getVersion() {
        return this.version;
    }

    public abstract ItemStack getResult();

    public HandlerList getHandlers() {
        return list;
    }

    public static HandlerList getHandlerList() {
        return list;
    }
}

