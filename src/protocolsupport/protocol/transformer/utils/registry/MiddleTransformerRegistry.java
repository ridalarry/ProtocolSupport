/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EnumProtocol
 */
package protocolsupport.protocol.transformer.utils.registry;

import net.minecraft.server.v1_8_R3.EnumProtocol;

public class MiddleTransformerRegistry<T> {
    private static final int enumProtocolLength = EnumProtocol.values().length;
    private final LazyNewInstance<T>[] registry = new LazyNewInstance[enumProtocolLength * 256];

    public void register(EnumProtocol protocol, int packetId, Class<? extends T> packetTransformer) {
        this.registry[MiddleTransformerRegistry.toKey((EnumProtocol)protocol, (int)packetId)] = new LazyNewInstance<T>(packetTransformer);
    }

    public T getTransformer(EnumProtocol protocol, int packetId) throws InstantiationException, IllegalAccessException {
        LazyNewInstance<T> transformer = this.registry[MiddleTransformerRegistry.toKey(protocol, packetId)];
        if (transformer == null) {
            return null;
        }
        return transformer.getInstance();
    }

    static int toKey(EnumProtocol protocol, int packetId) {
        return protocol.ordinal() << 8 | packetId;
    }

    private static class LazyNewInstance<T> {
        private final Class<? extends T> clazz;
        private T instance;

        public LazyNewInstance(Class<? extends T> clazz) {
            this.clazz = clazz;
        }

        public T getInstance() throws InstantiationException, IllegalAccessException {
            if (this.instance == null) {
                this.instance = this.clazz.newInstance();
            }
            return this.instance;
        }
    }

}

