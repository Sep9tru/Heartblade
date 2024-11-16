package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.ToRavage;
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

public class xiaochaoblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect ToRavage = SpecialEffects.register(new ToRavage());
    String name = "flammpfeil.slashblade.named.xiaochaoblade";
    public xiaochaoblade() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 233);
        ItemSlashBlade.setBaseAttackModifier(tag, 60.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/xiaochaoblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/xiaochaoblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 4);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16744192);;
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.looting, 6);
        customblade.addEnchantment(Enchantment.unbreaking, 8);
        customblade.addEnchantment(Enchantment.fortune, 6);
        SpecialEffects.addEffect(customblade, ToRavage);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        String name = "flammpfeil.slashblade.named.xiaochaoblade";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);

            ItemSlashBlade.RepairCount.set(tag, 60);
            ItemSlashBlade.ProudSoul.set(tag, 3000);

            reqiredBlade.addEnchantment(Enchantment.looting, 3);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            reqiredBlade.addEnchantment(Enchantment.fortune, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "DQZ",
                    "OBO",
                    "ZRG",

                    'G', new ItemStack(Items.diamond),
                    'D', new ItemStack(Items.rotten_flesh),
                    'O', new ItemStack(Items.gold_ingot),
                    'Q', new ItemStack(Items.water_bucket),
                    'R', SlashBlade.getCustomBlade(name3),
                    'B', reqiredBlade,
                    'Z', soul });
            SlashBlade.addRecipe("xiaochaoblade", recipe);
        }
}
