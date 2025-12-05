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
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class matongcblade {
    String name = "heartblade.named.matongcblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "flammpfeil.slashblade.named.muramasa";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/matongcblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/matongcblade");
//        ItemSlashBlade.SpecialAttackType.set(tag, 5);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, -32767);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 7);
        customblade.addEnchantment(Enchantments.THORNS, 6);
        customblade.addEnchantment(Enchantments.RESPIRATION, 5);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.matongcblade_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        SpecialEffects.addEffect(customblade, HbSEs.Breakeverything);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7“小V啊，小V实在是太可怜了在厕所蹲一晚上居然蹲的头发都白了..”"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
        ItemStack tinySoul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);

        if(Loader.isModLoaded("slashblade_addon")) {

            ItemStack materialBladeMS = SlashBlade.findItemStack("flammpfeil.slashblade", "slashbladenamed", 1);
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeMS);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, "flammpfeil.slashblade.named.kamuy.water");
            ItemSlashBlade.TextureName.set(reqTag, "named/kamuy/water");
            ItemSlashBlade.ModelName.set(reqTag, "named/kamuy/kamuy");
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            ItemSlashBlade.RepairCount.set(reqTag, 38);
            materialBladeMS.addEnchantment(Enchantments.UNBREAKING, 3);
            materialBladeMS.addEnchantment(Enchantments.THORNS, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "matongcblade"),
                    finalblade, materialBladeX,
                    new Object[]{
                            "121",
                            "2M2",
                            "3X4",
                            '1', new ItemStack(Items.WATER_BUCKET),
                            '2', new ItemStack(Items.PAPER),
                            '3', tinySoul,
                            '4', new ItemStack(Items.GOLDEN_LEGGINGS),
                            'X', materialBladeX,
                            'M', materialBladeMS
                    });
            SlashBlade.addRecipe("matongcblade", recipe);
        }else{
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            ItemSlashBlade.RepairCount.set(reqTag, 38);
            materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);
            materialBladeM.addEnchantment(Enchantments.THORNS, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "matongcblade"),
                    finalblade, materialBladeX,
                    new Object[]{
                            "121",
                            "2M2",
                            "3X4",
                            '1', new ItemStack(Items.WATER_BUCKET),
                            '2', new ItemStack(Items.PAPER),
                            '3', tinySoul,
                            '4', new ItemStack(Items.GOLDEN_LEGGINGS),
                            'X', materialBladeX,
                            'M', materialBladeM
                    });
            SlashBlade.addRecipe("matongcblade", recipe);
        }
    }
}