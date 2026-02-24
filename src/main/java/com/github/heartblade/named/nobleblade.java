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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class nobleblade {
    String name = "heartblade.named.nobleblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "wrap.reforged.diamond_katana";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/nobleblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/nobleblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 13467442);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 10);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.nobleblade_baseAttackModifier);
        if(Loader.isModLoaded("negorerouse")) {
            SpecialEffects.addEffect(customblade, NrSEs.ORACLE);
        }else{
            SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        }
        SpecialEffects.addEffect(customblade, HbSEs.charges);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7三男子自称装逼之神而掐架(以下忽略N字...)"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = SlashBlade.findItemStack("flammpfeil.slashblade", "slashbladeWrapper", 1);
        ItemStack sphere = BladeUtils.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

        if(Loader.isModLoaded("negorerouse")){
            ItemStack materialBladeMS = BladeUtils.findItemStack(NegoreRouse.MODID, "nrSlashBlade", 1);
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeMS);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, "moflop.slashblade.nier");
            ItemSlashBlade.TextureName.set(reqTag, "named/negorerouse/nier");
            ItemSlashBlade.ModelName.set(reqTag, "named/negorerouse/nier");
            ItemSlashBlade.ProudSoul.set(reqTag, 10000);
            materialBladeMS.addEnchantment(Enchantments.UNBREAKING, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"nobleblade"),
                    finalblade,materialBladeX,
                    new Object[]{
                            "123",
                            "4M4",
                            "5X5",
                            '1', sphere,
                            '2', new ItemStack(Items.SLIME_BALL),
                            '3', new ItemStack(Blocks.PISTON),
                            '4', new ItemStack(Blocks.JUKEBOX),
                            '5', new ItemStack(Items.DIAMOND_AXE),
                            'X', materialBladeX,
                            'M', materialBladeMS
                    });
            SlashBlade.addRecipe("nobleblade", recipe);
        }else{

            NBTTagCompound displayTagX = new NBTTagCompound();
            materialBladeX.setTagInfo("display", displayTagX);
            NBTTagList loreListX = new NBTTagList();
            loreListX.appendTag(new NBTTagString("§r"));
            loreListX.appendTag(new NBTTagString("§7“用于合成奇迹武器的重要部件。”"));
            loreListX.appendTag(new NBTTagString("§6该刀合成时不会继承奇迹部件X的属性"));
            loreListX.appendTag(new NBTTagString("§6而是继承另一个材料刀"));
            displayTagX.setTag("Lore", loreListX);

            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, materialNameM);
            ItemSlashBlade.TextureName.set(reqTag, "BalkonDiamond");
            ItemSlashBlade.ModelName.set(reqTag, "BalkonDiamond");
            ItemSlashBlade.ProudSoul.set(reqTag, 10000);
            materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "nobleblade"),
                    finalblade, materialBladeM,
                    new Object[]{
                            "123",
                            "4M4",
                            "5X5",
                            '1', sphere,
                            '2', new ItemStack(Items.SLIME_BALL),
                            '3', new ItemStack(Blocks.PISTON),
                            '4', new ItemStack(Blocks.JUKEBOX),
                            '5', new ItemStack(Items.DIAMOND_AXE),
                            'X', materialBladeX,
                            'M', materialBladeM
                    });
            SlashBlade.addRecipe("nobleblade", recipe);
        }
    }
}
