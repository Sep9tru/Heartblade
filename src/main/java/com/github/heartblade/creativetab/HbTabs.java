package com.github.heartblade.creativetab;

import com.github.heartblade.blocks.BlockLoader;
import com.github.heartblade.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author Moflop
 * @updateDate 2020/02/12
 */
public class HbTabs {
    public static final CreativeTabs Hb_Item = new CreativeTabs("hbItem")
    {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockLoader.WONDER_REACTOR_CORE);
        }
    };
}
