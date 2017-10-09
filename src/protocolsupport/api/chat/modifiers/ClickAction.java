/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.api.chat.modifiers;

import java.net.MalformedURLException;
import java.net.URL;

public class ClickAction {
    private Type type;
    private String value;

    public ClickAction(Type action, String value) {
        this.type = action;
        this.value = value;
    }

    public ClickAction(URL url) {
        this.type = Type.OPEN_URL;
        this.value = url.toString();
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public URL getUrl() throws MalformedURLException {
        if (this.type == Type.OPEN_URL) {
            return new URL(this.value);
        }
        throw new IllegalStateException((Object)((Object)this.type) + " is not an " + (Object)((Object)Type.OPEN_URL));
    }

    public static enum Type {
        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        TWITCH_USER_INFO,
        SUGGEST_COMMAND,
        CHANGE_PAGE;
        

        private Type() {
        }
    }

}

