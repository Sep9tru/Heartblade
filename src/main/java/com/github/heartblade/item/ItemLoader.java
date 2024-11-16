package com.github.heartblade.item;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;

import static cpw.mods.fml.common.registry.GameRegistry.registerItem;

public class ItemLoader {
    public static Item whiteButterfly = new testItem();
    public ItemLoader(FMLPreInitializationEvent event) {
        register(whiteButterfly, "whiteButterfly");
    }
    public static void init(FMLPreInitializationEvent event) {
    }
    public static void register(Item item, String name) { registerItem(item, name);
    }
}



