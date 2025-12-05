package com.github.heartblade.common;

import com.github.heartblade.HbConfig;
import com.github.heartblade.init.HbBlades;
import com.github.heartblade.init.HbEntitys;
import com.github.heartblade.init.HbSAs;
import com.github.heartblade.init.HbSEs;
import com.github.heartblade.item.event.LootDropsEvent;
import com.github.heartblade.specialeffects.event.SEEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        new HbSEs();
        new HbBlades();
        new HbSAs();
        new HbEntitys();
        new HbConfig(event);
    }

    public void init(FMLInitializationEvent event){
        new SEEvent();
        new LootDropsEvent();
    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
