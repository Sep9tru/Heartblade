package com.github.heartblade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.item.ModRecord;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "heartblade",
        name = "heartblade",
        version = "1.5.0_hotfix1",
        dependencies = "required-after:flammpfeil.slashblade"
)
public class heartblade {
    public static final String MODID = "heartblade";
    @Instance("heartblade")
    public static heartblade instance;
    @SidedProxy(
            clientSide = "com.github.heartblade.client.ClientProxy",
            serverSide = "com.github.heartblade.common.CommonProxy"
    )
    public static CommonProxy proxy;
    
    public heartblade() {
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        ModRecord.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }



}