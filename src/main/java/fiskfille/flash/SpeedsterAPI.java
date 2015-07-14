package fiskfille.flash;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fiskfille.flash.common.speedster.Speedster;

/**
 * @author FiskFille
 */
public class SpeedsterAPI
{
    private static List<Speedster> speedsters = new ArrayList<Speedster>();
    
    /**
     * Used to register the specified speedster.
     * 
     * @param speedster The speedster registered.
     */
    public static void registerSpeedster(Speedster speedster)
    {
        if (!speedsters.contains(speedster))
        {
            speedsters.add(speedster);
        }
        else
        {
        	Logger.getLogger("[SpeedsterAPI]").log(Level.WARNING, speedster.getName() + " has already been registered!");
        }
    }
    
    /**
     * @returns a list of registered speedsters.
     */
    public static List<Speedster> getSpeedsters()
    {
        return speedsters;
    }
}
