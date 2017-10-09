/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.google.gson.TypeAdapterFactory
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.server.v1_8_R3.ChatDeserializer
 *  net.minecraft.server.v1_8_R3.ChatModifier
 *  net.minecraft.server.v1_8_R3.ChatModifier$ChatModifierSerializer
 *  net.minecraft.server.v1_8_R3.ChatTypeAdapterFactory
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent
 *  net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer
 *  net.minecraft.server.v1_8_R3.ServerPing
 *  net.minecraft.server.v1_8_R3.ServerPing$Serializer
 *  net.minecraft.server.v1_8_R3.ServerPing$ServerData
 *  net.minecraft.server.v1_8_R3.ServerPing$ServerPingPlayerSample
 */
package protocolsupport.protocol.transformer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.ChatDeserializer;
import net.minecraft.server.v1_8_R3.ChatModifier;
import net.minecraft.server.v1_8_R3.ChatTypeAdapterFactory;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.ServerPing;

public class ServerPingSerializers {
    public static final Gson PING_GSON = new GsonBuilder().registerTypeAdapter(ServerPing.ServerData.class, (Object)new ServerDataSerializer()).registerTypeAdapter(ServerPing.ServerPingPlayerSample.class, (Object)new PlayerSampleSerializer()).registerTypeAdapter(ServerPing.class, (Object)new ServerPing.Serializer()).registerTypeHierarchyAdapter(IChatBaseComponent.class, (Object)new IChatBaseComponent.ChatSerializer()).registerTypeHierarchyAdapter(ChatModifier.class, (Object)new ChatModifier.ChatModifierSerializer()).registerTypeAdapterFactory((TypeAdapterFactory)new ChatTypeAdapterFactory()).create();

    public static class PlayerSampleSerializer
    implements JsonDeserializer<ServerPing.ServerPingPlayerSample>,
    JsonSerializer<ServerPing.ServerPingPlayerSample> {
        public ServerPing.ServerPingPlayerSample deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
            JsonArray sample;
            JsonObject players = ChatDeserializer.l((JsonElement)element, (String)"players");
            ServerPing.ServerPingPlayerSample serverPingPlayerSample = new ServerPing.ServerPingPlayerSample(ChatDeserializer.m((JsonObject)players, (String)"max"), ChatDeserializer.m((JsonObject)players, (String)"online"));
            if (ChatDeserializer.d((JsonObject)players, (String)"sample") && (sample = ChatDeserializer.t((JsonObject)players, (String)"sample")).size() > 0) {
                GameProfile[] array = new GameProfile[sample.size()];
                for (int i = 0; i < array.length; ++i) {
                    JsonObject j = ChatDeserializer.l((JsonElement)sample.get(i), (String)("player[" + i + "]"));
                    String h = ChatDeserializer.h((JsonObject)j, (String)"id");
                    array[i] = new GameProfile(UUID.fromString(h), ChatDeserializer.h((JsonObject)j, (String)"name"));
                }
                serverPingPlayerSample.a(array);
            }
            return serverPingPlayerSample;
        }

        public JsonElement serialize(ServerPing.ServerPingPlayerSample data, Type type, JsonSerializationContext ctx) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("max", (Number)data.a());
            jsonObject.addProperty("online", (Number)data.b());
            if (data.c() != null && data.c().length > 0) {
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < data.c().length; ++i) {
                    JsonObject jsonObject2 = new JsonObject();
                    UUID id = data.c()[i].getId();
                    jsonObject2.addProperty("id", id == null ? "" : id.toString());
                    jsonObject2.addProperty("name", data.c()[i].getName());
                    jsonArray.add((JsonElement)jsonObject2);
                }
                jsonObject.add("sample", (JsonElement)jsonArray);
            }
            return jsonObject;
        }
    }

    public static class ServerDataSerializer
    implements JsonDeserializer<ServerPing.ServerData>,
    JsonSerializer<ServerPing.ServerData> {
        public ServerPing.ServerData deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
            JsonObject l = ChatDeserializer.l((JsonElement)element, (String)"version");
            return new ServerPing.ServerData(ChatDeserializer.h((JsonObject)l, (String)"name"), ChatDeserializer.m((JsonObject)l, (String)"protocol"));
        }

        public JsonElement serialize(ServerPing.ServerData data, Type type, JsonSerializationContext ctx) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", data.a());
            jsonObject.addProperty("protocol", (Number)data.b());
            return jsonObject;
        }
    }

}

