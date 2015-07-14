package fiskfille.flash.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.flash.FlashReflection;
import fiskfille.flash.client.render.entity.RenderStreak;
import fiskfille.flash.client.render.item.RenderItemFlashRing;
import fiskfille.flash.common.entity.EntityStreak;
import fiskfille.flash.common.event.ClientEventHandler;
import fiskfille.flash.common.item.FlashItems;
import fiskfille.flash.common.keybinds.FlashKeyBinds;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		super.preInit();
		FlashReflection.client();
		FlashKeyBinds.register();
		
		registerEventHandler(new ClientEventHandler());
//		RobotHelper.createSession();
	}
	
	public void init()
	{
		super.init();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStreak.class, new RenderStreak());
		
		MinecraftForgeClient.registerItemRenderer(FlashItems.theFlashRing, new RenderItemFlashRing());
	}
	
	public EntityPlayer getPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}