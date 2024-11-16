package com.github.heartblade.blade;

import com.github.heartblade.specialattack.MaximumBetHyper;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.powerLocked;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class mirageEdge {
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect powerLocked = SpecialEffects.register(new powerLocked());
    String name = "flammpfeil.slashblade.named.mirageEdge";
    public mirageEdge() {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemSlashBlade.specialAttacks.put(6661, new MaximumBetHyper());
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/mirageEdge");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/mirageEdge");
        ItemSlashBladeNamed.CustomMaxDamage.set(tag,256);
        ItemSlashBlade.setBaseAttackModifier(tag, 5.0F);
        ItemSlashBlade.SpecialAttackType.set(tag, 6661);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, powerLocked);
        ItemSlashBlade.SummonedSwordColor.set(tag, 7396315);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        String name = "flammpfeil.slashblade.named.mirageEdge";

        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);

        ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        ItemSlashBlade.SpecialAttackType.set(sphere.getTagCompound(), 1560404);

        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);

        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");

        IRecipe recipe = new RecipeAwakeBlade(blade, sphere, new Object[]{
                "  0",
                " P ",
                "0  ",

                'P', sphere,
                '0', soul,});
        SlashBlade.addRecipe("mirageEdge", recipe);
    }
}