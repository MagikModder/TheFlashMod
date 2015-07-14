package fiskfille.flash.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import fiskfille.flash.client.model.ModelBipedMultiLayer;
import fiskfille.flash.common.speedster.Speedster;
import fiskfille.flash.common.utils.FlashHelper;

public class RenderPlayerHand extends RenderPlayer
{
	private Minecraft mc = Minecraft.getMinecraft();
    public Render parent;

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
    	if (parent instanceof RenderPlayer)
    	{
    		((RenderPlayer)parent).renderFirstPersonArm(player);
    	}
    	else
    	{
    		super.renderFirstPersonArm(player);
    	}
        
    	ItemStack itemstack = player.getCurrentArmor(2);
    	
    	if (itemstack != null)
    	{
    		Speedster speedster = FlashHelper.getSpeedsterFromArmor(player, 2); 
    		
    		if (speedster != null)
    		{
    			bindTexture(speedster.getTexture(itemstack, player, 1, null));
    			ModelBipedMultiLayer model = (ModelBipedMultiLayer) speedster.getModel();
    			ModelRenderer arm = modelBipedMain.bipedRightArm;
    			
    			if (parent instanceof RenderPlayer)
    	    	{
    	    		arm = ((RenderPlayer)parent).modelBipedMain.bipedRightArm;
    	    	}
    			
    			float f = 1.0F;
    	        GL11.glColor3f(f, f, f);
    	        model.onGround = 0.0F;
    	        model.sync(arm, model.bipedRightArm);
    	        model.sync(arm, model.bipedRightArmL2);
    	        model.bipedRightArm.render(0.0625F);
    	        model.bipedRightArmL2.render(0.0625F);
    		}
    	}
    }

    public void setParent(Render render)
    {
        parent = render;
    }
}