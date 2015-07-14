package fiskfille.flash.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityStreak extends Entity 
{
	public EntityLivingBase parent;
	public EntityStreak streak;
	public long lastUpdate;
	
	public final int bolts = 10;
	public double[] boltOffsetX = new double[bolts * 2 + 1];
	public double[] boltOffsetY = new double[bolts * 2 + 1];
	public double[] boltOffsetZ = new double[bolts * 2 + 1];

	public EntityStreak(World world) 
	{
		super(world);
		setSize(0.1F, 0.1F);

		lastUpdate = world.getWorldTime();
		ignoreFrustumCheck = true;
		renderDistanceWeight = 10D;
	}

	public EntityStreak(World world, EntityLivingBase ent) 
	{
		this(world);
		parent = ent;
		setLocationAndAngles(parent.posX, parent.posY - 0.8D + (parent != Minecraft.getMinecraft().thePlayer ? parent.getEyeHeight() : 0), parent.posZ, parent.rotationYaw, parent.rotationPitch);
		setBoltOffsets();
	}

	private void setBoltOffsets()
	{
		double multiplier = 0.5D;
		
		for (int i = 0; i < bolts; ++i)
		{
			boltOffsetX[i] = (rand.nextDouble() - 0.5D) * multiplier;
			boltOffsetY[i] = (rand.nextDouble() - 0.5D) * multiplier;
			boltOffsetZ[i] = (rand.nextDouble() - 0.5D) * multiplier;
		}
	}

	@Override
	public void onUpdate()
	{
		++ticksExisted;
		
		setBoltOffsets();
		
		if (ticksExisted > 10 || (parent == null || !parent.isEntityAlive() || parent.isChild()))
		{
			setDead();
			return;
		}

		lastUpdate = worldObj.getWorldTime();
	}

	@Override
	public boolean shouldRenderInPass(int pass)
	{
		return pass == 1;
	}

	@Override
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public void setDead()
	{
		super.setDead();
	}

	@Override
	public void entityInit() 
	{
	}

	@Override
	public boolean writeToNBTOptional(NBTTagCompound par1NBTTagCompound)//disable saving of the entity
	{
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) 
	{
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
	}
}