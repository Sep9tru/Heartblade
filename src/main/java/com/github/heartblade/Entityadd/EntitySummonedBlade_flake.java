package com.github.heartblade.Entityadd;

import mods.flammpfeil.slashblade.ItemSlashBlade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class EntitySummonedBlade_flake extends EntityPhantomSwordBase_DaNiu {
    public long hitTime = 0L;

    public float hitStopFactor = 0.0F;

    public static float high = 1.0F;

    public static float wide = 2.0F;

    public EntitySummonedBlade_flake(World par1World) {
        super(par1World);
        setInterval(0);
        setSize(wide * 1.0F, high * 1.0F);
        this.hitStopFactor = this.rand.nextFloat();
    }

    public EntitySummonedBlade_flake(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
    }

    public EntitySummonedBlade_flake(World par1World, EntityLivingBase entityLiving, float AttackLevel, float roll) {
        super(par1World, entityLiving, AttackLevel, roll);
        float dist = 1.0F;
        int dirFactor = this.rand.nextInt(6);
        float rotBase = 30.0F;
        float[] rolls = { 180.0F + rotBase, -180.0F, 180.0F - rotBase, -rotBase, 0.0F, rotBase };
        setRoll(rolls[dirFactor]);
        int[][] pattern = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, 1 }, { -1, 0 }, { -1, -1 } };
        double yaw = Math.toRadians((-this.thrower.rotationYaw + (90 * pattern[dirFactor][0])));
        double x = Math.sin(yaw);
        double y = (pattern[dirFactor][1] * 0.5F);
        double z = Math.cos(yaw);
        x *= dist;
        y *= dist;
        z *= dist;
        Vec3 vec = this.thrower.getLookVec();
        if (vec != null) {
            x -= vec.xCoord;
            y -= vec.yCoord;
            z -= vec.zCoord;
        }
        setLocationAndAngles(this.thrower.posX + x, this.thrower.posY + y, this.thrower.posZ + z, this.thrower.rotationYaw, this.thrower.rotationPitch);
        this.iniYaw = this.thrower.rotationYaw;
        this.iniPitch = this.thrower.rotationPitch;
        setDriveVector(5.0F);
    }

    protected boolean onImpact(MovingObjectPosition mop) {
        if (mop.entityHit == null)
            setDead();
        boolean result = true;
        if (mop.entityHit != null) {
            Entity target = mop.entityHit;
            if (target != this.thrower) {
                if (mop.hitInfo.equals(ItemSlashBlade.AttackableSelector)) {
                    attackEntity(target);
                } else {
                    destructEntity(target);
                }
                this.hitTime = this.worldObj.getTotalWorldTime();
            } else {
                result = false;
            }
        }
        return result;
    }

    public void calculateSpeed() {}

    public boolean doTargeting() {
        int targetid = getTargetEntityId();
        if (targetid == 0) {
            double expandFactor = 15.0D;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(expandFactor, expandFactor, expandFactor));
            list.removeAll(this.alreadyHitEntity);
            double tmpDistance = 15.0D;
            Entity pointedEntity = null;
            EntityLivingBase viewer = null;
            if (getThrower() != null && getThrower() instanceof EntityLivingBase)
                viewer = (EntityLivingBase)getThrower();
            if (viewer != null)
                for (Entity entity : list) {
                    if (entity == null || !entity.canBeCollidedWith())
                        continue;
                    if (!ItemSlashBlade.AttackableSelector.isEntityApplicable(entity))
                        continue;
                    if (!viewer.canEntityBeSeen(entity))
                        continue;
                    double d3 = getDistanceToEntity(entity);
                    if (d3 < tmpDistance || tmpDistance == 0.0D) {
                        if (entity == getRidingEntity() && !entity.canRiderInteract()) {
                            if (tmpDistance == 0.0D)
                                pointedEntity = entity;
                            continue;
                        }
                        pointedEntity = entity;
                        tmpDistance = d3;
                    }
                }
            if (pointedEntity != null)
                setTargetEntityId(pointedEntity.getEntityId());
        }
        if (targetid != 0 && getInterval() < this.ticksExisted) {
            Entity target = this.worldObj.getEntityByID(targetid);
            Entity player = this.thrower;
            if (target == player) {
                double dAmbit1 = 40.0D;
                AxisAlignedBB ab1 = AxisAlignedBB.getBoundingBox(this.posX - dAmbit1, this.posY - dAmbit1, this.posZ - dAmbit1, this.posX + dAmbit1, this.posY + dAmbit1, this.posZ + dAmbit1);
                List<Entity> list1 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, ab1, ItemSlashBlade.AttackableSelector);
                Entity entity = this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, ab1, this);
                for (Entity entity2 : list1) {
                    if (entity2 == null)
                        setLifeTime(0);
                    if (entity != null && entity == entity2)
                        target = entity;
                }
            }
            double dAmbit = 100.0D;
            AxisAlignedBB ab = AxisAlignedBB.getBoundingBox(this.posX - dAmbit, this.posY - dAmbit, this.posZ - dAmbit, this.posX + dAmbit, this.posY + dAmbit, this.posZ + dAmbit);
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, ab, ItemSlashBlade.AttackableSelector);
            Entity entity1 = this.worldObj.findNearestEntityWithinAABB(EntityLiving.class, ab, this);
            if (target == null)
                for (Entity entity : list) {
                    if (entity == null)
                        setLifeTime(0);
                    if (entity1 != null && entity1 == entity)
                        target = entity1;
                }
            if (target != null) {
                if (Float.isNaN(this.iniPitch) && this.thrower != null) {
                    this.iniYaw = this.thrower.rotationYaw;
                    this.iniPitch = this.thrower.rotationPitch;
                }
                float lastYaw = this.iniYaw;
                float lastPitch = this.iniPitch;
                faceEntity(this, target, 10.0F, 10.0F);
                float lastSpeed = (float)Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ).lengthVector();
                float speedFactor = Math.abs(this.iniYaw - lastYaw) / 10.0F + Math.abs(this.iniPitch - lastPitch) / 10.0F;
                speedFactor = 1.0F - Math.min(speedFactor, 0.75F);
                speedFactor = (0.75F * speedFactor + lastSpeed * 9.0F) / 10.0F;
                setDriveVector(speedFactor, false);
            }
        }
        return true;
    }
}

