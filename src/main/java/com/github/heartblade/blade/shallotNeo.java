package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.skys_the_Limit;
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

import static NegoreRouse.mods.slashblade.Blade.Artemis.Oracle;

public class shallotNeo {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Sky_9 = SpecialEffects.register(new skys_the_Limit());
    String name = "flammpfeil.slashblade.named.shallotNeo";
    public shallotNeo() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 7);
        ItemSlashBlade.setBaseAttackModifier(tag, 128.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/shallot");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/shallot");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.IsNoScabbard.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.KillCount.set(tag, 1000);
        customblade.addEnchantment(Enchantment.unbreaking, 10);
        customblade.addEnchantment(Enchantment.knockback, 5);
        customblade.addEnchantment(Enchantment.punch, 5);
        SpecialEffects.addEffect(customblade, Sky_9);
        if (Loader.isModLoaded("negorerouse")) {
            SpecialEffects.addEffect(customblade, Oracle);
        } else {
            SpecialEffects.addEffect(customblade, Remodeling);
        }
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("heartblade")) {
            String name = "flammpfeil.slashblade.named.shallotNeo";
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);

            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.shallot", 1);

            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");

            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "   ",
                    "OVO",
                    "   ",

                    'O', soul,
                    'V', reqiredBlade,});
            SlashBlade.addRecipe("shallot", recipe);
        }
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.shallot";
        String name2 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, SlashBlade.getCustomBlade(name2), new Object[]{
                "  6",
                " 5 ",
                "78 ",

                '7', new ItemStack(Items.flower_pot),
                '8', new ItemStack(Blocks.leaves),
                '5', SlashBlade.getCustomBlade(name2),
                '6', soul });
        SlashBlade.addRecipe("shallotNeo", recipe);
    }
}
