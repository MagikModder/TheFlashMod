package fiskfille.flash.common.speedster;

import java.awt.Color;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class Speedster
{
	public abstract ModelBiped getModel();
	
	public abstract ResourceLocation getTexture(ItemStack stack, Entity entity, int slot, String type);
	
	public abstract String getName();
	
	public abstract Color getTrailBlurColor();

	public abstract Color getTrailFlashColor();

	public abstract Item getHelmet();
	public abstract Item getChestplate();
	public abstract Item getLeggings();
	public abstract Item getBoots();
}
