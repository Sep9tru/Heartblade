package com.github.heartblade.Entityadd;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityPhantomSwordBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityPhantomSwordBase_DaNiu extends Entity implements IThrowableEntity {
    protected Entity thrower;

    protected ItemStack blade = null;

    protected List<Entity> alreadyHitEntity = new ArrayList<Entity>();

    protected float AttackLevel = 0.0F;

    static final int THROWER_ENTITY_ID = 11;

    float speed;

    float iniYaw;

    float iniPitch;

    double hitX;

    double hitY;

    double hitZ;

    float hitYaw;

    float hitPitch;

    public Entity ridingEntity2;

    public EntityPhantomSwordBase_DaNiu(World par1World) {
        super(par1World);
        this.speed = 0.0F;
        this.iniYaw = Float.NaN;
        this.iniPitch = Float.NaN;
        this.ridingEntity2 = null;
        this.noClip = true;
    }

    public EntityPhantomSwordBase_DaNiu(World par1World, EntityLivingBase entityLiving, float AttackLevel, float roll) {
        this(par1World, entityLiving, AttackLevel);
        setRoll(roll);
    }

    public EntityPhantomSwordBase_DaNiu(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        this(par1World);
        this.AttackLevel = AttackLevel;
        this.yOffset = entityLiving.getEyeHeight() / 2.0F;
        setThrower((Entity)entityLiving);
        this.blade = entityLiving.getHeldItem();
        if (this.blade != null && !(this.blade.getItem() instanceof ItemSlashBlade))
            this.blade = null;
        this.alreadyHitEntity.clear();
        this.alreadyHitEntity.add(this.thrower);
        this.alreadyHitEntity.add(this.thrower.ridingEntity);
        this.alreadyHitEntity.add(this.thrower.riddenByEntity);
        this.ticksExisted = 0;
        setSize(0.5F, 0.5F);
        float dist = 2.0F;
        double ran = (this.rand.nextFloat() - 0.5D) * 2.0D;
        double yaw = Math.toRadians((-this.thrower.rotationYaw + 90.0F));
        double x = ran * Math.sin(yaw);
        double y = 1.0D - Math.abs(ran);
        double z = ran * Math.cos(yaw);
        x *= dist;
        y *= dist;
        z *= dist;
        setLocationAndAngles(this.thrower.posX + x, this.thrower.posY + y, this.thrower.posZ + z, this.thrower.rotationYaw, this.thrower.rotationPitch);
        this.iniYaw = this.thrower.rotationYaw;
        this.iniPitch = this.thrower.rotationPitch;
        setDriveVector(1.75F);
    }

    protected void entityInit() {
        getDataWatcher().addObject(11, Integer.valueOf(0));
        getDataWatcher().addObject(4, Integer.valueOf(0));
        getDataWatcher().addObject(5, Float.valueOf(0.0F));
        getDataWatcher().addObject(6, Integer.valueOf(20));
        getDataWatcher().addObject(7, Integer.valueOf(7));
        getDataWatcher().addObject(10, Integer.valueOf(3355647));
        getDataWatcher().addObject(15, Float.valueOf(0.01F));
    }

    public float getSize() {
        return getDataWatcher().getWatchableObjectFloat(15);
    }

    public void setSize(float value) {
        getDataWatcher().updateObject(15, Float.valueOf(value));
    }

    public int getThrowerEntityId() {
        return getDataWatcher().getWatchableObjectInt(11);
    }

    public void setThrowerEntityId(int entityid) {
        getDataWatcher().updateObject(11, Integer.valueOf(entityid));
    }

    public int getTargetEntityId() {
        return getDataWatcher().getWatchableObjectInt(4);
    }

    public void setTargetEntityId(int entityid) {
        getDataWatcher().updateObject(4, Integer.valueOf(entityid));
    }

    public float getRoll() {
        return getDataWatcher().getWatchableObjectFloat(5);
    }

    public void setRoll(float roll) {
        getDataWatcher().updateObject(5, Float.valueOf(roll));
    }

    public int getLifeTime() {
        return getDataWatcher().getWatchableObjectInt(6);
    }

    public void setLifeTime(int lifetime) {
        getDataWatcher().updateObject(6, Integer.valueOf(lifetime));
    }

    public int getInterval() {
        return getDataWatcher().getWatchableObjectInt(7);
    }

    public void setInterval(int value) {
        getDataWatcher().updateObject(7, Integer.valueOf(value));
    }

    public int getColor() {
        return getDataWatcher().getWatchableObjectInt(10);
    }

    public void setColor(int value) {
        getDataWatcher().updateObject(10, Integer.valueOf(value));
    }

    public boolean doTargeting() {
        if (this.ticksExisted > getInterval())
            return false;
        int targetid = getTargetEntityId();
        Entity owner = this.thrower;
        if (this.thrower == null)
            owner = this;
        if (targetid == 0) {
            Entity rayEntity = getRayTrace(owner, 30.0D);
            if (rayEntity != null) {
                targetid = rayEntity.getEntityId();
                setTargetEntityId(rayEntity.getEntityId());
            }
        }
        if (targetid == 0) {
            Entity rayEntity = getRayTrace(owner, 30.0D, 5.0F, 5.0F);
            if (rayEntity != null) {
                targetid = rayEntity.getEntityId();
                setTargetEntityId(rayEntity.getEntityId());
            }
        }
        if (targetid != 0) {
            Entity target = this.worldObj.getEntityByID(targetid);
            if (target != null) {
                if (Float.isNaN(this.iniPitch) && this.thrower != null) {
                    this.iniYaw = this.thrower.rotationYaw;
                    this.iniPitch = this.thrower.rotationPitch;
                }
                faceEntity(this, target, this.ticksExisted * 1.0F, this.ticksExisted * 1.0F);
                setDriveVector(1.75F, false);
            }
        }
        return true;
    }

    public Entity getRayTrace(Entity owner, double reachMax) {
        return getRayTrace(owner, reachMax, 1.0F, 0.0F);
    }

    public Entity getRayTrace(Entity owner, double reachMax, float expandFactor, float expandBorder) {
        float par1 = 1.0F;
        MovingObjectPosition objectMouseOver = rayTrace(owner, reachMax, par1);
        double reachMin = reachMax;
        Vec3 entityPos = getPosition(owner);
        if (objectMouseOver != null)
            reachMin = objectMouseOver.hitVec.distanceTo(entityPos);
        Vec3 lookVec = getLook(owner, par1);
        Vec3 reachVec = entityPos.addVector(lookVec.xCoord * reachMax, lookVec.yCoord * reachMax, lookVec.zCoord * reachMax);
        Entity pointedEntity = null;
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(lookVec.xCoord * reachMax, lookVec.yCoord * reachMax, lookVec.zCoord * reachMax).expand(expandFactor, expandFactor, expandFactor));
        list.removeAll(this.alreadyHitEntity);
        double tmpDistance = reachMin;
        EntityLivingBase viewer = (owner instanceof EntityLivingBase) ? (EntityLivingBase)owner : null;
        for (Entity entity : list) {
            if (entity == null || !entity.canBeCollidedWith())
                continue;
            if (!ItemSlashBlade.AttackableSelector.isEntityApplicable(entity))
                continue;
            if (viewer != null && !viewer.canEntityBeSeen(entity))
                continue;
            float borderSize = entity.getCollisionBorderSize() + expandBorder;
            AxisAlignedBB axisalignedbb = entity.boundingBox.expand(borderSize, borderSize, borderSize);
            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(entityPos, reachVec);
            if (axisalignedbb.isVecInside(entityPos)) {
                if (0.0D < tmpDistance || tmpDistance == 0.0D) {
                    pointedEntity = entity;
                    tmpDistance = 0.0D;
                }
                continue;
            }
            if (movingobjectposition != null) {
                double d3 = entityPos.distanceTo(movingobjectposition.hitVec);
                if (d3 < tmpDistance || tmpDistance == 0.0D) {
                    if (entity == this.ridingEntity && !entity.canRiderInteract()) {
                        if (tmpDistance == 0.0D)
                            pointedEntity = entity;
                        continue;
                    }
                    pointedEntity = entity;
                    tmpDistance = d3;
                }
            }
        }
        return pointedEntity;
    }

    public static MovingObjectPosition rayTrace(Entity owner, double par1, float par3) {
        Vec3 vec3 = getPosition(owner);
        Vec3 vec31 = getLook(owner, par3);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * par1, vec31.yCoord * par1, vec31.zCoord * par1);
        return owner.worldObj.func_147447_a(vec3, vec32, false, false, true);
    }

    public static Vec3 getPosition(Entity owner) {
        return Vec3.createVectorHelper(owner.posX, owner.posY + owner.getEyeHeight(), owner.posZ);
    }

    public static Vec3 getLook(Entity owner, float rotMax) {
        if (rotMax == 1.0F) {
            float f7 = MathHelper.cos(-owner.rotationYaw * 0.017453292F - 3.1415927F);
            float f8 = MathHelper.sin(-owner.rotationYaw * 0.017453292F - 3.1415927F);
            float f9 = -MathHelper.cos(-owner.rotationPitch * 0.017453292F);
            float f10 = MathHelper.sin(-owner.rotationPitch * 0.017453292F);
            return Vec3.createVectorHelper((f8 * f9), f10, (f7 * f9));
        }
        float f1 = owner.prevRotationPitch + (owner.rotationPitch - owner.prevRotationPitch) * rotMax;
        float f2 = owner.prevRotationYaw + (owner.rotationYaw - owner.prevRotationYaw) * rotMax;
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        return Vec3.createVectorHelper((f4 * f5), f6, (f3 * f5));
    }

    public void faceEntity(Entity viewer, Entity target, float yawStep, float pitchStep) {
        double d2, d0 = target.posX - viewer.posX;
        double d1 = target.posZ - viewer.posZ;
        if (target instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)target;
            d2 = entitylivingbase.posY + entitylivingbase.getEyeHeight() - viewer.posY + viewer.getEyeHeight();
        } else {
            d2 = (target.boundingBox.minY + target.boundingBox.maxY) / 2.0D - viewer.posY + viewer.getEyeHeight();
        }
        double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
        float f2 = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
        float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / Math.PI);
        this.iniPitch = updateRotation(this.iniPitch, f3, pitchStep);
        this.iniYaw = updateRotation(this.iniYaw, f2, yawStep);
    }

    private float updateRotation(float par1, float par2, float par3) {
        float f3 = MathHelper.wrapAngleTo180_float(par2 - par1);
        if (f3 > par3)
            f3 = par3;
        if (f3 < -par3)
            f3 = -par3;
        return par1 + f3;
    }

    public void setDriveVector(float fYVecOfset) {
        setDriveVector(fYVecOfset, true);
    }

    public void setDriveVector(float fYVecOfst, boolean init) {
        float fYawDtoR = this.iniYaw / 180.0F * 3.1415927F;
        float fPitDtoR = this.iniPitch / 180.0F * 3.1415927F;
        this.motionX = (-MathHelper.sin(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst);
        this.motionY = (-MathHelper.sin(fPitDtoR) * fYVecOfst);
        this.motionZ = (MathHelper.cos(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst);
        float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.rotationPitch = (float)(Math.atan2(this.motionY, f3) * 180.0D / Math.PI);
        if (init) {
            this.speed = fYVecOfst;
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
    }

    public void updateRidden() {
        Entity ridingEntity = this.ridingEntity2;
        if (ridingEntity.isDead) {
            setDead();
            return;
        }
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        this.posX = ridingEntity.posX + this.hitX * Math.cos(Math.toRadians(ridingEntity.rotationYaw)) - this.hitZ * Math.sin(Math.toRadians(ridingEntity.rotationYaw));
        this.posY = ridingEntity.posY + this.hitY;
        this.posZ = ridingEntity.posZ + this.hitX * Math.sin(Math.toRadians(ridingEntity.rotationYaw)) + this.hitZ * Math.cos(Math.toRadians(ridingEntity.rotationYaw));
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;
        this.rotationPitch = ridingEntity.rotationPitch + this.hitPitch;
        this.rotationYaw = ridingEntity.rotationYaw + this.hitYaw;
        setPosition(this.posX, this.posY, this.posZ);
        setRotation(this.rotationYaw, this.rotationPitch);
        if (this.ticksExisted >= 200) {
            if (!ridingEntity.isDead && !this.worldObj.isRemote) {
                float magicDamage = Math.max(1.0F, this.AttackLevel / 2.0F);
                ridingEntity.hurtResistantTime = 0;
                DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage();
                ridingEntity.attackEntityFrom(ds, magicDamage);
                if (this.blade != null && ridingEntity instanceof EntityLivingBase) {
                    if (this.thrower != null) {
                        StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.BreakPhantomSword);
                        ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)ridingEntity, (EntityLivingBase)this.thrower);
                    }
                    ridingEntity.motionX = 0.0D;
                    ridingEntity.motionY = 0.0D;
                    ridingEntity.motionZ = 0.0D;
                    ridingEntity.addVelocity(0.0D, 0.1D, 0.0D);
                    ((EntityLivingBase)ridingEntity).hurtTime = 1;
                }
            }
            setDead();
        }
    }

    protected void initRotation() {
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }
    }

    protected MovingObjectPosition getMovingObjectPosition() {
        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        if (movingobjectposition != null) {
            int x = MathHelper.floor_double(movingobjectposition.hitVec.xCoord);
            int y = MathHelper.floor_double(movingobjectposition.hitVec.yCoord);
            int z = MathHelper.floor_double(movingobjectposition.hitVec.zCoord);
            int offset = -1;
            Block block = this.worldObj.getBlock(x, y + offset, z);
            if (block != null)
                if (block.getCollisionBoundingBoxFromPool(this.worldObj, x, y + offset, z) == null) {
                    movingobjectposition = null;
                } else {
                    vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
                }
        }
        Entity entity = null;
        AxisAlignedBB bb = this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D);
        IEntitySelector[] selectors = { ItemSlashBlade.DestructableSelector, ItemSlashBlade.AttackableSelector };
        for (IEntitySelector selector : selectors) {
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bb, selector);
            list.removeAll(this.alreadyHitEntity);
            if (selector.equals(ItemSlashBlade.AttackableSelector) && getTargetEntityId() != 0) {
                Entity target = this.worldObj.getEntityByID(getTargetEntityId());
                if (target != null && target.boundingBox.intersectsWith(bb))
                    list.add(target);
            }
            double d0 = 0.0D;
            for (int i = 0; i < list.size(); i++) {
                Entity entity1 = list.get(i);
                if (!(entity1 instanceof EntityPhantomSwordBase) || ((EntityPhantomSwordBase)entity1).getThrower() != getThrower())
                    if (entity1.canBeCollidedWith()) {
                        float f1 = 0.3F;
                        AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, f1, f1);
                        MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
                        if (movingobjectposition1 != null) {
                            double d1 = vec31.distanceTo(movingobjectposition1.hitVec);
                            if (d1 < d0 || d0 == 0.0D) {
                                entity = entity1;
                                d0 = d1;
                            }
                        }
                    }
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
                movingobjectposition.hitInfo = selector;
                break;
            }
        }
        if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
            if (entityplayer.capabilities.disableDamage || (getThrower() != null && getThrower() instanceof EntityPlayer && !((EntityPlayer)getThrower()).canAttackPlayer(entityplayer)))
                movingobjectposition = null;
        }
        return movingobjectposition;
    }

    public void doRotation() {
        if (doTargeting())
            return;
        float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        for (this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
    }

    public void normalizeRotation() {
        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            this.prevRotationPitch += 360.0F;
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            this.prevRotationYaw -= 360.0F;
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            this.prevRotationYaw += 360.0F;
    }

    protected void destructEntity(Entity target) {
        if (this.thrower == null)
            return;
        StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.DestructObject);
        boolean isDestruction = true;
        if (target instanceof EntityFireball) {
            if (((EntityFireball)target).shootingEntity != null && ((EntityFireball)target).shootingEntity.getEntityId() == this.thrower.getEntityId()) {
                isDestruction = false;
            } else if (this.thrower instanceof EntityLivingBase) {
                isDestruction = !target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.thrower), this.AttackLevel);
            }
        } else if (target instanceof EntityArrow) {
            if (((EntityArrow)target).shootingEntity != null && ((EntityArrow)target).shootingEntity.getEntityId() == this.thrower.getEntityId())
                isDestruction = false;
        } else if (target instanceof EntityThrowable && ((EntityThrowable)target).getThrower() != null && ((EntityThrowable)target).getThrower().getEntityId() == this.thrower.getEntityId()) {
            isDestruction = false;
        }
        if (isDestruction && target instanceof IThrowableEntity && ((IThrowableEntity)target).getThrower() != null && ((IThrowableEntity)target).getThrower().getEntityId() == this.thrower.getEntityId())
            isDestruction = false;
        if (isDestruction) {
            target.motionX = 0.0D;
            target.motionY = 0.0D;
            target.motionZ = 0.0D;
            target.setDead();
            for (int var1 = 0; var1 < 10; var1++) {
                Random rand = getRand();
                double var2 = rand.nextGaussian() * 0.02D;
                double var4 = rand.nextGaussian() * 0.02D;
                double var6 = rand.nextGaussian() * 0.02D;
                double var8 = 10.0D;
                this.worldObj.spawnParticle("explode", target.posX + (rand.nextFloat() * target.width * 2.0F) - target.width - var2 * var8, target.posY + (rand.nextFloat() * target.height) - var4 * var8, target.posZ + (rand.nextFloat() * target.width * 2.0F) - target.width - var6 * var8, var2, var4, var6);
            }
        }
        StylishRankManager.doAttack(this.thrower);
        setDead();
    }

    protected void attackEntity(Entity target) {
        if (this.thrower != null)
            this.thrower.getEntityData().setInteger("LastHitSummonedSwords2", getEntityId());
        mountEntity(target);
        if (!this.worldObj.isRemote) {
            float magicDamage = Math.max(1.0F, this.AttackLevel);
            target.hurtResistantTime = 0;
            DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage();
            target.attackEntityFrom(ds, magicDamage);
            if (this.blade != null && target instanceof EntityLivingBase && this.thrower != null && this.thrower instanceof EntityLivingBase) {
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.PhantomSword);
                ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)target, (EntityLivingBase)this.thrower);
                target.motionX = 0.0D;
                target.motionY = 0.0D;
                target.motionZ = 0.0D;
                target.addVelocity(0.0D, 0.1D, 0.0D);
                ((EntityLivingBase)target).hurtTime = 1;
                ((ItemSlashBlade)this.blade.getItem()).setDaunting((EntityLivingBase)target);
            }
        }
    }

    protected void blastAttackEntity(Entity target) {
        if (!this.worldObj.isRemote) {
            float magicDamage = 1.0F;
            target.hurtResistantTime = 0;
            DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage();
            target.attackEntityFrom(ds, magicDamage);
            if (this.blade != null && target instanceof EntityLivingBase && this.thrower != null && this.thrower instanceof EntityLivingBase) {
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.PhantomSword);
                ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)target, (EntityLivingBase)this.thrower);
                target.motionX = 0.0D;
                target.motionY = 0.0D;
                target.motionZ = 0.0D;
                target.addVelocity(0.0D, 0.1D, 0.0D);
                ((EntityLivingBase)target).hurtTime = 1;
                ((ItemSlashBlade)this.blade.getItem()).setDaunting((EntityLivingBase)target);
            }
        }
    }

    protected boolean onImpact(MovingObjectPosition mop) {
        boolean result = true;
        if (mop.entityHit != null) {
            Entity target = mop.entityHit;
            if (mop.hitInfo.equals(ItemSlashBlade.AttackableSelector)) {
                attackEntity(target);
            } else {
                destructEntity(target);
            }
        } else if (!this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) {
            result = true;
        }
        return result;
    }

    public void spawnParticle() {
        if (isInWater())
            for (int l = 0; l < 4; l++) {
                float trailLength = 0.25F;
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * trailLength, this.posY - this.motionY * trailLength, this.posZ - this.motionZ * trailLength, this.motionX, this.motionY, this.motionZ);
            }
    }

    public void calculateSpeed() {
        float speedReductionFactor = 1.1F;
        if (isInWater())
            speedReductionFactor = 1.0F;
        this.motionX *= speedReductionFactor;
        this.motionY *= speedReductionFactor;
        this.motionZ *= speedReductionFactor;
    }

    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        if (this.ridingEntity2 != null) {
            updateRidden();
        } else {
            if (this.ticksExisted >= getLifeTime())
                setDead();
            initRotation();
            MovingObjectPosition movingobjectposition = getMovingObjectPosition();
            if (movingobjectposition != null)
                if (onImpact(movingobjectposition))
                    return;
            calculateSpeed();
            doRotation();
            if (getInterval() < this.ticksExisted)
                moveEntity(this.motionX, this.motionY, this.motionZ);
            normalizeRotation();
            spawnParticle();
        }
    }

    public void setDead() {
        if (this.thrower != null && this.thrower instanceof EntityPlayer)
            ((EntityPlayer)this.thrower).onCriticalHit(this);
        this.worldObj.playSoundEffect(this.prevPosX, this.prevPosY, this.prevPosZ, "dig.glass", 0.25F, 1.6F);
        AxisAlignedBB bb = this.boundingBox.expand(1.0D, 1.0D, 1.0D);
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bb, ItemSlashBlade.AttackableSelector);
        list.removeAll(this.alreadyHitEntity);
        for (Entity target : list) {
            if (target == null)
                continue;
            blastAttackEntity(target);
        }
        super.setDead();
    }

    public Random getRand() {
        return this.rand;
    }

    public boolean isOffsetPositionInLiquid(double par1, double par3, double par5) {
        return false;
    }

    public void moveEntity(double par1, double par3, double par5) {
        super.moveEntity(par1, par3, par5);
    }

    protected void dealFireDamage(int par1) {}

    public boolean handleWaterMovement() {
        return false;
    }

    public boolean isInsideOfMaterial(Material par1Material) {
        return false;
    }

    public boolean handleLavaMovement() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1) {
        float f1 = 0.5F;
        if (f1 < 0.0F)
            f1 = 0.0F;
        if (f1 > 1.0F)
            f1 = 1.0F;
        int i = super.getBrightnessForRender(par1);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        j += (int)(f1 * 15.0F * 16.0F);
        if (j > 240)
            j = 240;
        return j | k << 16;
    }

    public float getBrightness(float par1) {
        float f1 = super.getBrightness(par1);
        float f2 = 0.9F;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }

    public Entity getRidingEntity() {
        return this.ridingEntity2;
    }

    public void mountEntity(Entity par1Entity) {
        if (par1Entity != null) {
            this.hitYaw = this.rotationYaw - par1Entity.rotationYaw;
            this.hitPitch = this.rotationPitch - par1Entity.rotationPitch;
            this.hitX = this.posX - par1Entity.posX;
            this.hitY = this.posY - par1Entity.posY;
            this.hitZ = this.posZ - par1Entity.posZ;
            this.ridingEntity2 = par1Entity;
            this.ticksExisted = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {}

    public void setInPortal() {}

    public boolean isBurning() {
        return false;
    }

    public boolean shouldRenderInPass(int pass) {
        return (pass == 1);
    }

    public void setInWeb() {}

    public void setThrowableHeading(double v, double v2, double v3, float v4, float v5) {}

    public Entity getThrower() {
        if (this.thrower == null) {
            int id = getThrowerEntityId();
            if (id != 0)
                this.thrower = this.worldObj.getEntityByID(id);
        }
        return this.thrower;
    }

    public void setThrower(Entity entity) {
        if (entity != null)
            setThrowerEntityId(entity.getEntityId());
        this.thrower = entity;
    }
}
