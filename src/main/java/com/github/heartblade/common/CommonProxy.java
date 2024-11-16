package com.github.heartblade.common;

import com.github.heartblade.CraftingLoader;
import com.github.heartblade.Entityadd.*;
import com.github.heartblade.Itemblade.Itemblade;
import com.github.heartblade.blade.*;
import com.github.heartblade.blocks.BlockLoader;
import com.github.heartblade.creativetab.CreativeTabsLoader;
import com.github.heartblade.heartblade;
import com.github.heartblade.inventory.GuiElementLoader;
import com.github.heartblade.item.ItemLoader;
import com.github.heartblade.recipe.Recipe;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.Item;

public class CommonProxy {
    public static Item xiaochaoblade;
    public static Item matongcblade;
    public static Item dguoblade;
    public static Item ziminblade;
    public static Item desireblade;
    public static Item justiceGun;
    public static Item YamatoHyper;
    public static Item mirageEdge;
    public static Item wonderblade;
    public static Item maxkimblade;
    public static boolean FireDamage = true;
    public void preInit(FMLPreInitializationEvent event)
    {

        SlashBlade.InitEventBus.register(new maxkimblade());
        maxkimblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(maxkimblade, "maxkimblade");

        SlashBlade.InitEventBus.register(new xiaochaoblade());
        xiaochaoblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(xiaochaoblade, "xiaochaoblade");

        SlashBlade.InitEventBus.register(new matongcblade());
        matongcblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(matongcblade, "matongcblade");

        SlashBlade.InitEventBus.register(new dguoblade());
        dguoblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(dguoblade, "dguoblade");

        SlashBlade.InitEventBus.register(new ziminblade());
        ziminblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(ziminblade, "ziminblade");

        SlashBlade.InitEventBus.register(new desireblade());
        desireblade = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(desireblade, "desireblade");

        SlashBlade.InitEventBus.register(new justiceGun());
        justiceGun = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(justiceGun, "justiceGun");

        SlashBlade.InitEventBus.register(new YamatoHyperNeo());
        YamatoHyper = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(YamatoHyper, "YamatoHyper");

        SlashBlade.InitEventBus.register(new mirageEdge());
        mirageEdge = (new Itemblade(Item.ToolMaterial.IRON, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(mirageEdge, "mirageEdge");

        SlashBlade.InitEventBus.register(new wonderblade());
        wonderblade = (new Itemblade(Item.ToolMaterial.EMERALD, 4.0F)).setTextureName("flammpfeil.slashblade:proudsoul");
        GameRegistry.registerItem(wonderblade, "wonderblade");

        SlashBlade.InitEventBus.register(new xiaohuangOrArui());
        SlashBlade.InitEventBus.register(new yunxingheblade());
        SlashBlade.InitEventBus.register(new nobleblade());
        SlashBlade.InitEventBus.register(new iceblade());
        SlashBlade.InitEventBus.register(new Itan());
        SlashBlade.InitEventBus.register(new SolemnLament());
        SlashBlade.InitEventBus.register(new keyuanzhang());
        SlashBlade.InitEventBus.register(new shallot());
        SlashBlade.InitEventBus.register(new vanity());
        SlashBlade.InitEventBus.register(new phantomOfGods());
        SlashBlade.InitEventBus.register(new YamatoHyper());
        SlashBlade.InitEventBus.register(new voidSword());
        SlashBlade.InitEventBus.register(new shallotNeo());
        SlashBlade.InitEventBus.register(heartblade.instance);
        EntityRegistry.registerModEntity(EntityDriveAdd.class, "DriveM1", 1701, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntitySummonedButterfly.class, "ButterflyM1", 1703, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntitySlashDimensionAdd.class, "SlashDimensionM1", 1702, heartblade.instance, 250, 200, true);
        EntityRegistry.registerModEntity(EntityCutEndAdd.class, "CutEnd", 21, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntityCutEndRange.class, "CutEndRange", 26, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntityCutEndManager.class, "CutEndManager", 9914, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntitySummonedBlade_flake.class, "SummonedSword233", 1704, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntityPhantomSwordBase_DaNiu.class, "EntityPhantomSwordBase_DaNiu", 1555, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntityPhantomBullet.class, "EntityPhantomBullet", 5424, heartblade.instance, 250, 1, true);
        EntityRegistry.registerModEntity(EntityMaximumBetHyper.class, "EntityMaximumBetHyper", 4767, heartblade.instance, 250, 1, true);
     new CreativeTabsLoader(event);
     new BlockLoader(event);
     new ItemLoader(event);
     new CraftingLoader();
        new CraftingLoader();
        new GuiElementLoader();
    }
    public void init(FMLInitializationEvent event)
    {
        Recipe.init();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}

