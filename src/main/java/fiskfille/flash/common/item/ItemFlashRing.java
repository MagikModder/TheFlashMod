package fiskfille.flash.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import fiskfille.flash.SpeedsterAPI;
import fiskfille.flash.common.item.armor.ItemSpeedsterArmor;
import fiskfille.flash.common.speedster.Speedster;
import fiskfille.flash.common.utils.FlashHelper;

public class ItemFlashRing extends Item
{
    public ItemFlashRing()
    {
        super();
        this.setMaxStackSize(1);
    }
    
    public void setNBTData(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet, ItemStack itemstack)
    {
        ItemStack[] itemstacks = {head, chest, legs, feet};
        NBTTagList itemsList = new NBTTagList();
        
        for (int i = 0; i < itemstacks.length; ++i)
        {
            if (itemstacks[i] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                itemstacks[i].writeToNBT(itemTag);
                itemsList.appendTag(itemTag);
            }
        }
        
        itemstack.getTagCompound().setTag("Items", itemsList);
    }
    
    public void setNBTData(ItemStack itemstack)
    {
        int speedsterIndex = 0;
        
        for (Speedster speedster : SpeedsterAPI.getSpeedsters())
        {
            if (speedsterIndex == itemstack.getItemDamage())
            {
                ItemStack head = new ItemStack(speedster.getHelmet());
                ItemStack chest = new ItemStack(speedster.getChestplate());
                ItemStack legs = new ItemStack(speedster.getLeggings());
                ItemStack feet = new ItemStack(speedster.getBoots());
                
                ItemStack[] itemstacks = {head, chest, legs, feet};
                
                NBTTagList itemsList = new NBTTagList();
                
                for (int i = 0; i < itemstacks.length; ++i)
                {
                    if (itemstacks[i] != null)
                    {
                        NBTTagCompound itemTag = new NBTTagCompound();
                        itemTag.setByte("Slot", (byte) i);
                        itemstacks[i].writeToNBT(itemTag);
                        itemsList.appendTag(itemTag);
                    }
                }
                
                itemstack.getTagCompound().setTag("Items", itemsList);
            }
            
            ++speedsterIndex;
        }
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
    	if (itemstack.hasTagCompound() && !itemstack.getTagCompound().getBoolean("Dispensed"))
    	{
    		Speedster speedster = ((ItemSpeedsterArmor)getArmorFromNBT(itemstack)[0].getItem()).getSpeedster();
    		list.add(speedster.getName());
    	}
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
            itemstack.getTagCompound().setBoolean("Dispensed", true);
        }
        
        if (itemstack.getTagCompound().getBoolean("Dispensed"))
        {
        	if (FlashHelper.isPlayerSpeedster(player))
        	{
        		setNBTData(player.getCurrentArmor(3), player.getCurrentArmor(2), player.getCurrentArmor(1), player.getCurrentArmor(0), itemstack);
        		player.setCurrentItemOrArmor(4, null);
        		player.setCurrentItemOrArmor(3, null);
        		player.setCurrentItemOrArmor(2, null);
        		player.setCurrentItemOrArmor(1, null);
        		itemstack.getTagCompound().setBoolean("Dispensed", false);
        	}
        }
        else
        {
        	ItemStack[] armorFromNBT = getArmorFromNBT(itemstack);
            
            if (armorFromNBT == null)
            {
                setNBTData(itemstack);
                armorFromNBT = getArmorFromNBT(itemstack);
            }
            
            boolean server = !player.worldObj.isRemote;
            
            if (armorFromNBT[0] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(3) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(3), 0);
                    }
                }
                
                player.setCurrentItemOrArmor(4, armorFromNBT[0]);
            }
            
            if (armorFromNBT[1] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(2) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(2), 0);
                    }
                }
                
                player.setCurrentItemOrArmor(3, armorFromNBT[1]);
            }
            
            if (armorFromNBT[2] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(1) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(1), 0);
                    }
                }
                
                player.setCurrentItemOrArmor(2, armorFromNBT[2]);
            }
            
            if (armorFromNBT[3] != null)
            {
                if (server)
                {
                    if (player.getCurrentArmor(0) != null)
                    {
                        player.entityDropItem(player.getCurrentArmor(0), 0);
                    }
                }
                
                player.setCurrentItemOrArmor(1, armorFromNBT[3]);
            }
            
            itemstack.getTagCompound().setBoolean("Dispensed", true);
        }
        
        return itemstack;
    }
    
    public ItemStack[] getArmorFromNBT(ItemStack itemstack)
    {
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Items"))
        {
            NBTTagList nbtItems = itemstack.getTagCompound().getTagList("Items", 10);
            ItemStack[] items = new ItemStack[4];
            
            for (int i = 0; i < nbtItems.tagCount(); ++i)
            {
                NBTTagCompound item = (NBTTagCompound) nbtItems.getCompoundTagAt(i);
                
                byte slot = item.getByte("Slot");
                
                if (slot >= 0 && slot < items.length)
                {
                    items[slot] = ItemStack.loadItemStackFromNBT(item);
                }
            }
            
            return items;
        }
        
        return null;
    }
}
