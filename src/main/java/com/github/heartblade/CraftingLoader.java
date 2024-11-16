package com.github.heartblade;

import com.github.heartblade.blocks.BlockLoader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
    }

    public static void registerRecipe()
    {
    GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.wonderReactor_Neo), new Object[]
        {
                                        "III",
                                        "C*C",
                                        "III",
                                        'I', Items.iron_ingot,
                                        '*', Items.clock,
                                        'C', Blocks.lapis_block,
        });
    }
}
