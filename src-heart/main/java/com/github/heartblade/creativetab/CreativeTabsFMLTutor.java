package com.github.heartblade.creativetab;


import com.github.heartblade.blocks.BlockLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsFMLTutor extends CreativeTabs {
    public CreativeTabsFMLTutor() {
        super("heartblade");
        this.setBackgroundImageName("heartblade.png");
    }


    @Override
    public Item getTabIconItem() {

        return Item.getItemFromBlock(BlockLoader.wonderReactor_Neo);
    }
}
