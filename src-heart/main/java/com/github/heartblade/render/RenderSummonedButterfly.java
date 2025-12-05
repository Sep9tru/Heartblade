package com.github.heartblade.render;

import com.github.heartblade.Entityadd.EntitySummonedButterfly;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

 @SideOnly(Side.CLIENT)
 public class RenderSummonedButterfly
   extends Render
 {
   private static double[][] dVec = new double[][] { { 1.05D, 5.03D, 22.63D }, { 2.36D, 3.05D, 8.4D }, { 3.83D, 1.2D, -5.34D }, { 5.4D, -0.47D, -18.09D }, { 7.13D, -1.85D, -29.36D }, { 9.07D, -2.85D, -38.66D }, { 12.52D, -3.37D, -47.44D }, { 21.05D, -1.84D, -51.88D }, { 30.57D, 0.87D, -50.69D }, { 39.45D, 3.14D, -51.22D }, { 42.36D, 4.8D, -45.76D }, { 47.93D, 7.38D, -39.0D }, { 55.35D, 10.33D, -32.93D }, { 59.25D, 12.66D, -24.99D }, { 59.61D, 14.03D, -17.16D }, { 58.07D, 14.95D, -9.08D }, { 55.23D, 15.26D, -2.54D }, { 51.3D, 15.08D, 2.69D }, { 46.49D, 14.49D, 6.9D }, { 41.03D, 13.61D, 10.33D }, { 35.13D, 12.52D, 13.24D }, { 29.02D, 11.33D, 15.89D }, { 35.81D, 13.07D, 15.54D }, { 45.37D, 15.58D, 15.38D }, { 55.78D, 18.52D, 16.53D }, { 61.65D, 20.7D, 20.35D }, { 63.76D, 22.38D, 27.2D }, { 66.06D, 24.43D, 36.03D }, { 68.45D, 26.8D, 46.65D }, { 70.12D, 29.0D, 57.46D }, { 70.21D, 30.56D, 66.88D }, { 65.14D, 30.42D, 74.24D }, { 54.81D, 27.67D, 74.13D }, { 46.95D, 25.3D, 72.35D }, { 38.75D, 22.58D, 68.98D }, { 30.12D, 19.35D, 63.18D }, { 21.03D, 15.45D, 54.08D }, { 11.39D, 10.74D, 40.85D }, { -37.42D, 3.13D, -51.46D }, { -28.53D, 0.86D, -50.94D }, { -22.07D, -1.09D, -52.38D }, { -15.97D, -2.61D, -51.86D }, { -10.48D, -3.39D, -47.68D }, { -7.03D, -2.87D, -38.9D }, { -40.32D, 4.79D, -46.01D }, { -5.09D, -1.86D, -29.6D }, { -3.36D, -0.48D, -18.33D }, { -45.89D, 7.37D, -39.24D }, { -53.31D, 10.32D, -33.17D }, { -57.22D, 12.65D, -25.24D }, { -57.57D, 14.02D, -17.41D }, { -56.03D, 14.94D, -9.32D }, { -53.19D, 15.25D, -2.79D }, { -49.26D, 15.07D, 2.45D }, { -44.45D, 14.48D, 6.66D }, { -38.99D, 13.59D, 10.08D }, { -33.1D, 12.51D, 12.99D }, { -1.79D, 1.19D, -5.58D }, { -26.98D, 11.32D, 15.64D }, { -0.32D, 3.04D, 8.16D }, { -9.36D, 10.73D, 40.61D }, { -33.77D, 13.06D, 15.29D }, { -43.33D, 15.57D, 15.14D }, { -53.75D, 18.51D, 16.29D }, { -59.61D, 20.69D, 20.1D }, { -61.72D, 22.36D, 26.95D }, { -18.99D, 15.44D, 53.84D }, { -64.02D, 24.42D, 35.79D }, { -28.09D, 19.34D, 62.93D }, { -66.42D, 26.79D, 46.41D }, { -36.71D, 22.57D, 68.74D }, { -44.92D, 25.29D, 72.11D }, { -68.08D, 28.99D, 57.22D }, { -52.77D, 27.66D, 73.88D }, { -68.18D, 30.55D, 66.63D }, { -63.1D, 30.41D, 74.0D }, { 0.98D, 5.02D, 22.39D } };
   private static int[][] nVecPos = new int[][] { { 8, 9, 10 }, { 7, 8, 10 }, { 6, 7, 10 }, { 10, 5, 6 }, { 11, 4, 5 }, { 11, 5, 10 }, { 11, 3, 4 }, { 12, 3, 11 }, { 14, 3, 12 }, { 14, 12, 13 }, { 16, 3, 14 }, { 16, 14, 15 }, { 18, 3, 16 }, { 18, 16, 17 }, { 20, 3, 18 }, { 20, 18, 19 }, { 21, 2, 3 }, { 21, 3, 20 }, { 0, 1, 2 }, { 0, 2, 21 }, { 22, 37, 0 }, { 22, 0, 21 }, { 23, 37, 22 }, { 37, 23, 24 }, { 37, 24, 25 }, { 36, 37, 25 }, { 36, 25, 26 }, { 35, 36, 26 }, { 35, 26, 27 }, { 34, 35, 27 }, { 34, 27, 28 }, { 29, 33, 34 }, { 29, 34, 28 }, { 30, 32, 33 }, { 30, 33, 29 }, { 31, 32, 30 }, { 44, 38, 39 }, { 39, 40, 44 }, { 44, 40, 41 }, { 41, 42, 44 }, { 44, 42, 43 }, { 44, 43, 45 }, { 44, 45, 47 }, { 45, 46, 47 }, { 48, 47, 46 }, { 48, 46, 50 }, { 48, 50, 49 }, { 51, 50, 46 }, { 51, 46, 52 }, { 52, 46, 54 }, { 52, 54, 53 }, { 55, 54, 46 }, { 55, 46, 56 }, { 46, 57, 58 }, { 46, 58, 56 }, { 57, 59, 76 }, { 57, 76, 58 }, { 58, 76, 60 }, { 58, 60, 61 }, { 62, 61, 60 }, { 63, 62, 60 }, { 64, 63, 60 }, { 65, 64, 60 }, { 65, 60, 66 }, { 67, 65, 66 }, { 67, 66, 68 }, { 69, 67, 68 }, { 69, 68, 70 }, { 69, 70, 71 }, { 69, 71, 72 }, { 72, 71, 73 }, { 72, 73, 74 }, { 73, 75, 74 }, { 10, 9, 8 }, { 8, 7, 10 }, { 7, 6, 10 }, { 10, 6, 5 }, { 5, 4, 11 }, { 5, 11, 10 }, { 4, 3, 11 }, { 11, 3, 12 }, { 13, 12, 3 }, { 13, 3, 14 }, { 14, 3, 16 }, { 14, 16, 15 }, { 16, 3, 18 }, { 16, 18, 17 }, { 19, 18, 3 }, { 19, 3, 20 }, { 3, 2, 21 }, { 3, 21, 20 }, { 2, 1, 0 }, { 2, 0, 21 }, { 21, 0, 37 }, { 21, 37, 22 }, { 23, 22, 37 }, { 24, 23, 37 }, { 25, 24, 37 }, { 25, 37, 36 }, { 25, 36, 26 }, { 27, 26, 36 }, { 27, 36, 35 }, { 28, 27, 35 }, { 28, 35, 34 }, { 28, 34, 33 }, { 28, 33, 29 }, { 33, 32, 30 }, { 33, 30, 29 }, { 30, 32, 31 }, { 39, 38, 44 }, { 40, 39, 44 }, { 41, 40, 44 }, { 42, 41, 44 }, { 43, 42, 44 }, { 47, 45, 43 }, { 47, 43, 44 }, { 47, 46, 45 }, { 48, 46, 47 }, { 50, 46, 48 }, { 50, 48, 49 }, { 46, 50, 51 }, { 46, 51, 52 }, { 54, 46, 52 }, { 54, 52, 53 }, { 56, 46, 54 }, { 56, 54, 55 }, { 58, 57, 46 }, { 58, 46, 56 }, { 76, 59, 57 }, { 76, 57, 58 }, { 60, 76, 58 }, { 60, 58, 61 }, { 60, 61, 62 }, { 63, 60, 62 }, { 64, 60, 63 }, { 60, 64, 65 }, { 60, 65, 66 }, { 68, 66, 65 }, { 68, 65, 67 }, { 70, 68, 67 }, { 70, 67, 69 }, { 72, 71, 70 }, { 72, 70, 69 }, { 73, 71, 72 }, { 73, 72, 74 }, { 75, 73, 74 } };
   public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
     if (entity instanceof EntitySummonedButterfly) {
       doDriveRender((EntitySummonedButterfly)entity, d0, d1, d2, f, f1);
     }
   }
   protected ResourceLocation getEntityTexture(Entity var1) {
        /* 256 */     return null;
        /*     */   }
   private void doDriveRender(EntitySummonedButterfly entityPhantomSword, double dX, double dY, double dZ, float f, float f1) {
     Tessellator tessellator = Tessellator.instance;
     GL11.glDisable(3553);
     GL11.glDisable(2896);
     GL11.glEnable(3042);
       int color = entityPhantomSword.getColor();
       boolean inverse = (color < 0);
       color = Math.abs(color);
       if (!inverse) {
           GL11.glBlendFunc(770, 1);
       } else {
           GL11.glBlendFunc(775, 0);
       }
     GL11.glBlendFunc(770, 1);
     GL11.glPushMatrix();

     GL11.glTranslatef((float)dX, (float)dY, (float)dZ);
     GL11.glRotatef(entityPhantomSword.rotationYaw, 0.0F, 1.0F, 0.0F);
     GL11.glRotatef(-entityPhantomSword.rotationPitch, 1.0F, 0.0F, 0.0F);
     GL11.glRotatef(entityPhantomSword.getRoll(), 0.0F, 0.0F, 1.0F);

     float scale = 0.0045F;
     GL11.glScalef(scale, scale, scale);
     GL11.glScalef(0.5F, 0.5F, 1.0F);


     float lifetime = entityPhantomSword.getLifeTime();
     float ticks = entityPhantomSword.ticksExisted;
     tessellator.startDrawing(4);
     tessellator.setColorRGBA_I(color, 3092303);


     double dScale = 1.0D;
     for (int idx = 0; idx < nVecPos.length; idx++) {

       tessellator.addVertex(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale);
       tessellator.addVertex(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale);
       tessellator.addVertex(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale);
     }
     tessellator.draw();

     GL11.glPopMatrix();
     GL11.glDisable(3042);
     GL11.glEnable(2896);
     GL11.glEnable(3553);
   }
 }