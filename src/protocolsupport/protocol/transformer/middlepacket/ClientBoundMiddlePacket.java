/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.protocol.transformer.middlepacket;

import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.MiddlePacket;

public abstract class ClientBoundMiddlePacket<T>
extends MiddlePacket {
    protected LocalStorage storage;

    public void setLocalStorage(LocalStorage storage) {
        this.storage = storage;
    }

    public void handle() {
    }

    public abstract void readFromServerData(PacketDataSerializer var1) throws IOException;

    public abstract T toData(ProtocolVersion var1) throws IOException;
}

