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

public class xiaohuangOrArui {
    String name = "heartblade.named.xiaohuangOrArui";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "wrap.reforged.iron_katana";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/xiaohuangOrArui");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/xiaohuangOrArui");
//        ItemSlashBlade.SpecialAttackType.set(tag, 2);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.SummonedSwordColor.set(tag, 16720896);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 13);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 5);
        customblade.addEnchantment(Enchantments.FIRE_PROTECTION, 6);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.xiaohuangOrArui_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Never);
        SpecialEffects.addEffect(customblade, HbSEs.sprint);
        SpecialEffects.addEffect(customblade, HbSEs.maximumDrive);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§r“Erenrat_huang 因为 僵尸猪人 注定要摔死”"));
        loreList.appendTag(new NBTTagString("§7-节选自 《【小煌】极限生存EP.7一失足成千古恨》。"));
        displayTag.setTag("Lore", loreList);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = SlashBlade.findItemStack("flammpfeil.slashblade", "slashbladeWrapper", 1);
        ItemStack soul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);
        if(Loader.isModLoaded("slashblade_addon")) {

            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, "flammpfeil.slashblade.named.kamuy.fire");
            ItemSlashBlade.TextureName.set(reqTag, "named/kamuy/fire");
            ItemSlashBlade.ModelName.set(reqTag, "named/kamuy/kamuy");
            ItemSlashBlade.RepairCount.set(reqTag, 35);
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"xiaohuangOrArui"),
                    finalblade,materialBladeX,
                    new Object[]{
                            "123",
                            "4M4",
                            "3X5",
                            '1', new ItemStack(Items.STONE_PICKAXE),
                            '2', new ItemStack(Items.CAKE),
                            '3', soul,
                            '4', new ItemStack(Items.BLAZE_ROD),
                            '5', new ItemStack(Items.DIAMOND_BOOTS),
                            'X', materialBladeX,
                            'M', materialBladeM
                    });
            SlashBlade.addRecipe("xiaohuangOrArui", recipe);
        }else{
            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
            ItemHbSlashBlade.CurrentItemName.set(reqTag, materialNameM);
            ItemSlashBlade.TextureName.set(reqTag, "BalkonDiamond");
            ItemSlashBlade.ModelName.set(reqTag, "BalkonDiamond");
            ItemSlashBlade.RepairCount.set(reqTag, 35);
            ItemSlashBlade.ProudSoul.set(reqTag, 1000);
            materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);

            IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID, "xiaohuangOrArui"),
                    finalblade,materialBladeX,
                    new Object[]{
                            "123",
                            "4M4",
                            "3X5",
                            '1', new ItemStack(Items.STONE_PICKAXE),
                            '2', new ItemStack(Items.CAKE),
                            '3', soul,
                            '4', new ItemStack(Items.BLAZE_ROD),
                            '5', new ItemStack(Items.DIAMOND_BOOTS),
                            'X', materialBladeX,
                            'M', materialBladeM
                    });
            SlashBlade.addRecipe("xiaohuangOrArui", recipe);
        }
    }
}
