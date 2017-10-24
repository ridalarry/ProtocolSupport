package protocolsupport.protocol.typeremapper.itemstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import org.bukkit.Material;

import gnu.trove.decorator.TIntObjectMapDecorator;
import gnu.trove.map.hash.TIntObjectHashMap;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.itemstack.fromclient.MonsterEggFromLegacyIdRemapper;
import protocolsupport.protocol.typeremapper.itemstack.fromclient.PotionFromLegacyIdRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.BookPagesToLegacyTextSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.DragonHeadSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.EmptyBookPageAdderSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.EnchantFilterNBTSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.MonsterEggToLegacyIdSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.MonsterEggToLegacyNameSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.PlayerSkullToLegacyOwnerSpecificRemapper;
import protocolsupport.protocol.typeremapper.itemstack.toclient.PotionToLegacyIdSpecificRemapper;
import protocolsupport.protocol.typeremapper.utils.RemappingRegistry;
import protocolsupport.protocol.typeremapper.utils.RemappingTable.ComplexIdRemappingTable;
import protocolsupport.protocol.utils.ProtocolVersionsHelper;
import protocolsupport.utils.Utils;
import protocolsupport.zplatform.itemstack.ItemStackWrapper;

@SuppressWarnings("deprecation")
public class ItemStackRemapper {

	public static final ItemIdDataRemappingRegistry ID_DATA_REMAPPING_REGISTRY = new ItemIdDataRemappingRegistry();

