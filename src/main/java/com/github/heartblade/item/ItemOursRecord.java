package com.github.heartblade.item;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemOursRecord extends ItemRecord implements ItemOreDict {
    private String recordName;

    public ItemOursRecord(String recordName) {
        super(recordName);
        this.recordName = recordName;
        this.setUnlocalizedName("oursrecord");
        this.setTextureName("heartblade:oursrecord_" + recordName);
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (this.recordName.equals("SweetTrack")) {
            tooltip.add(Utils.coloredString("&6因为圣光."));
        }

    }

    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("heartblade", name);
    }

    public void registerOreDict() {
        OreDictionary.registerOre("record", this);
    }
}

