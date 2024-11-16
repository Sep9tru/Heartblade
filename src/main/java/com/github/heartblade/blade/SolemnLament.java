package com.github.heartblade.blade;

import com.github.heartblade.item.ItemLoader;
import com.github.heartblade.specialattack.SAhuanyingdie;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.PhysicalAttack_2;
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

public class SolemnLament {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect PhysicalAttack_2 = SpecialEffects.register(new PhysicalAttack_2());
    public static ISpecialEffect SolemnLament = SpecialEffects.register(new com.github.heartblade.specialeffect.SolemnLament());
    String name = "flammpfeil.slashblade.named.SolemnLament";
    public SolemnLament() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemSlashBlade.specialAttacks.put(777, new SAhuanyingdie());
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 777);
        ItemSlashBlade.setBaseAttackModifier(tag, 7.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/SolemnLament");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/SolemnLament");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, -1842204);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        SpecialEffects.addEffect(customblade, Remodeling);
        SpecialEffects.addEffect(customblade, PhysicalAttack_2);
        SpecialEffects.addEffect(customblade, SolemnLament);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        String name = "flammpfeil.slashblade.named.SolemnLament";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.justiceGun", 1);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 7);
        ItemSlashBlade.KillCount.set(tag, 0);
        ItemSlashBlade.ProudSoul.set(tag, 77777);
        reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "177",
                "787",
                "771",

                '1', new ItemStack(ItemLoader.whiteButterfly),
                '7', new ItemStack(Items.bone),
                '8', reqiredBlade,
                });
        SlashBlade.addRecipe("SolemnLament", recipe);
    }
}

