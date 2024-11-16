package com.github.heartblade.blade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialattack.CutEnd;
import com.github.heartblade.specialattack.SlashDimension_Sexy;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.Slayall;
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

public class YamatoHyperNeo {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect VergilShadow = SpecialEffects.register(new Slayall());
    String name = "flammpfeil.slashblade.named.yamatoPowerNeo";
    public YamatoHyperNeo() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemSlashBlade.specialAttacks.put(1560404, new SlashDimension_Sexy());
        ItemSlashBlade.specialAttacks.put(257, new CutEnd());
        ItemStack customblade = new ItemStack(CommonProxy.YamatoHyper, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/YamatoHyper");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/YamatoPower");
        ItemSlashBladeNamed.CustomMaxDamage.set(tag,40);
        ItemSlashBlade.setBaseAttackModifier(tag, 13.0F);
        ItemSlashBlade.SpecialAttackType.set(tag, 1560404);
        ItemSlashBlade.StandbyRenderType.set(tag, 1);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        customblade.addEnchantment(Enchantment.thorns, 1);
        customblade.addEnchantment(Enchantment.power, 10);
        customblade.addEnchantment(Enchantment.punch, 2);
        customblade.getTagCompound().setBoolean("Unbreakable", true);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, VergilShadow);
        ItemSlashBlade.SummonedSwordColor.set(tag, 7396315);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("heartblade")) {
            String name = "flammpfeil.slashblade.named.yamatoPowerNeo";
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);

            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.yamatoPower", 1);

            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");

            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "   ",
                    "OVO",
                    "   ",

                    'O', soul,
                    'V', reqiredBlade,});
            SlashBlade.addRecipe("yamatoPower", recipe);
        }
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.yamatoPowerNeo";
        String name2 = "flammpfeil.slashblade.named.yamato";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 10);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 615404);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "ODO",
                "OVO",
                "OPO",

                'D', new ItemStack(Items.ghast_tear),
                'P', SlashBlade.getCustomBlade(name3),
                'V', reqiredBlade,
                'O', soul});
        SlashBlade.addRecipe("yamatoPowerNeo", recipe);
    }
}



