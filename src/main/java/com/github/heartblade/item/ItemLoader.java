package com.github.heartblade.item;

import com.github.heartblade.blocks.BlockLoader;
import com.github.heartblade.creativetab.HbTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;



@Mod.EventBusSubscriber
public class ItemLoader {

    public static final butterfly_head BUTTERFLY_HEAD= new butterfly_head();

    public static final ItemOldrecord OLD_RECORD = new ItemOldrecord("oldrecord", HbTabs.Hb_Item);
    public static final ItemOldrecord_1 OLD_RECORD_1 = new ItemOldrecord_1("oldrecord_1", HbTabs.Hb_Item);
    public static final ItemChaosRecord CHAOS_RECORD = new ItemChaosRecord("chaosrecord", HbTabs.Hb_Item);
    public static final ItemCorruptRecord CORRUPT_RECORD = new ItemCorruptRecord("corruptrecord", HbTabs.Hb_Item);
    public static final ItemDustyRecord DUSTY_RECORD = new ItemDustyRecord("dustyrecord", HbTabs.Hb_Item);
    public static final ItemIcedRecord ICED_RECORD = new ItemIcedRecord("icedrecord", HbTabs.Hb_Item);
    public static final ItemSunriseRecord SUNRISE_RECORD = new ItemSunriseRecord("sunriserecord", HbTabs.Hb_Item);
    public static final ItemOrangeRecord ORANGE_RECORD = new ItemOrangeRecord("orangerecord", HbTabs.Hb_Item);
    public static final ItemOursRecord OURS_RECORD = new ItemOursRecord("oursrecord", HbTabs.Hb_Item);
    public static final ItemPRecord PRECORD = new ItemPRecord("precord", HbTabs.Hb_Item);
    public static final ItemPRecord_1 PRECORD_1 = new ItemPRecord_1("precord_1", HbTabs.Hb_Item);

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry= event.getRegistry();

        registry.register(BUTTERFLY_HEAD);

        registry.register(OLD_RECORD);
        registry.register(OLD_RECORD_1);
        registry.register(CHAOS_RECORD);
        registry.register(CORRUPT_RECORD);
        registry.register(DUSTY_RECORD);
        registry.register(ICED_RECORD);
        registry.register(SUNRISE_RECORD);
        registry.register(ORANGE_RECORD);
        registry.register(OURS_RECORD);
        registry.register(PRECORD);
        registry.register(PRECORD_1);

    }
    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item){
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item,0, modelResourceLocation);
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry (ModelRegistryEvent event){

        registerModel(BUTTERFLY_HEAD);

        registerModel(OLD_RECORD);
        registerModel(OLD_RECORD_1);
        registerModel(CHAOS_RECORD);
        registerModel(CORRUPT_RECORD);
        registerModel(DUSTY_RECORD);
        registerModel(ICED_RECORD);
        registerModel(SUNRISE_RECORD);
        registerModel(ORANGE_RECORD);
        registerModel(OURS_RECORD);
        registerModel(PRECORD);
        registerModel(PRECORD_1);

    }
}