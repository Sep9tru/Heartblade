package com.github.heartblade.blade;


import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.charges;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

public class nobleblade {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect charges = SpecialEffects.register(new charges());
    String name = "flammpfeil.slashblade.named.nobleblade";
    public nobleblade() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 777);
        ItemSlashBlade.setBaseAttackModifier(tag, 2.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/nobleblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/nobleblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 13467442);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 9);
        SpecialEffects.addEffect(customblade, charges);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        String name = "flammpfeil.slashblade.named.nobleblade";
        String name3 = "flammpfeil.slashblade.named.wonderblade";
        ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
        ItemSlashBlade.RepairCount.set(tag, 0);
        ItemSlashBlade.KillCount.set(tag, 777);
        ItemSlashBlade.ProudSoul.set(tag, 0);
        reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
        GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
        IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                "648",
                "757",
                "191",

                '4', new ItemStack(Items.slime_ball),
                '8', new ItemStack(Blocks.piston),
                '7', new ItemStack(Blocks.jukebox),
                '1', new ItemStack(Items.diamond_axe),
                '9', SlashBlade.getCustomBlade(name3),
                '5', reqiredBlade,
                '6', sphere });
        SlashBlade.addRecipe("nobleblade", recipe);
    }

}
