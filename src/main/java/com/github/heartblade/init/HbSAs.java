package com.github.heartblade.init;

import com.github.heartblade.specialattack.*;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

public class HbSAs {
    public HbSAs(){
            loadSAs();
    }
    public void loadSAs(){
        ItemSlashBlade.specialAttacks.put(2342134, new autumnWind());
        ItemSlashBlade.specialAttacks.put(1560404, new SlashDimensionP());
//            System.out.println("SA registed!");
    }
}
