package com.github.heartblade.blade;

import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.skys_the_Limit;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class maxkimblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect Sky_9 = SpecialEffects.register(new skys_the_Limit());
    String name = "flammpfeil.slashblade.named.maxkimblade";
    public maxkimblade() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    }

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        ItemStack customblade = new ItemStack(CommonProxy.maxkimblade, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/maxkimblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/maxkimblade");
        ItemSlashBladeNamed.CustomMaxDamage.set(tag,40);
        ItemSlashBlade.setBaseAttackModifier(tag, 155.0F);
        ItemSlashBlade.SpecialAttackType.set(tag, 2);
        ItemSlashBlade.StandbyRenderType.set(tag, 1);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.IsNoScabbard.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.KillCount.set(tag, 1000);
        customblade.addEnchantment(Enchantment.silkTouch, 1);
        customblade.addEnchantment(Enchantment.unbreaking, 10);
        customblade.getTagCompound().setBoolean("Unbreakable", true);
        SpecialEffects.addEffect(customblade, Sky_9);
        SpecialEffects.addEffect(customblade, Remodeling);
        ItemSlashBlade.SummonedSwordColor.set(tag, 2251650);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.maxkimblade";
        String name2 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 10);
        reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                "DQZ",
                "ORO",
                "ZBG",

                'G', new ItemStack(Blocks.redstone_block),
                'D', new ItemStack(Items.iron_shovel),
                'O', new ItemStack(Blocks.iron_block),
                'Q', new ItemStack(Blocks.piston),
                'B', SlashBlade.getCustomBlade(name2),
                'R', reqiredBlade,
                'Z', sphere});
        SlashBlade.addRecipe("maxkimblade", recipe);
    }
}
