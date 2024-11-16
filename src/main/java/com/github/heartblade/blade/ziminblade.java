package com.github.heartblade.blade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialattack.autumnWind;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.strength;
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

public class ziminblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect strength = SpecialEffects.register(new strength());
    String name = "flammpfeil.slashblade.named.ziminblade";
    public ziminblade() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemSlashBlade.specialAttacks.put(2342134, new autumnWind());
        ItemStack customblade = new ItemStack(CommonProxy.ziminblade, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 233);
        ItemSlashBlade.setBaseAttackModifier(tag, 32.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/ziminblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/ziminblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 2342134);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 65280);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 10);
        SpecialEffects.addEffect(customblade, strength);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.ziminblade";
        String name2 = "flammpfeil.slashblade.named.tagayasan";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 26);
        ItemSlashBlade.ProudSoul.set(tag, 1000);
        reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "ZI1",
                "MBN",
                "PRQ",

                'Z', new ItemStack(Blocks.gravel),
                'I', new ItemStack(Items.iron_sword),
                '1', new ItemStack(Blocks.grass),
                'M', new ItemStack(Items.wooden_hoe),
                'P', new ItemStack(Items.diamond_pickaxe),
                'N', new ItemStack(Items.water_bucket),
                'R', SlashBlade.getCustomBlade(name3),
                'B', reqiredBlade,
                'Q', soul });
        SlashBlade.addRecipe("ziminblade", recipe);
    }
}

