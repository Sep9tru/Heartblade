package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.DarkPower;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

import static NegoreRouse.mods.slashblade.Blade.Artemis.Oracle;

public class phantomOfGods {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Darkpower = SpecialEffects.register(new DarkPower());
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    String name = "flammpfeil.slashblade.named.phantomOfGods";
    public phantomOfGods() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag,name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag,2019);
        //2019,时代的陨落
        if (Loader.isModLoaded("negorerouse")) {
            SpecialEffects.addEffect(customblade, Oracle);
        } else {
            SpecialEffects.addEffect(customblade, Remodeling);
        }
        SpecialEffects.addEffect(customblade, Darkpower);

        ItemSlashBlade.setBaseAttackModifier(tag, 42.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/phantomOfGods");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/phantomOfGods");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 15132922);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 13);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.phantomOfGods";
        String name2 = "flammpfeil.slashblade.named.desireblade";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 17);
        ItemSlashBlade.ProudSoul.set(tag, 4200);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "SES",
                "SBS",
                "SRS",
                'E', new ItemStack(Blocks.dragon_egg),
                'R', SlashBlade.getCustomBlade(name3),
                'B', reqiredBlade,
                'S', soul });
        SlashBlade.addRecipe("phantomOfGods", recipe);
    }

}