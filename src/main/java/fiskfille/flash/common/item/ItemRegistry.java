package fiskfille.flash.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.flash.Flash;

public class ItemRegistry
{
    public static void registerItem(Item item, String name)
    {
        item.setCreativeTab(Flash.tabFlash);
        
        registerItemNoTab(item, name);
    }
    
    public static void registerIngot(Item item, String name, String oreDictName)
    {
        registerItem(item, name);
        OreDictionary.registerOre(oreDictName, item);
    }
    
    public static void registerItemNoTab(Item item, String name)
    {
        String unlocalizedName = name.toLowerCase().replace(" ", "_").replace("'", "").replace("-", "");
        
        item.setUnlocalizedName(unlocalizedName);
        item.setTextureName(Flash.modid + ":" + unlocalizedName);
        
        GameRegistry.registerItem(item, unlocalizedName);
    }
}