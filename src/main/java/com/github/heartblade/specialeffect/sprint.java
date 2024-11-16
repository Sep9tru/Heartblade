package com.github.heartblade.specialeffect;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class sprint implements ISpecialEffect {
    private static final String EffectKey = "sprint";

    public sprint() {
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
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
            if (this.useBlade(ItemSlashBlade.getComboSequence(tag))) {
                switch(SpecialEffects.isEffective(player, event.blade, this)) {
                    case None:
                        return;
                    case Effective:
                        return;
                    case NonEffective:
                        if (player.getRNG().nextInt(4) != 0) {
                            return;
                        }
                    default:
                        PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                        int check = haste != null ? (haste.getAmplifier() != 1 ? 3 : 4) : 2;
                        if (player.swingProgressInt == check) {
                            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(),12000, 1));
                        }
                }
            }
        }
    }

    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    public int getDefaultRequiredLevel() {
        return 45;
    }

    public String getEffectKey() {
        return "sprint";
    }
}


