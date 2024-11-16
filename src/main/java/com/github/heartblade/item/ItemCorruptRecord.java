package com.github.heartblade.item;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemCorruptRecord extends ItemRecord implements ItemOreDict {
    private String recordName;

    public ItemCorruptRecord(String recordName) {
        super(recordName);
        this.recordName = recordName;
        this.setUnlocalizedName("corruptrecord");
        this.setTextureName("heartblade:corruptrecord_" + recordName);
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (this.recordName.equals("Corrupt")) {
            tooltip.add(Utils.coloredString("&9楼楼服务器BISS"));
        }

    }



    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("heartblade", name);
    }

    public void registerOreDict() {
        OreDictionary.registerOre("record", this);
    }
}