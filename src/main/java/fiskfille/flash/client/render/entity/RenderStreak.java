package fiskfille.flash.client.render.entity;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import fiskfille.flash.common.entity.EntityStreak;
import fiskfille.flash.common.speedster.Speedster;
import fiskfille.flash.common.utils.FlashHelper;

public class RenderStreak extends Render
{
    public RenderStreak()
    {
    	super();
        shadowSize = 0.0F;
    }

    public void renderStreak(EntityStreak streak, double x, double y, double z, float f, float partialTicks)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	
    	if (streak.isEntityAlive())
    	{
    		EntityPlayer player = (EntityPlayer)streak.parent;
    		Speedster speedster = FlashHelper.getSpeedster(player);
    		
    		if (speedster != null)
    		{
    			Color blurColor = speedster.getTrailBlurColor();
            	Color flashColor = speedster.getTrailFlashColor();
        		
    			GL11.glPushMatrix();
                Tessellator tessellator = Tessellator.instance;
                Entity entity = streak.streak;
                
                if (entity != null && (entity != mc.thePlayer || entity == mc.thePlayer && mc.gameSettings.thirdPersonView > 0))
                {            	
                    Vec3 vec3 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
                    vec3.rotateAroundX(-(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks) * (float)Math.PI / 180.0F);
                    vec3.rotateAroundY(-(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks) * (float)Math.PI / 180.0F);
                    double playerPosX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks + vec3.xCoord;
                    double playerPosY = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + vec3.yCoord;
                    double playerPosZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks + vec3.zCoord;
                    double yOffset = (entity == Minecraft.getMinecraft().thePlayer ? 0.0D : (double)entity.getEyeHeight()) + 0.1D;
                    
                    if (renderManager.options.thirdPersonView > 0 || entity != Minecraft.getMinecraft().thePlayer)
                    {
                        double side = 0;
                        double forward = 0;
                        playerPosX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks - forward * 0.35D - side * 0.85D;
                        playerPosY = entity.prevPosY + yOffset + (entity.posY - entity.prevPosY) * (double)partialTicks - 0.45D;
                        playerPosZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks - side * 0.35D + forward * 0.85D;
                    }
                    
                    double entityPosX = streak.prevPosX + (streak.posX - streak.prevPosX) * (double)partialTicks;
                    double entityPosY = streak.prevPosY + (streak.posY - streak.prevPosY) * (double)partialTicks - 0.35D;
                    double entityPosZ = streak.prevPosZ + (streak.posZ - streak.prevPosZ) * (double)partialTicks;
                    double diffPosX = (double)((float)(playerPosX - entityPosX));
                    double diffPosY = (double)((float)(playerPosY - entityPosY));
                    double diffPosZ = (double)((float)(playerPosZ - entityPosZ));
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    int height = 10;
                    int density = 12;

                	for (int j1 = -height; j1 <= height; ++j1)
                    {
                		tessellator.startDrawing(3);
                		GL11.glEnable(GL11.GL_BLEND);
                        byte segments = 1;
                        
                    	for (int i = 0; i <= segments; ++i)
                        {
                    		Random rand = new Random();
                    		double multiplier = 0.0D;
                    		double x1 = (rand.nextDouble() - 0.5D) * multiplier;
                    		double y1 = (rand.nextDouble() - 0.5D) * multiplier;
                    		double z1 = (rand.nextDouble() - 0.5D) * multiplier;
                    		
                            double f1 = (float)i / (float)segments;
                            tessellator.setColorRGBA(flashColor.getRed(), flashColor.getGreen(), flashColor.getBlue(), (int)((10 - streak.ticksExisted) * 25));
                            tessellator.addVertex(x + diffPosX * f1 + x1, (float)j1 / density + y + diffPosY * (f1 * f1 + f1) * 0.5D + y1, z + diffPosZ * f1 + z1);
                        }
                    	
                    	tessellator.draw();
                    }
                	
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }
                
                GL11.glPopMatrix();
                
                
                
                Render render = renderManager.getEntityRenderObject(player);
                
                if (render instanceof RenderPlayer && (player != mc.thePlayer || player == mc.thePlayer && mc.gameSettings.thirdPersonView > 0))
                {
                	ModelBase biped = ((RenderPlayer)render).modelBipedMain;
            		
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)x, (float)y + 0.6F - (player != Minecraft.getMinecraft().thePlayer ? 0.2F : 0), (float)z);
                    GL11.glRotatef(player.renderYawOffset, 0.0F, -1.0F, 0.0F);
                    
                    float scale = 0.9375F;
                    GL11.glScalef(scale, -scale, -scale);
                    
                    float f2 = player.renderYawOffset;
                    float f3 = player.rotationYawHead;
                    float f7 = player.limbSwingAmount;
                    float f8 = player.limbSwing - player.limbSwingAmount;

                    if (f7 > 1.0F)
                    {
                        f7 = 1.0F;
                    }

                    float f4 = 0.0F;
                    float f5 = player.rotationPitch;
                    
                    float alpha = (float)(10 - streak.ticksExisted) / 10 / 6;
                    GL11.glColor4f((float)blurColor.getRed() / 255, (float)blurColor.getGreen() / 255, (float)blurColor.getBlue() / 255, alpha);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    biped.render(player, f8, f7, f4, f3 - f2, f5, 0.0625F);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glDepthMask(true);
                    
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glPopMatrix();
                }
    		}
    	}
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderStreak((EntityStreak)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation("textures/entity/arrow.png");
    }
}
