package com.github.heartblade.specialeffect;

import com.github.heartblade.Entityadd.EntitySummonedBlade_flake;
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
import net.minecraft.world.World;

public class Extremelycold implements ISpecialEffect {
    static final String EffectKey = "Extremelycold";
    protected int BurstLevel = 45;

    public Extremelycold() {
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
                event.target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 180, 1));
                event.target.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 180, 0));
                event.target.addPotionEffect(new PotionEffect(Potion.wither.getId(), 80, 1));
                event.target.addPotionEffect(new PotionEffect(4, 60, 0));
                event.target.addPotionEffect(new PotionEffect(17, 60, 1));
                player.onEnchantmentCritical(event.target);
            }
        }
    }
    public void doAddAttack(ItemStack stack, EntityPlayer player, ItemSlashBlade.ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.worldObj;
        boolean cost = true;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, +1, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 3);
        } else {
            if (!world.isRemote) {
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 200, 0));
                float baseModif = ((ItemSlashBlade) stack.getItem()).getBaseAttackModifiers(tag);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = baseModif + (float) level;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float) level / 5.0F);
                }
                EntitySummonedBlade_flake entityDrive = new EntitySummonedBlade_flake(world, player, magicDamage, (float) (ItemSlashBlade.RepairCount.get(tag) * 1));
                if (entityDrive != null) {
                    EntitySummonedBlade_flake.wide = 3.0F;
                    EntitySummonedBlade_flake.high = 3.0F;
                    entityDrive.setSize(6.0E-3F);
                    entityDrive.setInterval(15);
                    entityDrive.setRoll(10);
                    entityDrive.setColor(3715294);
                    entityDrive.setLifeTime(76);
                    entityDrive.setDriveVector(1.3F);
                    world.spawnEntityInWorld(entityDrive);
                }
            }
        }
    }
    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return this.BurstLevel;
    }

    public String getEffectKey() {
        return "Extremelycold";
    }
}

