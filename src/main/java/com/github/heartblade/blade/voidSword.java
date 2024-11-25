package com.github.heartblade.blade;

import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.void_ek;
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

public class voidSword {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Eternal = SpecialEffects.register(new Eternal());
    public static ISpecialEffect void_ek = SpecialEffects.register(new void_ek());
    String name = "flammpfeil.slashblade.named.voidSword";

    public voidSword() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
        ItemSlashBlade.setBaseAttackModifier(tag, 36.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/voidSword");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/voidSword");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, -15132922);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        customblade.addEnchantment(Enchantment.unbreaking, 10);
        customblade.addEnchantment(Enchantment.thorns, 5);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        SpecialEffects.addEffect(customblade, Eternal);
        SpecialEffects.addEffect(customblade, void_ek);
        customblade.getTagCompound().setBoolean("Unbreakable", true);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }

    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.voidSword";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 36);
        ItemSlashBlade.ProudSoul.set(tag, 4200);
        reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
        reqiredBlade.addEnchantment(Enchantment.thorns, 3);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                "DQZ",
                "OBX",
                "ZRG",

                'G', new ItemStack(Items.golden_apple),
                'D', new ItemStack(Blocks.redstone_ore),
                'O', new ItemStack(Blocks.obsidian),
                'X', new ItemStack(Items.nether_star),
                'Q', new ItemStack(Items.ghast_tear),
                'R', SlashBlade.getCustomBlade(name3),
                'B', reqiredBlade,
                'Z', soul });
        SlashBlade.addRecipe("voidSword", recipe);
    }
}