package com.github.heartblade.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockLoader {
    public static Block WONDER_REACTOR_CORE = new BlockReactorCore();

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event){
        event.getRegistry().register(WONDER_REACTOR_CORE.setRegistryName("heartblade:wonder_reactor"));
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new ItemBlock(WONDER_REACTOR_CORE).setRegistryName("heartblade:wonder_reactor"));
    }
    @SubscribeEvent
    public static void registerItemBlockModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WONDER_REACTOR_CORE),0,new ModelResourceLocation(WONDER_REACTOR_CORE.getRegistryName(),"inventory"));
    }
}
