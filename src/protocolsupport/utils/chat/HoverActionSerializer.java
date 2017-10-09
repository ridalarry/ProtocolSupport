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
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.modifiers.HoverAction;

public class HoverActionSerializer
implements JsonDeserializer<HoverAction>,
JsonSerializer<HoverAction> {
    public HoverAction deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject jsonobject = element.getAsJsonObject();
        if (!jsonobject.has("hoverEvent")) {
            return null;
        }
        JsonObject clickObject = jsonobject.getAsJsonObject("hoverEvent");
        HoverAction.Type atype = HoverAction.Type.valueOf(clickObject.getAsJsonPrimitive("action").getAsString().toUpperCase());
        BaseComponent component = (BaseComponent)ctx.deserialize(clickObject.get("value"), BaseComponent.class);
        return new HoverAction(atype, atype == HoverAction.Type.SHOW_TEXT ? ChatAPI.toJSON(component) : component.getValue());
    }

    public JsonElement serialize(HoverAction action, Type type, JsonSerializationContext ctx) {
        JsonObject object = new JsonObject();
        object.addProperty("action", action.getType().toString().toLowerCase());
        object.add("value", action.getType() == HoverAction.Type.SHOW_TEXT ? ctx.serialize((Object)ChatAPI.fromJSON(action.getValue())) : new JsonPrimitive(action.getValue()));
        return object;
    }
}

