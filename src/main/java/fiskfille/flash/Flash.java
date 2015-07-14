package fiskfille.flash;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fiskfille.flash.common.item.FlashItems;
import fiskfille.flash.common.proxy.CommonProxy;

@Mod(modid = Flash.modid, name = "The Flash", version = Flash.version)
public class Flash
{
	public static final String modid = "flash";
	public static final String version = "${version}";
	
	@Instance(Flash.modid)
	public static Flash instance;
	
	@SidedProxy(clientSide = "fiskfille.flash.common.proxy.ClientProxy", serverSide = "fiskfille.flash.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabFlash = new CreativeTabs(CreativeTabs.getNextID(), "The Flash")
	{
		@Override
		public String getTranslatedTabLabel()
		{
			return "The Flash";
		}
		
		@Override
		public Item getTabIconItem()
		{
			return FlashItems.theFlashEmblem;
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
}