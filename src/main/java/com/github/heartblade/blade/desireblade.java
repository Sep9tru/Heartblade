package com.github.heartblade.blade;

import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.Yearn;
import com.tanzanite.saligia.item.ItemLoader;
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

public class desireblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Yearn = SpecialEffects.register(new Yearn());
    String name = "flammpfeil.slashblade.named.desireblade";
    public desireblade() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 66);
        ItemSlashBlade.setBaseAttackModifier(tag, 13.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/desireblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/desireblade");
        if (Loader.isModLoaded("saligia")) {
            ItemSlashBlade.SpecialAttackType.set(tag, 4100);
        } else {
            ItemSlashBlade.SpecialAttackType.set(tag, 4);
        }
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16740039);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 6);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, Yearn);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("saligia")) {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.desireblade";
            String name2 = "flammpfeil.slashblade.named.asmodeus";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            ItemSlashBlade.RepairCount.set(tag, 5);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "844",
                    "867",
                    "525",
                    '2', new ItemStack(Items.bed),
                    '4', new ItemStack(ItemLoader.LuxuriaFragment),
                    '5', new ItemStack(Items.blaze_rod),
                    '7', SlashBlade.getCustomBlade(name3),
                    '6', reqiredBlade,
                    '8', soul });
            SlashBlade.addRecipe("desireblade", recipe);
        } else {
            ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.desireblade";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            ItemSlashBlade.RepairCount.set(tag, 13);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                    "DQZ",
                    "OSO",
                    "ZRG",

                    'G', new ItemStack(Blocks.emerald_block),
                    'D', new ItemStack(Items.fishing_rod),
                    'O', new ItemStack(Items.bed),
                    'Q', new ItemStack(Items.blaze_rod),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', sphere});
            SlashBlade.addRecipe("desireblade", recipe);
        }
    }
}




