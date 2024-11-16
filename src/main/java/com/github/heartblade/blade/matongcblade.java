package com.github.heartblade.blade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialeffect.Breakeverything;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class matongcblade extends Item {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Breakeverything = SpecialEffects.register(new Breakeverything());
    String name = "flammpfeil.slashblade.named.matongcblade";
    public matongcblade() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(CommonProxy.matongcblade, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 233);
        ItemSlashBlade.setBaseAttackModifier(tag, 45.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/matongcblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/matongcblade");
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemSlashBlade.SpecialAttackType.set(tag, 36);
        } else {
            ItemSlashBlade.SpecialAttackType.set(tag, 5);
        }
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, -32767);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 10);
        customblade.addEnchantment(Enchantment.thorns, 6);
        customblade.addEnchantment(Enchantment.respiration, 10);
        SpecialEffects.addEffect(customblade, Breakeverything);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.matongcblade";
            String name2 = "flammpfeil.slashblade.named.kamuy.water";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 38);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            reqiredBlade.addEnchantment(Enchantment.thorns, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "GOG",
                    "ORO",
                    "ZBC",

                    'G', new ItemStack(Items.water_bucket),
                    'O', new ItemStack(Items.paper),
                    'C', new ItemStack(Items.golden_leggings),
                    'B', SlashBlade.getCustomBlade(name3),
                    'R', reqiredBlade,
                    'Z', soul});
            SlashBlade.addRecipe("matongcblade", recipe);
        } else {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.matongcblade";
            String name2 = "flammpfeil.slashblade.named.muramasa";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 38);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            reqiredBlade.addEnchantment(Enchantment.thorns, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "GOG",
                    "ORO",
                    "ZBC",

                    'G', new ItemStack(Items.water_bucket),
                    'O', new ItemStack(Items.paper),
                    'C', new ItemStack(Items.golden_leggings),
                    'B', SlashBlade.getCustomBlade(name3),
                    'R', reqiredBlade,
                    'Z', soul});
            SlashBlade.addRecipe("matongcblade", recipe);
        }

    }
}
