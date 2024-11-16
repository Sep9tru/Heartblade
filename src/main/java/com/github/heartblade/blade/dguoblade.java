package com.github.heartblade.blade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.NeverCheat;
import com.github.heartblade.specialeffect.Superme;
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

public class dguoblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Superme = SpecialEffects.register(new NeverCheat());
    public static ISpecialEffect NeverCheat = SpecialEffects.register(new Superme());
    String name = "flammpfeil.slashblade.named.dguoblade";
    public dguoblade() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(CommonProxy.dguoblade, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 8878);
        ItemSlashBlade.setBaseAttackModifier(tag, 13.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/dguoblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/dguoblade");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 7);
        customblade.addEnchantment(Enchantment.knockback, 3);
        customblade.addEnchantment(Enchantment.punch, 3);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, Superme);
        SpecialEffects.addEffect(customblade, NeverCheat);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.dguoblade";
        String name2 = "flammpfeil.slashblade.named.yamato.broken";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 6);
        ItemSlashBlade.ProudSoul.set(tag, 1000);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "SOQ",
                "CBD",
                "ZAS",

                'O', new ItemStack(Blocks.stone),
                'Q', new ItemStack(Items.potato),
                'C', new ItemStack(Items.water_bucket),
                'D', new ItemStack(Items.bone),
                'Z', new ItemStack(Items.name_tag),
                'A', SlashBlade.getCustomBlade(name3),
                'B', reqiredBlade,
                'S', soul });
        SlashBlade.addRecipe("dguoblade", recipe);
    }
}
