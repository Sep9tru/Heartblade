package com.github.heartblade.specialattack;

import com.github.heartblade.Entityadd.EntityDriveAdd;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityWitherSword;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class autumnWind extends SpecialAttackBase {
    public autumnWind() {
    }

    public String toString() {
        return "autumnWind";
    }

    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        double playerDist = 7.5D;

        if (!player.onGround) {
            playerDist *= 0.3499999940395355D;
        }

        player.motionX = -Math.sin(Math.toRadians((double) player.rotationYaw)) * playerDist;
        player.motionZ = Math.cos(Math.toRadians((double) player.rotationYaw)) * playerDist;
        if (!world.isRemote) {
            boolean cost = true;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, +0, false)) {
                ItemSlashBlade.damageItem(stack, +0, player);
            }

            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            float magicDamage = 7.0F + ItemSlashBlade.AttackAmplifier.get(tag) * ((float) level / 5.0F);
            ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 10, 0, true));
            EntityDriveAdd entityDrive = new EntityDriveAdd(world, player, magicDamage, false, 90.0F);
            if (entityDrive != null) {
                entityDrive.setColor(65280);
                entityDrive.setInitialSpeed(1.5F);
                entityDrive.setLifeTime(42);
                entityDrive.setParticle("happyVillager");
                world.spawnEntityInWorld(entityDrive);
            }
        }

        world.playSoundAtEntity(player, "random.explode", 1.0F, 1.0F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.HiraTuki);
        player.playSound("game.random.explode",1.0F,1.5F);
        if (!world.isRemote) {
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            Entity target = null;
            int entityId = ItemSlashBlade.TargetEntityId.get(tag);
            if (entityId != 0) {
                Entity tmp = world.getEntityByID(entityId);
                if (tmp != null && tmp.getDistanceToEntity(player) < 30.0F) {
                    target = tmp;
                }
            }

            if (target == null) {
                target = this.getEntityToWatch(player);
            }

            if (target != null) {
                ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
                boolean cost = true;
                if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -20, false)) {
                    ItemSlashBlade.damageItem(stack, 10, player);
                }

                StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.PhantomSword);
                blade.attackTargetEntity(stack, target, player, true);
                player.onCriticalHit(target);
                target.motionX = 0.0D;
                target.motionY = 0.0D;
                target.motionZ = 0.0D;
                if (target instanceof EntityLivingBase) {
                    blade.setDaunting((EntityLivingBase)target);
                    ((EntityLivingBase)target).hurtTime = 0;
                    ((EntityLivingBase)target).hurtResistantTime = 0;
                }

                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = 6.0F + ItemSlashBlade.AttackAmplifier.get(tag) * ((float)level / 5.0F);
                int count = 1 + StylishRankManager.getStylishRank(player);

                for(int i = 0; i < count; ++i) {
                    if (!world.isRemote) {
                        boolean isBurst = i % 2 == 0;
                        EntityWitherSword entityDrive = new EntityWitherSword(world, player, magicDamage, 90.0F);
                        if (entityDrive != null) {
                            entityDrive.setInterval(7 + i * 2);
                            entityDrive.setLifeTime(99);
                            int color = isBurst ? -16744192 : -16744192;
                            entityDrive.setColor(color);
                            entityDrive.setBurst(isBurst);
                            entityDrive.setTargetEntityId(target.getEntityId());
                            world.spawnEntityInWorld(entityDrive);
                        }
                    }
                }
            }
        }
    }

    private Entity getEntityToWatch(EntityPlayer player) {
        World world = player.worldObj;
        Entity target = null;

        for(int dist = 2; dist < 20; dist += 2) {
            AxisAlignedBB bb = player.boundingBox.copy();
            Vec3 vec = player.getLookVec();
            vec = vec.normalize();
            bb = bb.expand(2.0D, 0.25D, 2.0D);
            bb = bb.offset(vec.xCoord * (double)((float)dist), vec.yCoord * (double)((float)dist), vec.zCoord * (double)((float)dist));
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, bb, ItemSlashBlade.AttackableSelector);
            float distance = 30.0F;
            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Entity curEntity = (Entity)var9.next();
                float curDist = curEntity.getDistanceToEntity(player);
                if (curDist < distance) {
                    target = curEntity;
                    distance = curDist;
                }
            }

            if (target != null) {
                break;
            }
        }

        return target;
    }

}






