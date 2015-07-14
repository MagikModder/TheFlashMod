package fiskfille.flash.client.render.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import fiskfille.flash.client.render.entity.RenderPlayerHand;

public class RenderItemFlashRing implements IItemRenderer
{
	private RenderPlayerHand renderPlayerHand = new RenderPlayerHand();
	
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return false;
    }
    
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }
    
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
        	EntityPlayer player = (EntityPlayer)data[1];
        	Minecraft.getMinecraft().getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
        	
        	GL11.glPushMatrix();
        	
        	
        	
        	
        	new ModelBiped().bipedRightArm.render(0.0625F);
        	GL11.glPopMatrix();
        }
    }
}
