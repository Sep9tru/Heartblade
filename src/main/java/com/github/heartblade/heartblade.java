package com.github.heartblade;

import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.entity.EntitySlashDimensionP;
import mods.flammpfeil.slashblade.entity.EntitySlashDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = heartblade.MODID,
        name = heartblade.NAME,
        version = heartblade.VER,
        dependencies = heartblade.DEP,
        acceptedMinecraftVersions = "[1.12.2]"
)
public class heartblade {
    public static final String MODID = "heartblade";
    public static final String NAME = "heartblade";
    public static final String VER = "Beta_3.0.0";/** 版本号 */
    public static final String DEP = "required-after:flammpfeil.slashblade@[mc1.12-r30,);";/** 依赖拔刀剑版本 */
    public static Logger logger = LogManager.getLogger(MODID);

    @Instance(heartblade.MODID)
    public static heartblade instance;

    @SidedProxy(
            clientSide = "com.github.heartblade.client.ClientProxy",
            serverSide = "com.github.heartblade.common.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
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
