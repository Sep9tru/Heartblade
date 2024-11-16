package com.github.heartblade.item;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import net.minecraft.item.Item;

public class testItem extends Item {
    public testItem() {
        this.setUnlocalizedName("whiteButterfly");
        this.setTextureName("heartblade:whiteButterfly");
        this.setUnlocalizedName("whiteButterfly");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);

    }
}
