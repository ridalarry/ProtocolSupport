/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddlePosition<T>
extends ClientBoundMiddlePacket<T> {
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.x = serializer.readDouble();
        this.y = serializer.readDouble();
        this.z = serializer.readDouble();
        this.yaw = serializer.readFloat();
        this.pitch = serializer.readFloat();
        short field = serializer.readByte();
        Location location = this.player.getLocation();
        if ((field & 1) != 0) {
            this.x += location.getX();
        }
        if ((field & 2) != 0) {
            this.y += location.getY();
        }
        if ((field & 4) != 0) {
            this.z += location.getX();
        }
        if ((field & 8) != 0) {
            this.yaw += location.getYaw();
        }
        if ((field & 16) != 0) {
            this.pitch += location.getPitch();
        }
    }
}

