package com.github.heartblade.blocks;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockReactorCore extends Block {
        public BlockReactorCore() {
                super(Material.rock);
                setHarvestLevel("pickDiamond", 4);
                this.setBlockName("wonderReactor");
                this.setLightLevel(0.0F);
                this.setBlockTextureName("heartblade:wonder_reactor_old");
                this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
        }

}