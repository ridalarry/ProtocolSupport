/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  org.bukkit.ChatColor
 */
package protocolsupport.utils.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.bukkit.ChatColor;
import protocolsupport.api.chat.modifiers.Modifier;
import protocolsupport.utils.JsonUtils;

public class ModifierSerializer
implements JsonDeserializer<Modifier>,
JsonSerializer<Modifier> {
    public Modifier deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject jsonobject = element.getAsJsonObject();
        Modifier modifier = new Modifier();
        if (jsonobject.has("bold")) {
            modifier.setBold(jsonobject.get("bold").getAsBoolean());
        }
        if (jsonobject.has("italic")) {
            modifier.setItalic(jsonobject.get("italic").getAsBoolean());
        }
        if (jsonobject.has("underlined")) {
            modifier.setUnderlined(jsonobject.get("underlined").getAsBoolean());
        }
        if (jsonobject.has("strikethrough")) {
            modifier.setStrikethrough(jsonobject.get("strikethrough").getAsBoolean());
        }
        if (jsonobject.has("obfuscated")) {
            modifier.setRandom(jsonobject.get("obfuscated").getAsBoolean());
        }
        if (jsonobject.has("color")) {
            modifier.setColor(ChatColor.valueOf((String)jsonobject.get("color").getAsString().toUpperCase()));
        }
        return modifier;
    }

    public JsonElement serialize(Modifier modifier, Type type, JsonSerializationContext ctx) {
        JsonObject jsonobject = new JsonObject();
        JsonUtils.setIfNotNull(jsonobject, "bold", modifier.isBold());
        JsonUtils.setIfNotNull(jsonobject, "italic", modifier.isItalic());
        JsonUtils.setIfNotNull(jsonobject, "underlined", modifier.isUnderlined());
        JsonUtils.setIfNotNull(jsonobject, "strikethrough", modifier.isStrikethrough());
        JsonUtils.setIfNotNull(jsonobject, "obfuscated", modifier.isRandom());
        ChatColor color = modifier.getColor();
        if (color != null) {
            jsonobject.addProperty("color", color.name().toLowerCase());
        }
        return jsonobject;
    }
}