	public static class ItemIdDataRemappingRegistry extends RemappingRegistry<ComplexIdRemappingTable> {
		{
			applyDefaultRemaps();
		}
		public void applyDefaultRemaps() {
			remappings.clear();
			//modified copy of blocks remapper adapted to items
			registerRemapEntry(Material.SLIME_BLOCK, Material.EMERALD_BLOCK, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BARRIER, Material.GLASS, ProtocolVersionsHelper.BEFORE_1_8);
			//TODO: remap with something that has more strength
			registerRemapEntry(Material.IRON_TRAPDOOR, Material.TRAP_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.PRISMARINE, Material.MOSSY_COBBLESTONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.SEA_LANTERN, Material.GLOWSTONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.STANDING_BANNER, Material.SIGN_POST, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.WALL_BANNER, Material.WALL_SIGN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RED_SANDSTONE, Material.SANDSTONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RED_SANDSTONE_STAIRS, Material.SANDSTONE_STAIRS, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DOUBLE_STONE_SLAB2, Material.DOUBLE_STEP, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.STONE_SLAB2, Material.STEP, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.SPRUCE_FENCE_GATE, Material.FENCE_GATE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BIRCH_FENCE_GATE, Material.FENCE_GATE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.JUNGLE_FENCE_GATE, Material.FENCE_GATE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DARK_OAK_FENCE_GATE, Material.FENCE_GATE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.ACACIA_FENCE_GATE, Material.FENCE_GATE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.SPRUCE_FENCE, Material.FENCE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BIRCH_FENCE, Material.FENCE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.JUNGLE_FENCE, Material.FENCE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DARK_OAK_FENCE, Material.FENCE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.ACACIA_FENCE, Material.FENCE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.SPRUCE_DOOR, Material.WOODEN_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BIRCH_DOOR, Material.WOODEN_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.JUNGLE_DOOR, Material.WOODEN_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.ACACIA_DOOR, Material.WOODEN_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DARK_OAK_DOOR, Material.WOODEN_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DAYLIGHT_DETECTOR_INVERTED, Material.DAYLIGHT_DETECTOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.STAINED_GLASS, Material.GLASS, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.STAINED_GLASS_PANE, Material.THIN_GLASS, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.LEAVES_2, Material.LEAVES, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.LOG_2, Material.LOG, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.ACACIA_STAIRS, Material.WOOD_STAIRS, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.DARK_OAK_STAIRS, Material.WOOD_STAIRS, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.DOUBLE_PLANT, Material.YELLOW_FLOWER, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.PACKED_ICE, Material.WOOL, 3, ProtocolVersionsHelper.BEFORE_1_7);
			registerRemapEntry(Material.STAINED_CLAY, Material.STONE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.HAY_BLOCK, Material.STONE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.CARPET, Material.SNOW, 0, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.HARD_CLAY, Material.STONE, 0, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.COAL_BLOCK, Material.OBSIDIAN, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.DROPPER, Material.FURNACE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.HOPPER, Material.FURNACE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.QUARTZ_BLOCK, Material.STONE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.QUARTZ_STAIRS, Material.SMOOTH_STAIRS, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.DAYLIGHT_DETECTOR_INVERTED, Material.STEP, 0, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.DAYLIGHT_DETECTOR, Material.STEP, 0, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.TRAPPED_CHEST, Material.CHEST, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.REDSTONE_BLOCK, Material.DIAMOND_BLOCK, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.QUARTZ_ORE, Material.COAL_ORE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.GOLD_PLATE, Material.STONE_PLATE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.IRON_PLATE, Material.STONE_PLATE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.REDSTONE_COMPARATOR_OFF, Material.DIODE_BLOCK_OFF, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.REDSTONE_COMPARATOR_ON, Material.DIODE_BLOCK_ON, ProtocolVersionsHelper.BEFORE_1_5);
			//items remap
			registerRemapEntry(Material.BED, Material.BED, 0, ProtocolVersionsHelper.BEFORE_1_12);
			registerRemapEntry(Material.SPRUCE_DOOR_ITEM, Material.WOOD_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BIRCH_DOOR_ITEM, Material.WOOD_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.JUNGLE_DOOR_ITEM, Material.WOOD_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.ACACIA_DOOR_ITEM, Material.WOOD_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.DARK_OAK_DOOR_ITEM, Material.WOOD_DOOR, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RABBIT, Material.RAW_CHICKEN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.COOKED_RABBIT, Material.COOKED_CHICKEN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RABBIT_STEW, Material.MUSHROOM_SOUP, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.MUTTON, Material.RAW_CHICKEN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.COOKED_MUTTON, Material.COOKED_CHICKEN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.BANNER, Material.SIGN, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.PRISMARINE_SHARD, Material.STONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.PRISMARINE_CRYSTALS, Material.STONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RABBIT_FOOT, Material.STONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.RABBIT_HIDE, Material.STONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.ARMOR_STAND, Material.STONE, ProtocolVersionsHelper.BEFORE_1_8);
			registerRemapEntry(Material.IRON_BARDING, Material.LEATHER_CHESTPLATE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.GOLD_BARDING, Material.LEATHER_CHESTPLATE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.DIAMOND_BARDING, Material.LEATHER_CHESTPLATE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.LEASH, Material.STONE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.NAME_TAG, Material.STONE, ProtocolVersionsHelper.BEFORE_1_6);
			registerRemapEntry(Material.EXPLOSIVE_MINECART, Material.MINECART, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.HOPPER_MINECART, Material.MINECART, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.REDSTONE_COMPARATOR, Material.DIODE, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.NETHER_BRICK_ITEM, Material.CLAY_BRICK, ProtocolVersionsHelper.BEFORE_1_5);
			registerRemapEntry(Material.QUARTZ, Material.FEATHER, ProtocolVersionsHelper.BEFORE_1_5);
		}
		private void registerRemapEntry(Material from, Material to, ProtocolVersion... versions) {
			registerRemapEntry(from, to, -1, versions);
		}
		private void registerRemapEntry(Material from, Material to, int dataTo, ProtocolVersion... versions) {
			for (ProtocolVersion version : versions) {
				getTable(version).setSingleRemap(from.getId(), to.getId(), dataTo);
			}
		}
		@Override
		protected ComplexIdRemappingTable createTable() {
			return new ComplexIdRemappingTable();
		}
	}

	private static final TIntObjectHashMap<EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>>> toClientRemapper = new TIntObjectHashMap<>();
	private static final TIntObjectHashMap<EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>>> fromClientRemapper = new TIntObjectHashMap<>();

