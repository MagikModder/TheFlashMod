package fiskfille.utils.common.time;

import java.lang.reflect.Field;

public class Tickrate
{
	private static Field clientTimer = null;
	public static float CLIENT_TICKRATE = 20.0F;
	public static long MILISECONDS_PER_TICK = 50L;
	
    public static void updateClientTickrate(float tickrate) 
    {
    	net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
        
        try
        {
        	if (tickrate != CLIENT_TICKRATE)
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
                CLIENT_TICKRATE = tickrate;
                updateServerTickrate(tickrate);
        	}
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void updateServerTickrate(float tickrate) 
    {
    	MILISECONDS_PER_TICK = (long)(1000L / tickrate);
    }
}
