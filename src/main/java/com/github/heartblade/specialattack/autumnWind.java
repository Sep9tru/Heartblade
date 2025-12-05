package com.github.heartblade.specialattack;

import com.github.heartblade.entity.EntityDriveEx;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.ability.UntouchableTime;
import mods.flammpfeil.slashblade.entity.EntitySpearManager;
import mods.flammpfeil.slashblade.entity.EntityWitherSword;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import mods.flammpfeil.slashblade.util.ReflectionAccessHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Furia on 14/07/07.
 */
public class autumnWind extends SpecialAttackBase {
    @Override
    public String toString() {
        return "autumnWind";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

        double playerDist = 7.5;
        float attackDist = (float)(playerDist / 3.0);

        if(!player.onGround)
            playerDist *= 0.35f;
        ReflectionAccessHelper.setVelocity(player,
                -Math.sin(Math.toRadians(player.rotationYaw)) * playerDist,
                player.motionY,
                Math.cos(Math.toRadians(player.rotationYaw)) * playerDist);

        if(!world.isRemote){

            final int cost = -30;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
            }

            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,10,0,true,false));

            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
            float magicDamage = 7.0f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0f);

            EntityDriveEx entityDrive = new EntityDriveEx(world, player, magicDamage);
            if (entityDrive != null) {
                entityDrive.setInitialPosition(player.posX+player.getLookVec().x,
                        player.posY+player.getLookVec().y+1,
                        player.posZ+player.getLookVec().z,
                        player.rotationYaw,
                        player.rotationPitch,
                        90.0F,2.5f);
                entityDrive.setColor(65280);
                entityDrive.setLifeTime(42);
                entityDrive.setParticle(EnumParticleTypes.VILLAGER_HAPPY);
                world.spawnEntity(entityDrive);
            }
        }

        UntouchableTime.setUntouchableTime(player, 10);

        player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 2.0F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.HiraTuki);
        if(!world.isRemote){

            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            Entity target = null;

            int entityId = ItemSlashBlade.TargetEntityId.get(tag);

            if(entityId != 0){
                Entity tmp = world.getEntityByID(entityId);
                if(tmp != null){
                    if(tmp.getDistance(player) < 30.0f)
                        target = tmp;
                }
            }

            if(target == null){
                target = getEntityToWatch(player);
            }

            if(target != null){
                ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);

                StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.PhantomSword);
                blade.attackTargetEntity(stack, target, player, true);
                player.onCriticalHit(target);

                ReflectionAccessHelper.setVelocity(target, 0, 0, 0);
                //target.addVelocity(0.0, 0.55D, 0.0);

                if(target instanceof EntityLivingBase){
                    blade.setDaunting((EntityLivingBase)target);
                    ((EntityLivingBase) target).hurtTime = 0;
                    ((EntityLivingBase) target).hurtResistantTime = 0;
                }

                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                float magicDamage = 7.0f + ItemSlashBlade.AttackAmplifier.get(tag) * (level / 5.0f);

                int count = 1 + StylishRankManager.getStylishRank(player);

                for(int i = 0; i < count;i++){

                    if(!world.isRemote){
                        boolean isBurst = (i % 2 == 0);

                        EntityWitherSword entityDrive = new EntityWitherSword(world, player, magicDamage,90.0f);
                        if (entityDrive != null) {
                            entityDrive.setInterval(7+i*2);
                            entityDrive.setLifeTime(99);
                            int color = isBurst ? -16744192 : -16744192;
                            entityDrive.setColor(color);
                            entityDrive.setBurst(isBurst);
                            entityDrive.setTargetEntityId(target.getEntityId());
                            world.spawnEntity(entityDrive);

                        }
                    }
                }
            }
        }
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Kiriorosi);
    }

    private Entity getEntityToWatch(EntityPlayer player){
        World world = player.world;
        Entity target = null;
        for(int dist = 2; dist < 20; dist+=2){
            AxisAlignedBB bb = player.getEntityBoundingBox();
            Vec3d vec = player.getLookVec();
            vec = vec.normalize();
            bb = bb.grow(2.0f, 0.25f, 2.0f);
            bb = bb.offset(vec.x*(float)dist,vec.y*(float)dist,vec.z*(float)dist);

            List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());
            float distance = 30.0f;
            for(Entity curEntity : list){
                float curDist = curEntity.getDistance(player);
                if(curDist < distance)
                {
                    target = curEntity;
                    distance = curDist;
                }
            }
            if(target != null)
                break;
        }
        return target;
    }
}


