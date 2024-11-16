package com.github.heartblade.specialattack;

import com.github.heartblade.Entityadd.EntitySummonedButterfly;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

 public class SAhuanyingdie
   extends SpecialAttackBase{
   public String toString() {
        /* 24 */     return "huanyingdie";
        /*    */   }
   public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
     World world = player.worldObj;
     NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
     if (!world.isRemote) {
       int cost = -20;
       if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false)) {
         ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
       }
     }
     ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.HiraTuki);
     Random rand = new Random();
       ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
       float baseModif = blade.getBaseAttackModifiers(tag);
       float magicDamage = baseModif / 50.0F;
       EntitySummonedButterfly entityDrive = new EntitySummonedButterfly(world, (EntityLivingBase)player, magicDamage, 0.0F);
       entityDrive.setLifeTime(100);
       entityDrive.setColor(16777215);
       world.spawnEntityInWorld((Entity)entityDrive);
       if (entityDrive != null)
       {
         world.spawnEntityInWorld((Entity)entityDrive);
       }
     }
   }
