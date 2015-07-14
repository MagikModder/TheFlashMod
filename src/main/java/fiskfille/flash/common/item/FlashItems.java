package fiskfille.flash.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import fiskfille.flash.common.item.armor.ItemFlashArmor;
import fiskfille.flash.common.item.armor.ItemReverseFlashArmor;

public class FlashItems
{
	public static ArmorMaterial FLASHMATERIAL = EnumHelper.addArmorMaterial("Flash", 400 / 16, new int[] {2, 5, 4, 2}, 15);
		
	public static Item flashHelmet;
    public static Item flashChestplate;
    public static Item flashLeggings;
    public static Item flashBoots;
    
    public static Item reverseFlashHelmet;
    public static Item reverseFlashChestplate;
    public static Item reverseFlashLeggings;
    public static Item reverseFlashBoots;
    
    public static Item reinforcedTriPolymer;
    public static Item theFlashEmblem;
    public static Item theReverseFlashEmblem;
    public static Item theFlashRing;
	
	public static void register()
	{
		flashHelmet = new ItemFlashArmor(0);
        flashChestplate = new ItemFlashArmor(1);
        flashLeggings = new ItemFlashArmor(2);
        flashBoots = new ItemFlashArmor(3);
        
        reverseFlashHelmet = new ItemReverseFlashArmor(0);
        reverseFlashChestplate = new ItemReverseFlashArmor(1);
        reverseFlashLeggings = new ItemReverseFlashArmor(2);
        reverseFlashBoots = new ItemReverseFlashArmor(3);
        
        reinforcedTriPolymer = new ItemTriPolymer();
        theFlashEmblem = new Item();
        theReverseFlashEmblem = new Item();
        theFlashRing = new ItemFlashRing();
        
        
        
        ItemRegistry.registerItem(flashHelmet, "The Flash's Mask");
        ItemRegistry.registerItem(flashChestplate, "The Flash's Chestpiece");
        ItemRegistry.registerItem(flashLeggings, "The Flash's Pants");
        ItemRegistry.registerItem(flashBoots, "The Flash's Boots");
        
        ItemRegistry.registerItem(reverseFlashHelmet, "The Reverse Flash's Mask");
        ItemRegistry.registerItem(reverseFlashChestplate, "The Reverse Flash's Chestpiece");
        ItemRegistry.registerItem(reverseFlashLeggings, "The Reverse Flash's Pants");
        ItemRegistry.registerItem(reverseFlashBoots, "The Reverse Flash's Boots");
        
        ItemRegistry.registerItem(reinforcedTriPolymer, "Reinforced Tri-Polymer");
        ItemRegistry.registerItem(theFlashEmblem, "The Flash Emblem");
        ItemRegistry.registerItem(theReverseFlashEmblem, "The Reverse Flash Emblem");
        ItemRegistry.registerItem(theFlashRing, "The Flash Ring");
	}
}
