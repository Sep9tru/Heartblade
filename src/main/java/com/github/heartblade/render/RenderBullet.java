package com.github.heartblade.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderBullet extends Render {
    public static IModelCustom model = null;

    public static ResourceLocation modelLocation = new ResourceLocation("flammpfeil.slashblade", "model/util/Bullet.obj");

    public static ResourceLocation textureLocation = new ResourceLocation("flammpfeil.slashblade", "model/util/Bullet.png");

    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
        renderModel(entity, x, y, z, yaw, partialRenderTick);
    }

    public void renderModel(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
        if (model == null)
            model = AdvancedModelLoader.loadModel(modelLocation);
        bindTexture(textureLocation);
        GL11.glPushMatrix();
        if (entity instanceof com.github.heartblade.Entityadd.EntityPhantomSwordBase_DaNiu);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float scale = 0.01F;
        Random rand = new Random();
        float a = rand.nextInt(180);
        GL11.glRotatef(a / 1.0F, a / 1.0F, a / 1.0F, a / 1.0F);
        GL11.glScalef(scale, scale, scale);
        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        for (int i = 0; i < 5; i++) {
            GL11.glScaled(0.95D, 0.95D, 0.95D);
            model.renderPart("base");
        }
        GL11.glPopMatrix();
        int loop = 3;
        for (int j = 0; j < loop; j++) {
            GL11.glPushMatrix();
            GL11.glScaled(1.03D, 1.03D, 1.03D);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            model.renderPart("base");
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return textureLocation;
    }
}
