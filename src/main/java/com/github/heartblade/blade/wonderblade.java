package com.github.heartblade.blade;

import NegoreRouse.mods.slashblade.NegoreRouse;
import com.github.heartblade.blocks.BlockLoader;
import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialeffect.Eternal;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.stats.AchievementList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import ruby.bamboo.BambooInit;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

import java.util.Calendar;

public class wonderblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    String name = "flammpfeil.slashblade.named.wonderblade";
    public wonderblade() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }


    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(CommonProxy.wonderblade, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 13);
        ItemSlashBlade.setBaseAttackModifier(tag, 7.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/wonderblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/wonderblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        SpecialEffects.addEffect(customblade, Remodeling);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§b用于合成奇迹武器的重要部件"));
        loreList.appendTag(new NBTTagString("§d在此部件上锻造出的强度"));
        loreList.appendTag(new NBTTagString("§b可被继承到成品奇迹武器"));
        displayTag.setTag("Lore", loreList);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        Calendar ci = Calendar.getInstance();
        switch (ci.get(2)) {
            case 0:
                if (Loader.isModLoaded("heartblade")) {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "585",
                            "767",
                            "505",

                            '5', "record",
                            '6', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '7', new ItemStack(Items.golden_apple, 1,1),
                            '0', new ItemStack(Items.skull,1,1),
                            '8', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                }
            break;
            case 1:
            case 2:
            case 3:
                if (Loader.isModLoaded("BambooMod")) {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "689",
                            "757",
                            "906",

                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '6', new ItemStack(BambooInit.sakuraleavs, 1, 15),
                            '7', new ItemStack(Items.golden_apple),
                            '8', new ItemStack(BambooInit.kitunebi),
                            '0', new ItemStack(Items.nether_star),
                            '9', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                } else {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "689",
                            "757",
                            "606",

                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '6', new ItemStack(Blocks.leaves),
                            '7', new ItemStack(Items.golden_apple),
                            '8', new ItemStack(Blocks.sapling),
                            '0', new ItemStack(Items.nether_star),
                            '9', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                }
            break;
            case 4:
            case 5:
            case 6:
                if (Loader.isModLoaded("negorerouse")) {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "9 9",
                            " 5 ",
                            "909",

                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '0', new ItemStack(NegoreRouse.ItemFate_star),
                            '9', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                } else {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "9 9",
                            " 5 ",
                            "909",

                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '0', new ItemStack(Items.nether_star),
                            '9', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                }
            break;
            case 7:
            case 8:
            case 9:
                if (Loader.isModLoaded("TwilightForest")) {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "067",
                            "453",
                            "120",


                            '1', new ItemStack(TFItems.knightlySword),
                            '2', new ItemStack(TFItems.ironwoodIngot),
                            '3', new ItemStack(TFItems.magicMapFocus),
                            '4', new ItemStack(TFBlocks.auroraBlock),
                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '6', new ItemStack(TFItems.arcticFur),
                            '7', new ItemStack(TFItems.knightmetalRing),
                            '0', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                } else {
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "021",
                            "757",
                            "130",


                            '2', new ItemStack(Blocks.diamond_block),
                            '1', new ItemStack(Blocks.emerald_block),
                            '3', new ItemStack(Items.skull,1,1),
                            '7', new ItemStack(Blocks.redstone_block),
                            '5', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '0', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
                }
            break;
            case 10:
            case 11:
                    ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
                    String name = "flammpfeil.slashblade.named.wonderblade";
                    ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
                    ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
                    IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                            "585",
                            "767",
                            "505",

                            '5', "record",
                            '6', new ItemStack(BlockLoader.wonderReactor_Neo),
                            '7', new ItemStack(Items.golden_apple, 1,1),
                            '0', new ItemStack(Items.skull,1,1),
                            '8', soul});
                    SlashBlade.addRecipe("wonderblade", recipe);
            break;
        }
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, soul, new Object[]{
                "000",
                "050",
                "000",

                '5', new ItemStack(BlockLoader.wonderReactor),
                '0', soul});
        SlashBlade.addRecipe("wonderblade", recipe);
    }
}