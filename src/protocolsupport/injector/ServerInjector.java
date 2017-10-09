/*    */ package protocolsupport.injector;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_8_R3.Block;
/*    */ import net.minecraft.server.v1_8_R3.BlockStateList;
/*    */ import net.minecraft.server.v1_8_R3.Blocks;
/*    */ import net.minecraft.server.v1_8_R3.IBlockData;
/*    */ import net.minecraft.server.v1_8_R3.Item;
/*    */ import net.minecraft.server.v1_8_R3.ItemAnvil;
/*    */ import net.minecraft.server.v1_8_R3.ItemBlock;
/*    */ import net.minecraft.server.v1_8_R3.ItemCloth;
/*    */ import net.minecraft.server.v1_8_R3.ItemSpade;
/*    */ import net.minecraft.server.v1_8_R3.MinecraftKey;
/*    */ import net.minecraft.server.v1_8_R3.RegistryBlocks;
/*    */ import net.minecraft.server.v1_8_R3.RegistryID;
/*    */ import net.minecraft.server.v1_8_R3.RegistryMaterials;
/*    */ import net.minecraft.server.v1_8_R3.TileEntity;
/*    */ import protocolsupport.server.block.BlockSnow;
/*    */ import protocolsupport.server.item.ItemSnow;
/*    */ import protocolsupport.server.tileentity.TileEntityEnchantTable;
/*    */ import protocolsupport.utils.Utils;
/*    */ 
/*    */ public class ServerInjector
/*    */ {
/*    */   public static void inject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*    */   {
/* 31 */     registerTileEntity(TileEntityEnchantTable.class, "EnchantTable");
/* 32 */     registerBlock(116, "enchanting_table", new protocolsupport.server.block.BlockEnchantTable());
/* 33 */     registerBlock(145, "anvil", new ItemAnvil(new protocolsupport.server.block.BlockAnvil()).b("anvil"));
/* 34 */     registerBlock(171, "carpet", new ItemCloth(new protocolsupport.server.block.BlockCarpet()).b("woolCarpet"));
/* 35 */     registerBlock(78, "snow_layer", new ItemSnow(new BlockSnow()));
/* 36 */     fixBlocksRefs();
/* 37 */     fixShovel();
/* 38 */     org.bukkit.Bukkit.resetRecipes();
/*    */   }
/*    */   
/*    */   private static void registerTileEntity(Class<? extends TileEntity> entityClass, String name) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*    */   {
/* 43 */     ((Map)((Field)Utils.setAccessible(TileEntity.class.getDeclaredField("f"))).get(null)).put(name, entityClass);
/* 44 */     ((Map)((Field)Utils.setAccessible(TileEntity.class.getDeclaredField("g"))).get(null)).put(entityClass, name);
/*    */   }
/*    */   
/*    */   private static void registerBlock(int id, String name, Block block) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*    */   {
/* 49 */     MinecraftKey stringkey = new MinecraftKey(name);
/* 50 */     ItemBlock itemblock = new ItemBlock(block);
/* 51 */     Block.REGISTRY.a(id, stringkey, block);
/* 52 */     Iterator<IBlockData> blockdataiterator = block.P().a().iterator();
/* 53 */     while (blockdataiterator.hasNext()) {
/* 54 */       IBlockData blockdata = (IBlockData)blockdataiterator.next();
/* 55 */       int stateId = id << 4 | block.toLegacyData(blockdata);
/* 56 */       Block.d.a(blockdata, stateId);
/*    */     }
/* 58 */     Item.REGISTRY.a(id, stringkey, itemblock);
/* 59 */     ((Map)((Field)Utils.setAccessible(Item.class.getDeclaredField("a"))).get(null)).put(block, itemblock);
/*    */   }
/*    */   
/*    */   private static void registerBlock(int id, String name, ItemBlock itemblock) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*    */   {
/* 64 */     MinecraftKey stringkey = new MinecraftKey(name);
/* 65 */     Block.REGISTRY.a(id, stringkey, itemblock.d());
/* 66 */     Iterator<IBlockData> blockdataiterator = itemblock.d().P().a().iterator();
/* 67 */     while (blockdataiterator.hasNext()) {
/* 68 */       IBlockData blockdata = (IBlockData)blockdataiterator.next();
/* 69 */       int stateId = id << 4 | itemblock.d().toLegacyData(blockdata);
/* 70 */       Block.d.a(blockdata, stateId);
/*    */     }
/* 72 */     Item.REGISTRY.a(id, stringkey, itemblock);
/* 73 */     ((Map)((Field)Utils.setAccessible(Item.class.getDeclaredField("a"))).get(null)).put(itemblock.d(), itemblock);
/*    */   }
/*    */   
/*    */   private static void fixBlocksRefs() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
/* 77 */     for (Field field : Blocks.class.getDeclaredFields()) {
/* 78 */       field.setAccessible(true);
/* 79 */       if (Block.class.isAssignableFrom(field.getType())) {
/* 80 */         Block block = (Block)field.get(null);
/* 81 */         Block newblock = Block.getById(Block.getId(block));
/* 82 */         if (block != newblock) {
/* 83 */           Utils.setStaticFinalField(field, newblock);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void fixShovel() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*    */   {
/* 91 */     Set<Block> blocks = (Set)((Field)Utils.setAccessible(ItemSpade.class.getDeclaredField("c"))).get(null);
/* 92 */     blocks.add(Blocks.SNOW_LAYER);
/*    */   }
/*    */ }


/* Location:              F:\servers\GTA\plugins\ProtocolSupport-1.8.jar!\protocolsupport\injector\ServerInjector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */