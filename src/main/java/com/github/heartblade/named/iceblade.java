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
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class iceblade {
    String name = "heartblade.named.iceblade";
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
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/iceblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/iceblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 4);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 11397866);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 13);
        customblade.addEnchantment(Enchantments.FIRE_PROTECTION, 9);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.iceblade_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Never);
        SpecialEffects.addEffect(customblade, HbSEs.Extremelycold);
        SpecialEffects.addEffect(customblade, HbSEs.GodsBlade);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§r★身法 ★枪法 ★意识 ★战术"));
        loreList.appendTag(new NBTTagString("§7   -展示你们的实力-"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = new ItemStack(SlashBlade.wrapBlade);
        ItemStack Soul = BladeUtils.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

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
        ItemSlashBlade.KillCount.set(reqTag, 1000);
        ItemSlashBlade.ProudSoul.set(reqTag, 99999);
        materialBladeM.addEnchantment(Enchantments.FIRE_PROTECTION, 4);
        materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

        IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"iceblade"),
                finalblade,materialBladeM,
                new Object[]{
                        "123",
                        "4M4",
                        "3X5",
                        '1', new ItemStack(Items.DIAMOND_SWORD),
                        '2', new ItemStack(Blocks.SNOW),
                        '3', Soul,
                        '4', new ItemStack(Blocks.ICE),
                        '5', new ItemStack(Blocks.PACKED_ICE),
                        'X', materialBladeX,
                        'M', materialBladeM
                });
        SlashBlade.addRecipe("iceblade", recipe);
    }
}
