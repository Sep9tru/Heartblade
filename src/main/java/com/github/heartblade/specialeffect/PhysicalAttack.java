package com.github.heartblade.specialeffect;

import com.github.heartblade.Entityadd.EntityPhantomBullet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
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

import java.util.List;

public class PhysicalAttack implements ISpecialEffect {
    static final String EffectKey = "PhysicalAttack";
    protected int BurstLevel = 20;

    public PhysicalAttack() {
    }

    public void setBurstLevel(int level) {
        this.BurstLevel = level;
    }

    private boolean useBlade(ItemSlashBlade.ComboSequence sequence) {
        if (sequence.useScabbard) {
            return false;
        } else if (sequence == ItemSlashBlade.ComboSequence.None) {
            return false;
        } else {
            return sequence != ItemSlashBlade.ComboSequence.Noutou;
        }
    }

    @SubscribeEvent
    public void onUpdateItemSlashBlade(SlashBladeEvent.OnUpdateEvent event) {
        double d0, d1, d2, d3;
        if (!SpecialEffects.isPlayer(event.entity))
            return;
        EntityPlayer player = (EntityPlayer)event.entity;
        ItemStack blade = event.blade;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(blade);
        if (ItemSlashBlade.IsBroken.get(tag).booleanValue())
            return;
        switch (SpecialEffects.isEffective(player, event.blade, this).ordinal()) {
            case 0:
                return;
            case 1:
                return;
            case 2:
                d0 = player.getRNG().nextGaussian() * 0.02D;
                d1 = player.getRNG().nextGaussian() * 0.02D;
                d2 = player.getRNG().nextGaussian() * 0.02D;
                d3 = 10.0D;
                event.world.spawnParticle("witchMagic", player.posX + (player.getRNG().nextFloat() * player.width * 2.0F) - player.width - d0 * d3, player.posY, player.posZ + (player.getRNG().nextFloat() * player.width * 2.0F) - player.width - d2 * d3, d0, d1, d2);
                break;
        }
        ItemSlashBlade.ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
        if (!useBlade(seq))
            return;
        PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
        int check = (haste != null) ? ((haste.getAmplifier() != 1) ? 3 : 4) : 2;
        if (player.swingProgressInt != check)
            return;
        doSpacialAttack(event.blade, player, seq);
    }
    public void doSpacialAttack(ItemStack stack, EntityPlayer player, ItemSlashBlade.ComboSequence seq) {
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        world.playSoundAtEntity(player, "random.explode", 1.0F, 2.0F);
        if (!world.isRemote) {
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
            Entity target = null;
            int entityId = ItemSlashBlade.TargetEntityId.get(tag).intValue();
            if (entityId != 0) {
                Entity tmp = world.getEntityByID(entityId);
                if (tmp != null &&
                        tmp.getDistanceToEntity((Entity)player) < 77.0F)
                    target = tmp;
            }
            if (target == null)
                target = getEntityToWatch(player);
            if (target != null) {
                int cost = -40;
                if (!ItemSlashBlade.ProudSoul.tryAdd(tag, Integer.valueOf(-30), false))
                    ItemSlashBlade.damageItem(stack, 10, (EntityLivingBase)player);
                StylishRankManager.setNextAttackType((Entity)player, StylishRankManager.AttackTypes.PhantomSword);
                blade.attackTargetEntity(stack, target, player, Boolean.valueOf(true));
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
                float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * level / 5.0F;
                int count = 3;
                for (int i = 0; i < count; i++) {
                    if (!world.isRemote) {
                        boolean isBurst = (i % 2 == 0);
                        EntityPhantomBullet entityDrive = new EntityPhantomBullet(world, (EntityLivingBase)player, magicDamage, 0.0F);
                        if (entityDrive != null) {
                            entityDrive.setLifeTime(77);
                            entityDrive.setDriveVector((float)(1.0E-4D  * i + 1.0E-4D));
                            entityDrive.setInterval(5);
                            entityDrive.setTargetEntityId(target.getEntityId());
                            world.spawnEntityInWorld((Entity)entityDrive);
                        }
                    }
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
    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return this.BurstLevel;
    }

    public String getEffectKey() {
        return "PhysicalAttack";
    }
}