	private static void registerToClientRemapper(Material material, ItemStackSpecificRemapper transformer, ProtocolVersion... versions) {
		registerRemapper(toClientRemapper, material, transformer, versions);
	}

	private static void registerFromClientRemapper(Material material, ItemStackSpecificRemapper transformer, ProtocolVersion... versions) {
		registerRemapper(fromClientRemapper, material, transformer, versions);
	}

	private static void registerRemapper(TIntObjectHashMap<EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>>> registry, Material material, ItemStackSpecificRemapper transformer, ProtocolVersion... versions) {
		EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>> map = Utils.getFromMapOrCreateDefault(
			new TIntObjectMapDecorator<EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>>>(registry),
			material.getId(), new EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>>(ProtocolVersion.class)
		);
		Arrays.stream(versions).forEach(version -> Utils.getFromMapOrCreateDefault(map, version, new ArrayList<ItemStackSpecificRemapper>()).add(transformer));
	}

	//Order is important because some transformers may add tags in new format
	static {
		registerToClientRemapper(Material.MONSTER_EGG, new MonsterEggToLegacyNameSpecificRemapper(), ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_10, ProtocolVersion.MINECRAFT_1_9));
		registerToClientRemapper(Material.MONSTER_EGG, new MonsterEggToLegacyIdSpecificRemapper(), ProtocolVersionsHelper.BEFORE_1_9);
		registerToClientRemapper(Material.SKULL_ITEM, new DragonHeadSpecificRemapper(), ProtocolVersionsHelper.BEFORE_1_9);
		registerToClientRemapper(Material.SKULL_ITEM, new PlayerSkullToLegacyOwnerSpecificRemapper(), ProtocolVersion.getAllBeforeI(ProtocolVersion.MINECRAFT_1_7_5));
		registerToClientRemapper(Material.POTION, new PotionToLegacyIdSpecificRemapper(false), ProtocolVersionsHelper.BEFORE_1_9);
		registerToClientRemapper(Material.WRITTEN_BOOK, new BookPagesToLegacyTextSpecificRemapper(), ProtocolVersionsHelper.BEFORE_1_8);
		registerToClientRemapper(Material.BOOK_AND_QUILL, new EmptyBookPageAdderSpecificRemapper(), ProtocolVersionsHelper.ALL_PC);
		EnchantFilterNBTSpecificRemapper enchantfilter = new EnchantFilterNBTSpecificRemapper();
		Arrays.stream(Material.values()).forEach(material -> registerToClientRemapper(material, enchantfilter, ProtocolVersionsHelper.ALL_PC));
		registerFromClientRemapper(Material.POTION, new PotionFromLegacyIdRemapper(), ProtocolVersionsHelper.BEFORE_1_9);
		registerFromClientRemapper(Material.MONSTER_EGG, new MonsterEggFromLegacyIdRemapper(), ProtocolVersionsHelper.BEFORE_1_9);
	}

	public static ItemStackWrapper remapToClient(ProtocolVersion version, String locale, int originalTypeId, ItemStackWrapper itemstack) {
		return remap(toClientRemapper, version, locale, originalTypeId, itemstack);
	}

	public static ItemStackWrapper remapFromClient(ProtocolVersion version, String locale, ItemStackWrapper itemstack) {
		return remap(fromClientRemapper, version, locale, itemstack.getTypeId(), itemstack);
	}

	private static ItemStackWrapper remap(
			TIntObjectHashMap<EnumMap<ProtocolVersion,
			List<ItemStackSpecificRemapper>>> registry,
			ProtocolVersion version, String locale,
			int originalTypeId, ItemStackWrapper itemstack
	) {
		EnumMap<ProtocolVersion, List<ItemStackSpecificRemapper>> map = registry.get(originalTypeId);
		if (map != null) {
			List<ItemStackSpecificRemapper> transformers = map.get(version);
			if (transformers != null) {
				for (ItemStackSpecificRemapper transformer : transformers) {
					itemstack = transformer.remap(version, locale, itemstack);
				}
				return itemstack;
			}
		}
		return itemstack;
	}

}
