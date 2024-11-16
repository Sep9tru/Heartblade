package com.github.heartblade.specialattack;

import com.github.heartblade.Entityadd.EntityMaximumBetHyper;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MaximumBetHyper extends SpecialAttackBase {
    public String toString() {
        return "maximumbethyper";
    }

    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        if (!world.isRemote) {
            int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false))
                ItemSlashBlade.damageItem(stack, 0, (EntityLivingBase)player);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            EntityMaximumBetHyper entityDA = new EntityMaximumBetHyper(world, (EntityLivingBase)player);
            if (entityDA != null)
                world.spawnEntityInWorld((Entity)entityDA);
        }
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
    }
}
