package fiskfille.flash;

import fiskfille.flash.common.speedster.Speedster;
import fiskfille.flash.common.speedster.SpeedsterFlash;
import fiskfille.flash.common.speedster.SpeedsterReverseFlash;

public class SpeedsterManager
{
	public static Speedster speedsterFlash = new SpeedsterFlash();
	public static Speedster speedsterReverseFlash = new SpeedsterReverseFlash();
	
	public static void register()
    {
		SpeedsterAPI.registerSpeedster(speedsterFlash);
		SpeedsterAPI.registerSpeedster(speedsterReverseFlash);
    }
}
