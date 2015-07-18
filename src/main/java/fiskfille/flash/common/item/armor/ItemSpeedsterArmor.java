package fiskfille.flash.common.item.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.flash.Flash;
import fiskfille.flash.common.speedster.Speedster;

public abstract class ItemSpeedsterArmor extends ItemArmor
{
    public ItemSpeedsterArmor(ArmorMaterial material, int renderIndex, int armorPiece)
    {
        super(material, renderIndex, armorPiece);
    }
    
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return getSpeedster().getTexture(stack, entity, slot, type).toString();
    }
    
    public abstract Speedster getSpeedster();
    
    
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY)
    {
    	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		
		if (res != null)
		{
	        Tessellator tessellator = Tessellator.instance;

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.6F);
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV(0.0D, (double)height, -90.0D, 0.0D, 1.0D);
	        tessellator.addVertexWithUV((double)width, (double)height, -90.0D, 1.0D, 1.0D);
	        tessellator.addVertexWithUV((double)width, 0.0D, -90.0D, 1.0D, 0.0D);
	        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
	        tessellator.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        
			GL11.glEnable(GL11.GL_BLEND);
	    	GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glDepthMask(false);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        float f = 0.5F;
	        GL11.glColor4f(f, f, f, 1.0F);
	        GL11.glDisable(GL11.GL_ALPHA_TEST);
	        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Flash.modid, "textures/gui/flash.png"));
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV(0.0D, (double)height, -90.0D, 0.0D, 1.0D);
	        tessellator.addVertexWithUV((double)width, (double)height, -90.0D, 1.0D, 1.0D);
	        tessellator.addVertexWithUV((double)width, 0.0D, -90.0D, 1.0D, 0.0D);
	        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
	        tessellator.draw();
	        GL11.glDepthMask(true);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);	        
		}
    }
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot)
    {
        ModelBiped armorModel = getSpeedster().getModel();
        
        if (itemstack != null)
        {
            if (armorModel != null)
            {
                armorModel.bipedHead.showModel = armorSlot == 0;
                armorModel.bipedHeadwear.showModel = armorSlot == 0;
                armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
                armorModel.bipedRightArm.showModel = armorSlot == 1;
                armorModel.bipedLeftArm.showModel = armorSlot == 1;
                armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
                armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
                
                armorModel.isSneak = entityLiving.isSneaking();
                armorModel.isRiding = entityLiving.isRiding();
                armorModel.isChild = entityLiving.isChild();
                armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
                
                if (entityLiving instanceof EntityPlayer)
                {
                    ItemStack heldItem = entityLiving.getHeldItem();
                    armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 0 && heldItem != null && heldItem.getItemUseAction() == EnumAction.bow;
                    armorModel.heldItemRight = ((EntityPlayer) entityLiving).getItemInUseDuration() > 0 && heldItem != null && heldItem.getItemUseAction() == EnumAction.block ? 3 : (entityLiving.getEquipmentInSlot(0) != null ? 1 : 0);
                }
                
                return armorModel;
            }
        }
        
        return null;
    }
}
