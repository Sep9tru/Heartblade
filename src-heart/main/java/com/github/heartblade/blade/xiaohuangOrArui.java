package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.maximumDrive;
import com.github.heartblade.specialeffect.sprint;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class xiaohuangOrArui {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect sprint = SpecialEffects.register(new sprint());
    public static ISpecialEffect maximumDrive = SpecialEffects.register(new maximumDrive());
    public static ISpecialEffect Eternal = SpecialEffects.register(new Eternal());
    String name = "flammpfeil.slashblade.named.xiaohuangOrArui";

    public xiaohuangOrArui() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 2014);
        ItemSlashBlade.setBaseAttackModifier(tag, 42.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/xiaohuangOrArui");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/xiaohuangOrArui");
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemSlashBlade.SpecialAttackType.set(tag, 37);
        } else {
            ItemSlashBlade.SpecialAttackType.set(tag, 0);
        }
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16720896);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 6);
        customblade.addEnchantment(Enchantment.fireAspect, 5);
        customblade.addEnchantment(Enchantment.fireProtection, 6);
        SpecialEffects.addEffect(customblade, Eternal);
        SpecialEffects.addEffect(customblade, sprint);
        SpecialEffects.addEffect(customblade, maximumDrive);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.xiaohuangOrArui";
            String name2 = "flammpfeil.slashblade.named.kamuy.fire";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 35);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Items.diamond_boots),
                    'D', new ItemStack(Items.stone_pickaxe),
                    'O', new ItemStack(Items.blaze_rod),
                    'Q', new ItemStack(Items.cake),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', soul});
            SlashBlade.addRecipe("xiaohuangOrArui", recipe);
        } else {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.xiaohuangOrArui";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 42);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Items.diamond_boots),
                    'D', new ItemStack(Items.stone_pickaxe),
                    'O', new ItemStack(Items.blaze_rod),
                    'Q', new ItemStack(Items.cake),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', soul});
            SlashBlade.addRecipe("xiaohuangOrArui", recipe);
        }
    }
}
