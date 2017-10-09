package protocolsupport.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {
    public static void setIfNotNull(JsonObject jsonElement, String name, Boolean value) {
        if (value != null) {
            jsonElement.addProperty(name, value);
        }
    }

    public static String asString(JsonElement jsonElement, String name) {
        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        }
        throw new JsonSyntaxException("Expected " + name + " to be a string, was " + JsonUtils.toString(jsonElement));
    }

    public static String getString(JsonObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            return JsonUtils.asString(jsonObject.get(name), name);
        }
        throw new JsonSyntaxException("Missing " + name + ", expected to find a string");
    }

    private static String toString(JsonElement jsonElement) {
        String abbreviateMiddle = StringUtils.abbreviateMiddle((String)String.valueOf((Object)jsonElement), (String)"...", (int)10);
        if (jsonElement == null) {
            return "null (missing)";
        }
        if (jsonElement.isJsonNull()) {
            return "null (json)";
        }
        if (jsonElement.isJsonArray()) {
            return "an array (" + abbreviateMiddle + ")";
        }
        if (jsonElement.isJsonObject()) {
            return "an object (" + abbreviateMiddle + ")";
        }
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                return "a number (" + abbreviateMiddle + ")";
            }
            if (asJsonPrimitive.isBoolean()) {
                return "a boolean (" + abbreviateMiddle + ")";
            }
        }
        return abbreviateMiddle;
    }
}

