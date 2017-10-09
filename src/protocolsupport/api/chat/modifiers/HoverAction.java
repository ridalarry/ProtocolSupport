/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Achievement
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.MojangsonParseException
 *  net.minecraft.server.v1_8_R3.MojangsonParser
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.Statistic
 *  org.bukkit.Achievement
 *  org.bukkit.Statistic
 *  org.bukkit.craftbukkit.v1_8_R3.CraftStatistic
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.inventory.ItemStack
 */
package protocolsupport.api.chat.modifiers;

import java.util.UUID;
import net.minecraft.server.v1_8_R3.Achievement;
import net.minecraft.server.v1_8_R3.MojangsonParseException;
import net.minecraft.server.v1_8_R3.MojangsonParser;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_8_R3.CraftStatistic;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.utils.Any;

public class HoverAction {
    private Type type;
    private String value;

    public HoverAction(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public HoverAction(BaseComponent component) {
        this.type = Type.SHOW_TEXT;
        this.value = ChatAPI.toJSON(component);
    }

    public HoverAction(ItemStack itemstack) {
        this.type = Type.SHOW_ITEM;
        net.minecraft.server.v1_8_R3.ItemStack nmsitemstack = CraftItemStack.asNMSCopy((ItemStack)itemstack);
        NBTTagCompound compound = new NBTTagCompound();
        nmsitemstack.save(compound);
        this.value = compound.toString();
    }

    public HoverAction(Entity entity) {
        this(new EntityInfo(entity));
    }

    public HoverAction(EntityInfo entityinfo) {
        this.type = Type.SHOW_ENTITY;
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("type", entityinfo.getType().getName());
        compound.setString("id", entityinfo.getUUID().toString());
        compound.setString("name", entityinfo.getName());
        this.value = compound.toString();
    }

    public HoverAction(org.bukkit.Achievement achievment) {
        this.type = Type.SHOW_ACHIEVEMENT;
        this.value = CraftStatistic.getNMSAchievement((org.bukkit.Achievement)achievment).name;
    }

    public HoverAction(Statistic stat) {
        this.type = Type.SHOW_ACHIEVEMENT;
        this.value = CraftStatistic.getNMSStatistic((Statistic)stat).name;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public BaseComponent getText() {
        HoverAction.validateAction(this.type, Type.SHOW_TEXT);
        return ChatAPI.fromJSON(this.value);
    }

    public ItemStack getItemStack() {
        HoverAction.validateAction(this.type, Type.SHOW_ITEM);
        try {
            return CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)net.minecraft.server.v1_8_R3.ItemStack.createStack((NBTTagCompound)MojangsonParser.parse((String)this.value)));
        }
        catch (MojangsonParseException e) {
            throw new IllegalStateException("Unable to parse value to itemstack");
        }
    }

    public EntityInfo getEntity() {
        HoverAction.validateAction(this.type, Type.SHOW_ENTITY);
        try {
            NBTTagCompound compound = MojangsonParser.parse((String)this.value);
            return new EntityInfo(EntityType.fromName((String)compound.getString("type")), UUID.fromString(compound.getString("id")), compound.getString("name"));
        }
        catch (MojangsonParseException e) {
            throw new IllegalStateException("Unable to parse value to entity info");
        }
    }

    public Any<org.bukkit.Achievement, Statistic> getAchievmentOrStat() {
        HoverAction.validateAction(this.type, Type.SHOW_ACHIEVEMENT);
        org.bukkit.Achievement achievement = CraftStatistic.getBukkitAchievementByName((String)this.value);
        Statistic stat = CraftStatistic.getBukkitStatisticByName((String)this.value);
        return new Any<org.bukkit.Achievement, Statistic>(achievement, stat);
    }

    static void validateAction(Type current, Type expected) {
        if (current != expected) {
            throw new IllegalStateException((Object)((Object)current) + " is not an " + (Object)((Object)expected));
        }
    }

    public static class EntityInfo {
        private EntityType etype;
        private UUID uuid;
        private String name;

        public EntityInfo(EntityType etype, UUID uuid, String name) {
            this.etype = etype;
            this.uuid = uuid;
            this.name = name;
        }

        public EntityInfo(Entity entity) {
            this(entity.getType(), entity.getUniqueId(), entity.getName());
        }

        public EntityType getType() {
            return this.etype;
        }

        public UUID getUUID() {
            return this.uuid;
        }

        public String getName() {
            return this.name;
        }
    }

    public static enum Type {
        SHOW_TEXT,
        SHOW_ACHIEVEMENT,
        SHOW_ITEM,
        SHOW_ENTITY;
        

        private Type() {
        }
    }

}

