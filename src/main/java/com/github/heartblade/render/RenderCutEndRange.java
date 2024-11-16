package com.github.heartblade.render;

import com.github.heartblade.Entityadd.EntityCutEndRange;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCutEndRange extends Render {
    private static double[][] dVec = new double[][] {
            { 0.0D, 33.7131D, 0.0D }, { 0.0D, 31.1468D, 12.9014D }, { 9.1227D, 31.1468D, 9.1227D }, { 12.9014D, 31.1468D, -0.0D }, { 9.1227D, 31.1468D, -9.1227D }, { -0.0D, 31.1468D, -12.9014D }, { -9.1227D, 31.1468D, -9.1227D }, { -12.9014D, 31.1468D, 0.0D }, { -9.1227D, 31.1468D, 9.1227D }, { 0.0D, 23.8387D, 23.8387D },
            { 16.8565D, 23.8387D, 16.8565D }, { 23.8387D, 23.8387D, -0.0D }, { 16.8565D, 23.8387D, -16.8565D }, { -0.0D, 23.8387D, -23.8387D }, { -16.8565D, 23.8387D, -16.8565D }, { -23.8387D, 23.8387D, 0.0D }, { -16.8565D, 23.8387D, 16.8565D }, { 0.0D, 12.9014D, 31.1468D }, { 22.0241D, 12.9014D, 22.0241D }, { 31.1468D, 12.9014D, -0.0D },
            { 22.0241D, 12.9014D, -22.0241D }, { -0.0D, 12.9014D, -31.1468D }, { -22.0241D, 12.9014D, -22.0241D }, { -31.1468D, 12.9014D, 0.0D }, { -22.0241D, 12.9014D, 22.0241D }, { 0.0D, -0.0D, 33.7131D }, { 23.8387D, -0.0D, 23.8387D }, { 33.7131D, -0.0D, -0.0D }, { 23.8387D, -0.0D, -23.8387D }, { -0.0D, -0.0D, -33.7131D },
            { -23.8387D, -0.0D, -23.8387D }, { -33.7131D, -0.0D, 0.0D }, { -23.8387D, -0.0D, 23.8387D }, { 0.0D, -12.9014D, 31.1468D }, { 22.0241D, -12.9014D, 22.0241D }, { 31.1468D, -12.9014D, -0.0D }, { 22.0241D, -12.9014D, -22.0241D }, { -0.0D, -12.9014D, -31.1468D }, { -22.0241D, -12.9014D, -22.0241D }, { -31.1468D, -12.9014D, 0.0D },
            { -22.0241D, -12.9014D, 22.0241D }, { 0.0D, -23.8387D, 23.8387D }, { 16.8565D, -23.8387D, 16.8565D }, { 23.8387D, -23.8387D, -0.0D }, { 16.8565D, -23.8387D, -16.8565D }, { -0.0D, -23.8387D, -23.8387D }, { -16.8565D, -23.8387D, -16.8565D }, { -23.8387D, -23.8387D, 0.0D }, { -16.8565D, -23.8387D, 16.8565D }, { 0.0D, -31.1468D, 12.9014D },
            { 9.1227D, -31.1468D, 9.1227D }, { 12.9014D, -31.1468D, -0.0D }, { 9.1227D, -31.1468D, -9.1227D }, { -0.0D, -31.1468D, -12.9014D }, { -9.1227D, -31.1468D, -9.1227D }, { -12.9014D, -31.1468D, 0.0D }, { -9.1227D, -31.1468D, 9.1227D }, { 0.0D, -33.7131D, 0.0D } };

    private static int[][] nVecPos = new int[][] {
            { 0, 2, 1 }, { 0, 3, 2 }, { 0, 4, 3 }, { 0, 5, 4 }, { 0, 6, 5 }, { 0, 7, 6 }, { 0, 8, 7 }, { 0, 1, 8 }, { 49, 50, 57 }, { 50, 51, 57 },
            { 51, 52, 57 }, { 52, 53, 57 }, { 53, 54, 57 }, { 54, 55, 57 }, { 55, 56, 57 }, { 56, 49, 57 }, { 2, 10, 9 }, { 2, 9, 1 }, { 3, 11, 10 }, { 3, 10, 2 },
            { 4, 12, 11 }, { 4, 11, 3 }, { 5, 13, 12 }, { 5, 12, 4 }, { 6, 14, 13 }, { 6, 13, 5 }, { 7, 15, 14 }, { 7, 14, 6 }, { 8, 16, 15 }, { 8, 15, 7 },
            { 1, 9, 16 }, { 1, 16, 8 }, { 10, 18, 17 }, { 10, 17, 9 }, { 11, 19, 18 }, { 11, 18, 10 }, { 12, 20, 19 }, { 12, 19, 11 }, { 13, 21, 20 }, { 13, 20, 12 },
            { 14, 22, 21 }, { 14, 21, 13 }, { 14, 15, 23 }, { 14, 23, 22 }, { 16, 24, 23 }, { 16, 23, 15 }, { 9, 17, 24 }, { 9, 24, 16 }, { 18, 26, 25 }, { 18, 25, 17 },
            { 19, 27, 26 }, { 19, 26, 18 }, { 20, 28, 27 }, { 20, 27, 19 }, { 21, 29, 28 }, { 21, 28, 20 }, { 22, 30, 29 }, { 22, 29, 21 }, { 23, 31, 30 }, { 23, 30, 22 },
            { 24, 32, 31 }, { 24, 31, 23 }, { 17, 25, 32 }, { 17, 32, 24 }, { 26, 34, 33 }, { 26, 33, 25 }, { 27, 35, 34 }, { 27, 34, 26 }, { 28, 36, 35 }, { 28, 35, 27 },
            { 29, 37, 36 }, { 29, 36, 28 }, { 30, 38, 37 }, { 30, 37, 29 }, { 31, 39, 38 }, { 31, 38, 30 }, { 32, 40, 39 }, { 32, 39, 31 }, { 25, 33, 40 }, { 25, 40, 32 },
            { 33, 34, 42 }, { 33, 42, 41 }, { 35, 43, 42 }, { 35, 42, 34 }, { 36, 44, 43 }, { 36, 43, 35 }, { 37, 45, 44 }, { 37, 44, 36 }, { 38, 46, 45 }, { 38, 45, 37 },
            { 39, 47, 46 }, { 39, 46, 38 }, { 40, 48, 47 }, { 40, 47, 39 }, { 33, 41, 48 }, { 33, 48, 40 }, { 42, 50, 49 }, { 42, 49, 41 }, { 43, 51, 50 }, { 43, 50, 42 },
            { 44, 52, 51 }, { 44, 51, 43 }, { 45, 53, 52 }, { 45, 52, 44 }, { 46, 54, 53 }, { 46, 53, 45 }, { 47, 55, 54 }, { 47, 54, 46 }, { 47, 48, 56 }, { 47, 56, 55 },
            { 41, 49, 56 }, { 41, 56, 48 } };

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        if (entity instanceof EntityCutEndRange)
            doDriveRender((EntityCutEndRange)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return null;
    }

    private void doDriveRender(EntityCutEndRange entitySummonedBlade, double dX, double dY, double dZ, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        int color = entitySummonedBlade.getColor();
        boolean inverse = (color < 0);
        color = Math.abs(color);
        if (!inverse) {
            GL11.glBlendFunc(770, 1);
        } else {
            GL11.glBlendFunc(775, 0);
        }
        GL11.glPushMatrix();
        float scale = 0.48F;
        GL11.glScalef(scale / 2.0F, scale / 2.0F, scale / 2.0F);
        tessellator.startDrawing(4);
        tessellator.setColorRGBA_I(color, 255);
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
