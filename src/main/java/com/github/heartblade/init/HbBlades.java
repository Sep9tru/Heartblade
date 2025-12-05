package com.github.heartblade.init;

import com.github.heartblade.named.*;
import mods.flammpfeil.slashblade.SlashBlade;
import com.github.heartblade.creativetab.HbTabs;
import com.github.heartblade.named.item.ItemHbSlashBlade;
import net.minecraft.item.Item;

public class HbBlades {
    public static final Item HB_BLADE = new ItemHbSlashBlade(Item.ToolMaterial.IRON, 4.0f,"hbSlashBlade")
            .setCreativeTab(HbTabs.Hb_Item);

    public HbBlades(){
        loadBlade();
    }

    public void loadBlade(){
        loadBlade(new wonderblade());
        loadBlade(new nobleblade());
        loadBlade(new xiaochaoblade());
        loadBlade(new ziminblade());
        loadBlade(new matongcblade());
        loadBlade(new dguoblade());
        loadBlade(new yunxingheblade());
        loadBlade(new iceblade());
        loadBlade(new xiaohuangOrArui());
        loadBlade(new katana_blades());
        loadBlade(new YamatoPower());
    }

    public void loadBlade(Object blade) {
        SlashBlade.InitEventBus.register(blade);
    }

}
