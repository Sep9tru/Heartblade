package com.github.heartblade.entity;

import java.util.List;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.util.math.RayTraceResult;

public class EntityFlake extends EntityPhantomSwordExBase
{
    public long hitTime;
    public float hitStopFactor;

    public EntityFlake(final World par1World) {
        super(par1World);
        this.hitTime = 0L;
        this.hitStopFactor = 0.0f;
        this.setInterval(0);
        this.hitStopFactor = this.rand.nextFloat();
    }

    public EntityFlake(final World par1World, final EntityLivingBase entityLiving, final float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
        this.hitTime = 0L;
        this.hitStopFactor = 0.0f;
    }

    public EntityFlake(final World par1World, final EntityLivingBase entityLiving, final float AttackLevel, final float roll) {
        super(par1World, entityLiving, AttackLevel, roll);
        this.hitTime = 0L;
        this.hitStopFactor = 0.0f;
        final float dist = 1.0f;
        final int dirFactor = this.rand.nextInt(6);
        final float rotBase = 30.0f;
        final float[] rolls = { 180.0f + rotBase, -180.0f, 180.0f - rotBase, -rotBase, 0.0f, rotBase };
        this.setRoll(rolls[dirFactor]);
        final int[][] pattern = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, 1 }, { -1, 0 }, { -1, -1 } };
        final double yaw = Math.toRadians(-this.thrower.rotationYaw + 90 * pattern[dirFactor][0]);
        double x = Math.sin(yaw);
        double y = pattern[dirFactor][1] * 0.5f;
        double z = Math.cos(yaw);
        x *= dist;
        y *= dist;
        z *= dist;
        final Vec3d vec = this.thrower.getLookVec();
        if (vec != null) {
            x -= vec.x;
            y -= vec.y;
            z -= vec.z;
        }
        this.setLocationAndAngles(this.thrower.posX + x, this.thrower.posY + this.thrower.getEyeHeight() / 2.0f + y, this.thrower.posZ + z, this.thrower.rotationYaw, this.thrower.rotationPitch);
        this.iniYaw = this.thrower.rotationYaw;
        this.iniPitch = this.thrower.rotationPitch;
        this.setDriveVector(1.75f);
    }

    @Override
    protected boolean onImpact(final RayTraceResult mop) {
        boolean result = true;
        if (mop.entityHit != null) {
            final Entity target = mop.entityHit;
            if (mop.hitInfo.equals(EntitySelectorAttackable.getInstance())) {
                this.attackEntity(target);
            }
            else {
                this.destructEntity(target);
            }
            this.hitTime = this.getEntityWorld().getTotalWorldTime();
        }
        else {
            result = false;
        }
        return result;
    }

    @Override
    public void calculateSpeed() {
    }

    @Override
    public boolean doTargeting() {
        final int targetid = this.getTargetEntityId();
        if (targetid == 0) {
            final double expandFactor = 15.0;
            final List<Entity> list = (List<Entity>)this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, this.getEntityBoundingBox().grow(expandFactor, expandFactor, expandFactor));
            list.removeAll(this.alreadyHitEntity);
            double tmpDistance = 15.0;
            Entity pointedEntity = null;
            EntityLivingBase viewer = null;
            if (this.getThrower() != null && this.getThrower() instanceof EntityLivingBase) {
                viewer = (EntityLivingBase)this.getThrower();
            }
            if (viewer != null) {
                for (final Entity entity : list) {
                    if (entity != null) {
                        if (!entity.canBeCollidedWith()) {
                            continue;
                        }
                        if (!EntitySelectorAttackable.getInstance().apply((Entity) entity)) {
                            continue;
                        }
                        if (!viewer.canEntityBeSeen(entity)) {
                            continue;
                        }
                        final double d3 = this.getDistance(entity);
                        if (d3 >= tmpDistance && tmpDistance != 0.0) {
                            continue;
                        }
                        if (entity == this.getRidingEntity() && !entity.canRiderInteract()) {
                            if (tmpDistance != 0.0) {
                                continue;
                            }
                            pointedEntity = entity;
                        }
                        else {
                            pointedEntity = entity;
                            tmpDistance = d3;
                        }
                    }
                }
            }
            if (pointedEntity != null) {
                this.setTargetEntityId(pointedEntity.getEntityId());
            }
        }
        if (targetid != 0 && this.getInterval() < this.ticksExisted) {
            final Entity target = this.world.getEntityByID(targetid);
            if (target != null) {
                if (Float.isNaN(this.iniPitch) && this.thrower != null) {
                    this.iniYaw = this.thrower.rotationYaw;
                    this.iniPitch = this.thrower.rotationPitch;
                }
                final float lastYaw = this.iniYaw;
                final float lastPitch = this.iniPitch;
                this.faceEntity(this, target, 10.0f, 10.0f);
                final float lastSpeed = (float)new Vec3d(this.motionX, this.motionY, this.motionZ).length();
                float speedFactor = Math.abs(this.iniYaw - lastYaw) / 10.0f + Math.abs(this.iniPitch - lastPitch) / 10.0f;
                speedFactor = 1.0f - Math.min(speedFactor, 0.75f);
                speedFactor = (0.75f * speedFactor + lastSpeed * 9.0f) / 10.0f;
                this.setDriveVector(speedFactor, false);
            }
        }
        return true;
    }
}
