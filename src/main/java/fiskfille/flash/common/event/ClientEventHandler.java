package fiskfille.flash.common.event;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fiskfille.flash.FlashReflection;
import fiskfille.flash.client.render.entity.RenderPlayerHand;
import fiskfille.flash.client.sound.SoundFlash;
import fiskfille.flash.common.data.DataManager;
import fiskfille.flash.common.keybinds.FlashKeyBinds;
import fiskfille.flash.common.speedster.SpeedsterReverseFlash;
import fiskfille.flash.common.time.TimeHelper;
import fiskfille.flash.common.utils.FlashHelper;
import fiskfille.utils.common.time.Tickrate;

public class ClientEventHandler
{
	private Minecraft mc = Minecraft.getMinecraft();
	public RenderPlayerHand renderHandInstance;
	public float renderTick;
	
	private boolean keyBindingSuperSpeedPressed = false;
	private boolean keyBindingSlowMotionPressed = false;
	
	public ClientEventHandler()
	{
		renderHandInstance = new RenderPlayerHand();
		renderHandInstance.setRenderManager(RenderManager.instance);
	}
	
	@SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {
        EntityPlayer player = mc.thePlayer;
        
        if (mc.currentScreen == null)
        {
        	if (FlashKeyBinds.keyBindingSuperSpeed.getIsKeyPressed())
            {
        		if (!keyBindingSuperSpeedPressed)
        		{
        			keyBindingSuperSpeedPressed = true;
        			DataManager.setIsSpeeding(player, !DataManager.getIsSpeeding(player));
        		}
            }
        	else
        	{
        		keyBindingSuperSpeedPressed = false;
        	}
        	
        	if (FlashKeyBinds.keyBindingSlowMotion.getIsKeyPressed())
            {
        		if (!keyBindingSlowMotionPressed)
        		{
        			keyBindingSlowMotionPressed = true;
        			DataManager.setInSlowMotion(player, !DataManager.getInSlowMotion(player));
        		}
            }
        	else
        	{
        		keyBindingSlowMotionPressed = false;
        	}
        }
    }

	@SubscribeEvent
	public void onPlayerUpdate(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.worldObj;
		
		if (FlashHelper.areAllPlayersSlowMotion(world))
		{
			TimeHelper.updateClientTickrate(5);
		}
		else
		{
			TimeHelper.updateClientTickrate(20);
		}
		
		for (int i = 0; i < 10; ++i)
		{
			Random rand = new Random();
			float multiplier = 0.065F;
			SpeedsterReverseFlash.reverseFlashBlurOffsetsX[i] = (rand.nextFloat() - 0.5F) * multiplier;
			SpeedsterReverseFlash.reverseFlashBlurOffsetsY[i] = (rand.nextFloat() - 0.5F) * multiplier;
			SpeedsterReverseFlash.reverseFlashBlurOffsetsZ[i] = (rand.nextFloat() - 0.5F) * multiplier;
		}
		
		if (event.phase == TickEvent.Phase.END)
		{
			DataManager.setSpeed(player);
		}
	}
	
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = event.entityPlayer;
		
		if (FlashHelper.getSpeedsterFromArmor(player, 3) != null)
		{
			event.renderer.modelBipedMain.bipedHeadwear.offsetY = 256;
		}
		else
		{
			event.renderer.modelBipedMain.bipedHeadwear.offsetY = 0;
		}
	}
	
	@SubscribeEvent
	public void onSound(PlaySoundEvent17 event)
	{
		if (mc.thePlayer != null)
		{
			if (FlashHelper.areAllPlayersSlowMotion(mc.theWorld))
			{
				ISound sound = event.sound;
				float pitchMultiplier = Tickrate.CLIENT_TICKRATE / 20F;
				event.result = new SoundFlash(sound.getPositionedSoundLocation(), sound.getVolume(), sound.getPitch() * pitchMultiplier, sound.canRepeat(), sound.getRepeatDelay(), sound.getAttenuationType(), sound.getXPosF(), sound.getYPosF(), sound.getZPosF());
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRenderHand(RenderHandEvent event)
	{
		EntityClientPlayerMP player = mc.thePlayer;
		Object renderObj = RenderManager.instance.entityRenderMap.get(player.getClass());
		
		if (renderObj != null && FlashHelper.getSpeedsterFromArmor(player, 2) != null)
		{
			event.setCanceled(true);
			Render rend = RenderManager.instance.getEntityRenderObject(player);
			
			GL11.glPushMatrix();
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			renderHandInstance.setParent(rend);
			RenderManager.instance.entityRenderMap.put(player.getClass(), renderHandInstance);

			FlashReflection.renderHand(mc.entityRenderer, event.partialTicks, 0);

			RenderManager.instance.entityRenderMap.put(player.getClass(), rend);
			GL11.glPopMatrix();
		}
	}
	
//	@SubscribeEvent
//	public void onChatReceived(ClientChatReceivedEvent event)
//	{
//		EntityPlayer player = mc.thePlayer;
//		IChatComponent message = event.message;
//		
//		String s = message.getUnformattedText();
//		String s1 = "<" + player.getCommandSenderName() + ">";
//		
//		if (s.startsWith(s1))
//		{
//			s = s.replace(s1 + " ", "");
//			RobotHelper.think(player, s);
//		}
//	}
}
