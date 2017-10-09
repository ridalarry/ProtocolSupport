/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package protocolsupport.protocol.transformer.v_1_6.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ChatEncoder {
    private static final Gson gson = new GsonBuilder().create();

    public static String encode(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("text", message);
        return gson.toJson((JsonElement)jsonObject);
    }
}

