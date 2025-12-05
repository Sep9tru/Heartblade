package com.github.heartblade.named;

import com.github.heartblade.blocks.BlockLoader;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author 520
 * @updateDate 2020/02/13
 */
public class wonderblade {
    String name = "heartblade.named.wonderblade";
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){
        ItemStack customblade = new ItemStack(HbBlades.HB_BLADE,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        ItemHbSlashBlade.CurrentItemName.set(tag, name);
        ItemHbSlashBlade.CustomMaxDamage.set(tag, 9999);
        ItemHbSlashBlade.IsDefaultBewitched.set(tag, true);
        ItemHbSlashBlade.isHbBlade.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/heartblade/wonderblade");
        ItemSlashBlade.ModelName.set(tag, "named/heartblade/wonderblade");
        ItemSlashBlade.SpecialAttackType.set(tag, 1);
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        ItemSlashBlade.BaseAttackModifier.set(tag, 8.0F);
        SpecialEffects.addEffect(customblade, HbSEs.Eternal);
        NBTTagCompound displayTag = new NBTTagCompound();
        customblade.setTagInfo("display", displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString("§r"));
        loreList.appendTag(new NBTTagString("§7“用于合成奇迹武器的重要部件。”"));
        displayTag.setTag("Lore", loreList);
        BladeUtils.registerCustomItemStack(name, customblade);
        BladeUtils.HbNamedBlades.add(name);

        ItemStack materialName = new ItemStack(SlashBlade.wrapBlade);
        ItemStack soul = BladeUtils.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialName);
        ItemSlashBlade.ProudSoul.set(reqTag, 50);
        ItemStack blackblade = BladeUtils.findItemStack(heartblade.MODID, name, 1);
        IRecipe recipe = new HbRecipeBlade(new ResourceLocation(heartblade.MODID,"wonderblade"),
                blackblade,materialName,
                new Object[]{
                        " IX",
                        "IWI",
                        "SI ",
                        'S', new ItemStack(Items.IRON_SWORD),
                        'I', soul,
                        'X', new ItemStack(BlockLoader.WONDER_REACTOR_CORE),
                        'W', materialName
        });
        SlashBlade.addRecipe("wonderblade", recipe);
    }
}
