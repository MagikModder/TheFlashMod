package fiskfille.flash.client.sound;

import net.minecraft.util.ResourceLocation;
import fiskfille.flash.Flash;

public class FlashSoundHandler
{
	public static SoundFlash slowMotion = SoundFlash.makeSound(new ResourceLocation(Flash.modid, "slow-mo"), true, 10.0F, 0.5F);
}
