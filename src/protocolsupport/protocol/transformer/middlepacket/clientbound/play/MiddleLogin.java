/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.api.tab.TabAPI;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedPlayer;

public abstract class MiddleLogin<T>
extends ClientBoundMiddlePacket<T> {
    protected int playerEntityId;
    protected int gamemode;
    protected int dimension;
    protected int difficulty;
    protected int maxplayers;
    protected String leveltype;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.playerEntityId = serializer.readInt();
        this.gamemode = serializer.readByte();
        this.dimension = serializer.readByte();
        this.difficulty = serializer.readByte();
        serializer.readByte();
        this.maxplayers = TabAPI.getMaxTabSize();
        this.leveltype = serializer.readString(16);
    }

    @Override
    public void handle() {
        this.storage.addWatchedSelfPlayer(new WatchedPlayer(this.playerEntityId));
    }
}

