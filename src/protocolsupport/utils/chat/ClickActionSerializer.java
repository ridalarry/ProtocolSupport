/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package protocolsupport.utils.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import protocolsupport.api.chat.modifiers.ClickAction;

public class ClickActionSerializer
implements JsonDeserializer<ClickAction>,
JsonSerializer<ClickAction> {
    public ClickAction deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject jsonobject = element.getAsJsonObject();
        if (!jsonobject.has("clickEvent")) {
            return null;
        }
        JsonObject clickObject = jsonobject.getAsJsonObject("clickEvent");
        ClickAction.Type atype = ClickAction.Type.valueOf(clickObject.getAsJsonPrimitive("action").getAsString().toUpperCase());
        return new ClickAction(atype, clickObject.getAsJsonPrimitive("value").getAsString());
    }

    public JsonElement serialize(ClickAction action, Type type, JsonSerializationContext ctx) {
        JsonObject object = new JsonObject();
        object.addProperty("action", action.getType().toString().toLowerCase());
        object.addProperty("value", action.getValue());
        return object;
    }
}

