package com.github.heartblade.blocks;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockLoader {

    public static Block sakuraLeaves = GameRegistry.findBlock("BambooMod", "sakuraLog");
    public static Block wonderReactor = new BlockReactorCore();
    public static Block wonderReactor_Neo = new BlockReactorCore_Neo();

    public BlockLoader(FMLPreInitializationEvent event) {
        register(wonderReactor,  "wonder_reactor");
        register(wonderReactor_Neo,  "wonder_reactor_neo");
    }

    private static void register(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }
}
