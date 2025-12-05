package com.github.heartblade.item;

import com.github.heartblade.heartblade;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ItemOldrecord_1 extends ItemRecord {

    public static SoundEvent soundIn = new SoundEvent(new ResourceLocation(heartblade.MODID, "oldrecord_1"));

    public ItemOldrecord_1(String name, CreativeTabs tab) {
        super(name, soundIn);
        setCreativeTab(tab);
        setTranslationKey(name);
        setRegistryName(name);
    }
}