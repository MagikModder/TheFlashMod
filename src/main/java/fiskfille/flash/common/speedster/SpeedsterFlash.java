package fiskfille.flash.common.speedster;

import java.awt.Color;

import fiskfille.flash.Flash;
import fiskfille.flash.client.model.ModelBipedMultiLayer;
import fiskfille.flash.common.item.FlashItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SpeedsterFlash extends Speedster
{
	private ModelBiped model = new ModelBipedMultiLayer(0.1F, 0.4F);
	
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
			return new ResourceLocation(Flash.modid, "textures/models/armor/flash_layer2.png");
		}
		else
		{
			return new ResourceLocation(Flash.modid, "textures/models/armor/flash_layer1.png");
		}
	}
	
	@Override
	public String getName()
	{
		return "Flash";
	}
	
	@Override
	public Color getTrailBlurColor()
	{
		return new Color(0xFF2700);
	}
	
	@Override
	public Color getTrailFlashColor()
	{
		return new Color(0xFFA300);
	}
	
	@Override
	public Item getHelmet()
	{
		return FlashItems.flashHelmet;
	}

	@Override
	public Item getChestplate()
	{
		return FlashItems.flashChestplate;
	}

	@Override
	public Item getLeggings()
	{
		return FlashItems.flashLeggings;
	}

	@Override
	public Item getBoots()
	{
		return FlashItems.flashBoots;
	}
}
