package fiskfille.flash.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fiskfille.flash.common.data.DataManager;
import fiskfille.flash.common.entity.EntityStreak;
import fiskfille.flash.common.motion.FlashMotionManager;
import fiskfille.flash.common.time.TimeHelper;
import fiskfille.flash.common.utils.FlashHelper;

public class CommonEventHandler
{
	@SubscribeEvent
	public void onLivingJump(LivingJumpEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player))
			{
				player.motionY += 0.2F;
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		
		if (player != null)
		{
			if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player))
			{
				player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1);
				player.stepHeight = 1.0F;
			}
			else if (player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() == 1)
			{
				player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000149011612D);
				player.stepHeight = 0.5F;
			}
			
			if (FlashHelper.areAllPlayersSlowMotion(player.worldObj))
			{
				TimeHelper.updateServerTickrate(5);
			}
			else
			{
				TimeHelper.updateServerTickrate(20);
			}
			
			DataManager.setSpeed(player);
			
			if (event.phase == TickEvent.Phase.START)
			{
				if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player) && (player.prevPosX != player.posX || player.prevPosZ != player.posZ))
				{
					EntityStreak entity = new EntityStreak(player.worldObj, player);
					entity.streak = DataManager.getNewestStreak(player);
					DataManager.setNewestStreak(player, entity);
					
					player.worldObj.spawnEntityInWorld(entity);
				}
			}
			else
			{
				DataManager.setSpeed(player);
				
				if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player) && (player.prevPosX != player.posX || player.prevPosZ != player.posZ))
				{					
					if (player.moveForward > 0 && player.isInWater() && DataManager.getSpeed(player) >= 75)
					{
						player.motionY = Math.max(0.0F, player.motionY);
						FlashMotionManager.moveForward(player, 200, false);
					}
				}
				
				if (FlashHelper.isPlayerSpeedster(player))
				{
					if (player.ticksExisted % 40 == 0)
					{
						player.heal(1.0F);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.source != null && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			
			if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player) && !event.source.isProjectile() && !event.source.isExplosion() && !event.source.isFireDamage() && !event.source.isMagicDamage())
			{
				player.swingProgressInt += 2F;
				event.entityLiving.hurtResistantTime = 0;
				event.ammount *= 0.5F;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player))
			{
				if (event.source != null && event.source.getSourceOfDamage() instanceof EntityArrow)
				{
					EntityArrow entity = (EntityArrow)event.source.getSourceOfDamage();
					
					if (player.getHeldItem() == null && isPlayerLookingAt(player, entity))
					{
						event.setCanceled(true);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.arrow));
						entity.setDead();
					}
				}
			}
		}
	}
	
	private boolean isPlayerLookingAt(EntityPlayer player, Entity entity)
	{
		Vec3 vec3 = player.getLook(1.0F).normalize();
        Vec3 vec31 = Vec3.createVectorHelper(entity.posX - player.posX, entity.boundingBox.minY + (double)(entity.height / 2.0F) - (player.posY + (double)player.getEyeHeight()), entity.posZ - player.posZ);
        double d0 = vec31.lengthVector();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        
        return d1 > 1.0D - 0.25D / d0 && player.canEntityBeSeen(entity);
	}
	
	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if (DataManager.getIsSpeeding(player) && FlashHelper.canRunSuperSpeed(player))
			{
				event.distance /= 2;
			}
		}
	}
}
