/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 */
package protocolsupport.api.remapper;

import org.bukkit.Material;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;

public class BlockRemapperControl {
    private final RemappingTable table;

    public BlockRemapperControl(ProtocolVersion version) {
        switch (version) {
            case MINECRAFT_1_8: {
                throw new IllegalArgumentException("Remapper for version " + (Object)((Object)version) + " doesn't exist");
            }
            case UNKNOWN: {
                throw new IllegalArgumentException((Object)((Object)version) + " is not a valid protocol version");
            }
        }
        this.table = IdRemapper.BLOCK.getTable(version);
    }

    public void setRemap(Material from, Material to) {
        this.setRemap(from.getId(), to.getId());
    }

    public void setRemap(int from, int to) {
        this.table.setRemap(from, to);
    }

    public Material getRemap(Material material) {
        return Material.getMaterial((int)this.getRemap(material.getId()));
    }

    public int getRemap(int id) {
        return this.table.getRemap(id);
    }

}

