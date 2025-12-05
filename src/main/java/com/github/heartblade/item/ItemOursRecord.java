package com.github.heartblade.item;

import com.github.heartblade.heartblade;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.*;
import net.minecraft.creativetab.CreativeTabs;

public class ItemOursRecord extends ItemRecord {

    public static SoundEvent soundIn = new SoundEvent(new ResourceLocation(heartblade.MODID, "oursrecord"));

    public ItemOursRecord(String name, CreativeTabs tab) {
        super(name, soundIn);
        setCreativeTab(tab);
        setTranslationKey(name);
        setRegistryName(name);
    }
}
