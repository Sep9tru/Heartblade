package com.github.heartblade.named;

import com.github.heartblade.HbConfig;
import com.github.heartblade.HbRecipeBlade;
import com.github.heartblade.heartblade;
import com.github.heartblade.init.HbBlades;
import com.github.heartblade.init.HbSEs;
import com.github.heartblade.named.item.ItemHbSlashBlade;
import com.github.heartblade.utils.BladeUtils;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YamatoPower {
    String name = "heartblade.named.yamatoPowerNeo";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "flammpfeil.slashblade.named.yamato";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 75);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/YamatoHyper");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/YamatoPower");
        ItemSlashBlade.SpecialAttackType.set(tag, 1560404);
        ItemSlashBlade.StandbyRenderType.set(tag, 1);
        ItemSlashBlade.SummonedSwordColor.set(tag, 7396315);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.POWER, 10);
        customblade.addEnchantment(Enchantments.THORNS, 1);
        customblade.addEnchantment(Enchantments.PUNCH, 2);
        customblade.getTagCompound().setBoolean("Unbreakable", true);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.YamatoPower_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        SpecialEffects.addEffect(customblade, HbSEs.VergilShadow);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
        ItemStack soul = BladeUtils.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
        ItemSlashBlade.RepairCount.set(reqTag, 10);
        ItemSlashBlade.KillCount.set(reqTag, 1000);
        ItemSlashBlade.ProudSoul.set(reqTag, 615404);

        IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"yamatoPowerNeo"),
                finalblade,materialBladeM,
                new Object[]{
                        "010",
                        "0M0",
                        "0X0",
                        '0', soul,
                        '1', new ItemStack(Items.GHAST_TEAR),
                        'X', materialBladeX,
                        'M', materialBladeM
                });
        SlashBlade.addRecipe("yamatoPowerNeo", recipe);
    }
}
