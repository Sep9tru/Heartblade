package com.github.heartblade.blade;

import com.github.heartblade.specialattack.CutEnd;
import com.github.heartblade.specialattack.SlashDimension_Sexy;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.Slayall;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class YamatoHyper {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect VergilShadow = SpecialEffects.register(new Slayall());
    String name = "flammpfeil.slashblade.named.yamatoPower";
    public YamatoHyper() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemSlashBlade.specialAttacks.put(1560404, new SlashDimension_Sexy());
        ItemSlashBlade.specialAttacks.put(257, new CutEnd());
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
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
}



