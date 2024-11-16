package com.github.heartblade.Entityadd;

import com.github.heartblade.common.CommonProxy;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.EntityDrive;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntitySlashDimension;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class EntityCutEndManager extends Entity implements IThrowableEntity {
    protected Entity thrower;

    protected ItemStack blade = null;

    protected List<Entity> alreadyHitEntity = new ArrayList<Entity>();

    public EntityCutEndManager(World par1World) {
        super(par1World);
        this.ticksExisted = 0;
    }

    protected void entityInit() {
        getDataWatcher().addObject(4, Integer.valueOf(0));
    }

    int getThrowerEntityID() {
        return getDataWatcher().getWatchableObjectInt(4);
    }

    void setThrowerEntityID(int id) {
        getDataWatcher().updateObject(4, Integer.valueOf(id));
    }

    public EntityCutEndManager(World par1World, EntityLivingBase entityLiving) {
        this(par1World);
        this.yOffset = entityLiving.getEyeHeight() / 2.0F;
        this.thrower = (Entity)entityLiving;
        setThrowerEntityID(this.thrower.getEntityId());
        this.blade = entityLiving.getHeldItem();
        if (this.blade != null && !(this.blade.getItem() instanceof ItemSlashBlade))
            this.blade = null;
        this.alreadyHitEntity.clear();
        this.alreadyHitEntity.add(this.thrower);
        this.alreadyHitEntity.add(this.thrower.ridingEntity);
        this.alreadyHitEntity.add(this.thrower.riddenByEntity);
        this.ticksExisted = 0;
        setSize(64.0F, 32.0F);
        setLocationAndAngles(this.thrower.posX, this.thrower.posY, this.thrower.posZ, this.thrower.rotationYaw, this.thrower.rotationPitch);
    }

    public void onUpdate() {
        if (this.thrower == null && getThrowerEntityID() != 0)
            this.thrower = this.worldObj.getEntityByID(getThrowerEntityID());
        if (this.blade == null && getThrower() != null && getThrower() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)getThrower();
            ItemStack stack = player.getHeldItem();
            if (stack.getItem() instanceof ItemSlashBlade)
                this.blade = stack;
        }
        if (this.thrower != null) {
            this.thrower.motionX = 0.0D;
            this.thrower.motionY = 0.0D;
            this.thrower.motionZ = 0.0D;
            if (getThrower() != null && getThrower() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) getThrower();
                if (this.ticksExisted < 3)
                    player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
                if (this.ticksExisted < 8) {
                    for (int i = 0; i < 20; i++) {
                        double d0 = player.getRNG().nextGaussian() * 0.2D;
                        double d1 = player.getRNG().nextGaussian() * 0.2D;
                        double d2 = player.getRNG().nextGaussian() * 0.2D;
                        double d3 = 16.0D;
                        this.worldObj.spawnParticle("witchMagic", player.posX + (player
                                .getRNG().nextFloat() * player.width * 2.0F) - player.width - d0 * d3, player.posY, player.posZ + (player

                                .getRNG().nextFloat() * player.width * 2.0F) - player.width - d2 * d3, d0, d1, d2);
                    }
//                    player.worldObj.playSoundAtEntity((Entity)player, "mob.blaze.hit", 1.0F, 1.0F);
                }
                if (this.ticksExisted == 5)
                    if (this.blade != null) {
                        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
                        ItemSlashBlade bladeItem = (ItemSlashBlade) this.blade.getItem();
                        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
                        bladeItem.doSwingItem(this.blade, (EntityPlayer) getThrower());
                        doAttack(ItemSlashBlade.ComboSequence.SlashEdge);
                    }
                if (this.ticksExisted == 10)
                    if (this.blade != null) {
                        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
                        ItemSlashBlade bladeItem = (ItemSlashBlade) this.blade.getItem();
                        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.RapidSlash);
                        bladeItem.doSwingItem(this.blade, (EntityPlayer) getThrower());
                        doAttack(ItemSlashBlade.ComboSequence.RapidSlash);
                    }
                if (this.ticksExisted == 10)
                    if (this.blade != null) {
                        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
                        ItemSlashBlade bladeItem = (ItemSlashBlade) this.blade.getItem();
                        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.ReturnEdge);
                        if (getThrower() != null && getThrower() instanceof EntityPlayer) {
                            bladeItem.doSwingItem(this.blade, (EntityPlayer) getThrower());
                            player.playSound("flammpfeil.slashblade:swingblade", 1.0F, 0.5F + player.getRNG().nextFloat() * 0.05F);
                        }
//                    doAttack(ItemSlashBlade.ComboSequence.ReturnEdge);
                    }
                if (this.ticksExisted == 25)
                    if (this.blade != null) {
                        player.worldObj.playSoundAtEntity((Entity) player, "random.explode", 80.0F, 2.0F);
                    }
                if (this.ticksExisted == 30)
                    if (this.blade != null) {
                        int g;
                        for (g = 0; g <= 8; ++g) {
                            ItemStack bbb = player.inventory.getStackInSlot(g);
                            if (bbb != null && (bbb.getItem() == CommonProxy.YamatoHyper)) {
                                bbb.setItemDamage(bbb.getMaxDamage() / 2);
                            }
                        }
                    }
            }
    }
        if (!this.worldObj.isRemote) {
            AxisAlignedBB bb = this.boundingBox.copy().offset(0.0D, (-this.height / 2.0F), 0.0D);
            if (this.ticksExisted == 2 && getThrower() != null) {
                List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.AttackableSelector);
                list.removeAll(this.alreadyHitEntity);
                if (this.blade != null) {
                    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
                    for (Entity curEntity : list) {
                        if (curEntity instanceof EntityLivingBase) {
                            int stanTicks = 40;
                            if (!curEntity.worldObj.isRemote) {
                                ((EntityLivingBase)curEntity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), stanTicks, 30, true));
                                ((EntityLivingBase)curEntity).attackTime = stanTicks;
                            }
                            StunManager.setStun((EntityLivingBase)curEntity, stanTicks);
                            StunManager.setFreeze((EntityLivingBase)curEntity, stanTicks);
                            for (int i = 0; i < 5; i++)
                                this.worldObj.spawnParticle("portal", curEntity.posX + (this.rand
                                        .nextDouble() - 0.5D) * curEntity.width, curEntity.posY + this.rand
                                        .nextDouble() * curEntity.height - 0.25D, curEntity.posZ + (this.rand
                                        .nextDouble() - 0.5D) * curEntity.width, (this.rand
                                        .nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
                        }
                    }
                }
            }
            if (this.ticksExisted == 25 && getThrower() != null) {
                List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.AttackableSelector);
                list.removeAll(this.alreadyHitEntity);
                StylishRankManager.setNextAttackType(getThrower(), StylishRankManager.AttackTypes.JudgmentCut);
                if (this.blade != null) {
                    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
                    ItemSlashBlade bladeItem = (ItemSlashBlade)this.blade.getItem();
                    int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.blade);
                    float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * level / 5.0F;
                    for (Entity curEntity : list) {
                        if (!(getThrower() instanceof EntityPlayer))
                            continue;
                        bladeItem.attackTargetEntity(this.blade, curEntity, (EntityPlayer)getThrower(), Boolean.valueOf(true));
                        ((EntityPlayer)getThrower()).onEnchantmentCritical(curEntity);
                        curEntity.hurtResistantTime = 0;
                        int i;
                        for (i = 0; i < 5; i++) {
                            EntityDrive entityDrive = new EntityDrive(this.worldObj, (EntityLivingBase)getThrower(), Math.min(1.0F, magicDamage), true, 0.0F);
                            float rotationYaw = curEntity.rotationYaw + (60 * i) + (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                            float rotationPitch = (entityDrive.getRand().nextFloat() - 0.5F) * 60.0F;
                            float fYawDtoR = rotationYaw / 180.0F * 3.1415927F;
                            float fPitDtoR = rotationPitch / 180.0F * 3.1415927F;
                            float fYVecOfst = 0.5F;
                            float motionX = -MathHelper.sin(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                            float motionY = -MathHelper.sin(fPitDtoR) * fYVecOfst;
                            float motionZ = MathHelper.cos(fYawDtoR) * MathHelper.cos(fPitDtoR) * fYVecOfst * 2.0F;
                            entityDrive.setLocationAndAngles(curEntity.posX - motionX, curEntity.posY + curEntity
                                    .getEyeHeight() / 2.0D - motionY, curEntity.posZ - motionZ, rotationYaw, rotationPitch);
                            entityDrive.setDriveVector(fYVecOfst);
                            entityDrive.setLifeTime(8);
                            entityDrive.setIsMultiHit(false);
                            int rank = StylishRankManager.getStylishRank(getThrower());
                            if (5 <= rank) {
                                EnumSet<ItemSlashBlade.SwordType> type = bladeItem.getSwordType(this.blade);
                                entityDrive.setIsSlashDimension(type.contains(ItemSlashBlade.SwordType.FiercerEdge));
                            }
                            entityDrive.setRoll(90.0F + 120.0F * (entityDrive.getRand().nextFloat() - 0.5F));
                            if (entityDrive != null)
                                this.worldObj.spawnEntityInWorld((Entity)entityDrive);
                        }
                        if (!curEntity.worldObj.isRemote)
                            for (i = 0; i < 2; i++) {
                                EntitySlashDimension dim = new EntitySlashDimension(curEntity.worldObj, (EntityLivingBase)getThrower(), 1.0F);
                                if (dim != null) {
                                    dim.setPosition(curEntity.posX + (this.rand.nextFloat() - 0.5D) * 5.0D, curEntity.posY + (curEntity.height * this.rand.nextFloat()), curEntity.posZ + (this.rand.nextFloat() - 0.5D) * 5.0D);
                                    dim.setLifeTime(10 + i * 3);
                                    dim.setIsSlashDimension(true);
                                    curEntity.worldObj.spawnEntityInWorld(dim);
                                }
                            }
                    }
                }
            }
        }
        if (this.ticksExisted >= 30) {
            if (this.blade != null) {
                ItemSlashBlade bladeItem = (ItemSlashBlade)this.blade.getItem();
                if (getThrower() != null && getThrower() instanceof EntityPlayer)
                    bladeItem.doSwingItem(this.blade, (EntityPlayer)getThrower());
                this.blade.setItemDamage(this.blade.getMaxDamage() / 2);
                }
            this.alreadyHitEntity.clear();
            this.alreadyHitEntity = null;
            setDead();
        }
    }
    public void doAttack(ItemSlashBlade.ComboSequence combo) {
        if (this.blade == null)
            return;
        if (!(this.blade.getItem() instanceof ItemSlashBlade))
            return;
        ItemSlashBlade itemBlade = (ItemSlashBlade)this.blade.getItem();
        if (this.worldObj.isRemote)
            return;
        if (!(getThrower() instanceof EntityLivingBase))
            return;
        EntityLivingBase entityLiving = (EntityLivingBase)getThrower();
        double dAmbit = 0.5D;
        AxisAlignedBB bb = itemBlade.getBBofCombo(this.blade, combo, entityLiving);
        bb = bb.expand(0.0D, dAmbit, 0.0D);
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
                    isDestruction = !curEntity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 1.0F);
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
                this.worldObj.spawnParticle("explode", curEntity.posX + (rand.nextFloat() * curEntity.width * 2.0F) - curEntity.width - var2 * var8, curEntity.posY + (rand.nextFloat() * curEntity.height) - var4 * var8, curEntity.posZ + (rand.nextFloat() * curEntity.width * 2.0F) - curEntity.width - var6 * var8, var2, var4, var6);
            }
            StylishRankManager.doAttack(this.thrower);
        }
        list = this.worldObj.getEntitiesWithinAABBExcludingEntity(getThrower(), bb, ItemSlashBlade.AttackableSelector);
        list.removeAll(this.alreadyHitEntity);
        StylishRankManager.setNextAttackType(this.thrower, StylishRankManager.AttackTypes.Spear);
        if (this.blade != null) {
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
            for (Entity curEntity : list) {
                curEntity.hurtResistantTime = 0;
                if (this.thrower instanceof EntityPlayer) {
                    itemBlade.attackTargetEntity(this.blade, curEntity, (EntityPlayer)this.thrower, Boolean.valueOf(true));
                    continue;
                }
                EntityDamageSource entityDamageSource = new EntityDamageSource("mob", getThrower());
                curEntity.attackEntityFrom((DamageSource)entityDamageSource, 10.0F);
                if (this.blade != null && curEntity instanceof EntityLivingBase)
                    ((ItemSlashBlade)this.blade.getItem()).hitEntity(this.blade, (EntityLivingBase)curEntity, (EntityLivingBase)this.thrower);
            }
        }
        float magicDamage = 0.5F;
        if (getThrower() != null) {
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(this.blade);
            float baseModif = itemBlade.getBaseAttackModifiers(tag);
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.blade);
            int rank = StylishRankManager.getStylishRank(getThrower());
            magicDamage = baseModif;
            if (5 <= rank)
                magicDamage += ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (0.5F + level / 5.0F);
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
}
