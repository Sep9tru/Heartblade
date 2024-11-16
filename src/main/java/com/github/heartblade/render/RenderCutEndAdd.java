package com.github.heartblade.render;

import com.github.heartblade.Entityadd.EntityCutEndAdd;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCutEndAdd extends Render {
    private static double[][] dVec = new double[][] {
            { 2.0E-4D, -0.033599999D, -2.0E-4D }, { 0.0572D, -0.033599999D, 0.3989D }, { -0.034600001D, -0.023282379D, 0.85737836D }, { -0.057700001D, -0.033599999D, 0.3996D }, { 2.0E-4D, 0.46270001D, -0.066500001D }, { 0.049199998D, 0.59069997D, 0.2432D }, { -0.0073000002D, 0.77200001D, 0.58697593D }, { -0.050000001D, 0.59069997D, 0.2437D }, { 2.0E-4D, 1.3645999D, -0.35452089D }, { 0.025699999D, 1.4586999D, -0.1495D },
            { 2.0E-4D, 1.2005913D, 0.41285828D }, { -0.031199999D, 1.467D, -0.14650001D }, { 2.0E-4D, 2.2442999D, -0.25792262D }, { 2.0E-4D, 2.1234D, -0.61365759D }, { 2.0E-4D, 3.1021848D, -0.9228D }, { 2.0E-4D, -0.033599999D, -2.0E-4D }, { 0.0572D, -0.033599999D, 0.3989D }, { -0.034600001D, -0.011D, 0.84394145D }, { -0.057700001D, -0.033599999D, 0.3996D }, { 2.0E-4D, -0.80409998D, -0.070299998D },
            { 0.049199998D, -0.77819997D, 0.2384D }, { 2.0E-4D, -0.78579998D, 0.66593587D }, { -0.0493D, -0.77759999D, 0.23899999D }, { 2.0E-4D, -1.6982931D, -0.30720001D }, { 0.029100001D, -1.9813D, -0.1785D }, { 2.0E-4D, -1.7744334D, 0.18211585D }, { -0.0287D, -1.9813D, -0.1785D }, { 2.0E-4D, -2.5529001D, -0.66720003D }, { 2.0E-4D, -2.4138D, -0.78250003D }, { 2.0E-4D, -2.7284062D, -0.98678678D } };

    private static int[][] nVecPos = new int[][] {
            { 0, 1, 5, 4 }, { 1, 2, 6, 5 }, { 2, 3, 7, 6 }, { 3, 0, 4, 7 }, { 4, 5, 9, 8 }, { 5, 6, 10, 9 }, { 6, 7, 11, 10 }, { 7, 4, 8, 11 }, { 14, 11, 8, 13 }, { 10, 11, 14, 12 },
            { 8, 9, 14, 13 }, { 10, 12, 14, 9 }, { 15, 19, 20, 16 }, { 16, 20, 21, 17 }, { 17, 21, 22, 18 }, { 18, 22, 19, 15 }, { 19, 23, 24, 20 }, { 20, 24, 25, 21 }, { 21, 25, 26, 22 }, { 22, 26, 23, 19 },
            { 29, 28, 23, 26 }, { 25, 27, 29, 26 }, { 23, 28, 29, 24 }, { 25, 24, 29, 27 } };

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        if (entity instanceof EntityCutEndAdd)
            doDriveRender((EntityCutEndAdd)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return null;
    }

    private void doDriveRender(EntityCutEndAdd entityDrive, double dX, double dY, double dZ, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)dX, (float)dY, (float)dZ);
        GL11.glRotatef(entityDrive.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-entityDrive.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(entityDrive.getRoll(), 0.0F, 0.0F, 1.0F);
        GL11.glScalef(1.0F,3.0F,1.0F);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(entityDrive.getColor(), 255);
        double dScale = 1.0D;
        for (int idx = 0; idx < nVecPos.length; idx++) {
            tessellator.addVertex(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][3]][0] * dScale, dVec[nVecPos[idx][3]][1] * dScale, dVec[nVecPos[idx][3]][2] * dScale);
        }
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
}
