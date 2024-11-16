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
import net.minecraft.world.World;

public class Theholylightshines implements ISpecialEffect {
    static final String EffectKey = "Theholylightshines";
    protected int Level = 7;
    public Theholylightshines() {
    }

    public void setBurstLevel(int level) {
        this.Level = level;
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
                        return; case 1:
                        return; case 2:
                        double d0 = player.getRNG().nextGaussian() * 0.02D;
                        double d1 = player.getRNG().nextGaussian() * 0.02D;
                        double d2 = player.getRNG().nextGaussian() * 0.02D;
                        double d3 = 10.0D;
                        player.worldObj.spawnParticle("witchMagic", player.posX + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d0 * d3) * 2.0D, player.posY, player.posZ + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d2 * d3) * 2.0D, d0, d1, d2);
                    default:
                        ItemSlashBlade.ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
                        if (this.useBlade(seq)) {
                            PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                            int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                            if (player.swingProgressInt == check) { this.doAddAttack(event.blade, player, seq); }
                        }
                }
            }
        }
    }

    public void doAddAttack(ItemStack stack, EntityPlayer player, ItemSlashBlade.ComboSequence setCombo) {
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        World world = player.worldObj;
        boolean cost = true;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, +0, false)) {
            stack.setItemDamage(stack.getMaxDamage() + 0);
        } else {
            if (!world.isRemote) {
                player.addPotionEffect(new PotionEffect(16, 160, 0));
                player.addPotionEffect(new PotionEffect(5, 20, 0));
                player.addPotionEffect(new PotionEffect(11, 20, 0));
                player.addPotionEffect(new PotionEffect(12, 20, 0));
                float baseModif = ((ItemSlashBlade)stack.getItem()).getBaseAttackModifiers(tag);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                float magicDamage = baseModif + (float)level;
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                    magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5F + (float)level / 5.0F);
                }
                EntityDriveAdd entityDrive = new EntityDriveAdd(world, player, magicDamage, false, 90.0F - setCombo.swingDirection);
                if (entityDrive != null) {
                    entityDrive.setColor(0);
                    entityDrive.setInitialSpeed(2.5F);
                    entityDrive.setLifeTime(30);
                    entityDrive.setParticle("fireworksSpark");
                    world.spawnEntityInWorld(entityDrive);
                }
            }
        }
    }

    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return this.Level;
    }

    public String getEffectKey() {
        return "Theholylightshines";
    }
}

