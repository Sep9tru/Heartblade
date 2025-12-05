package com.github.heartblade.specialeffects;

import mods.flammpfeil.slashblade.specialeffect.IRemovable;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import net.minecraft.item.ItemStack;

public class SpecialEffects implements ISpecialEffect, IRemovable {
    private int level;
    private String name;
    private Boolean canCopy;
    private Boolean canRemoval;

    public SpecialEffects(String name, int level, Boolean canCopy, Boolean canRemoval){
        this.level = level;
        this.name = name;
        this.canCopy = canCopy;
        this.canRemoval = canRemoval;
        mods.flammpfeil.slashblade.specialeffect.SpecialEffects.register(this);
    }

    public SpecialEffects(String name, int level){
        this(name,level,false,false);
    }

    @Override
    public void register() {}

    @Override
    public int getDefaultRequiredLevel() {
        return this.level;
    }

    @Override
    public String getEffectKey() {
        return name;
    }

    @Override
    public boolean canCopy(ItemStack stack) {
        return canCopy;
    }

    @Override
    public boolean canRemoval(ItemStack stack) {
        return canRemoval;
    }
}
