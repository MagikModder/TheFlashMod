package fiskfille.flash.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import fiskfille.flash.SpeedsterManager;
import fiskfille.flash.common.event.CommonEventHandler;
import fiskfille.flash.common.item.FlashItems;
import fiskfille.flash.common.network.FlashNetworkManager;
import fiskfille.flash.common.recipe.FlashRecipes;

public class CommonProxy
{
	public void preInit()
	{
		FlashItems.register();
		FlashRecipes.register();
		SpeedsterManager.register();
		FlashNetworkManager.registerPackets();
		
		registerEventHandler(new CommonEventHandler());
	}
	
	public void init()
	{
		
	}
	
	public void registerEventHandler(Object obj)
	{
		MinecraftForge.EVENT_BUS.register(obj);
        FMLCommonHandler.instance().bus().register(obj);
	}
	
	public EntityPlayer getPlayer()
	{
		return null;
	}
}