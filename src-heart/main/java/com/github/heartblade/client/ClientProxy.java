package com.github.heartblade.client;


import com.github.heartblade.Entityadd.*;
import com.github.heartblade.common.CommonProxy;
import com.github.heartblade.render.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.flammpfeil.slashblade.ItemRendererBaseWeapon;
import mods.flammpfeil.slashblade.RenderDrive;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy
   extends CommonProxy
 {
   public void preInit(FMLPreInitializationEvent event) {
     super.preInit(event);
     ItemRendererBaseWeapon renderer = new ItemRendererBaseWeapon();
     MinecraftForgeClient.registerItemRenderer(CommonProxy.xiaochaoblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.matongcblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.dguoblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.ziminblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.desireblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.justiceGun, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.YamatoHyper, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.mirageEdge, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.wonderblade, renderer);
     MinecraftForgeClient.registerItemRenderer(CommonProxy.maxkimblade, renderer);
   }


   public void init(FMLInitializationEvent event) {
     RenderDrive rd = new RenderDrive();
     super.init(event);
       RenderingRegistry.registerEntityRenderingHandler(EntityDriveAdd.class, (Render)new RenderDriveAdd());
     RenderingRegistry.registerEntityRenderingHandler(EntitySlashDimensionAdd.class, (Render)new RenderSlashDimensionAdd());
       RenderingRegistry.registerEntityRenderingHandler(EntitySummonedSwordAdd.class, (Render)new RenderSummonedSwordAdd());
       RenderingRegistry.registerEntityRenderingHandler(EntitySummonedButterfly.class, (Render)new RenderSummonedButterfly());
     RenderingRegistry.registerEntityRenderingHandler(EntityCutEndRange.class, (Render)new RenderCutEndRange());
     RenderingRegistry.registerEntityRenderingHandler(EntityCutEndAdd.class, (Render)new RenderCutEndAdd());
     RenderingRegistry.registerEntityRenderingHandler(EntitySummonedBlade_flake.class, (Render)new RenderCAT());
     RenderingRegistry.registerEntityRenderingHandler(EntityPhantomBullet.class, (Render)new RenderBullet());
     RenderingRegistry.registerEntityRenderingHandler(EntityMaximumBetHyper.class, rd);
     RenderingRegistry.registerEntityRenderingHandler(EntityCutEndManager.class, rd);
     {
     super.init(event);
   }
 }
   public void postInit(FMLPostInitializationEvent event) {
        /* 35 */     super.postInit(event);
        /*    */   }
}
