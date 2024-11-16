package com.github.heartblade.Entityadd;

import cpw.mods.fml.relauncher.ReflectionHelper;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntityAquaEdge_kamuy extends EntityDriveAdd {
    public EntityAquaEdge_kamuy(World par1World) {
        super(par1World);
    }

    public EntityAquaEdge_kamuy(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit, float roll) {
        super(par1World, entityLiving, AttackLevel, multiHit, roll);
        this.setRoll(roll);
    }

    public EntityAquaEdge_kamuy(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit) {
        super(par1World, entityLiving, AttackLevel, multiHit);
    }

    public EntityAquaEdge_kamuy(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
    }

    public void onImpact(Entity curEntity, float damage) {
        this.spawnParticle(curEntity);
        if (!curEntity.worldObj.isRemote) {
            curEntity.hurtResistantTime = 0;
            DamageSource ds = (new EntityDamageSource("drown", this.getThrower())).setDamageBypassesArmor().setMagicDamage();
            curEntity.attackEntityFrom(ds, damage);
        }

        ReflectionHelper.setPrivateValue(Entity.class, curEntity, true, new String[]{"inWater", "field_70171_ac"});
        if (curEntity.isBurning()) {
            curEntity.playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
            ReflectionHelper.setPrivateValue(Entity.class, curEntity, 0, new String[]{"fire", "field_70151_c"});
            byte data = curEntity.getDataWatcher().getWatchableObjectByte(0);
            boolean flag = false;
            int offset = 0;
            if (flag) {
                curEntity.getDataWatcher().updateObject(0, (byte)(data | 1 << offset));
            } else {
                curEntity.getDataWatcher().updateObject(0, (byte)(data & ~(1 << offset)));
            }
        }

        if (!curEntity.worldObj.isRemote && this.blade != null && curEntity instanceof EntityLivingBase) {
            ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)curEntity, (EntityLivingBase)this.thrower);
        }

        if (curEntity instanceof EntityEnderman) {
            ((EntityEnderman)curEntity).setTarget((Entity)null);
            ((EntityEnderman)curEntity).setScreaming(false);
            ReflectionHelper.setPrivateValue(EntityEnderman.class, (EntityEnderman)curEntity, false, new String[]{"isAggressive", "field_104003_g"});
        }

    }

    private void spawnParticle(Entity target) {
        target.worldObj.spawnParticle("largeexplode", target.posX, target.posY + (double)target.height, target.posZ, 3.0D, 3.0D, 3.0D);
        target.worldObj.spawnParticle("largeexplode", target.posX + 1.0D, target.posY + (double)target.height + 1.0D, target.posZ, 3.0D, 3.0D, 3.0D);
        target.worldObj.spawnParticle("largeexplode", target.posX, target.posY + (double)target.height + 0.5D, target.posZ + 1.0D, 3.0D, 3.0D, 3.0D);
    }
}

