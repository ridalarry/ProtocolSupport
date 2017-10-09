/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleRespawn<T>
extends ClientBoundMiddlePacket<T> {
    protected int dimension;
    protected int difficulty;
    protected int gamemode;
    protected String leveltype;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.dimension = serializer.readInt();
        this.difficulty = serializer.readByte();
        this.gamemode = serializer.readByte();
        this.leveltype = serializer.readString(16);
    }

    @Override
    public void handle() {
        this.storage.clearWatchedEntities();
    }
}

