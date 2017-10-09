/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.handler.codec.EncoderException
 *  io.netty.util.ReferenceCounted
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.GameProfileSerializer
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer
 *  net.minecraft.server.v1_8_R3.Item
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Items
 *  net.minecraft.server.v1_8_R3.NBTBase
 *  net.minecraft.server.v1_8_R3.NBTCompressedStreamTools
 *  net.minecraft.server.v1_8_R3.NBTReadLimiter
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.NBTTagList
 *  net.minecraft.server.v1_8_R3.NBTTagString
 *  net.minecraft.server.v1_8_R3.PacketDataSerializer
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.event.Event
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.RegisteredListener
 *  org.spigotmc.LimitStream
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol;

import com.mojang.authlib.GameProfile;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ReferenceCounted;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.GameProfileSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTCompressedStreamTools;
import net.minecraft.server.v1_8_R3.NBTReadLimiter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredListener;
import org.spigotmc.LimitStream;
import org.spigotmc.SneakyThrow;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.events.ItemStackWriteEvent;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.protocol.typeskipper.id.IdSkipper;
import protocolsupport.protocol.typeskipper.id.SkippingRegistry;
import protocolsupport.protocol.typeskipper.id.SkippingTable;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.ChannelUtils;

public class PacketDataSerializer
extends net.minecraft.server.v1_8_R3.PacketDataSerializer {
    private ProtocolVersion version;

    public PacketDataSerializer(ByteBuf buf, ProtocolVersion version) {
        this(buf);
        this.version = version;
    }

    public ProtocolVersion getVersion() {
        return this.version;
    }

    protected PacketDataSerializer(ByteBuf buf) {
        super(buf);
    }

    protected void setVersion(ProtocolVersion version) {
        this.version = version;
    }

    public void a(net.minecraft.server.v1_8_R3.ItemStack itemstack) {
        if ((itemstack = this.transformItemStack(itemstack)) == null || itemstack.getItem() == null) {
            this.writeShort(-1);
        } else {
            int itemId = Item.getId((Item)itemstack.getItem());
            this.writeShort(IdRemapper.ITEM.getTable(this.getVersion()).getRemap(itemId));
            this.writeByte(itemstack.count);
            this.writeShort(itemstack.getData());
            this.a(itemstack.getTag());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (this.getVersion().isBefore(ProtocolVersion.MINECRAFT_1_8)) {
            if (nbttagcompound == null) {
                this.writeShort(-1);
            } else {
                byte[] abyte = PacketDataSerializer.write(nbttagcompound);
                this.writeShort(abyte.length);
                this.writeBytes(abyte);
            }
        } else if (nbttagcompound == null) {
            this.writeByte(0);
        } else {
            ByteBufOutputStream out = new ByteBufOutputStream(Allocator.allocateBuffer());
            try {
                NBTCompressedStreamTools.a((NBTTagCompound)nbttagcompound, (DataOutput)new DataOutputStream((OutputStream)out));
                this.writeBytes(out.buffer());
            }
            catch (Throwable ioexception) {
                throw new EncoderException(ioexception);
            }
            finally {
                out.buffer().release();
            }
        }
    }

    private net.minecraft.server.v1_8_R3.ItemStack transformItemStack(net.minecraft.server.v1_8_R3.ItemStack original) {
        if (original == null) {
            return null;
        }
        net.minecraft.server.v1_8_R3.ItemStack itemstack = original.cloneItemStack();
        Item item = itemstack.getItem();
        NBTTagCompound nbttagcompound = itemstack.getTag();
        if (nbttagcompound != null) {
            if (this.getVersion().isBefore(ProtocolVersion.MINECRAFT_1_8) && item == Items.WRITTEN_BOOK && nbttagcompound.hasKeyOfType("pages", 9)) {
                NBTTagList pages = nbttagcompound.getList("pages", 8);
                NBTTagList newpages = new NBTTagList();
                for (int i = 0; i < pages.size(); ++i) {
                    newpages.add((NBTBase)new NBTTagString(LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)pages.getString(i)))));
                }
                nbttagcompound.set("pages", (NBTBase)newpages);
            }
            if (this.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_7_5) && item == Items.SKULL) {
                PacketDataSerializer.transformSkull(nbttagcompound);
            }
            if (nbttagcompound.hasKeyOfType("ench", 9)) {
                SkippingTable enchSkip = IdSkipper.ENCHANT.getTable(this.getVersion());
                NBTTagList enchList = nbttagcompound.getList("ench", 10);
                NBTTagList newList = new NBTTagList();
                for (int i = 0; i < enchList.size(); ++i) {
                    NBTTagCompound enchData = enchList.get(i);
                    if (enchSkip.shouldSkip(enchData.getInt("id") & 65535)) continue;
                    newList.add((NBTBase)enchData);
                }
                if (newList.size() > 0) {
                    nbttagcompound.set("ench", (NBTBase)newList);
                } else {
                    nbttagcompound.remove("ench");
                }
            }
        }
        if (ItemStackWriteEvent.getHandlerList().getRegisteredListeners().length > 0) {
            InternalItemStackWriteEvent event = new InternalItemStackWriteEvent(this.getVersion(), original, itemstack);
            Bukkit.getPluginManager().callEvent((Event)event);
        }
        return itemstack;
    }

    public static void transformSkull(NBTTagCompound nbttagcompound) {
        PacketDataSerializer.transformSkull(nbttagcompound, "SkullOwner", "SkullOwner");
        PacketDataSerializer.transformSkull(nbttagcompound, "Owner", "ExtraType");
    }

    private static void transformSkull(NBTTagCompound tag, String tagname, String newtagname) {
        if (tag.hasKeyOfType(tagname, 10)) {
            GameProfile gameprofile = GameProfileSerializer.deserialize((NBTTagCompound)tag.getCompound(tagname));
            if (gameprofile.getName() != null) {
                tag.set(newtagname, (NBTBase)new NBTTagString(gameprofile.getName()));
            } else {
                tag.remove(tagname);
            }
        }
    }

    public NBTTagCompound h() throws IOException {
        if (this.getVersion().isBefore(ProtocolVersion.MINECRAFT_1_8)) {
            short length = this.readShort();
            if (length < 0) {
                return null;
            }
            byte[] data = new byte[length];
            this.readBytes(data);
            return PacketDataSerializer.read(data, new NBTReadLimiter(0x200000));
        }
        int index = this.readerIndex();
        if (this.readByte() == 0) {
            return null;
        }
        this.readerIndex(index);
        return NBTCompressedStreamTools.a((DataInput)new DataInputStream((InputStream)new ByteBufInputStream((ByteBuf)this)), (NBTReadLimiter)new NBTReadLimiter(0x200000));
    }

    public String c(int limit) {
        if (this.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_6_4)) {
            return new String(ChannelUtils.toArray(this.readBytes(this.readUnsignedShort() * 2)), StandardCharsets.UTF_16BE);
        }
        return super.c(limit);
    }

    public PacketDataSerializer a(String string) {
        if (this.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_6_4)) {
            this.writeShort(string.length());
            this.writeBytes(string.getBytes(StandardCharsets.UTF_16BE));
        } else {
            super.a(string);
        }
        return this;
    }

    public byte[] a() {
        if (this.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_7_10)) {
            byte[] array = new byte[this.readShort()];
            this.readBytes(array);
            return array;
        }
        return super.a();
    }

    public void a(byte[] array) {
        if (this.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_7_10)) {
            if (array.length > 32767) {
                throw new IllegalArgumentException("Too big array length of " + array.length);
            }
            this.writeShort(array.length);
            this.writeBytes(array);
        } else {
            super.a(array);
        }
    }

    public int readVarInt() {
        return this.e();
    }

    public void writeVarInt(int varInt) {
        this.b(varInt);
    }

    public String readString(int limit) {
        return this.c(limit);
    }

    public void writeString(String string) {
        this.a(string);
    }

    public net.minecraft.server.v1_8_R3.ItemStack readItemStack() throws IOException {
        return this.i();
    }

    public void writeItemStack(net.minecraft.server.v1_8_R3.ItemStack itemstack) {
        this.a(itemstack);
    }

    public byte[] readArray() {
        return this.a();
    }

    public void writeArray(byte[] array) {
        this.a(array);
    }

    public BlockPosition readPosition() {
        return this.c();
    }

    public void writePosition(BlockPosition position) {
        this.a(position);
    }

    public UUID readUUID() {
        return this.g();
    }

    public void writeUUID(UUID uuid) {
        this.a(uuid);
    }

    public NBTTagCompound readTag() throws IOException {
        return this.h();
    }

    public void writeTag(NBTTagCompound tag) {
        this.a(tag);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static NBTTagCompound read(byte[] data, NBTReadLimiter nbtreadlimiter) {
        try {
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream((InputStream)new LimitStream((InputStream)new GZIPInputStream(new ByteArrayInputStream(data)), nbtreadlimiter)));
            Throwable throwable = null;
            try {
                NBTTagCompound nBTTagCompound = NBTCompressedStreamTools.a((DataInput)datainputstream, (NBTReadLimiter)nbtreadlimiter);
                return nBTTagCompound;
            }
            catch (Throwable throwable3) {
                throwable = throwable3;
                throw throwable3;
            }
            finally {
                if (datainputstream != null) {
                    if (throwable != null) {
                        try {
                            datainputstream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    } else {
                        datainputstream.close();
                    }
                }
            }
        }
        catch (IOException ex) {
            SneakyThrow.sneaky((Throwable)ex);
            return null;
        }
    }

    private static byte[] write(NBTTagCompound nbttagcompound) {
        try {
            ByteArrayOutputStream bytearrayoutputstream;
            bytearrayoutputstream = new ByteArrayOutputStream();
            DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));
            Throwable throwable = null;
            try {
                NBTCompressedStreamTools.a((NBTTagCompound)nbttagcompound, (DataOutput)dataoutputstream);
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                if (dataoutputstream != null) {
                    if (throwable != null) {
                        try {
                            dataoutputstream.close();
                        }
                        catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                    } else {
                        dataoutputstream.close();
                    }
                }
            }
            return bytearrayoutputstream.toByteArray();
        }
        catch (IOException ex) {
            SneakyThrow.sneaky((Throwable)ex);
            return null;
        }
    }

    public static class InternalItemStackWriteEvent
    extends ItemStackWriteEvent {
        private final CraftItemStack wrapped;

        public InternalItemStackWriteEvent(ProtocolVersion version, net.minecraft.server.v1_8_R3.ItemStack original, net.minecraft.server.v1_8_R3.ItemStack itemstack) {
            super(version, (ItemStack)CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)original));
            this.wrapped = CraftItemStack.asCraftMirror((net.minecraft.server.v1_8_R3.ItemStack)itemstack);
        }

        @Override
        public ItemStack getResult() {
            return this.wrapped;
        }
    }

}

