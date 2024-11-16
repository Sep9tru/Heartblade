package com.github.heartblade.specialattack;

import com.github.heartblade.Entityadd.EntityCutEndAdd;
import com.github.heartblade.Entityadd.EntityCutEndRange;
import com.github.heartblade.Entityadd.EntitybladeCombo;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

public class CutEnd extends SpecialAttackBase {
    public String toString() {
        return "CutEnd";
    }


    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        if (!world.isRemote) {
            int cost = -5;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-50), false))
                player.addPotionEffect(new PotionEffect(11, 15, 5, true));
            player.addPotionEffect(new PotionEffect(2, 15, 8, true));
            player.addPotionEffect(new PotionEffect(14, 15, 3, true));
            player.addPotionEffect(new PotionEffect(15, 3, 3, true));
            ItemSlashBlade.damageItem(stack, 100, (EntityLivingBase) player);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            float baseModif = blade.getBaseAttackModifiers(tag);
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            float magicDamage = baseModif;
            int rank = StylishRankManager.getStylishRank(player);
            if (5 <= rank) {
                magicDamage = baseModif + ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float)level / 5.0F);
            }
                magicDamage += ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (500.0F + level / 5.0F);
            player.worldObj.playSoundAtEntity((Entity) player, "heartblade:CutEnd", 80.0F, 1.0F);
            int i;
            for (i = 0; i < 35; i++) {
                Random rand = new Random();
                float a = rand.nextInt(180);
                float b = rand.nextInt(180);
                float c = rand.nextInt(360);
                EntityCutEndAdd EntityCutEndAdd = new EntityCutEndAdd(world, (EntityLivingBase) player, magicDamage / 50.0F, false, 0.0F);
                if (EntityCutEndAdd != null) {
                    EntityCutEndAdd.setColor(11397866);
                    EntityCutEndAdd.setLocationAndAngles(player.posX, player.posY, player.posZ, 180.0F + a, 180.0F + b);
                    EntityCutEndAdd.setDriveVector(1.5F);
                    EntityCutEndAdd.setLifeTime(20);
                    EntityCutEndAdd.setRoll(c);
                    world.spawnEntityInWorld((Entity) EntityCutEndAdd);
                }
                ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SSlashEdge);
            }
            for (i = 0; i < 35; i++) {
                Random rand = new Random();
                float a = rand.nextInt(180);
                float b = rand.nextInt(180);
                float c = rand.nextInt(360);
                EntityCutEndAdd EntityCutEndAdd = new EntityCutEndAdd(world, (EntityLivingBase) player, magicDamage / 50.0F, false, 0.0F);
                if (EntityCutEndAdd != null) {
                    EntityCutEndAdd.setColor(15132922);
                    EntityCutEndAdd.setLocationAndAngles(player.posX, player.posY, player.posZ, 180.0F + a, 180.0F + b);
                    EntityCutEndAdd.setDriveVector(1.5F);
                    EntityCutEndAdd.setLifeTime(20);
                    EntityCutEndAdd.setRoll(c);

                    world.spawnEntityInWorld((Entity) EntityCutEndAdd);
                }
                ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Battou);
            }
            EntitybladeCombo entityDrive2 = new EntitybladeCombo(player.worldObj, (EntityLivingBase) player);
            if (entityDrive2 != null)
                world.spawnEntityInWorld((Entity) entityDrive2);

            EntityCutEndRange entityDrive = new EntityCutEndRange(world, (EntityLivingBase) player, 5.0F);
            if (entityDrive != null) {
                entityDrive.setRoll(90.0F);
                entityDrive.setColor(2302862);
                entityDrive.setPosition(player.posX, player.posY + 1.0D, player.posZ);
                entityDrive.setLifeTime(15);
                entityDrive.setIsSlashDimension(true);
                world.spawnEntityInWorld((Entity) entityDrive);
            }
        }
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Kiriage);
    }

}



