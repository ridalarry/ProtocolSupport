/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.typeremapper.id;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.ProtocolVersionsHelper;

public class IdRemapper {
    public static final RemappingRegistry BLOCK = new RemappingRegistry(){

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(4096);
        }
    };
    public static final RemappingRegistry ITEM = new RemappingRegistry(){

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(4096);
        }
    };
    public static final RemappingRegistry ENTITY = new RemappingRegistry(){

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(256);
        }
    };
    public static final RemappingRegistry MAPCOLOR = new RemappingRegistry(){

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(64){

                @Override
                public int getRemap(int id) {
                    int realColor = (id & 255) >> 2;
                    return (this.table[realColor] << 2) + (id & 3);
                }
            };
        }

    };

}

