package com.github.heartblade.recipe;

import com.github.heartblade.item.ItemLoader;
import com.github.heartblade.item.ModRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class Recipe {
    public Recipe() {
    }
    public static void init() {
        registerChestLoot(new ItemStack(ItemLoader.whiteButterfly), 1, 1, 7);
        registerChestLoot(new ItemStack(ModRecord.oursRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.orangeRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.dustyRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.icedRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.corruptRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.nullRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.pRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.pRecord2), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.chaosRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.oldRecord), 1, 1, 1);
        registerChestLoot(new ItemStack(ModRecord.oldRecord2), 1, 1, 1);

    }
    public static void registerChestLoot(ItemStack loot, int min, int max, int rarity) {
        ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(loot, min, max, rarity));
    }
}
