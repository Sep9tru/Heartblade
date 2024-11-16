package com.github.heartblade.blade;

import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.Theholylightshines;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
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

public class yunxingheblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Theholylightshines = SpecialEffects.register(new Theholylightshines());
    String name = "flammpfeil.slashblade.named.yunxingheblade";
    public yunxingheblade() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 777);
        ItemSlashBlade.setBaseAttackModifier(tag, 30.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/yunxingheblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/yunxingheblade");
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemSlashBlade.SpecialAttackType.set(tag, 38);
        } else {
            ItemSlashBlade.SpecialAttackType.set(tag, 1);
        }
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 15395502);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.smite, 10);
        customblade.addEnchantment(Enchantment.baneOfArthropods, 10);
        customblade.addEnchantment(Enchantment.unbreaking, 6);
        SpecialEffects.addEffect(customblade, Theholylightshines);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }


    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("flammpfeil.slashblade.kamuy")) {
            ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.yunxingheblade";
            String name2 = "flammpfeil.slashblade.named.kamuy.lightning";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 23);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.smite, 5);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Blocks.cactus),
                    'D', new ItemStack(Items.golden_sword),
                    'O', new ItemStack(Blocks.glowstone),
                    'Q', new ItemStack(Items.bed),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', sphere});
            SlashBlade.addRecipe("yunxingheblade", recipe);
        } else {
            ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.yunxingheblade";
            String name2 = "flammpfeil.slashblade.named.sange";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 30);
            ItemSlashBlade.ProudSoul.set(tag, 1000);
            reqiredBlade.addEnchantment(Enchantment.smite, 5);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Blocks.cactus),
                    'D', new ItemStack(Items.golden_sword),
                    'O', new ItemStack(Blocks.glowstone),
                    'Q', new ItemStack(Items.bed),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', sphere});
            SlashBlade.addRecipe("yunxingheblade", recipe);
        }
    }
}
