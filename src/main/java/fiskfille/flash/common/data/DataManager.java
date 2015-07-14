package fiskfille.flash.common.data;

import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.Maps;

import fiskfille.flash.common.entity.EntityStreak;
import fiskfille.flash.common.network.FlashNetworkManager;
import fiskfille.flash.common.network.PacketSetSlowMotion;
import fiskfille.flash.common.network.PacketSetSpeeding;

public class DataManager
{
	public static Map<String, EntityStreak> newestStreak = Maps.newHashMap();
	public static Map<String, Double> speed = Maps.newHashMap();
	public static Map<String, Boolean> isSpeeding = Maps.newHashMap();
	public static Map<String, Boolean> inSlowMotion = Maps.newHashMap();
	
	public static void setNewestStreak(EntityPlayer player, EntityStreak streak)
	{
		newestStreak.put(player.getUniqueID().toString(), streak);
	}
	
	public static EntityStreak getNewestStreak(EntityPlayer player)
	{
		String s = player.getUniqueID().toString();
		return newestStreak.get(s);
	}
	
    private static void initMap(Map map, Object key, Object value)
    {
    	if (map.get(key) == null)
    	{
    		map.put(key, value);
    	}
    }
    
    public static double getSpeed(EntityPlayer player)
    {
    	if (player.worldObj.isRemote)
    	{
        	double diffX = (player.posX - player.lastTickPosX);
            double diffY = (player.posY - player.lastTickPosY);
            double diffZ = (player.posZ - player.lastTickPosZ);
            double blocksMoved = Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ));
            double speed1 = (double) (blocksMoved / 50) * 60 * 60;
            return speed1;
    	}
    	
    	String s = player.getUniqueID().toString();
    	initMap(speed, s, 0);
    	return speed.get(s);
    }
    
	public static void setSpeed(EntityPlayer player)
	{
		double diffX = (player.posX - player.lastTickPosX);
        double diffY = (player.posY - player.lastTickPosY);
        double diffZ = (player.posZ - player.lastTickPosZ);
        double blocksMoved = Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ));
        double speed1 = (double) (blocksMoved / 50) * 60 * 60;
        
        speed.put(player.getUniqueID().toString(), speed1);
	}
	
	public static void setIsSpeedingWithoutNotify(EntityPlayer player, boolean speeding)
    {
		isSpeeding.put(player.getUniqueID().toString(), speeding);
    }
	
	public static void setIsSpeeding(EntityPlayer player, boolean speeding)
    {
		if (player.worldObj.isRemote)
        {
            FlashNetworkManager.networkWrapper.sendToServer(new PacketSetSpeeding(player, speeding));
        }
        else
        {
            FlashNetworkManager.networkWrapper.sendToDimension(new PacketSetSpeeding(player, speeding), player.dimension);
        }
		
		isSpeeding.put(player.getUniqueID().toString(), speeding);
		setNewestStreak(player, null);
    }
	
	public static boolean getIsSpeeding(EntityPlayer player)
	{
		initMap(isSpeeding, player.getUniqueID().toString(), false);
		return isSpeeding.get(player.getUniqueID().toString());
	}
	
	public static void setInSlowMotionWithoutNotify(EntityPlayer player, boolean slowMotion)
    {
		inSlowMotion.put(player.getUniqueID().toString(), slowMotion);
    }
	
	public static void setInSlowMotion(EntityPlayer player, boolean slowMotion)
    {
		if (player.worldObj.isRemote)
        {
            FlashNetworkManager.networkWrapper.sendToServer(new PacketSetSlowMotion(player, slowMotion));
        }
        else
        {
            FlashNetworkManager.networkWrapper.sendToDimension(new PacketSetSlowMotion(player, slowMotion), player.dimension);
        }
		
		inSlowMotion.put(player.getUniqueID().toString(), slowMotion);
    }
	
	public static boolean getInSlowMotion(EntityPlayer player)
	{
		initMap(inSlowMotion, player.getUniqueID().toString(), false);
		return inSlowMotion.get(player.getUniqueID().toString());
	}
}
