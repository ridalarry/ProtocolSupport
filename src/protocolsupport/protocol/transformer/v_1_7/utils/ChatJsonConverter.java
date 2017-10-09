/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Item
 *  net.minecraft.server.v1_8_R3.MinecraftKey
 *  net.minecraft.server.v1_8_R3.MinecraftServer
 *  net.minecraft.server.v1_8_R3.MojangsonParseException
 *  net.minecraft.server.v1_8_R3.MojangsonParser
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.RegistryMaterials
 */
package protocolsupport.protocol.transformer.v_1_7.utils;

import java.util.List;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.MinecraftKey;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.MojangsonParseException;
import net.minecraft.server.v1_8_R3.MojangsonParser;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.RegistryMaterials;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.modifiers.ClickAction;
import protocolsupport.api.chat.modifiers.HoverAction;

public class ChatJsonConverter {
    public static String convert(String message) {
        BaseComponent component = ChatAPI.fromJSON(message);
        ChatJsonConverter.walkComponent(component);
        return ChatAPI.toJSON(component);
    }

    private static void walkComponent(BaseComponent component) {
        ChatJsonConverter.fixComponent(component);
        for (BaseComponent sibling : component.getSiblings()) {
            ChatJsonConverter.walkComponent(sibling);
        }
    }

    private static void fixComponent(BaseComponent component) {
        ClickAction click;
        block6 : {
            HoverAction hover = component.getHoverAction();
            if (hover != null && hover.getType() == HoverAction.Type.SHOW_ITEM) {
                try {
                    NBTTagCompound compound = MojangsonParser.parse((String)hover.getValue());
                    String id = compound.getString("id");
                    Item item = (Item)Item.REGISTRY.get(new MinecraftKey(id));
                    if (item != null) {
                        compound.setInt("id", Item.getId((Item)item));
                    }
                    component.setHoverAction(new HoverAction(HoverAction.Type.SHOW_ITEM, compound.toString()));
                }
                catch (MojangsonParseException t) {
                    if (!MinecraftServer.getServer().isDebugging()) break block6;
                    t.printStackTrace();
                }
            }
        }
        if ((click = component.getClickAction()) != null && click.getType() == ClickAction.Type.OPEN_URL) {
            String url = click.getValue();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            component.setClickAction(new ClickAction(ClickAction.Type.OPEN_URL, url));
        }
    }
}

