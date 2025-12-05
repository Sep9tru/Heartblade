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
import moflop.mods.negorerouse.NegoreRouse;
import moflop.mods.negorerouse.init.NrSEs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class ziminblade {
    @ObjectHolder("cyclicmagic:emerald_pickaxe") static Item PICKAXE;

    String name = "heartblade.named.ziminblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "flammpfeil.slashblade.named.tagayasan";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/ziminblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/ziminblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 2342134);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 65280);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 10);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.ziminblade_baseAttackModifier);
        if(Loader.isModLoaded("negorerouse")) {
            SpecialEffects.addEffect(customblade, NrSEs.ORACLE);
        }else{
            SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        }
        SpecialEffects.addEffect(customblade, HbSEs.strength);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7“哇！哇哇！哇！哎呀我的神镐！”"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack tinySoul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);

        if(Loader.isModLoaded("negorerouse")){
            if(Loader.isModLoaded("cyclicmagic")) {
                ItemStack materialBladeMS = BladeUtils.findItemStack(NegoreRouse.MODID, "nrSlashBlade", 1);
                NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeMS);
                ItemHbSlashBlade.CurrentItemName.set(reqTag, "moflop.slashblade.chronos");
                ItemSlashBlade.TextureName.set(reqTag, "named/negorerouse/chronos");
                ItemSlashBlade.ModelName.set(reqTag, "named/negorerouse/chronos");
                ItemSlashBlade.ProudSoul.set(reqTag, 5000);
                ItemSlashBlade.RepairCount.set(reqTag, 26);
                materialBladeMS.addEnchantment(Enchantments.UNBREAKING, 3);

                IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "ziminblade"),
                        finalblade, materialBladeX,
                        new Object[]{
                                "123",
                                "4M5",
                                "6X7",
                                '1', new ItemStack(Blocks.GRAVEL),
                                '2', new ItemStack(Items.IRON_SWORD),
                                '3', new ItemStack(Blocks.GRASS),
                                '4', new ItemStack(Items.WOODEN_HOE),
                                '5', new ItemStack(Items.WATER_BUCKET),
                                '6', PICKAXE,
                                '7', tinySoul,
                                'X', materialBladeX,
                                'M', materialBladeMS
                        });
                SlashBlade.addRecipe("ziminblade", recipe);
            }else{
                ItemStack materialBladeMS = BladeUtils.findItemStack(NegoreRouse.MODID, "nrSlashBlade", 1);
                NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeMS);
                ItemHbSlashBlade.CurrentItemName.set(reqTag, "moflop.slashblade.chronos");
                ItemSlashBlade.TextureName.set(reqTag, "named/negorerouse/chronos");
                ItemSlashBlade.ModelName.set(reqTag, "named/negorerouse/chronos");
                ItemSlashBlade.ProudSoul.set(reqTag, 5000);
                ItemSlashBlade.RepairCount.set(reqTag, 26);
                materialBladeMS.addEnchantment(Enchantments.UNBREAKING, 3);

                IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "ziminblade"),
                        finalblade, materialBladeX,
                        new Object[]{
                                "123",
                                "4M5",
                                "6X7",
                                '1', new ItemStack(Blocks.GRAVEL),
                                '2', new ItemStack(Items.IRON_SWORD),
                                '3', new ItemStack(Blocks.GRASS),
                                '4', new ItemStack(Items.WOODEN_HOE),
                                '5', new ItemStack(Items.WATER_BUCKET),
                                '6', new ItemStack(Items.DIAMOND_PICKAXE),
                                '7', tinySoul,
                                'X', materialBladeX,
                                'M', materialBladeMS
                        });
                SlashBlade.addRecipe("ziminblade", recipe);
            }
        }else{
            if(Loader.isModLoaded("cyclicmagic")) {
                ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
                NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
                ItemSlashBlade.ProudSoul.set(reqTag, 1000);
                ItemSlashBlade.RepairCount.set(reqTag, 26);
                materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

                IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "ziminblade"),
                        finalblade, materialBladeX,
                        new Object[]{
                                "123",
                                "4M5",
                                "6X7",
                                '1', new ItemStack(Blocks.GRAVEL),
                                '2', new ItemStack(Items.IRON_SWORD),
                                '3', new ItemStack(Blocks.GRASS),
                                '4', new ItemStack(Items.WOODEN_HOE),
                                '5', new ItemStack(Items.WATER_BUCKET),
                                '6', PICKAXE,
                                '7', tinySoul,
                                'X', materialBladeX,
                                'M', materialBladeM
                        });
                SlashBlade.addRecipe("ziminblade", recipe);
            }else{
                ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
                NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
                ItemSlashBlade.ProudSoul.set(reqTag, 1000);
                ItemSlashBlade.RepairCount.set(reqTag, 26);
                materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

                IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "ziminblade"),
                        finalblade, materialBladeX,
                        new Object[]{
                                "123",
                                "4M5",
                                "6X7",
                                '1', new ItemStack(Blocks.GRAVEL),
                                '2', new ItemStack(Items.IRON_SWORD),
                                '3', new ItemStack(Blocks.GRASS),
                                '4', new ItemStack(Items.WOODEN_HOE),
                                '5', new ItemStack(Items.WATER_BUCKET),
                                '6', new ItemStack(Items.DIAMOND_PICKAXE),
                                '7', tinySoul,
                                'X', materialBladeX,
                                'M', materialBladeM
                        });
                SlashBlade.addRecipe("ziminblade", recipe);
            }
        }
    }
}