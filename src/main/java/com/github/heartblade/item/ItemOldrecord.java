package com.github.heartblade.item;

import com.github.heartblade.heartblade;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.*;
import net.minecraft.creativetab.CreativeTabs;

public class ItemOldrecord extends ItemRecord {

    public static SoundEvent soundIn = new SoundEvent(new ResourceLocation(heartblade.MODID, "oldrecord"));

    public ItemOldrecord(String name, CreativeTabs tab) {
        super(name, soundIn);
        setCreativeTab(tab);
        setTranslationKey(name);
        setRegistryName(name);
    }
}
