/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.api.chat.components;

import protocolsupport.api.chat.components.BaseComponent;

public class TextComponent
extends BaseComponent {
    private String text;

    public TextComponent(String text) {
        this.text = text;
    }

    @Override
    public String getValue() {
        return this.text;
    }
}

