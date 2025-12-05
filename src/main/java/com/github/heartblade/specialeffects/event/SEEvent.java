package com.github.heartblade.specialeffects.event;

import com.github.heartblade.entity.EntityDriveEx;
import com.github.heartblade.entity.EntityFlake;
import com.github.heartblade.entity.EntityPhantomSwordEx;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade.ComboSequence;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;

import com.github.heartblade.init.HbSEs;
import net.minecraft.enchantment.EnchantmentHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SEEvent {

    private static final double RATE_1 = 0.95;
    private static final double RATE_2 = 0.05;

    public SEEvent(){
        SlashBladeHooks.EventBus.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /** se:永恒 */
    @SubscribeEvent
    public void eternalUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;
        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Eternal)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                break;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,20 * 1,0));
    }

    /** se:不灭 */
    @SubscribeEvent
    public void neverUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Never)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                break;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,20 * 1,0));
    }

    /** se:续航塔 */
    @SubscribeEvent
    public void nobleUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.charges)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.HASTE,200 * 1,0));
        player.addPotionEffect(new PotionEffect(MobEffects.SATURATION,20 * 1,0));
        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING,200 * 1,0));
    }
    @SubscribeEvent
    public void nobleEffectEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.charges)){
            case None:
                return;
            case Effective:
                break;
            case NonEffective:
                return;
        }
        World world = player.world;
        target.addPotionEffect(new PotionEffect(MobEffects.HASTE,6000*1,0));
        target.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING,6000*1,0));
        target.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,60*1,7));
    }

    /** se:蹂躏 */
    @SubscribeEvent
    public void toRavageUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.ToRavage)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY,200 * 1,0));
    }
    @SubscribeEvent
    public void toRavageEffectEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.ToRavage)){
            case None:
                return;
            case Effective:
                break;
            case NonEffective:
                return;
        }
        World world = player.world;
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,20*10,4));
    }

    /** se: 喷水*/
    @SubscribeEvent
    public void gebilaowangUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Breakeverything)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        if (player.isInWater()){
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,20 * 1,1));
            if (player.isPotionApplicable(new PotionEffect(MobEffects.MINING_FATIGUE))){
                player.removePotionEffect(Potion.getPotionById(4));
            }
        }else if(player.world.isRaining()) {
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,20 * 1,0));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,60 * 1,0));
        }else{
            if (player.isBurning()) {
                player.extinguish();
            }
        }
    }
    @SubscribeEvent
    public void gebilaowangEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.Breakeverything)){
            case None:
                return;
            case Effective:
                break;
            case NonEffective:
                return;
        }
        World world = player.world;
        if (target.isInWater()) {
            target.attackEntityFrom(DamageSource.DROWN, 12);
            target.playSound(SoundEvents.ENTITY_PLAYER_SPLASH,1.0f,1.0f);
            ((WorldServer)target.world).spawnParticle(EnumParticleTypes.WATER_BUBBLE,
                    false,
                    target.posX,
                    target.posY + target.height/2,
                    target.posZ,
                    20, 0, 0, 0, 0.1);
        }else{
            target.attackEntityFrom(DamageSource.DROWN, 6);
            ((WorldServer)target.world).spawnParticle(EnumParticleTypes.WATER_SPLASH,
                    false,
                    target.posX,
                    target.posY + target.height/2,
                    target.posZ,
                    20, 0, 0, 0, 0.1);
        }
        if (target.isBurning()) {
            target.extinguish();
        }
    }

    /** se: 实力*/
    @SubscribeEvent
    public void zhemoqiangUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.strength)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED,100 * 1,4));
        player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,100 * 1,2));
        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,200 * 1,0));
    }

    /** se: 圣光闪耀*/
    @SubscribeEvent
    public void holylightsUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Theholylightshines)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,200,0));
        if (player.isPotionActive(Potion.getPotionById(19))){
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,400 * 1,0));
            player.removePotionEffect(Potion.getPotionById(19));
        }
        if (player.isPotionActive(Potion.getPotionById(15))){
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,400 * 1,0));
            player.removePotionEffect(Potion.getPotionById(15));
        }
        if (player.isPotionActive(Potion.getPotionById(9))){
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,400 * 1,0));
            player.removePotionEffect(Potion.getPotionById(9));
        }
        if (SpecialEffects.isPlayer(event.entity)) {
            if (!ItemSlashBlade.IsBroken.get(tag)) {
                switch(SpecialEffects.isEffective(player, event.blade, HbSEs.Theholylightshines)) {
                    case None:
                        return;
                    case NonEffective:
                        return;
                    case Effective:
                        double d0 = player.getRNG().nextGaussian() * 0.02D;
                        double d1 = player.getRNG().nextGaussian() * 0.02D;
                        double d2 = player.getRNG().nextGaussian() * 0.02D;
                        double d3 = 10.0D;
                        event.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, player.posX + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d0 * d3, player.posY, player.posZ + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d2 * d3, d0, d1, d2);
                    default:
                        ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
                        if (this.useBlade(seq)) {
                            PotionEffect haste = player.getActivePotionEffect(MobEffects.SPEED);
                            int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                            if (player.swingProgressInt == check) {
                                this.doLightlingAttack(event.blade, player, seq);
                            }
                        }
                }
            }
        }
    }
    public void doLightlingAttack(ItemStack stack, EntityPlayer player, ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.world;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -0, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 0);
        } else {
            if (!world.isRemote) {
                float baseModif = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                float magicDamage = baseModif;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.7F + (float)level / 7.0F);
                }
                EntityPhantomSwordEx entityDrive = new EntityPhantomSwordEx(world, player,magicDamage,90.0f);
                if (entityDrive != null) {
                    entityDrive.setInterval(7);
                    final int color = 7364008;
                    entityDrive.setColor(color);
                    entityDrive.setLifeTime(77);
                    entityDrive.setRoll(90.0F - setCombo.swingDirection);
                    entityDrive.setDriveVector(3.0F);
                    world.spawnEntity(entityDrive);
                }
            }
        }
    }
    @SubscribeEvent
    public void lightsEvent(SlashBladeEvent.ImpactEffectEvent event){
        double random = Math.random();
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.Theholylightshines)){
            case None:
                return;
            case NonEffective:
                return;
            case Effective:
                break;
        }
        World world = player.world;
        if (random >= RATE_1) {
            world.addWeatherEffect(new EntityLightningBolt(world,target.posX,target.posY,target.posZ,true));
            target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 20);
        }else{
            target.playSound(SoundEvents.ENTITY_FIREWORK_BLAST,1.0f,1.0f);
            ((WorldServer)target.world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,
                    false,
                    target.posX,
                    target.posY + target.height/2,
                    target.posZ,
                    7, 0, 0, 0, 0.7);
        }
    }

    /** se: 超级自我*/
    @SubscribeEvent
    public void supermeUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Superme)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,20 * 1,0));
        player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,20 * 1,0));
    }
    /** se: 永不作弊*/
    @SubscribeEvent
    public void nevercheatUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.NeverCheat)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        if (player.getHealth() < player.getMaxHealth()) {
            player.setHealth((float) (player.getHealth() + 0.2));
        }
    }

    /** se: 极速*/
    @SubscribeEvent
    public void sprintUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.sprint)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 达到所需等级 */
            case Effective:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                break;
        }
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED,12000 * 1,1));
    }

    /** se: 极限*/
    @SubscribeEvent
    public void maximumDriveUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.maximumDrive)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        if (player.isBurning()) {
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 60 * 1, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60 * 1, 0));
        }
        if (player.isInLava()){
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,60 * 1,1));
        }
        if (SpecialEffects.isPlayer(event.entity)) {
            if (!ItemSlashBlade.IsBroken.get(tag)) {
                switch(SpecialEffects.isEffective(player, event.blade, HbSEs.maximumDrive)) {
                    case None:
                        return;
                    case NonEffective:
                        return;
                    case Effective:
                        double d0 = player.getRNG().nextGaussian() * 0.02D;
                        double d1 = player.getRNG().nextGaussian() * 0.02D;
                        double d2 = player.getRNG().nextGaussian() * 0.02D;
                        double d3 = 10.0D;
                        event.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, player.posX + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d0 * d3, player.posY, player.posZ + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d2 * d3, d0, d1, d2);
                    default:
                        ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
                        if (this.useBlade(seq)) {
                            PotionEffect haste = player.getActivePotionEffect(MobEffects.SPEED);
                            int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                            if (player.swingProgressInt == check) {
                                this.domaximumDrive(event.blade, player, seq);
                            }
                        }
                }
            }
        }
    }
    public void domaximumDrive(ItemStack stack, EntityPlayer player, ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.world;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -0, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 0);
        } else {
            if (!world.isRemote) {
                float baseModif = EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, stack);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                float magicDamage = baseModif;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float)level / 5.0F);
                }

                EntityDriveEx entityDrive = new EntityDriveEx(world, player, magicDamage);
                if (entityDrive != null) {
                    entityDrive.setInitialPosition(player.posX+player.getLookVec().x,
                            player.posY+player.getLookVec().y+1,
                            player.posZ+player.getLookVec().z,
                            player.rotationYaw,
                            player.rotationPitch,
                            90.0F - setCombo.swingDirection,1.5f);
                    entityDrive.setColor(16720896);
                    entityDrive.setLifeTime(99);
                    entityDrive.setParticle(EnumParticleTypes.FLAME);
                    world.spawnEntity(entityDrive);
                    entityDrive.playSound(SoundEvents.ENTITY_GHAST_SHOOT,1.0f,1.0f);
                }
            }
        }
    }
    @SubscribeEvent
    public void maximumDriveEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.maximumDrive)){
            case None:
                return;
            case Effective:
                break;
            case NonEffective:
                return;
        }
        World world = player.world;
        target.attackEntityFrom(DamageSource.ON_FIRE,8);
    }

    /** se: 极寒*/
    @SubscribeEvent
    public void extremelycoldEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.Extremelycold)){
            case None:
                return;
            case NonEffective:
                return;
            case Effective:
                break;
        }
        World world = player.world;
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,180*1,1));
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,180*1,0));
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER,180*1,0));
        target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,180*1,0));
        ((WorldServer)target.world).spawnParticle(EnumParticleTypes.SNOW_SHOVEL,
                false,
                target.posX,
                target.posY + target.height/2,
                target.posZ,
                20, 0, 0, 0, 0.1);
        if (target.isBurning()) {
            target.extinguish();
            target.attackEntityFrom(DamageSource.WITHER,3);
        }
    }
    @SubscribeEvent
    public void coldUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.Extremelycold)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        if (SpecialEffects.isPlayer(event.entity)) {
            if (!ItemSlashBlade.IsBroken.get(tag)) {
                switch(SpecialEffects.isEffective(player, event.blade, HbSEs.Extremelycold)) {
                    case None:
                        return;
                    case NonEffective:
                        return;
                    case Effective:
                        double d0 = player.getRNG().nextGaussian() * 0.02D;
                        double d1 = player.getRNG().nextGaussian() * 0.02D;
                        double d2 = player.getRNG().nextGaussian() * 0.02D;
                        double d3 = 10.0D;
                        event.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, player.posX + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d0 * d3, player.posY, player.posZ + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d2 * d3, d0, d1, d2);
                    default:
                        ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
                        if (this.useBlade(seq)) {
                            PotionEffect haste = player.getActivePotionEffect(MobEffects.SPEED);
                            int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                            if (player.swingProgressInt == check) {
                                this.doFlakeAttack(event.blade, player, seq);
                            }
                        }
                }
            }
        }
    }
    public void doFlakeAttack(ItemStack stack, EntityPlayer player, ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.world;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -0, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 0);
        } else {
            if (!world.isRemote) {
                float baseModif = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                float magicDamage = baseModif;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float)level / 3.0F);
                }
                EntityFlake entityDrive = new EntityFlake(world, player,magicDamage,90.0f);
                if (entityDrive != null) {
                    entityDrive.setColor(-11397866);
                    entityDrive.setLifeTime(76);
                    entityDrive.setRoll(90.0F - setCombo.swingDirection);
                    entityDrive.setDriveVector(1.3F);
                    world.spawnEntity(entityDrive);
                }
            }
        }
    }

    /** se: 神之刄*/
    @SubscribeEvent
    public void godsbladeEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;
        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.GodsBlade)){
            case None:
                return;
            case NonEffective:
                return;
            case Effective:
                break;
        }
        World world = player.world;
        target.attackEntityFrom(DamageSource.DRAGON_BREATH,2);
    }

    /** se: SLAYALL*/
    @SubscribeEvent
    public void powerUpdate(SlashBladeEvent.OnUpdateEvent event) {
        if(!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if(!useBlade(ItemSlashBlade.getComboSequence(tag))) return;

        switch (SpecialEffects.isEffective(player,event.blade, HbSEs.VergilShadow)){
            /** 任何时候可触发 */
            case None:
                return;
            /** 未达到所需等级 */
            case NonEffective:
                return;
            /** 达到所需等级 */
            case Effective:
                break;
        }
        if(ItemSlashBlade.ProudSoul.get(tag) < 10000) {
            ItemSlashBlade.ProudSoul.tryAdd(tag, +1, false);
        }
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED,20 * 1,0));
        player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,40 * 1,1));
    }
    @SubscribeEvent
    public void powerEvent(SlashBladeEvent.ImpactEffectEvent event){
        if(!SpecialEffects.isPlayer(event.user)) return;
        EntityPlayer player = (EntityPlayer) event.user;
        EntityLivingBase target = event.target;

        switch (SpecialEffects.isEffective(player, event.blade, HbSEs.VergilShadow)){
            case None:
                return;
            case Effective:
                break;
            case NonEffective:
                return;
        }
        World world = player.world;
        target.attackEntityFrom(DamageSource.MAGIC,5);
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,2));
    }

    private boolean useBlade(ComboSequence sequence){
        if(sequence.useScabbard) return false;
        if(sequence == ItemSlashBlade.ComboSequence.None) return false;
        if(sequence == ItemSlashBlade.ComboSequence.Noutou) return false;
        return true;
    }
}
