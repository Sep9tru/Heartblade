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

public class strength implements ISpecialEffect {
    static final String EffectKey = "strength";
    protected int Level = 1;

    public strength() {
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
                        return;
                    case 1:
                        return;
                    case 2:
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
                            if (player.swingProgressInt == check) {
                                this.doAddAttack(event.blade, player, seq);
                            }
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
            player.addPotionEffect(new PotionEffect(1,200,4));
            player.addPotionEffect(new PotionEffect(8, 200, 2));
            player.addPotionEffect(new PotionEffect(13, 200, 0));
            int i;
            for (i = 0; i <= 8; ++i) {
                ItemStack ggg = player.inventory.getStackInSlot(i);
                if (ggg != null && (ggg.getItem() == CommonProxy.dguoblade)) {
                    player.attackEntityFrom((new DamageSource("Youknowtherules")).setDifficultyScaled().setMagicDamage(), 999999.0F);
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
        return "strength";
    }
}
