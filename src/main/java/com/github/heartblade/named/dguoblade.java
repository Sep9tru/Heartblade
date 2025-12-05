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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class dguoblade {
    String name = "heartblade.named.dguoblade";
    String materialNameX = "heartblade.named.wonderblade";
    String materialNameM = "flammpfeil.slashblade.named.yamato.broken";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 233);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/dguoblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/dguoblade");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.KillCount.set(tag, 1000);
        ItemSlashBlade.ProudSoul.set(tag, 10000);
        customblade.addEnchantment(Enchantments.UNBREAKING, 6);
        customblade.addEnchantment(Enchantments.KNOCKBACK, 3);
        customblade.addEnchantment(Enchantments.PUNCH, 3);
        ItemSlashBlade.BaseAttackModifier.set(tag, HbConfig.dguoblade_baseAttackModifier);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        SpecialEffects.addEffect(customblade, HbSEs.Superme);
        SpecialEffects.addEffect(customblade, HbSEs.NeverCheat);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7我的天，坂齐喵！？"));
        displayTag.setTag("Lore", loreList);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);

        ItemStack finalblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        ItemStack materialBladeX = BladeUtils.findItemStack(heartblade.MODID, materialNameX, 1);
        ItemStack materialBladeM = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameM, 1);
        ItemStack tinySoul = BladeUtils.findItemStack("flammpfeil.slashblade", "tiny_bladesoul", 1);

        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeM);
        ItemSlashBlade.ProudSoul.set(reqTag, 1000);
        ItemSlashBlade.RepairCount.set(reqTag, 6);
        materialBladeM.addEnchantment(Enchantments.UNBREAKING, 3);
        customblade.addEnchantment(Enchantments.KNOCKBACK, 2);
        materialBladeM.addEnchantment(Enchantments.PUNCH, 2);

        IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"dguoblade"),
                finalblade,materialBladeX,
                new Object[]{
                        "123",
                        "4M5",
                        "6X1",
                        '1', tinySoul,
                        '2', new ItemStack(Blocks.STONE),
                        '3', new ItemStack(Items.POTATO),
                        '4', new ItemStack(Items.WATER_BUCKET),
                        '5', new ItemStack(Items.BONE),
                        '6', new ItemStack(Items.NAME_TAG),
                        'X', materialBladeX,
                        'M', materialBladeM
                });
        SlashBlade.addRecipe("dguoblade", recipe);
    }
}
