package fiskfille.flash.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.flash.Flash;

public class ItemTriPolymer extends Item
{
	public static final String[] unlocalizedNames = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
	public static final int[] colors = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

	public ItemTriPolymer()
	{
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public String getUnlocalizedName(ItemStack itemstack)
	{
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, 15);
		return "item." + unlocalizedNames[i] + "_reinforced_tripolymer";
	}

	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 16; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	public int getColorFromItemStack(ItemStack itemstack, int layer)
	{
		int damage = itemstack.getItemDamage();
		int j = MathHelper.clamp_int(damage, 0, 15);
		return colors[j];
	}

	public void registerIcons(IIconRegister par1IIconRegister)
	{
		itemIcon = par1IIconRegister.registerIcon(Flash.modid + ":reinforced_tripolymer");
	}
}
