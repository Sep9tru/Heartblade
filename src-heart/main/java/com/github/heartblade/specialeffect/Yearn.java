package com.github.heartblade.specialeffect;

import com.github.heartblade.common.CommonProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class Yearn implements ISpecialEffect {
    static final String EffectKey = "Yearn";
    protected int Level = 6;
    public Yearn() {
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
        double d0;
        double d1;
        double d2;
        double d3;
        for(int i = 0; i < 50; ++i) {
            d0 = player.getRNG().nextGaussian() * 0.02D;
            d1 = player.getRNG().nextGaussian() * 0.02D;
            d2 = player.getRNG().nextGaussian() * 0.02D;
            d3 = 10.0D;
            player.worldObj.spawnParticle("witchMagic", player.posX + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d0 * d3) * 2.0D, player.posY, player.posZ + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d2 * d3) * 2.0D, d0, d1, d2);
        }

        World world = player.worldObj;
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(17, 200, 2));
            player.addPotionEffect(new PotionEffect(9, 40, 0));
            int i;
            for (i = 0; i <= 8; ++i) {
                ItemStack aaa = player.inventory.getStackInSlot(i);
                if (aaa != null && (aaa.getItem() == CommonProxy.justiceGun)) {
                    player.attackEntityFrom((new DamageSource("Youknowtherules")).setDifficultyScaled().setMagicDamage(), 999999.0F);
                }
            }
            int g;
            for (g = 0; g <= 8; ++g) {
                FoodStats ggg = player.getFoodStats();
                if (ggg != null && (ggg.getFoodLevel()>18)) {
                    player.addPotionEffect(new PotionEffect(22, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()>16)) {
                    player.addPotionEffect(new PotionEffect(5, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()>14)) {
                    player.addPotionEffect(new PotionEffect(21, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()>12)) {
                    player.addPotionEffect(new PotionEffect(11, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()>10)) {
                    player.addPotionEffect(new PotionEffect(10, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()>8)) {
                    player.addPotionEffect(new PotionEffect(1, 20, 0));
                }
                if (ggg != null && (ggg.getFoodLevel()<6)) {
                    player.addPotionEffect(new PotionEffect(2, 20, 0));
                    player.addPotionEffect(new PotionEffect(18, 20, 8));
                }
                player.addExhaustion(0.2F);
            }
        }

        d0 = player.getRNG().nextGaussian() * 0.02D;
        d1 = player.getRNG().nextGaussian() * 0.02D;
        d2 = player.getRNG().nextGaussian() * 0.02D;
        d3 = 10.0D;
        player.worldObj.spawnParticle("witchMagic", player.posX + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d0 * d3) * 2.0D, player.posY, player.posZ + ((double)(player.getRNG().nextFloat() * player.width * 2.0F - player.width) - d2 * d3) * 2.0D, d0, d1, d2);
    }
    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return this.Level;
    }

    public String getEffectKey() {
        return "Yearn";
    }
}

