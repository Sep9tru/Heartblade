package com.github.heartblade.specialattack;

import com.github.heartblade.Entityadd.EntityCutEndAdd;
import com.github.heartblade.Entityadd.EntityCutEndManager;
import com.github.heartblade.Entityadd.EntityCutEndRange;
import com.github.heartblade.Entityadd.EntitySlashDimensionAdd;
import com.github.heartblade.common.CommonProxy;
import mods.flammpfeil.slashblade.EntityDrive;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.ability.UntouchableTime;
import mods.flammpfeil.slashblade.entity.EntityJudgmentCutManager;
import mods.flammpfeil.slashblade.specialattack.IJustSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.ISuperSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class SlashDimension_Sexy extends SpecialAttackBase implements IJustSpecialAttack, ISuperSpecialAttack {
    @Override
    public String toString() {
        return "Slashdimension_Sexy";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        int g;
        for (g = 0; g <= 8; ++g) {
            FoodStats ggg = player.getFoodStats();
            ItemStack bbb = player.inventory.getStackInSlot(g);
                if (bbb != null && (bbb.getItem() == CommonProxy.YamatoHyper)){
                    if (bbb.getItemDamage() < 1 ) {
                        if (ggg != null && (ggg.getFoodLevel()>18)) {
                        if (!world.isRemote) {
                            int cost = -5;
                            player.worldObj.playSoundAtEntity((Entity) player, "heartblade:CutEnd", 80.0F, 1.0F);
                            player.addPotionEffect(new PotionEffect(11, 35, 5, true));
                            player.addPotionEffect(new PotionEffect(1, 35, 12, true));
                            player.addPotionEffect(new PotionEffect(14, 35, 3, true));
                            player.addPotionEffect(new PotionEffect(15, 30, 0, true));
                            ggg.setFoodLevel(1);
                            ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
                            float baseModif = blade.getBaseAttackModifiers(tag);
                            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                            float magicDamage = baseModif;
                            int rank = StylishRankManager.getStylishRank(player);
                            if (5 <= rank) {
                                magicDamage = baseModif + ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float) level / 5.0F);
                            }
                            magicDamage += ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (420.0F + level / 5.0F);
                            for (g = 0; g < 35; g++) {
                                Random rand = new Random();
                                float a = rand.nextInt(180);
                                float b = rand.nextInt(180);
                                float c = rand.nextInt(360);
                                EntityCutEndAdd EntityCutEndAdd = new EntityCutEndAdd(world, (EntityLivingBase) player, magicDamage / 50.0F, false, 0.0F);
                                if (EntityCutEndAdd != null) {
                                    EntityCutEndAdd.setSpeedByTimeEvent(1.1F, 0.0F, 4);
                                    EntityCutEndAdd.setColor(15132922);
                                    EntityCutEndAdd.setLocationAndAngles(player.posX, player.posY, player.posZ, 180.0F + a, 180.0F + b);
                                    EntityCutEndAdd.setDriveVector(1.5F);
                                    EntityCutEndAdd.setParticle("fireworksSpark");
                                    EntityCutEndAdd.setLifeTime(25);
                                    EntityCutEndAdd.setRoll(c);
                                    world.spawnEntityInWorld((Entity) EntityCutEndAdd);
                                }
                            }
                            for (g = 0; g < 35; g++) {
                                Random rand = new Random();
                                float a = rand.nextInt(180);
                                float b = rand.nextInt(180);
                                float c = rand.nextInt(360);
                                EntityCutEndAdd EntityCutEndAdd = new EntityCutEndAdd(world, (EntityLivingBase) player, magicDamage / 50.0F, false, 0.0F);
                                if (EntityCutEndAdd != null) {
                                    EntityCutEndAdd.setSpeedByTimeEvent(1.1F, 0.0F, 5);
                                    EntityCutEndAdd.setColor(15132922);
                                    EntityCutEndAdd.setLocationAndAngles(player.posX, player.posY, player.posZ, 180.0F + a, 180.0F + b);
                                    EntityCutEndAdd.setDriveVector(1.5F);
                                    EntityCutEndAdd.setParticle("fireworksSpark");
                                    EntityCutEndAdd.setLifeTime(25);
                                    EntityCutEndAdd.setRoll(c);
                                    world.spawnEntityInWorld((Entity) EntityCutEndAdd);
                                }
                            }
                            EntityCutEndRange entityDrive = new EntityCutEndRange(world, (EntityLivingBase) player, 5.0F);
                            if (entityDrive != null) {
                                entityDrive.setRoll(90.0F);
                                entityDrive.setColor(2302862);
                                entityDrive.setPosition(player.posX, player.posY + 1.0D, player.posZ);
                                entityDrive.setLifeTime(25);
                                entityDrive.setIsSlashDimension(true);
                                world.spawnEntityInWorld((Entity) entityDrive);
                            }
                            EntityCutEndManager entityDrive2 = new EntityCutEndManager(player.worldObj, (EntityLivingBase) player);
                            if (entityDrive2 != null)
                                world.spawnEntityInWorld((Entity) entityDrive2);
                        }
                    }
                }
            }
        }
        Entity target = null;
        int entityId = ItemSlashBlade.TargetEntityId.get(tag).intValue();
        if (entityId != 0) {
            Entity tmp = world.getEntityByID(entityId);
            if (tmp != null &&
                    tmp.getDistanceToEntity((Entity)player) < 30.0F)
                target = tmp;
        }
        if (target == null)
            target = getEntityToWatch(player);
        if (target == null) {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
            player.playSound("mob.endermen.portal", 0.5F, 1.0F);
            int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false))
                ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
            if (!player.worldObj.isRemote) {
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (15 + level / 5.0F);
                EntitySlashDimensionAdd dim = new EntitySlashDimensionAdd(world, (EntityLivingBase)player, magicDamage);
                if (dim != null) {
                    Vec3 pos = player.getLookVec();
                    float scale = 5.0F;
                    pos.xCoord *= scale;
                    pos.yCoord *= scale;
                    pos.zCoord *= scale;
                    pos = pos.addVector(player.posX, player.posY, player.posZ);
                    pos = pos.addVector(0.0D, player.getEyeHeight(), 0.0D);
                    Vec3 offset = Vec3.createVectorHelper(player.posX, player.posY, player.posZ).addVector(0.0D, player.getEyeHeight(), 0.0D);
                    Vec3 look = player.getLookVec();
                    Vec3 offsettedLook = offset.addVector(look.xCoord * 5.0D, look.yCoord * 5.0D, look.zCoord * 5.0D);
                    MovingObjectPosition movingobjectposition = world.rayTraceBlocks(offset, offsettedLook);
                    if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                        Block block = world.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
                        if (block != null && block.isCollidable()) {
                            Vec3 tmppos = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
                            if (1.0D < tmppos.distanceTo(Vec3.createVectorHelper(player.posX, player.posY, player.posZ)))
                                pos = tmppos;
                        }
                    }
                    dim.setPosition(pos.xCoord, pos.yCoord, pos.zCoord);
                    dim.setLifeTime(25);
                    dim.setIsSlashDimension(true);
                    world.spawnEntityInWorld((Entity)dim);
                }
            }
        } else {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
            spawnParticle(world, target);
            player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 0.5F, 1.0F);
            int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false))
                ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
            AxisAlignedBB bb = target.boundingBox.copy();
            bb = bb.expand(2.0D, 0.25D, 2.0D);
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)player, bb, ItemSlashBlade.AttackableSelector);
            if (!ItemSlashBlade.AttackableSelector.isEntityApplicable(target))
                list.add(target);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            for (Entity curEntity : list) {
                StylishRankManager.setNextAttackType((Entity)player, StylishRankManager.AttackTypes.SlashDim);
                blade.attackTargetEntity(stack, curEntity, player, Boolean.valueOf(true));
            }
            if (!target.worldObj.isRemote) {
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (15 + level / 5.0F);
                EntitySlashDimensionAdd dim = new EntitySlashDimensionAdd(world, (EntityLivingBase)player, magicDamage);
                if (dim != null) {
                    dim.setPosition(target.posX, target.posY + target.height / 2.0D, target.posZ);
                    dim.setLifeTime(25);
                    world.spawnEntityInWorld((Entity)dim);
                }
            }
        }
    }

    private Entity getEntityToWatch(EntityPlayer player) {
        World world = player.worldObj;
        Entity target = null;
        for (int dist = 2; dist < 20; dist += 2) {
            AxisAlignedBB bb = player.boundingBox.copy();
            Vec3 vec = player.getLookVec();
            vec = vec.normalize();
            bb = bb.expand(2.0D, 0.25D, 2.0D);
            bb = bb.offset(vec.xCoord * dist, vec.yCoord * dist, vec.zCoord * dist);
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)player, bb, ItemSlashBlade.AttackableSelector);
            float distance = 30.0F;
            for (Entity curEntity : list) {
                float curDist = curEntity.getDistanceToEntity((Entity)player);
                if (curDist < distance) {
                    target = curEntity;
                    distance = curDist;
                }
            }
            if (target != null)
                break;
        }
        return target;
    }

    private void spawnParticle(World world, Entity target) {
        world.spawnParticle("largeexplode", target.posX, target.posY + target.height, target.posZ, 3.0D, 3.0D, 3.0D);
        world.spawnParticle("largeexplode", target.posX + 1.0D, target.posY + target.height + 1.0D, target.posZ, 3.0D, 3.0D, 3.0D);
        world.spawnParticle("largeexplode", target.posX, target.posY + target.height + 0.5D, target.posZ + 1.0D, 3.0D, 3.0D, 3.0D);
    }

    public void doJustSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        Entity target = null;
        int entityId = ItemSlashBlade.TargetEntityId.get(tag).intValue();
        if (entityId != 0) {
            Entity tmp = world.getEntityByID(entityId);
            if (tmp != null &&
                    tmp.getDistanceToEntity((Entity)player) < 30.0F)
                target = tmp;
        }
        if (target == null)
            target = getEntityToWatch(player);
        if (target == null) {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
            player.playSound("mob.endermen.portal", 0.5F, 1.0F);
            int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false))
                ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
            if (!player.worldObj.isRemote) {
                Vec3 pos = player.getLookVec();
                float scale = 5.0F;
                pos.xCoord *= scale;
                pos.yCoord *= scale;
                pos.zCoord *= scale;
                pos = pos.addVector(player.posX, player.posY, player.posZ);
                pos = pos.addVector(0.0D, player.getEyeHeight(), 0.0D);
                ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
                Vec3 offset = Vec3.createVectorHelper(player.posX, player.posY, player.posZ).addVector(0.0D, player.getEyeHeight(), 0.0D);
                Vec3 look = player.getLookVec();
                Vec3 offsettedLook = offset.addVector(look.xCoord * 5.0D, look.yCoord * 5.0D, look.zCoord * 5.0D);
                MovingObjectPosition movingobjectposition = world.rayTraceBlocks(offset, offsettedLook);
                if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    Block block = world.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
                    if (block != null && block.isCollidable()) {
                        Vec3 tmppos = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
                        if (1.0D < tmppos.distanceTo(Vec3.createVectorHelper(player.posX, player.posY, player.posZ)))
                            pos = tmppos;
                    }
                }
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (15 + level / 5.0F);
                EntitySlashDimensionAdd dim = new EntitySlashDimensionAdd(world, (EntityLivingBase)player, magicDamage);
                if (dim != null) {
                    dim.setPosition(pos.xCoord, pos.yCoord, pos.zCoord);
                    dim.setLifeTime(25);
                    dim.setIsSlashDimension(true);
                    world.spawnEntityInWorld((Entity)dim);
                }
                magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * level / 5.0F;
                for (int i = 0; i < 5; i++) {
                    EntityDrive entityDrive = new EntityDrive(world, (EntityLivingBase)player, Math.min(1.0F, magicDamage / 3.0F), false, 0.0F);
                    float rotationYaw = (60 * i) + (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                    float rotationPitch = (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                    float fYawDtoR = rotationYaw / 180.0F * 3.1415927F;
                    float fPitDtoR = rotationPitch / 180.0F * 3.1415927F;
                    float fYVecOfst = 0.5F;
                    float motionX = -MathHelper.sin(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                    float motionY = -MathHelper.sin(fPitDtoR) * fYVecOfst;
                    float motionZ = MathHelper.cos(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                    entityDrive.setLocationAndAngles(pos.xCoord - motionX, pos.yCoord - motionY, pos.zCoord - motionZ, rotationYaw, rotationPitch);
                    entityDrive.setDriveVector(fYVecOfst);
                    entityDrive.setLifeTime(8);
                    entityDrive.setIsMultiHit(false);
                    int rank = StylishRankManager.getStylishRank((Entity)player);
                    if (5 <= rank) {
                        EnumSet<ItemSlashBlade.SwordType> type = blade.getSwordType(stack);
                        entityDrive.setIsSlashDimension(type.contains(ItemSlashBlade.SwordType.FiercerEdge));
                    }
                    entityDrive.setRoll(90.0F + 120.0F * (entityDrive.getRand().nextFloat() - 0.5F));
                    if (entityDrive != null)
                        world.spawnEntityInWorld((Entity)entityDrive);
                }
            }
        } else {
            ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
            player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 0.5F, 1.0F);
            int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-20), false))
                ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
            AxisAlignedBB bb = target.boundingBox.copy();
            bb = bb.expand(2.0D, 0.25D, 2.0D);
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)player, bb, ItemSlashBlade.AttackableSelector);
            if (!ItemSlashBlade.AttackableSelector.isEntityApplicable(target))
                list.add(target);
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            for (Entity curEntity : list) {
                StylishRankManager.setNextAttackType((Entity)player, StylishRankManager.AttackTypes.SlashDim);
                blade.attackTargetEntity(stack, curEntity, player, Boolean.valueOf(true));
                player.onEnchantmentCritical(curEntity);
            }
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (30 + level / 5.0F);
            if (0 < level)
                for (int i = 0; i < 5; i++) {
                    EntityDrive entityDrive = new EntityDrive(world, (EntityLivingBase)player, Math.min(1.0F, magicDamage / 3.0F), false, 0.0F);
                    float rotationYaw = target.rotationYaw + (60 * i) + (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                    float rotationPitch = (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                    float fYawDtoR = rotationYaw / 180.0F * 3.1415927F;
                    float fPitDtoR = rotationPitch / 180.0F * 3.1415927F;
                    float fYVecOfst = 0.5F;
                    float motionX = -MathHelper.sin(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                    float motionY = -MathHelper.sin(fPitDtoR) * fYVecOfst;
                    float motionZ = MathHelper.cos(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                    entityDrive.setLocationAndAngles(target.posX - motionX, target.posY + target
                            .getEyeHeight() / 2.0D - motionY, target.posZ - motionZ, rotationYaw, rotationPitch);
                    entityDrive.setDriveVector(fYVecOfst);
                    entityDrive.setLifeTime(8);
                    entityDrive.setIsMultiHit(false);
                    int rank = StylishRankManager.getStylishRank((Entity)player);
                    if (5 <= rank) {
                        EnumSet<ItemSlashBlade.SwordType> type = blade.getSwordType(stack);
                        entityDrive.setIsSlashDimension(type.contains(ItemSlashBlade.SwordType.FiercerEdge));
                    }
                    entityDrive.setRoll(90.0F + 120.0F * (entityDrive.getRand().nextFloat() - 0.5F));
                    if (entityDrive != null)
                        world.spawnEntityInWorld((Entity)entityDrive);
                }
            if (!target.worldObj.isRemote) {
                EntitySlashDimensionAdd dim = new EntitySlashDimensionAdd(world, (EntityLivingBase)player, magicDamage);
                if (dim != null) {
                    dim.setPosition(target.posX, target.posY + target.height / 2.0D, target.posZ);
                    dim.setLifeTime(25);
                    dim.setColor(15132922);
                    dim.setIsSlashDimension(true);
                    world.spawnEntityInWorld((Entity)dim);
                }
            }
        }
    }

    @Override
    public void doSuperSpecialAttack(ItemStack stack, EntityPlayer player) {

        EntityJudgmentCutManager entityDA = new EntityJudgmentCutManager(player.worldObj, player);
        if (entityDA != null) {
            player.worldObj.spawnEntityInWorld(entityDA);
        }
        UntouchableTime.setUntouchableTime(player, 30, true);

    }

}