package com.github.heartblade.named;

import com.github.heartblade.HbRecipeBlade;
import com.github.heartblade.heartblade;
import com.github.heartblade.named.item.ItemHbSlashBlade;
import com.github.heartblade.utils.BladeUtils;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class katana_blades {
    String name0 = "wrap.reforged.wooden_katana";
    String name1 = "wrap.reforged.stone_katana";
    String name2 = "wrap.reforged.iron_katana";
    String name3 = "wrap.reforged.golden_katana";
    String name4 = "wrap.reforged.diamond_katana";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack tinySoul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        ItemStack materialName = new ItemStack(SlashBlade.wrapBlade);

        ItemStack customblade0 = new ItemStack(SlashBlade.wrapBlade,1,0);
        NBTTagCompound tag0 = new NBTTagCompound();
        customblade0.setTagCompound(tag0);
        SlashBlade.wrapBlade.removeWrapItem(customblade0);
        ItemStack innerBlade0 = SlashBlade.findItemStack("minecraft", "wooden_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customblade0, innerBlade0);
        ItemSlashBlade.TextureName.set(tag0, "BalkonWood");
        ItemSlashBlade.ModelName.set(tag0, "BalkonWood");
        ItemHbSlashBlade.CurrentItemName.set(tag0, name0);
        ItemHbSlashBlade.CustomMaxDamage.set(tag0, 59);
        ItemHbSlashBlade.isHbBlade.set(tag0, true);
        customblade0.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.BaseAttackModifier.set(tag0, 5.0F);
        BladeUtils.registerCustomItemStack(name0, customblade0);
        BladeUtils.HbNamedBlades.add(name0);
        ItemStack blade0 = BladeUtils.findItemStack(heartblade.MODID, name0, 1);
        IRecipe recipe0 = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"wooden_katana"),
                blade0,materialName,
                new Object[]{
                        " 32",
                        "M2 ",
                        "1  ",
                        '1', new ItemStack(Items.STICK),
                        '2', new ItemStack(Blocks.PLANKS),
                        '3', tinySoul,
                        'M', materialName
                });
        SlashBlade.addRecipe("wooden_katana", recipe0);

        ItemStack customblade1 = new ItemStack(SlashBlade.wrapBlade,1,0);
        NBTTagCompound tag1 = new NBTTagCompound();
        customblade1.setTagCompound(tag1);
        SlashBlade.wrapBlade.removeWrapItem(customblade1);
        ItemStack innerBlade1 = SlashBlade.findItemStack("minecraft", "stone_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customblade1, innerBlade1);
        ItemSlashBlade.TextureName.set(tag1, "BalkonStone");
        ItemSlashBlade.ModelName.set(tag1, "BalkonStone");
        ItemHbSlashBlade.CurrentItemName.set(tag1, name1);
        ItemHbSlashBlade.CustomMaxDamage.set(tag1, 131);
        ItemHbSlashBlade.isHbBlade.set(tag1, true);
        customblade1.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.BaseAttackModifier.set(tag1, 6.0F);
        BladeUtils.registerCustomItemStack(name1, customblade1);
        BladeUtils.HbNamedBlades.add(name1);
        ItemStack blade1 = BladeUtils.findItemStack(heartblade.MODID, name1, 1);
        IRecipe recipe1 = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"stone_katana"),
                blade1,materialName,
                new Object[]{
                        " 32",
                        "M2 ",
                        "1  ",
                        '1', new ItemStack(Items.STICK),
                        '2', new ItemStack(Blocks.COBBLESTONE),
                        '3', tinySoul,
                        'M', materialName
                });
        SlashBlade.addRecipe("stone_katana", recipe1);

        ItemStack customblade2 = new ItemStack(SlashBlade.wrapBlade,1,0);
        NBTTagCompound tag2 = new NBTTagCompound();
        customblade2.setTagCompound(tag2);
        SlashBlade.wrapBlade.removeWrapItem(customblade2);
        ItemStack innerBlade2 = SlashBlade.findItemStack("minecraft", "iron_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customblade2, innerBlade2);
        ItemSlashBlade.TextureName.set(tag2, "BalkonIron");
        ItemSlashBlade.ModelName.set(tag2, "BalkonIron");
        ItemHbSlashBlade.CurrentItemName.set(tag2, name2);
        ItemHbSlashBlade.CustomMaxDamage.set(tag2, 250);
        ItemHbSlashBlade.isHbBlade.set(tag2, true);
        customblade2.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.BaseAttackModifier.set(tag2, 6.0F);
        BladeUtils.registerCustomItemStack(name2, customblade2);
        BladeUtils.HbNamedBlades.add(name2);
        ItemStack blade2 = BladeUtils.findItemStack(heartblade.MODID, name2, 1);
        IRecipe recipe2 = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"iron_katana"),
                blade2,materialName,
                new Object[]{
                        " 32",
                        "M2 ",
                        "1  ",
                        '1', new ItemStack(Items.STICK),
                        '2', new ItemStack(Items.IRON_INGOT),
                        '3', tinySoul,
                        'M', materialName
                });
        SlashBlade.addRecipe("iron_katana", recipe2);

        ItemStack customblade3 = new ItemStack(SlashBlade.wrapBlade,1,0);
        NBTTagCompound tag3 = new NBTTagCompound();
        customblade3.setTagCompound(tag3);
        SlashBlade.wrapBlade.removeWrapItem(customblade3);
        ItemStack innerBlade3 = SlashBlade.findItemStack("minecraft", "golden_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customblade3, innerBlade3);
        ItemSlashBlade.TextureName.set(tag3, "BalkonGold");
        ItemSlashBlade.ModelName.set(tag3, "BalkonGold");
        ItemHbSlashBlade.CurrentItemName.set(tag3, name3);
        ItemHbSlashBlade.CustomMaxDamage.set(tag3, 32);
        ItemHbSlashBlade.isHbBlade.set(tag3, true);
        customblade3.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.BaseAttackModifier.set(tag3, 8.0F);
        BladeUtils.registerCustomItemStack(name3, customblade3);
        BladeUtils.HbNamedBlades.add(name3);
        ItemStack blade3 = BladeUtils.findItemStack(heartblade.MODID, name3, 1);
        IRecipe recipe3 = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"golden_katana"),
                blade3,materialName,
                new Object[]{
                        " 32",
                        "M2 ",
                        "1  ",
                        '1', new ItemStack(Items.STICK),
                        '2', new ItemStack(Items.GOLD_INGOT),
                        '3', tinySoul,
                        'M', materialName
                });
        SlashBlade.addRecipe("golden_katana", recipe3);

        ItemStack customblade4 = new ItemStack(SlashBlade.wrapBlade,1,0);
        NBTTagCompound tag4 = new NBTTagCompound();
        customblade4.setTagCompound(tag4);
        SlashBlade.wrapBlade.removeWrapItem(customblade4);
        ItemStack innerBlade4 = SlashBlade.findItemStack("minecraft", "diamond_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customblade4, innerBlade4);
        ItemSlashBlade.TextureName.set(tag4, "BalkonDiamond");
        ItemSlashBlade.ModelName.set(tag4, "BalkonDiamond");
        ItemHbSlashBlade.CurrentItemName.set(tag4, name4);
        ItemHbSlashBlade.CustomMaxDamage.set(tag4, 1561);
        ItemHbSlashBlade.isHbBlade.set(tag4, true);
        customblade4.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.BaseAttackModifier.set(tag4, 7.0F);
        BladeUtils.registerCustomItemStack(name4, customblade4);
        BladeUtils.HbNamedBlades.add(name4);
        ItemStack blade4 = BladeUtils.findItemStack(heartblade.MODID, name4, 1);
        IRecipe recipe4 = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"diamond_katana"),
                blade4,materialName,
                new Object[]{
                        " 32",
                        "M2 ",
                        "1  ",
                        '1', new ItemStack(Items.STICK),
                        '2', new ItemStack(Items.DIAMOND),
                        '3', tinySoul,
                        'M', materialName
                });
        SlashBlade.addRecipe("diamond_katana", recipe4);
    }
}
