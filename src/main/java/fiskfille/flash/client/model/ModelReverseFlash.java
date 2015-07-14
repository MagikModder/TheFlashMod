package fiskfille.flash.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.flash.Flash;
import fiskfille.flash.common.speedster.SpeedsterReverseFlash;
import fiskfille.flash.common.utils.FlashHelper;

public class ModelReverseFlash extends ModelBipedMultiLayer
{
	public ModelReverseFlash()
	{
		super(0.1F, 0.4F);
	}
	
	private void renderSuit(Entity entity)
	{
		float f5 = 0.0625F;
		this.bipedLeftArm.render(f5);
		this.bipedBody.render(f5);
		this.bipedLeftArmL2.render(f5);
		this.bipedHeadwear.render(f5);
		this.bipedEars.render(f5);
		this.bipedRightArmL2.render(f5);
		this.bipedLeftLeg.render(f5);
		this.bipedRightArm.render(f5);
		this.bipedLeftLegL2.render(f5);
		this.bipedRightLeg.render(f5);
		this.bipedBodyL2.render(f5);
		this.bipedHead.render(f5);
		this.bipedRightLegL2.render(f5);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{ 
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		renderSuit(entity);
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			
			if (FlashHelper.isPlayerSpeedster(player))
			{
				for (int i = 0; i < 10; ++i)
				{
					GL11.glPushMatrix();
					GL11.glColor4f(1, 1, 1, 0.2F);
		            GL11.glDepthMask(false);
		            GL11.glEnable(GL11.GL_BLEND);
		            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
		            GL11.glTranslatef(SpeedsterReverseFlash.reverseFlashBlurOffsetsX[i], SpeedsterReverseFlash.reverseFlashBlurOffsetsY[i], SpeedsterReverseFlash.reverseFlashBlurOffsetsZ[i]);
		            renderSuit(entity);
		            renderEyes(entity);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		            GL11.glPopMatrix();
		            GL11.glDepthMask(true);
				}
				
//				GL11.glPushMatrix();
//	            GL11.glDepthMask(false);
//	            GL11.glEnable(GL11.GL_BLEND);
//	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//	            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
//	            renderEyes(entity);
//	            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
//	            GL11.glPopMatrix();
//	            GL11.glDepthMask(true);
			}
		}
	}
	
	public void renderEyes(Entity entity)
	{
		int j = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Flash.modid, "textures/models/armor/reverse_flash_eyes.png"));
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1, 1, 1, 0.6F);
		setLighting(61680);
		bipedHead.render(0.0625F);
		bipedHeadwear.render(0.0625F);
		setLighting(entity.getBrightnessForRender(0.0625F));
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, j);
	}
	
	public void setLighting(int c0)
	{
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
	}
}
