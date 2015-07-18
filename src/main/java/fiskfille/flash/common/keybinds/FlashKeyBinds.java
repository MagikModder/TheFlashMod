package fiskfille.flash.common.keybinds;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class FlashKeyBinds
{
	public static FlashKeyBinding keyBindingSuperSpeed = new FlashKeyBinding("Toggle Super Speed", Keyboard.KEY_N);
	public static FlashKeyBinding keyBindingSlowMotion = new FlashKeyBinding("Toggle Slow Motion Vision", Keyboard.KEY_M);

	public static void register()
	{
		ClientRegistry.registerKeyBinding(keyBindingSuperSpeed);
		ClientRegistry.registerKeyBinding(keyBindingSlowMotion);
	}
}
 