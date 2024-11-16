package com.github.heartblade.blade;

import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.Extremelycold;
import com.github.heartblade.specialeffect.GodsBlade;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class iceblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Extremelycold = SpecialEffects.register(new Extremelycold());
    public static ISpecialEffect GodsBlade = SpecialEffects.register(new GodsBlade());
    String name = "flammpfeil.slashblade.named.iceblade";
    public iceblade() {
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
        ItemSlashBlade.setBaseAttackModifier(tag, 30.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/iceblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/iceblade");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 11397866);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.fireProtection, 9);
        customblade.addEnchantment(Enchantment.unbreaking, 9);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, Extremelycold);
        SpecialEffects.addEffect(customblade, GodsBlade);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("flammpfeil.frostwolf")) {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.iceblade";
            String name2 = "flammpfeil.slashblade.named.frostwolfb";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 23);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.fireProtection, 4);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Blocks.packed_ice),
                    'D', new ItemStack(Items.diamond_sword),
                    'O', new ItemStack(Blocks.ice),
                    'Q', new ItemStack(Blocks.snow),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', soul});
            SlashBlade.addRecipe("iceblade", recipe);
        } else {
            ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.iceblade";
            String name2 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 30);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.fireProtection, 4);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                    "DQZ",
                    "ORO",
                    "ZBG",

                    'G', new ItemStack(Blocks.packed_ice),
                    'D', new ItemStack(Items.diamond_sword),
                    'O', new ItemStack(Blocks.ice),
                    'Q', new ItemStack(Blocks.snow),
                    'B', SlashBlade.getCustomBlade(name2),
                    'R', reqiredBlade,
                    'Z', sphere});
            SlashBlade.addRecipe("iceblade", recipe);
        }
    }
}


