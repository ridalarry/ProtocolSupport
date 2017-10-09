/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.api.chat.components;

import java.util.ArrayList;
import java.util.List;
import protocolsupport.api.chat.components.BaseComponent;

public class TranslateComponent
extends BaseComponent {
    private String translationKey;
    private List<Object> args = new ArrayList<Object>();

    public /* varargs */ TranslateComponent(String translationKey, Object ... values) {
        this.translationKey = translationKey;
        for (Object v : values) {
            this.args.add(v);
        }
    }

    public String getTranslationKey() {
        return this.translationKey;
    }

    public List<Object> getArgs() {
        return this.args;
    }

    @Override
    public String getValue() {
        return "";
    }
}

