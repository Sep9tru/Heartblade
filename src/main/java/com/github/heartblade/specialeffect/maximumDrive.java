package com.github.heartblade.specialeffect;


import com.github.heartblade.Entityadd.EntityDriveAdd;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class maximumDrive implements ISpecialEffect {
    static final String EffectKey = "maximumDrive";
    protected int BurstLevel = 45;

    public maximumDrive() {
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
        if (SpecialEffects.isPlayer(event.entity)) {
            EntityPlayer player = (EntityPlayer)event.entity;
            ItemStack blade = event.blade;
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(blade);
            if (!ItemSlashBlade.IsBroken.get(tag)) {
                switch(SpecialEffects.isEffective(player, event.blade, this).ordinal()) {
                    case 0:
                        return;
                    case 1:
                        return;
                    case 2:
                        double d0 = player.getRNG().nextGaussian() * 0.02D;
                        double d1 = player.getRNG().nextGaussian() * 0.02D;
                        double d2 = player.getRNG().nextGaussian() * 0.02D;
                        double d3 = 10.0D;
                        event.world.spawnParticle("witchMagic", player.posX + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d0 * d3, player.posY, player.posZ + (double)(player.getRNG().nextFloat() * player.width * 2.0F) - (double)player.width - d2 * d3, d0, d1, d2);
                    default:
                        ItemSlashBlade.ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
                        if (this.useBlade(seq)) {
                            PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                            int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                            if (player.swingProgressInt == check) {
                                this.doAddAttack(event.blade, player, seq);
                            }
                        }
                }
            }
        }
    }
    @SubscribeEvent
    public void onImpactEffectEvent(SlashBladeEvent.ImpactEffectEvent event) {
        if (this.useBlade(event.sequence)) {
            if (SpecialEffects.isPlayer(event.user)) {
                EntityPlayer player = (EntityPlayer)event.user;
                switch(SpecialEffects.isEffective(player, event.blade, this)) {
                    case None:
                        return;
                    case Effective:
                        if (event.target.getRNG().nextInt(2) != 0) {
                            return;
                        }
                        break;
                    case NonEffective:
                        if (event.target.getRNG().nextInt(5) != 0) {
                            return;
                        }
                }
                event.target.attackEntityFrom(DamageSource.onFire, 2.0F);
                event.target.attackEntityFrom(DamageSource.inFire, 6.0F);
                player.onEnchantmentCritical(event.target);
            }
        }
    }

    public void doAddAttack(ItemStack stack, EntityPlayer player, ItemSlashBlade.ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.worldObj;
        boolean cost = true;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, +0, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 3);
        } else {
            if (!world.isRemote) {
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 200, 0));
                float baseModif = ((ItemSlashBlade)stack.getItem()).getBaseAttackModifiers(tag);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = baseModif + (float)level;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float)level / 5.0F);
                }
                EntityDriveAdd entityDrive = new EntityDriveAdd(world, player, magicDamage, false, 90.0F - setCombo.swingDirection);
                if (entityDrive != null) {
                    entityDrive.setColor(16720896);
                    entityDrive.setInitialSpeed(1.5F);
                    entityDrive.setLifeTime(99);
                    entityDrive.setParticle("flame");
                    world.spawnEntityInWorld(entityDrive);
                }
            }
            world.playSoundAtEntity(player, "mob.ghast.fireball", 1.0F, 1.0F);
        }
    }
    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return this.BurstLevel;
    }

    public String getEffectKey() {
        return "maximumDrive";
    }
}
