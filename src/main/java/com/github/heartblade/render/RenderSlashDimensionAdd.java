package com.github.heartblade.render;

import com.github.heartblade.Entityadd.EntitySlashDimensionAdd;
import mods.flammpfeil.slashblade.client.model.obj.Util;
import mods.flammpfeil.slashblade.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderSlashDimensionAdd extends Render{

 static public IModelCustom model = null;

 static public ResourceLocation modelLocation = new ResourceLocation("flammpfeil.slashblade", "model/util/ThisIsPower/model.obj");
 static public ResourceLocation textureLocation = new ResourceLocation("flammpfeil.slashblade", "model/util/ThisIsPower/texture.png");

 private TextureManager engine() {
  return this.renderManager.renderEngine;
 }

 public void doRender(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
  renderModel(entity, x, y, z, yaw, partialRenderTick);
 }

 public void renderModel(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
  if (model == null) {
   model = AdvancedModelLoader.loadModel(modelLocation);
   if (model instanceof WavefrontObject)
    Util.replaceFace((WavefrontObject)model);
  }
  bindEntityTexture(entity);
  GL11.glPushMatrix();
  GL11.glPushAttrib(1048575);
  GL11.glShadeModel(7425);
  GL11.glDisable(2896);
  GL11.glEnable(3042);
  int color = 5592575;
  int lifetime = 20;
  if (entity instanceof EntitySlashDimensionAdd) {
   color = ((EntitySlashDimensionAdd)entity).getColor();
   lifetime = ((EntitySlashDimensionAdd)entity).getLifeTime();
  }
  boolean inverse = (color < 0);
  double deathTime = lifetime;
  double baseAlpha = Math.sin(1.5707963267948966D * Math.min(deathTime, ((lifetime - entity.ticksExisted) - partialRenderTick)) / deathTime);
  int seed = entity.getEntityData().getInteger("seed");
  int baseColor = color;
  Color col = new Color(baseColor);
  float[] hsb = Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), null);
  baseColor = Color.getHSBColor(0.5F + hsb[0], hsb[1], 0.2F).getRGB() & 0xFFFFFF;
  baseColor |= (int)(102.0D * baseAlpha) << 24;
  GL11.glTranslatef((float)x, (float)y, (float)z);
  float rotParTicks = 40.0F;
  float rot = entity.ticksExisted % rotParTicks / rotParTicks * 360.0F + partialRenderTick * 360.0F / rotParTicks;
  float scale = 0.01F;
  GL11.glScalef(scale, scale, scale);
  float lastx = OpenGlHelper.lastBrightnessX;
  float lasty = OpenGlHelper.lastBrightnessY;
  OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
  Color tmpColor = new Color(baseColor, true);
  GL11.glColor4f(tmpColor.getRed() / 255.0F, tmpColor.getGreen() / 255.0F, tmpColor.getBlue() / 255.0F, tmpColor.getAlpha() / 255.0F);
  OpenGlHelper.glBlendFunc(770, 1, 1, 0);
  GlStateManager.glBlendEquation(32779);
  GL11.glPushMatrix();
  for (int i = 0; i < 5; i++) {
   GL11.glScaled(0.95D, 0.95D, 0.95D);
   model.renderPart("base");
  }
  GL11.glPopMatrix();
  int loop = 3;
  for (int j = 0; j < loop; j++) {
   GL11.glPushMatrix();
   float ticks = 15.0F;
   float wave = (entity.ticksExisted + ticks / loop * j + partialRenderTick) % ticks;
   double waveScale = 1.0D + 0.03D * wave;
   GL11.glScaled(waveScale, waveScale, waveScale);
   Color color1 = new Color(baseColor & 0xFFFFFF | (int)(136.0F * (ticks - wave) / ticks) << 24, true);
   GL11.glColor4f(color1.getRed() / 255.0F, color1.getGreen() / 255.0F, color1.getBlue() / 255.0F, color1.getAlpha() / 255.0F);
   model.renderPart("base");
   GL11.glPopMatrix();
  }
  GlStateManager.glBlendEquation(32774);
  int windCount = 5;
  for (int k = 0; k < windCount; k++) {
   GL11.glPushMatrix();
   GL11.glRotated(360.0D / windCount * k, 1.0D, 0.0D, 0.0D);
   GL11.glRotated(30.0D, 0.0D, 1.0D, 0.0D);
   double rotWind = 18.0D;
   double offsetBase = 7.0D;
   double offset = k * offsetBase;
   double motionLen = offsetBase * (windCount - 1);
   double ticks = (entity.ticksExisted + partialRenderTick + seed);
   double offsetTicks = ticks + offset;
   double progress = offsetTicks % motionLen / motionLen;
   double rad = 6.283185307179586D;
   rad *= progress;
   Color color1 = new Color(color & 0xFFFFFF | (int)Math.min(0.0D, 255.0D * Math.sin(rad)) << 24, true);
   GL11.glColor4f(color1.getRed() / 255.0F, color1.getGreen() / 255.0F, color1.getBlue() / 255.0F, color1.getAlpha() / 255.0F);
   double windScale = 0.4D + progress;
   GL11.glScaled(windScale, windScale, windScale);
   GL11.glRotated(rotWind * offsetTicks, 0.0D, 0.0D, 1.0D);
   model.renderPart("wind");
   GL11.glPopMatrix();
  }
  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastx, lasty);
  GL11.glDisable(3042);
  GL11.glEnable(2896);
  GL11.glShadeModel(7424);
  GL11.glPopAttrib();
  GL11.glPopMatrix();
 }

 protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
  return textureLocation;
 }
}
