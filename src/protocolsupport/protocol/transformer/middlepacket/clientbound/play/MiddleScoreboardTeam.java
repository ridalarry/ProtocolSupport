/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleScoreboardTeam<T>
extends ClientBoundMiddlePacket<T> {
    protected String name;
    protected int mode;
    protected String displayName;
    protected String prefix;
    protected String suffix;
    protected int friendlyFire;
    protected String nameTagVisibility;
    protected int color;
    protected String[] players;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.name = serializer.readString(16);
        this.mode = serializer.readUnsignedByte();
        if (this.mode == 0 || this.mode == 2) {
            this.displayName = serializer.readString(32);
            this.prefix = serializer.readString(16);
            this.suffix = serializer.readString(16);
            this.friendlyFire = serializer.readUnsignedByte();
            this.nameTagVisibility = serializer.readString(32);
            this.color = serializer.readUnsignedByte();
        }
        if (this.mode == 0 || this.mode == 3 || this.mode == 4) {
            this.players = new String[serializer.readVarInt()];
            for (int i = 0; i < this.players.length; ++i) {
                this.players[i] = serializer.readString(40);
            }
        }
    }
}

