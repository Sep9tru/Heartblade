package com.github.heartblade.named;

import cn.mmf.slashblade_addon.BladeLoader;
import cn.mmf.slashblade_addon.SJAP;
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

public class yunxingheblade {
    String name = "heartblade.named.yunxingheblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "flammpfeil.slashblade.named.sange";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/yunxingheblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/yunxingheblade");
//        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16766720);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.SMITE, 15);
        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 15);
        customblade.addEnchantment(Enchantments.UNBREAKING, 6);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.yunxingheblade_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        SpecialEffects.addEffect(customblade, HbSEs.Theholylightshines);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7“小残，请原谅爸爸的独自离开。等我找回圣光之后，我一定会回来，与你 并肩同行...”"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack sphere = BladeUtils.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

        if(Loader.isModLoaded("slashblade_addon")) {

            ItemStack materialBladeMS = SlashBlade.findItemStack("flammpfeil.slashblade", "slashbladenamed", 1);
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeMS);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, "flammpfeil.slashblade.named.kamuy.lightning");
            ItemSlashBlade.TextureName.set(reqTag, "named/kamuy/lightning");
            ItemSlashBlade.ModelName.set(reqTag, "named/kamuy/kamuy");
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            ItemSlashBlade.RepairCount.set(reqTag, 30);
            materialBladeMS.addEnchantment(Enchantments.UNBREAKING, 3);
            materialBladeMS.addEnchantment(Enchantments.SMITE, 5);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "yunxingheblade"),
                    finalblade, materialBladeX,
                    new Object[]{
                            "123",
                            "4M4",
                            "3X5",
                            '1', new ItemStack(Items.GOLDEN_SWORD),
                            '2', new ItemStack(Items.BED),
                            '3', sphere,
                            '4', new ItemStack(Blocks.GLOWSTONE),
                            '5', new ItemStack(Blocks.CACTUS),
                            'X', materialBladeX,
                            'M', materialBladeMS
                    });
            SlashBlade.addRecipe("yunxingheblade", recipe);
        }else{
            ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            ItemSlashBlade.RepairCount.set(reqTag, 30);
            materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);
            materialBladeM.addEnchantment(Enchantments.SMITE, 5);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "yunxingheblade"),
                    finalblade, materialBladeX,
                    new Object[]{
                            "123",
                            "4M4",
                            "3X5",
                            '1', new ItemStack(Items.GOLDEN_SWORD),
                            '2', new ItemStack(Items.BED),
                            '3', sphere,
                            '4', new ItemStack(Blocks.GLOWSTONE),
                            '5', new ItemStack(Blocks.CACTUS),
                            'X', materialBladeX,
                            'M', materialBladeM
                    });
            SlashBlade.addRecipe("yunxingheblade", recipe);
        }
    }
}