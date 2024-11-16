package com.github.heartblade.item;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;

public class Utils {
    public Utils() {
    }

    public static ChatComponentText coloredText(String text) {
        return new ChatComponentText(text.replaceAll("&([0-9a-z])", "§$1"));
    }

    public static String coloredString(String text) {
        return text.replaceAll("&([0-9a-z])", "§$1");
    }

    public static ChatComponentText coloredLink(String text, String url) {
        ChatComponentText ret = coloredText(text);
        ret.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, url));
        return ret;
    }
}