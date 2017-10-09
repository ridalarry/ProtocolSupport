/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  gnu.trove.map.hash.TIntObjectHashMap
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 */
package protocolsupport.protocol.typeremapper.nbt.tileupdate;

import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.EnumMap;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.typeremapper.nbt.tileupdate.SpecificTransformer;
import protocolsupport.utils.ProtocolVersionsHelper;

public class TileNBTTransformer {
    private static final TIntObjectHashMap<EnumMap<ProtocolVersion, SpecificTransformer>> registry = new TIntObjectHashMap();

    private static /* varargs */ void register(int type, SpecificTransformer transformer, ProtocolVersion ... versions) {
        registry.putIfAbsent(type, new EnumMap(ProtocolVersion.class));
        EnumMap map = (EnumMap)registry.get(type);
        for (ProtocolVersion version : versions) {
            map.put(version, transformer);
        }
    }

    public static NBTTagCompound transform(int type, ProtocolVersion version, NBTTagCompound compound) {
        SpecificTransformer transformer;
        EnumMap map = (EnumMap)registry.get(type);
        if (map != null && (transformer = (SpecificTransformer)map.get((Object)version)) != null) {
            return transformer.transform(compound);
        }
        return compound;
    }

    static {
        TileNBTTransformer.register(1, new SpecificTransformer(){

            @Override
            public NBTTagCompound transform(NBTTagCompound input) {
                input.remove("SpawnPotentials");
                input.remove("SpawnData");
                return input;
            }
        }, ProtocolVersionsHelper.BEFORE_1_7);
        TileNBTTransformer.register(4, new SpecificTransformer(){

            @Override
            public NBTTagCompound transform(NBTTagCompound input) {
                PacketDataSerializer.transformSkull(input);
                return input;
            }
        }, ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_7_5));
    }

}

