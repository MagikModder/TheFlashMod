package fiskfille.flash.common.utils;

import java.util.List;

import javax.xml.transform.Transformer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import fiskfille.flash.common.data.DataManager;
import fiskfille.flash.common.item.armor.ItemSpeedsterArmor;
import fiskfille.flash.common.speedster.Speedster;
import fiskfille.flash.common.speedster.SpeedsterReverseFlash;
import fiskfille.utils.common.time.Tickrate;

public class FlashHelper
{
	public static boolean isPlayerSpeedster(EntityPlayer player)
    {
        Speedster helmetSpeedster = getSpeedsterFromArmor(player, 3);
        Speedster chestSpeedster = getSpeedsterFromArmor(player, 2);
        Speedster legsSpeedster = getSpeedsterFromArmor(player, 1);
        Speedster feetSpeedster = getSpeedsterFromArmor(player, 0);
        
        return helmetSpeedster != null && helmetSpeedster == chestSpeedster && chestSpeedster == legsSpeedster && legsSpeedster == feetSpeedster;
    }
    
    public static Speedster getSpeedster(EntityPlayer player)
    {
        if (player != null && isPlayerSpeedster(player))
        {
            return getSpeedsterFromArmor(player, 0);
        }
        
        return null;
    }
    
    public static Speedster getSpeedsterFromArmor(EntityPlayer player, int slot)
    {
        ItemStack currentArmorStack = player.getCurrentArmor(slot);
        
        if (currentArmorStack != null)
        {
            Item currentArmor = currentArmorStack.getItem();
            
            if (currentArmor instanceof ItemSpeedsterArmor)
            {
                return ((ItemSpeedsterArmor)currentArmor).getSpeedster();
            }
        }
        
        return null;
    }

	public static boolean canRunSuperSpeed(EntityPlayer player)
	{
		return isPlayerSpeedster(player);
	}
	
	public static boolean areAllPlayersSlowMotion(World world)
	{
		List<EntityPlayer> players = world.playerEntities;
		boolean flag = true;
		
		for (EntityPlayer player : players)
		{
			if (!DataManager.getIsSpeeding(player) || !canRunSuperSpeed(player) || !DataManager.getInSlowMotion(player) || !player.isEntityAlive())
			{
				flag = false;
			}
		}
		
		return flag;
	}
}
