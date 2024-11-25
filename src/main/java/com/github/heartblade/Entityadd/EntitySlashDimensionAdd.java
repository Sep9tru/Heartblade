 package com.github.heartblade.Entityadd;

 import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.ability.TeleportCanceller;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitySlashDimensionAdd extends Entity implements IThrowableEntity {
    protected Entity thrower;

    protected ItemStack blade = null;

    protected List<Entity> alreadyHitEntity = new ArrayList<Entity>();

    protected float AttackLevel = 0.0F;

    private static final int LIFETIME = 3;

    private static final int SINGLE_HIT = 4;

    private static final int IS_SLASH_DIMENSION = 5;

    private static final int THROWER_ENTITY_ID = 6;

    private static final int INTERVAL = 7;

    private static final int COLOR = 8;

    public EntitySlashDimensionAdd(World par1World) {
        super(par1World);
        this.ticksExisted = 0;
        getEntityData().setInteger("seed", this.rand.nextInt(50));
    }

    public EntitySlashDimensionAdd(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit) {
        this(par1World, entityLiving, AttackLevel);
        setIsSingleHit(multiHit);
    }

    public EntitySlashDimensionAdd(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        this(par1World);
        this.AttackLevel = AttackLevel;
        this.thrower = (Entity)entityLiving;
        this.blade = entityLiving.getHeldItem();
        if (this.blade != null && !(this.blade.getItem() instanceof ItemSlashBlade))
            this.blade = null;
        this.alreadyHitEntity.clear();
        this.alreadyHitEntity.add(this.thrower);
        this.alreadyHitEntity.add(this.thrower.ridingEntity);
        this.alreadyHitEntity.add(this.thrower.riddenByEntity);
        this.ticksExisted = 0;
        setSize(4.0F, 4.0F);
    }

    protected void entityInit() {
        getDataWatcher().addObject(3, Integer.valueOf(20));
        getDataWatcher().addObject(4, Byte.valueOf((byte)0));
        getDataWatcher().addObject(5, Byte.valueOf((byte)0));
        getDataWatcher().addObject(6, Integer.valueOf(0));
        getDataWatcher().addObject(7, Integer.valueOf(7));
        getDataWatcher().addObject(8, Integer.valueOf(3355647));
        getDataWatcher().addObject(24, String.valueOf("model/util/ThisIsPower/texture.png"));
        getDataWatcher().addObject(25, String.valueOf("model/util/ThisIsPower/model.obj"));
    }

    public boolean getIsSingleHit() {
        return (getDataWatcher().getWatchableObjectByte(4) != 0);
    }

    public void setIsSingleHit(boolean isSingleHit) {
        getDataWatcher().updateObject(4, Byte.valueOf((byte) (isSingleHit ? 1 : 0)));
    }

    public int getLifeTime() {
        return getDataWatcher().getWatchableObjectInt(3);
    }

    public void setLifeTime(int lifetime) {
        getDataWatcher().updateObject(3, Integer.valueOf(lifetime));
    }

    public boolean getIsSlashDimension() {
        return (getDataWatcher().getWatchableObjectByte(5) != 0);
    }

    public void setIsSlashDimension(boolean isSlashDimension) {
        getDataWatcher().updateObject(5, Byte.valueOf((byte) (isSlashDimension ? 1 : 0)));
    }

    public int getInterval() {
        return getDataWatcher().getWatchableObjectInt(7);
    }

    public void setInterval(int value) {
        getDataWatcher().updateObject(7, Integer.valueOf(value));
    }

    public int getColor() {
        return getDataWatcher().getWatchableObjectInt(8);
    }

    public void setColor(int value) {
        getDataWatcher().updateObject(8, Integer.valueOf(value));
    }

    public int getThrowerEntityId() {
        return getDataWatcher().getWatchableObjectInt(6);
    }

    public void setThrowerEntityId(int entityid) {
        getDataWatcher().updateObject(6, Integer.valueOf(entityid));
    }

    public void onUpdate() {
        super.onUpdate();
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        if (!this.worldObj.isRemote) {
            if (this.ticksExisted < 8 && this.ticksExisted % 2 == 0)
                playSound("mob.wither.hurt", 0.2F, 0.5F + 0.25F * this.rand.nextFloat());
            AxisAlignedBB bb = this.boundingBox;
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
                    } else if (curEntity instanceof EntityThrowable && (
                            (EntityThrowable)curEntity).getThrower() != null && ((EntityThrowable)curEntity).getThrower().getEntityId() == entityLiving.getEntityId()) {
                        isDestruction = false;
                    }
                    if (!isDestruction)
                        continue;
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
                        this.worldObj.spawnParticle("explode", curEntity.posX + (rand
                                .nextFloat() * curEntity.width * 2.0F) - curEntity.width - var2 * var8, curEntity.posY + (rand
                                .nextFloat() * curEntity.height) - var4 * var8, curEntity.posZ + (rand
                                .nextFloat() * curEntity.width * 2.0F) - curEntity.width - var6 * var8, var2, var4, var6);
                    }
                    StylishRankManager.doAttack(this.thrower);
                }
            }
            if (getIsSingleHit() || this.ticksExisted % 2 == 0) {
                List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.AttackableSelector);
                list.removeAll(this.alreadyHitEntity);
                if (getIsSingleHit())
                    this.alreadyHitEntity.addAll(list);
                float magicDamage = Math.max(1.0F, this.AttackLevel);
                StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.SlashDimMagic);
                for (Entity curEntity : list) {
                    if (getIsSlashDimension() &&
                            curEntity instanceof EntityLivingBase) {
                        float health = ((EntityLivingBase)curEntity).getHealth();
                        if (0.0F < health) {
                            health = Math.max(1.0F, health - magicDamage);
                            ((EntityLivingBase)curEntity).setHealth(health);
                        }
                    }
                    Vec3 pos = Vec3.createVectorHelper(curEntity.posX, curEntity.posY, curEntity.posZ);
                    TeleportCanceller.setCancel(curEntity);
                    curEntity.hurtResistantTime = 0;
                    DamageSource ds = (new EntityDamageSource("directMagic", getThrower())).setDamageBypassesArmor().setMagicDamage().setProjectile();
                    if (this.blade != null && curEntity instanceof EntityLivingBase)
                        ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)curEntity, (EntityLivingBase)this.thrower);
                    curEntity.motionX = 0.0D;
                    curEntity.motionY = 0.0D;
                    curEntity.motionZ = 0.0D;
                    if (3 < this.ticksExisted &&
                            this.blade != null && curEntity instanceof EntityLivingBase) {
                        if (getIsSlashDimension()) {
                            curEntity.addVelocity(0.0D, 0.5D, 0.0D);
                            continue;
                        }
                        int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.blade);
                        if (0 < level) {
                            curEntity.addVelocity(
                                    Math.sin(((getThrower()).rotationYaw * 3.1415927F / 180.0F)) * level * 0.5D, 0.2D,

                                    -Math.cos(((getThrower()).rotationYaw * 3.1415927F / 180.0F)) * level * 0.5D);
                            continue;
                        }
                        curEntity.addVelocity(
                                -Math.sin(((getThrower()).rotationYaw * 3.1415927F / 180.0F)) * 0.5D, 0.2D,

                                Math.cos(((getThrower()).rotationYaw * 3.1415927F / 180.0F)) * 0.5D);
                    }
                }
            }
        }
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