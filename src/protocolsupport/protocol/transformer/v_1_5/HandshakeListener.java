/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.LoginListener
 *  net.minecraft.server.v1_8_R3.NetworkManager
 */
package protocolsupport.protocol.transformer.v_1_5;

import net.minecraft.server.v1_8_R3.NetworkManager;
import protocolsupport.protocol.transformer.handlers.AbstractHandshakeListener;
import protocolsupport.protocol.transformer.v_1_5.LoginListener;

public class HandshakeListener
extends AbstractHandshakeListener {
    public HandshakeListener(NetworkManager networkmanager) {
        super(networkmanager);
    }

    @Override
    public LoginListener getLoginListener(NetworkManager networkManager) {
        return new LoginListener(networkManager);
    }
}

