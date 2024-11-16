package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.Eternal;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static NegoreRouse.mods.slashblade.Blade.Artemis.Oracle;

public class shallot {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    String name = "flammpfeil.slashblade.named.shallot";
    public shallot() {
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
        ItemSlashBlade.setBaseAttackModifier(tag, 3.0F);
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
        if (Loader.isModLoaded("negorerouse")) {
            SpecialEffects.addEffect(customblade, Oracle);
        } else {
            SpecialEffects.addEffect(customblade, Remodeling);
        }
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
    }
}