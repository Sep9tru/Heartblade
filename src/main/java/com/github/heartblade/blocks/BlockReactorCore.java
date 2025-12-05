package com.github.heartblade.blocks;

import com.github.heartblade.creativetab.HbTabs;
import com.github.heartblade.init.HbBlades;
import com.github.heartblade.utils.BladeUtils;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockReactorCore extends Block {
    public BlockReactorCore() {
        super(Material.GROUND);
        this.setLightLevel(1.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(50F);
        this.setCreativeTab(HbTabs.Hb_Item);
        this.setTranslationKey("wonder_reactor");
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canSpawnInBlock() {
        return false;
    }

    private boolean shrineAccepted(BlockPos pos, World world) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        return world.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x, y - 1, z + 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x, y - 1, z - 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y - 1, z)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y - 1, z)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y - 1, z - 1)) == Blocks.GOLD_BLOCK.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y - 1, z + 1)) == Blocks.GOLD_BLOCK.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y - 1, z - 1)) == Blocks.GOLD_BLOCK.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y - 1, z + 1)) == Blocks.GOLD_BLOCK.getDefaultState()

                && world.getBlockState(new BlockPos(x - 1, y, z - 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y, z + 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y, z - 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y, z + 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y, z)) == Blocks.AIR.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y, z)) == Blocks.AIR.getDefaultState()
                && world.getBlockState(new BlockPos(x, y, z + 1)) == Blocks.AIR.getDefaultState()
                && world.getBlockState(new BlockPos(x, y, z - 1)) == Blocks.AIR.getDefaultState()

                && world.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x, y + 1, z + 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x, y + 1, z - 1)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x + 1, y + 1, z)) == Blocks.COBBLESTONE.getDefaultState()
                && world.getBlockState(new BlockPos(x - 1, y + 1, z)) == Blocks.COBBLESTONE.getDefaultState();

    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItemMainhand();
        ItemStack itemStack_a = player.getHeldItemOffhand();
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(itemStack);
        if(this.shrineAccepted(pos, world)) {
            if(itemStack_a.getItem() == SlashBlade.proudSoul) {
                if(itemStack.getItem() == HbBlades.HB_BLADE) {
                    if (player.experienceLevel >= 5){
                    player.addExperienceLevel(-5);
                    itemStack_a.shrink(1);
                    ItemSlashBlade.RepairCount.tryAdd(tag, +1, false);
                    world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false));
                    player.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                    }
                }
            }
        }
        return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
    }
}
