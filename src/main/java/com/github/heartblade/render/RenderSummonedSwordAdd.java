/*     */ package com.github.heartblade.render;
/*     */ 
/*     */ import com.github.heartblade.Entityadd.EntitySummonedSwordAdd;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderSummonedSwordAdd
/*     */   extends Render
/*     */ {
/*  18 */   private static double[][] dVec = new double[][] { { 0.0D, 0.0D, 417.7431D }, { 0.0D, -44.6113D, 0.0D }, { 38.9907D, 0.0D, 50.0D }, { 0.0D, 44.6113D, 0.0D }, { -38.9907D, 0.0D, 50.0D }, { 38.9907D, 0.0D, -50.0D }, { -38.9907D, 0.0D, -50.0D }, { 0.0D, 0.0D, -214.0305D }, { 159.1439D, 0.0D, -49.6611D }, { -159.1439D, 0.0D, -49.6611D } };
/*  29 */   private static int[][] nVecPos = new int[][] { { 0, 2, 1 }, { 0, 3, 2 }, { 0, 4, 3 }, { 0, 1, 4 }, { 1, 5, 7 }, { 5, 3, 7 }, { 3, 6, 7 }, { 6, 1, 7 }, { 2, 8, 1 }, { 5, 8, 3 }, { 4, 9, 3 }, { 6, 9, 1 }, { 1, 8, 5 }, { 1, 9, 4 }, { 3, 8, 2 }, { 3, 9, 6 } };
/*     */   public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
/*  49 */     if (entity instanceof EntitySummonedSwordAdd) {
/*  50 */       doDriveRender((EntitySummonedSwordAdd)entity, d0, d1, d2, f, f1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity var1) {
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void doDriveRender(EntitySummonedSwordAdd entityPhantomSword, double dX, double dY, double dZ, float f, float f1) {
/*  61 */     Tessellator tessellator = Tessellator.instance;
/*  62 */     GL11.glDisable(3553);
/*  63 */     GL11.glDisable(2896);
/*  64 */     GL11.glEnable(3042);
/*  65 */     GL11.glBlendFunc(770, 1);
/*     */ 
/*     */ 
/*     */     
/*  69 */     GL11.glPushMatrix();
/*     */     
/*  71 */     GL11.glTranslatef((float)dX, (float)dY, (float)dZ);
/*  72 */     GL11.glRotatef(entityPhantomSword.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  73 */     GL11.glRotatef(-entityPhantomSword.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  74 */     GL11.glRotatef(entityPhantomSword.getRoll(), 0.0F, 0.0F, 1.0F);
/*     */ 
/*     */     
/*  77 */     float scale = 0.0045F;
/*  78 */     GL11.glScalef(scale, scale, scale);
/*  79 */     GL11.glScalef(0.5F, 0.5F, 1.0F);
/*     */ 
/*     */     
/*  82 */     float lifetime = entityPhantomSword.getLifeTime();
/*  83 */     float ticks = entityPhantomSword.ticksExisted;
/*  84 */     tessellator.startDrawing(4);
/*  85 */     tessellator.setColorRGBA_F(0.2F, 0.2F, 1.0F, 1.0F);
/*     */ 
/*     */     
/*  88 */     double dScale = 1.0D;
/*  89 */     for (int idx = 0; idx < nVecPos.length; idx++) {
/*     */       
/*  91 */       tessellator.addVertex(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale);
/*  92 */       tessellator.addVertex(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale);
/*  93 */       tessellator.addVertex(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale);
/*     */     } 
/*  95 */     tessellator.draw();
/*     */     
/*  97 */     GL11.glPopMatrix();
/*  98 */     GL11.glDisable(3042);
/*  99 */     GL11.glEnable(2896);
/* 100 */     GL11.glEnable(3553);
/*     */   }
/*     */ }


/* Location:              F:\forge-1.7.10-10.13.4.1614-1.7.10-src\万物皆可为兵刃r1修复版beta.1-deobf.jar!\com\moe\allweapon\render\RenderSummonedSwordAdd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */