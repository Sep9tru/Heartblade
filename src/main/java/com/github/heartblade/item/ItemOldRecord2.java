package com.github.heartblade.item;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemOldRecord2 extends ItemRecord implements ItemOreDict {
    private String recordName;

    public ItemOldRecord2(String recordName) {
        super(recordName);
        this.recordName = recordName;
        this.setUnlocalizedName("oldrecord2");
        this.setTextureName("heartblade:oldrecord2_" + recordName);
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (this.recordName.equals("Old2")) {
            tooltip.add(Utils.coloredString("&3刻在DNA里的旋律."));
        }

    }

    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("heartblade", name);
    }

    public void registerOreDict() {
        OreDictionary.registerOre("record2", this);
    }
}