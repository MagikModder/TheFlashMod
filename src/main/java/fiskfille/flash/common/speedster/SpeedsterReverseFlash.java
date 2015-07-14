package fiskfille.flash.common.speedster;

import java.awt.Color;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import fiskfille.flash.Flash;
import fiskfille.flash.client.model.ModelReverseFlash;
import fiskfille.flash.common.item.FlashItems;
import fiskfille.flash.common.utils.FlashHelper;

public class SpeedsterReverseFlash extends Speedster
{
	public static float[] reverseFlashBlurOffsetsX = new float[10];
	public static float[] reverseFlashBlurOffsetsY = new float[10];
	public static float[] reverseFlashBlurOffsetsZ = new float[10];
	
	private ModelBiped model = new ModelReverseFlash();
	
	@Override
	public ModelBiped getModel()
	{
		return model;
	}
	
	@Override
	public ResourceLocation getTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (slot == 2)
		{
			return new ResourceLocation(Flash.modid, "textures/models/armor/reverse_flash_layer2.png");
		}
		else
		{
			return new ResourceLocation(Flash.modid, "textures/models/armor/reverse_flash_layer1.png");
		}
	}
	
	@Override
	public String getName()
	{
		return "Reverse Flash";
	}
	
	@Override
	public Color getTrailBlurColor()
	{
		return new Color(0xFF5200);
	}
	
	@Override
	public Color getTrailFlashColor()
	{
		return new Color(0xFF0000);
	}

	@Override
	public Item getHelmet()
	{
		return FlashItems.reverseFlashHelmet;
	}

	@Override
	public Item getChestplate()
	{
		return FlashItems.reverseFlashChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return FlashItems.reverseFlashLeggings;
	}

	@Override
	public Item getBoots()
	{
		return FlashItems.reverseFlashBoots;
	}
}
