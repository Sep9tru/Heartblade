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
import net.minecraft.world.World;

public class powerLocked implements ISpecialEffect {
    static final String EffectKey = "powerLocked";
    protected int Level = 1;
    public powerLocked() {
    }

    public void setBurstLevel(int level) {
        this.Level = level;
    }
    private boolean useBlade(ItemSlashBlade.ComboSequence sequence) {
        if (sequence.useScabbard)
            return false;
        if (sequence == ItemSlashBlade.ComboSequence.None)
            return false;
        if (sequence == ItemSlashBlade.ComboSequence.Noutou)
            return false;
        return true;
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
        doAddAttack(event.blade, player, seq);
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
                int i;
                for (i = 0; i <= 8; ++i) {
                    ItemStack ggg = player.inventory.getStackInSlot(i);
                    if (ggg != null && (ggg.getItem() == CommonProxy.YamatoHyper)) {
                        event.target.attackEntityFrom(DamageSource.magic, 42.0F);
                        event.target.addPotionEffect(new PotionEffect(2, 20, 1));
                    }
                }
                player.onEnchantmentCritical(event.target);
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
            player.addPotionEffect(new PotionEffect(4, 20, 4));
            int i;
            for (i = 0; i <= 8; ++i) {
                ItemStack ggg = player.inventory.getStackInSlot(i);
                if (ggg != null && (ggg.getItem() == CommonProxy.YamatoHyper)) {
                    player.addPotionEffect(new PotionEffect(8, 40, 4));
                    player.addPotionEffect(new PotionEffect(1, 20, 0));
                    player.addPotionEffect(new PotionEffect(5, 20, 0));
                }
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
        return "powerLocked";
    }
}