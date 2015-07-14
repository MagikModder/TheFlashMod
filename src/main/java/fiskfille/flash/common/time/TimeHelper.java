package fiskfille.flash.common.time;

import java.lang.reflect.Field;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import fiskfille.flash.common.network.FlashNetworkManager;
import fiskfille.flash.common.network.PacketSetTickspeed;
import fiskfille.utils.common.time.Tickrate;

public class TimeHelper
{
	private static Field clientTimer = null;

	public static void updateClientTickrate(float tickrate) 
	{
		net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();

		try
		{
			if (tickrate != Tickrate.CLIENT_TICKRATE)
			{
				if (clientTimer == null)
				{
					for (Field f : mc.getClass().getDeclaredFields())
					{
						if (f.getType() == net.minecraft.util.Timer.class)
						{
							clientTimer = f;
							clientTimer.setAccessible(true);
							break;
						}
					}
				}

				net.minecraft.util.Timer timer = (net.minecraft.util.Timer)clientTimer.get(mc);
				timer.timerSpeed = tickrate / 20F;
				Tickrate.CLIENT_TICKRATE = tickrate;
				updateServerTickrate(tickrate);

				FlashNetworkManager.networkWrapper.sendToServer(new PacketSetTickspeed(tickrate));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void updateServerTickrate(float tickrate) 
	{
		Tickrate.MILISECONDS_PER_TICK = (long)(1000L / tickrate);
	}
}
