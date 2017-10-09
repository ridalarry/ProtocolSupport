/*
 * Decompiled with CFR 0_122.
 */
package protocolsupport.api.chat.components;

import protocolsupport.api.chat.components.BaseComponent;

public class SelectorComponent
extends BaseComponent {
    private String selector;

    public SelectorComponent(String selector) {
        this.selector = selector;
    }

    @Override
    public String getValue() {
        return this.selector;
    }
}

