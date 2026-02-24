package com.github.heartblade.named;

import com.github.heartblade.HbConfig;
import com.github.heartblade.heartblade;
import com.github.heartblade.named.item.ItemHbSlashBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import com.github.heartblade.HbRecipeBlade;
import com.github.heartblade.init.HbBlades;
import com.github.heartblade.init.HbSEs;
import com.github.heartblade.utils.BladeUtils;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class xiaochaoblade {
    String name = "heartblade.named.xiaochaoblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "wrap.reforged.golden_katana";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/xiaochaoblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/xiaochaoblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 4);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16744192);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 7);
        customblade.addEnchantment(Enchantments.LOOTING, 8);
        customblade.addEnchantment(Enchantments.FORTUNE, 6);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.xiaochaoblade_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        SpecialEffects.addEffect(customblade, HbSEs.ToRavage);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7周六，被自己帅醒后开空调吃电线§2蹂躏这条蛇"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = new ItemStack(SlashBlade.wrapBlade);
        ItemStack tinySoul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);

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
        ItemSlashBlade.TextureName.set(reqTag, "BalkonGold");
        ItemSlashBlade.ModelName.set(reqTag, "BalkonGold");
        //ItemSlashBlade.KillCount.set(reqTag, 6666);
        //ItemSlashBlade.ProudSoul.set(reqTag, 66666);
        materialBladeM.addEnchantment(Enchantments.LOOTING, 3);
        materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);
        materialBladeM.addEnchantment(Enchantments.FORTUNE, 3);

        IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"xiaochaoblade"),
                finalblade,materialBladeM,
                new Object[]{
                        "123",
                        "4M4",
                        "3X5",
                        '1', new ItemStack(Items.ROTTEN_FLESH),
                        '2', new ItemStack(Items.WATER_BUCKET),
                        '3', tinySoul,
                        '4', new ItemStack(Items.GOLD_INGOT),
                        '5', new ItemStack(Items.DIAMOND),
                        'X', materialBladeX,
                        'M', materialBladeM
                        });
        SlashBlade.addRecipe("xiaochaoblade", recipe);
    }
}