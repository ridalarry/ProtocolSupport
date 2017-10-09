/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 *  org.bukkit.event.HandlerList
 */
package protocolsupport.api.events;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.bukkit.event.HandlerList;
import protocolsupport.api.events.PlayerEvent;

public class PlayerPropertiesResolveEvent
extends PlayerEvent {
    private final HashMap<String, ProfileProperty> properties = new HashMap();
    private static final HandlerList list = new HandlerList();

    public PlayerPropertiesResolveEvent(InetSocketAddress address, String username, List<ProfileProperty> properties) {
        super(address, username);
        for (ProfileProperty property : properties) {
            this.addProperty(property);
        }
    }

    public Map<String, ProfileProperty> getProperties() {
        return new HashMap<String, ProfileProperty>(this.properties);
    }

    public boolean hasProperty(String name) {
        return this.properties.containsKey(name);
    }

    public void removeProperty(String name) {
        this.properties.remove(name);
    }

    public void addProperty(ProfileProperty property) {
        this.properties.put(property.getName(), property);
    }

    public HandlerList getHandlers() {
        return list;
    }

    public static HandlerList getHandlerList() {
        return list;
    }

    public static class ProfileProperty {
        private final String name;
        private final String value;
        private final String signature;

        public ProfileProperty(String name, String value, String signature) {
            Validate.notNull((Object)name, (String)"Name cannot be null", (Object[])new Object[0]);
            Validate.notNull((Object)value, (String)"Value cannot be null", (Object[])new Object[0]);
            this.name = name;
            this.value = value;
            this.signature = signature;
        }

        public ProfileProperty(String name, String value) {
            this(name, value, null);
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public String getSignature() {
            return this.signature;
        }

        public boolean hasSignature() {
            return this.signature != null;
        }
    }

}

