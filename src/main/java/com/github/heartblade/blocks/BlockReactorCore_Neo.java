package com.github.heartblade.blocks;

import com.github.heartblade.creativetab.CreativeTabsLoader;
import com.github.heartblade.heartblade;
import com.github.heartblade.inventory.GuiElementLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BlockReactorCore_Neo extends Block {
    public BlockReactorCore_Neo() {
        super(Material.rock);
        setHarvestLevel("pickDiamond", 4);
        this.setBlockName("wonderReactor_Neo");
        this.setLightLevel(1.0F);
        this.setBlockTextureName("heartblade:wonder_reactor");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }
    public void randomDisplayTick(World world, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random rnd) {
        for (int l = 0; l < 2; l++) {
            double d6 = (p_149734_2_ + rnd.nextFloat());
            double d1 = (p_149734_3_ + rnd.nextFloat());
            d6 = (p_149734_4_ + rnd.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rnd.nextInt(2) * 2 - 1;
            int j1 = rnd.nextInt(2) * 2 - 1;
            d3 = (rnd.nextFloat() - 0.5D) * 0.125D;
            d4 = (rnd.nextFloat() - 0.5D) * 0.125D * 4.0D;
            d5 = (rnd.nextFloat() - 0.5D) * 0.125D;
            double d2 = p_149734_4_ + 0.5D + 0.25D * j1;
            d5 = (rnd.nextFloat() * 1.0F * j1) / 6.0D;
            double d0 = p_149734_2_ + 0.5D + 0.25D * i1;
            d3 = (rnd.nextFloat() * 1.0F * i1) / 6.0D;
            world.spawnParticle("fireworksSpark", d0, d1, d2, d3, d4, d5); }
    }
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }
    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block1 = world.getBlock(x,y+1,z);
            Block block2 = world.getBlock(x,y+1,z);
            Block block3 = world.getBlock(x,y+1,z);
            Block block4 = world.getBlock(x,y+1,z);
            byte b0 = 3;
            if (block1.func_149730_j() && !block2.func_149730_j()) {
                b0 = 3;
            }

            if (block2.func_149730_j() && !block1.func_149730_j()) {
                b0 = 2;
            }

            if (block3.func_149730_j() && !block4.func_149730_j()) {
                b0 = 5;
            }

            if (block4.func_149730_j() && !block3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }

    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
        if(world.getBlock(x+1, y-1, z+1) == Blocks.gold_block) {
            if (world.getBlock(x - 1, y-1, z - 1) == Blocks.gold_block) {
                if (world.getBlock(x + 1, y-1, z - 1) == Blocks.gold_block) {
                    if (world.getBlock(x - 1, y-1, z + 1) == Blocks.gold_block) {
                        if (world.getBlock(x + 0, y-1, z + 0) == Blocks.cobblestone) {
                            if (world.getBlock(x + 1, y-1, z + 0) == Blocks.cobblestone) {
                                if (world.getBlock(x + 0, y-1, z + 1) == Blocks.cobblestone) {
                                    if (world.getBlock(x + 0, y-1, z - 1) == Blocks.cobblestone) {
                                        if (world.getBlock(x - 1, y-1, z + 0) == Blocks.cobblestone) {
                                            if (world.getBlock(x + 0, y+0, z + 0) == BlockLoader.wonderReactor_Neo) {
                                                if (world.getBlock(x + 1, y+0, z + 1) == Blocks.cobblestone) {
                                                    if (world.getBlock(x + 1, y+0, z - 1) == Blocks.cobblestone) {
                                                        if (world.getBlock(x - 1, y+0, z + 1) == Blocks.cobblestone) {
                                                            if (world.getBlock(x - 1, y+0, z - 1) == Blocks.cobblestone) {
                                                                if (world.getBlock(x + 0, y+0, z + 1) == Blocks.air) {
                                                                    if (world.getBlock(x + 0, y+0, z - 1) == Blocks.air) {
                                                                        if (world.getBlock(x + 1, y+0, z + 0) == Blocks.air) {
                                                                            if (world.getBlock(x - 1, y+0, z + 0) == Blocks.air) {
                                                                                if (world.getBlock(x + 0, y + 1, z + 0) == Blocks.cobblestone) {
                                                                                    if (world.getBlock(x + 1, y + 1, z + 1) == Blocks.air) {
                                                                                        if (world.getBlock(x + 1, y + 1, z - 1) == Blocks.air) {
                                                                                            if (world.getBlock(x - 1, y + 1, z + 1) == Blocks.air) {
                                                                                                if (world.getBlock(x - 1, y + 1, z - 1) == Blocks.air) {
                                                                                                    if (world.getBlock(x + 0, y + 1, z + 1) == Blocks.cobblestone) {
                                                                                                        if (world.getBlock(x + 0, y + 1, z - 1) == Blocks.cobblestone) {
                                                                                                            if (world.getBlock(x + 1, y + 1, z + 0) == Blocks.cobblestone) {
                                                                                                                if (world.getBlock(x - 1, y + 1, z + 0) == Blocks.cobblestone) {
                                                                                                                    if (!world.isRemote) {
                                                                                                                        int id = GuiElementLoader.GUI_DEMO;
                                                                                                                        player.openGui(heartblade.instance, id, world, x, y, z);
                                                                                                                        world.playSoundAtEntity(player, "random.successful_hit", 1.0F, 1.0F);
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}