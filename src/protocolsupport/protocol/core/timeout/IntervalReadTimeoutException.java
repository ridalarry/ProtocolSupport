/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 */
package protocolsupport.protocol.core.timeout;

import net.minecraft.server.v1_8_R3.MinecraftServer;

public class IntervalReadTimeoutException
extends RuntimeException {
    private static final long serialVersionUID = 1;
    private static final IntervalReadTimeoutException notraceinstance = new IntervalReadTimeoutException(-1){
        private static final long serialVersionUID = 1;

        @Override
        public IntervalReadTimeoutException fillInStackTrace() {
            return this;
        }
    };

    public static IntervalReadTimeoutException getInstance(long lastReadTime) {
        return MinecraftServer.getServer().isDebugging() ? new IntervalReadTimeoutException(lastReadTime) : notraceinstance;
    }

    private IntervalReadTimeoutException(long lastReadTime) {
        super(lastReadTime == -1 ? "read timed out" : "read timed out: last message recv time: " + lastReadTime);
    }

}

