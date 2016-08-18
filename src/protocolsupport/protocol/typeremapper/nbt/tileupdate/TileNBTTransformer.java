package protocolsupport.protocol.typeremapper.nbt.tileupdate;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import net.minecraft.server.v1_10_R1.Item;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.serializer.ProtocolSupportPacketDataSerializer;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.utils.types.NBTTagCompoundWrapper;
import protocolsupport.utils.ProtocolVersionsHelper;
import protocolsupport.utils.Utils;

public class TileNBTTransformer {

	public static enum TileEntityUpdateType {
		UNKNOWN, MOB_SPAWNER, COMMAND_BLOCK, BEACON, SKULL, FLOWER_POT, BANNER, STRUCTURE, END_GATEWAY, SIGN; 
	}

	private static final EnumMap<TileEntityUpdateType, EnumMap<ProtocolVersion, List<SpecificTransformer>>> registry = new EnumMap<>(TileEntityUpdateType.class);

	private static void register(TileEntityUpdateType type, SpecificTransformer transformer, ProtocolVersion... versions) {
		EnumMap<ProtocolVersion, List<SpecificTransformer>> map = Utils.getOrCreateDefault(registry, type, new EnumMap<ProtocolVersion, List<SpecificTransformer>>(ProtocolVersion.class));
		for (ProtocolVersion version : versions) {
			Utils.getOrCreateDefault(map, version, new ArrayList<SpecificTransformer>()).add(transformer);
		}
	}

	static {
		register(
			TileEntityUpdateType.MOB_SPAWNER,
			new SpecificTransformer() {
				@Override
				public NBTTagCompoundWrapper transform(ProtocolVersion version, NBTTagCompoundWrapper input) {
					NBTTagCompoundWrapper spawndata = input.getCompound("SpawnData");
					input.remove("SpawnPotentials");
					input.remove("SpawnData");
					if (!spawndata.isNull()) {
						String mobname = spawndata.getString("id");
						if (!mobname.isEmpty()) {
							input.setString("EntityId", mobname);
						}
					}
					return input;
				}
			},
			ProtocolVersionsHelper.BEFORE_1_9
		);
		register(
			TileEntityUpdateType.SKULL,
			new SpecificTransformer() {
				@Override
				public NBTTagCompoundWrapper transform(ProtocolVersion version, NBTTagCompoundWrapper input) {
					if (input.getNumber("SkullType") == 5) {
						input.setByte("SkullType", 3);
						input.unwrap().set("Owner", ProtocolSupportPacketDataSerializer.createDragonHeadSkullTag());
					}
					return input;
				}
			},
			ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_9)
		);
		register(
			TileEntityUpdateType.SKULL,
			new SpecificTransformer() {
				@Override
				public NBTTagCompoundWrapper transform(ProtocolVersion version, NBTTagCompoundWrapper input) {
					ProtocolSupportPacketDataSerializer.transformSkull(input.unwrap());
					return input;
				}
			},
			ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_7_5)
		);
		register(
			TileEntityUpdateType.FLOWER_POT,
			new SpecificTransformer() {
				@Override
				public NBTTagCompoundWrapper transform(ProtocolVersion version, NBTTagCompoundWrapper input) {
					String itemId = input.getString("Item");
					if (!itemId.isEmpty()) {
						input.setInt("Item", IdRemapper.ITEM.getTable(version).getRemap(Item.getId(Item.d(itemId))));
					}
					return input;
				}
			},
			ProtocolVersionsHelper.BEFORE_1_8
		);
	}

	public static NBTTagCompoundWrapper transform(int type, ProtocolVersion version, NBTTagCompoundWrapper compound) {
		EnumMap<ProtocolVersion, List<SpecificTransformer>> map = registry.get(TileEntityUpdateType.values()[type]);
		if (map != null) {
			List<SpecificTransformer> transformers = map.get(version);
			if (transformers != null) {
				for (SpecificTransformer transformer : transformers) {
					compound = transformer.transform(version, compound);
				}
				return compound;
			}
		}
		return compound;
	}

}
