package com.github.heartblade;

import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeHeartblade extends RecipeAwakeBlade {
    ItemStack sphere;
    public RecipeHeartblade(ItemStack result,ItemStack requiredStateBlade,ItemStack reqiredSphere,Object... recipe) {
        super(result,requiredStateBlade,recipe);

        this.sphere = reqiredSphere;
    }

    public boolean matches(InventoryCrafting inv,World world) {
        boolean result = super.matches(inv,world);
        if (!result) {return false; }

        else if (this.sphere == null) {return false; }

        else { for(int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack current = inv.getStackInSlot(i);
                if (current.isItemEqual(this.sphere)) {


                    int currentsa = ItemSlashBlade.SpecialAttackType.get(current.getTagCompound());


                    int requiredsa = ItemSlashBlade.SpecialAttackType.get(this.sphere.getTagCompound());


                    if (requiredsa != currentsa) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}

