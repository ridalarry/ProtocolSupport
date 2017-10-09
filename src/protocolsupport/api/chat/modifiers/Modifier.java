/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package protocolsupport.api.chat.modifiers;

import org.bukkit.ChatColor;

public class Modifier {
    private ChatColor color;
    private Boolean bold;
    private Boolean italic;
    private Boolean underlined;
    private Boolean strikethrough;
    private Boolean obfuscated;

    public boolean isEmpty() {
        return this.color == null && this.bold == null && this.italic == null && this.underlined == null && this.strikethrough == null && this.obfuscated == null;
    }

    public void clear() {
        this.color = null;
        this.bold = null;
        this.italic = null;
        this.underlined = null;
        this.strikethrough = null;
        this.obfuscated = null;
    }

    public void set(ChatColor color) {
        if (color == null) {
            this.clear();
            return;
        }
        if (color.isColor()) {
            this.color = color;
        } else if (color.isFormat()) {
            switch (color) {
                case BOLD: {
                    this.bold = true;
                    break;
                }
                case ITALIC: {
                    this.italic = true;
                    break;
                }
                case STRIKETHROUGH: {
                    this.strikethrough = true;
                    break;
                }
                case UNDERLINE: {
                    this.underlined = true;
                    break;
                }
                case MAGIC: {
                    this.obfuscated = true;
                    break;
                }
            }
        }
        if (color == ChatColor.RESET) {
            this.color = ChatColor.WHITE;
            this.bold = false;
            this.italic = false;
            this.strikethrough = false;
            this.underlined = false;
            this.obfuscated = false;
        }
    }

    public ChatColor getColor() {
        return this.color;
    }

    public void setColor(ChatColor color) {
        if (color == null) {
            this.color = null;
            return;
        }
        if (color.isColor()) {
            this.color = color;
        }
    }

    public Boolean isBold() {
        return this.bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean isItalic() {
        return this.italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean isUnderlined() {
        return this.underlined;
    }

    public void setUnderlined(Boolean underlined) {
        this.underlined = underlined;
    }

    public Boolean isStrikethrough() {
        return this.strikethrough;
    }

    public void setStrikethrough(Boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public Boolean isRandom() {
        return this.obfuscated;
    }

    public void setRandom(Boolean random) {
        this.obfuscated = random;
    }

}

