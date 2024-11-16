package com.github.heartblade.Entityadd;

import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityPhantomSwordBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntityPhantomBullet extends EntityPhantomSwordBase {
    public EntityPhantomBullet(World par1World) {
        super(par1World);
    }

    public EntityPhantomBullet(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
    }

    public EntityPhantomBullet(World par1World, EntityLivingBase entityLiving, float AttackLevel, float roll) {
        super(par1World, entityLiving, AttackLevel, roll);
    }

    protected void entityInit() {
        super.entityInit();
        getDataWatcher().addObject(8, Byte.valueOf((byte)0));
    }

    public boolean getBurst() {
        return (getDataWatcher().getWatchableObjectByte(8) != 0);
    }

    public void setBurst(boolean value) {
        getDataWatcher().updateObject(8, Byte.valueOf((byte) (value ? 1 : 0)));
    }

    protected void attackEntity(Entity target) {
        if (getBurst())
            this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, false);
        if (!this.worldObj.isRemote) {
            float magicDamage = Math.max(1.0F, this.AttackLevel);
            target.hurtResistantTime = 0;
            DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage();
            target.attackEntityFrom(ds, magicDamage);
            if (this.blade != null && target instanceof EntityLivingBase && this.thrower != null && this.thrower instanceof EntityLivingBase) {
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.PhantomSword);
                ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)target, (EntityLivingBase)this.thrower);
                if (!target.isEntityAlive())
                    ((EntityLivingBase)this.thrower).heal(1.0F);
                target.motionX = 0.0D;
                target.motionY = 0.0D;
                target.motionZ = 0.0D;
                target.addVelocity(0.0D, 0.1D, 0.0D);
                ((EntityLivingBase)target).hurtTime = 1;
                if (!getBurst())
                    ((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1));
                ((ItemSlashBlade)this.blade.getItem()).setDaunting((EntityLivingBase)target);
            }
        }
        setDead();
    }
}
