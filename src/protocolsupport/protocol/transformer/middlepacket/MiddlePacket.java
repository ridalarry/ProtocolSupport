/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package protocolsupport.protocol.transformer.middlepacket;

import org.bukkit.entity.Player;

public abstract class MiddlePacket {
    protected Player player;

    public boolean needsPlayer() {
        return false;
    }

    public final void setPlayer(Player player) {
        this.player = player;
    }
}

