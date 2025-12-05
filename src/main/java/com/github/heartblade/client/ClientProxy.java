package com.github.heartblade.client;

import com.github.heartblade.client.render.entity.RenderDriveEx;
import com.github.heartblade.client.render.entity.RenderPhantomSwordExBase;
import com.github.heartblade.entity.EntityDriveEx;
import com.github.heartblade.entity.EntityPhantomSwordEx;
import com.github.heartblade.entity.EntityPhantomSwordExBase;
import com.github.heartblade.client.render.entity.*;
import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.entity.*;
import com.github.heartblade.utils.ItemUtils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        new ItemUtils();

        RenderingRegistry.registerEntityRenderingHandler(
                EntityDriveEx.class,
                new IRenderFactory<EntityDriveEx>() {
                    @Override
                    public Render<? super EntityDriveEx> createRenderFor(RenderManager manager)
                    {
                        return new RenderDriveEx(manager);
                    }
                });
        RenderingRegistry.registerEntityRenderingHandler(
                EntityPhantomSwordExBase.class,
                new IRenderFactory<EntityPhantomSwordExBase>() {
                    @Override
                    public Render<? super EntityPhantomSwordExBase> createRenderFor(RenderManager manager)
                    {
                        return new RenderPhantomSwordExBase(manager);
                    }
                });
        RenderingRegistry.registerEntityRenderingHandler(
                EntityPhantomSwordEx.class,
                new IRenderFactory<EntityPhantomSwordEx>() {
                    @Override
                    public Render<? super EntityPhantomSwordEx> createRenderFor(RenderManager manager)
                    {
                        return new RenderPhantomSwordExBase(manager);
                    }
                });
        RenderingRegistry.registerEntityRenderingHandler(
                EntityFlake.class,
                new IRenderFactory<EntityFlake>() {
                    @Override
                    public Render<? super EntityFlake> createRenderFor(RenderManager manager)
                    {
                        return new RenderFlake(manager);
                    }
                });
        RenderingRegistry.registerEntityRenderingHandler(
                EntitySlashDimensionP.class,
                new IRenderFactory<EntitySlashDimensionP>() {
                    @Override
                    public Render<? super EntitySlashDimensionP> createRenderFor(RenderManager manager)
                    {
                        return new RenderSlashDimensionP(manager);
                    }
                });
        RenderingRegistry.registerEntityRenderingHandler(
                EntitySlashDimensionP_core.class,
                new IRenderFactory<EntitySlashDimensionP_core>() {
                    @Override
                    public Render<? super EntitySlashDimensionP_core> createRenderFor(RenderManager manager)
                    {
                        return new RenderSlashDimensionP_core(manager);
                    }
                });
}


    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

}
