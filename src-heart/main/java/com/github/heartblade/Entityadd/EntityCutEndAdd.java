package com.github.heartblade.Entityadd;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityCutEndAdd extends Entity implements IThrowableEntity {
    protected Entity thrower;

    protected ItemStack blade = null;

    protected List<Entity> alreadyHitEntity = new ArrayList<Entity>();

    protected float AttackLevel = 0.0F;

    public EntityCutEndAdd(World par1World) {
        super(par1World);
    }

    public EntityCutEndAdd(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit, float roll) {
        this(par1World, entityLiving, AttackLevel, multiHit);
        setRoll(roll);
    }

    public EntityCutEndAdd(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit) {
        this(par1World, entityLiving, AttackLevel);
        setIsMultiHit(multiHit);
    }

    public EntityCutEndAdd(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        this(par1World);
        this.AttackLevel = AttackLevel;
        this.yOffset = entityLiving.getEyeHeight() / 2.0F;
        this.thrower = (Entity)entityLiving;
        this.blade = entityLiving.getHeldItem();
        if (this.blade != null && !(this.blade.getItem() instanceof ItemSlashBlade))
            this.blade = null;
        this.alreadyHitEntity.clear();
        this.alreadyHitEntity.add(this.thrower);
        this.alreadyHitEntity.add(this.thrower.ridingEntity);
        this.alreadyHitEntity.add(this.thrower.riddenByEntity);
        this.ticksExisted = 0;
        setSize(2.0F, 1.0F);
        setLocationAndAngles(this.thrower.posX, this.thrower.posY + this.thrower.getEyeHeight() / 2.0D, this.thrower.posZ, this.thrower.rotationYaw, this.thrower.rotationPitch);
        setDriveVector(0.75F);
        setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    }

    protected void entityInit() {
        getDataWatcher().addObject(4, Byte.valueOf((byte)0));
        getDataWatcher().addObject(5, Float.valueOf(0.0F));
        getDataWatcher().addObject(6, Integer.valueOf(20));
        getDataWatcher().addObject(10, Integer.valueOf(3355647));
        getDataWatcher().addObject(21, Float.valueOf(1.05F));
        getDataWatcher().addObject(22, Float.valueOf(0.0F));
        getDataWatcher().addObject(23, Integer.valueOf(0));
        getDataWatcher().addObject(24, String.valueOf(""));
        getDataWatcher().addObject(25, String.valueOf(""));
        getDataWatcher().addObject(26, Byte.valueOf((byte)1));
    }

    public int getColor() {
        return getDataWatcher().getWatchableObjectInt(10);
    }

    public void setColor(int value) {
        getDataWatcher().updateObject(10, Integer.valueOf(value));
    }

    public boolean getIsMultiHit() {
        return (getDataWatcher().getWatchableObjectByte(4) == 0);
    }

    public void setIsMultiHit(boolean isMultiHit) {
        getDataWatcher().updateObject(4, Byte.valueOf((byte)(isMultiHit ? 1 : 0)));
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

    public float getInitSpeed() {
        return getDataWatcher().getWatchableObjectFloat(21);
    }

    public void setInitSpeed(float value) {
        getDataWatcher().updateObject(21, Float.valueOf(value));
    }

    public float getNextSpeed() {
        return getDataWatcher().getWatchableObjectFloat(22);
    }

    public void setNextSpeed(float value) {
        getDataWatcher().updateObject(22, Float.valueOf(value));
    }

    public int getChangeTime() {
        return getDataWatcher().getWatchableObjectInt(23);
    }

    public void setChangeTime(int value) {
        getDataWatcher().updateObject(23, Integer.valueOf(value));
    }

    public String getSound() {
        return getDataWatcher().getWatchableObjectString(24);
    }

    public void setSound(String value) {
        getDataWatcher().updateObject(24, String.valueOf(value));
    }

    public String getParticle() {
        return getDataWatcher().getWatchableObjectString(25);
    }

    public void setParticle(String value) {
        getDataWatcher().updateObject(25, String.valueOf(value));
    }

    public boolean getIsPlayerSound() {
        return (getDataWatcher().getWatchableObjectByte(26) == 0);
    }

    public void setIsPlayerSound(boolean isPlaySound) {
        getDataWatcher().updateObject(26, Byte.valueOf((byte)(isPlaySound ? 0 : 1)));
    }

    public void setSpeedByTimeEvent(float initSpeed, float nextSpeed, int changeTime) {
        setInitSpeed(initSpeed);
        setNextSpeed(nextSpeed);
        setChangeTime(changeTime);
    }

    public void setInitialSpeed(float f) {
        setLocationAndAngles(this.thrower.posX, this.thrower.posY + this.thrower.getEyeHeight() / 2.0D, this.thrower.posZ, this.thrower.rotationYaw, this.thrower.rotationPitch);
        setDriveVector(f);
    }

    public void setDriveVector(float fYVecOfst) {
        float fYawDtoR = this.rotationYaw / 180.0F * 3.141593F;
        float fPitDtoR = this.rotationPitch / 180.0F * 3.141593F;
        this.motionX = (-MathHelper.sin(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst);
        this.motionY = (-MathHelper.sin(fPitDtoR) * fYVecOfst);
        this.motionZ = (MathHelper.cos(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst);
        float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f3) * 180.0D / Math.PI);
    }

    public void onUpdate() {
        if (this.thrower == null) {
            setDead();
            return;
        }
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        double dAmbit = 1.5D;
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.posX - dAmbit, this.posY - dAmbit, this.posZ - dAmbit, this.posX + dAmbit, this.posY + dAmbit, this.posZ + dAmbit);
        if (getThrower() instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase)getThrower();
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.DestructableSelector);
            StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.DestructObject);
            list.removeAll(this.alreadyHitEntity);
            this.alreadyHitEntity.addAll(list);
            for (Entity curEntity : list) {
                boolean isDestruction = true;
                if (curEntity instanceof EntityFireball) {
                    if (((EntityFireball)curEntity).shootingEntity != null && ((EntityFireball)curEntity).shootingEntity.getEntityId() == entityLiving.getEntityId()) {
                        isDestruction = false;
                    } else {
                        isDestruction = !curEntity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), this.AttackLevel);
                    }
                } else if (curEntity instanceof EntityArrow) {
                    if (((EntityArrow)curEntity).shootingEntity != null && ((EntityArrow)curEntity).shootingEntity.getEntityId() == entityLiving.getEntityId())
                        isDestruction = false;
                } else if (curEntity instanceof IThrowableEntity) {
                    if (((IThrowableEntity)curEntity).getThrower() != null && ((IThrowableEntity)curEntity).getThrower().getEntityId() == entityLiving.getEntityId())
                        isDestruction = false;
                } else if (curEntity instanceof EntityThrowable && ((EntityThrowable)curEntity).getThrower() != null && ((EntityThrowable)curEntity).getThrower().getEntityId() == entityLiving.getEntityId()) {
                    isDestruction = false;
                }
                if (isDestruction) {
                    curEntity.motionX = 0.0D;
                    curEntity.motionY = 0.0D;
                    curEntity.motionZ = 0.0D;
                    curEntity.setDead();
                    for (int var1 = 0; var1 < 10; var1++) {
                        Random rand = getRand();
                        double var2 = rand.nextGaussian() * 0.02D;
                        double var4 = rand.nextGaussian() * 0.02D;
                        double var6 = rand.nextGaussian() * 0.02D;
                        double var8 = 10.0D;
                        this.worldObj.spawnParticle("explode", curEntity.posX + (rand.nextFloat() * curEntity.width * 2.0F) - curEntity.width - var2 * var8, curEntity.posY + (rand.nextFloat() * curEntity.height) - var4 * var8, curEntity.posZ + (rand.nextFloat() * curEntity.width * 2.0F) - curEntity.width - var6 * var8, var2, var4, var6);
                    }
                    StylishRankManager.doAttack(this.thrower);
                }
            }
        }
        if (!getIsMultiHit() || this.ticksExisted % 2 == 0) {
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.AttackableSelector);
            list.removeAll(this.alreadyHitEntity);
            if (!getIsMultiHit())
                this.alreadyHitEntity.addAll(list);
            float magicDamage = Math.max(1.0F, this.AttackLevel);
            if (getIsMultiHit()) {
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.QuickDrive);
            } else {
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.Drive);
            }
            for (Entity curEntity : list)
                onImpact(curEntity, magicDamage);
        }
        int nPosX = MathHelper.floor_double(this.posX);
        int nPosY = MathHelper.floor_double(this.posY);
        int nPosZ = MathHelper.floor_double(this.posZ);
        Block nBlock = this.worldObj.getBlock(nPosX, nPosY, nPosZ);
        if (!nBlock.isAir((IBlockAccess)this.worldObj, nPosX, nPosY, nPosZ) && nBlock.getCollisionBoundingBoxFromPool(this.worldObj, nPosX, nPosY, nPosZ) != null)
            setDead();
        double m_x = this.motionX;
        double m_y = this.motionY;
        double m_z = this.motionZ;
        int changeTime = getChangeTime();
        if (changeTime != 0 && this.ticksExisted >= changeTime) {
            float nextSpeed = getNextSpeed();
            playSound();
            m_x *= nextSpeed;
            m_y *= nextSpeed;
            m_z *= nextSpeed;
            this.motionX = m_x;
            this.motionY = m_y;
            this.motionZ = m_z;
            this.posX += m_x;
            this.posY += m_y;
            this.posZ += m_z;
        } else {
            float initSpeed = getInitSpeed();
            if (initSpeed >= 1.05F) {
                m_x *= initSpeed;
                m_y *= initSpeed;
                m_z *= initSpeed;
                this.motionX = m_x;
                this.motionY = m_y;
                this.motionZ = m_z;
                this.posX += m_x;
                this.posY += m_y;
                this.posZ += m_z;
            } else {
                this.posX += this.motionX * 0.1D;
                this.posY += this.motionY * 0.1D;
                this.posZ += this.motionZ * 0.1D;
            }
        }
        playParticle();
        setPosition(this.posX, this.posY, this.posZ);
        if (this.ticksExisted >= getLifeTime()) {
            this.alreadyHitEntity.clear();
            this.alreadyHitEntity = null;
            setDead();
        }
    }

    public Random getRand() {
        return this.rand;
    }

    public boolean isOffsetPositionInLiquid(double par1, double par3, double par5) {
        return false;
    }

    public void moveEntity(double par1, double par3, double par5) {}

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

    public void mountEntity(Entity par1Entity) {}

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

    public Entity getThrower() {
        return this.thrower;
    }

    public void setThrower(Entity entity) {
        this.thrower = entity;
    }

    public void onImpact(Entity target, float damage) {
        if (target != null) {
            if (target.worldObj.isRemote)
                return;
            target.hurtResistantTime = 0;
            DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage();
            target.attackEntityFrom(ds, damage);
            if (this.blade != null && target instanceof EntityLivingBase)
                ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)target, (EntityLivingBase)this.thrower);
        }
    }

    public void playParticle() {
        String particle = getParticle();
        if (particle != null && !particle.equals("")) {
            Random rand = new Random();
            double var2 = rand.nextGaussian() * 0.02D;
            double var4 = rand.nextGaussian() * 0.02D;
            double var6 = rand.nextGaussian() * 0.02D;
            double var8 = 10.0D;
            this.worldObj.spawnParticle(particle, this.posX + (rand.nextFloat() * this.width * 2.0F) - this.width - var2 * var8, this.posY + (rand.nextFloat() * this.height) - var4 * var8, this.posZ + (rand.nextFloat() * this.width * 2.0F) - this.width - var6 * var8, var2, var4, var6);
        }
    }

    public void playSound() {
        if (!getIsPlayerSound()) {
            String sound = getSound();
            if (sound != null && !sound.equals("") && this.thrower instanceof net.minecraft.entity.player.EntityPlayer)
                this.thrower.playSound(sound, 1.0F, 1.5F);
            setIsPlayerSound(true);
        }
    }
}
