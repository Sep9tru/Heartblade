package com.github.heartblade.blade;

import com.github.heartblade.RecipeHeartblade;
import com.github.heartblade.specialeffect.Eternal;
import com.github.heartblade.specialeffect.PhysicalAttack;
import com.tanzanite.saligia.item.ItemLoader;
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

public class justiceGun {
    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect Remodeling = SpecialEffects.register(new Eternal());
    public static ISpecialEffect PhysicalAttack = SpecialEffects.register(new PhysicalAttack());
    String name = "flammpfeil.slashblade.named.justiceGun";
    public justiceGun() {
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
        ItemSlashBladeNamed.CustomMaxDamage.set(tag,210);
        ItemSlashBlade.setBaseAttackModifier(tag, 20.0F);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/justiceGun");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/justiceGun");
        if (Loader.isModLoaded("saligia")) {
            ItemSlashBlade.SpecialAttackType.set(tag, 4106);
        } else {
            ItemSlashBlade.SpecialAttackType.set(tag, 0);
        }
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16711680);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantment.unbreaking, 7);
        SpecialEffects.addEffect(customblade, PhysicalAttack);
        SpecialEffects.addEffect(customblade, Remodeling);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name);
        AchievementList.registerCraftingAchievement(this.name, customblade, AchievementList.getAchievement("buildSlashBlade"));
    }
    @SubscribeEvent
    public void postinit(LoadEvent.PostInitEvent event) {
        if (Loader.isModLoaded("saligia")) {
            ItemStack soul = GameRegistry.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.justiceGun";
            String name2 = "flammpfeil.slashblade.named.samael";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            ItemStack reqiredBlade = GameRegistry.findItemStack("flammpfeil.slashblade", name2, 1);

            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 13);
            ItemSlashBlade.ProudSoul.set(tag, 4200);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            IRecipe recipe = new RecipeAwakeBlade(blade, reqiredBlade, new Object[]{
                    "887",
                    "265",
                    "834",
                    '2', new ItemStack(Blocks.dispenser),
                    '4', new ItemStack(Items.iron_ingot),
                    '3', new ItemStack(Items.gunpowder),
                    '7', new ItemStack(ItemLoader.IraFragment),
                    '5', SlashBlade.getCustomBlade(name3),
                    '6', reqiredBlade,
                    '8', soul});
            SlashBlade.addRecipe("justiceGun", recipe);
        } else {
            ItemStack sphere = GameRegistry.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
            String name = "flammpfeil.slashblade.named.justiceGun";
            String name3 = "flammpfeil.slashblade.named.wonderblade";
            ItemStack blade = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
            ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
            ItemSlashBlade.RepairCount.set(tag, 20);
            ItemSlashBlade.ProudSoul.set(tag, 4200);
            reqiredBlade.addEnchantment(Enchantment.unbreaking, 3);
            GameRegistry.registerCustomItemStack(name + ".reqired", reqiredBlade);
            ItemSlashBladeNamed.NamedBlades.add("flammpfeil.slashblade:" + name + ".reqired");
            IRecipe recipe = new RecipeHeartblade(blade, reqiredBlade, sphere, new Object[]{
                    "OOL",
                    "PSB",
                    "OHZ",
                    'P', new ItemStack(Blocks.dispenser),
                    'Z', new ItemStack(Items.iron_ingot),
                    'L', new ItemStack(Blocks.redstone_block),
                    'H', new ItemStack(Items.gunpowder),
                    'B', SlashBlade.getCustomBlade(name3),
                    'S', reqiredBlade,
                    'O', sphere});
            SlashBlade.addRecipe("justiceGun", recipe);
        }
    }
}
