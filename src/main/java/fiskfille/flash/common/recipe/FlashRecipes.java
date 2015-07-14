package fiskfille.flash.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.flash.common.item.FlashItems;

public class FlashRecipes
{
	public static void register()
	{
		GameRegistry.addRecipe(new ItemStack(FlashItems.flashHelmet, 1), new Object[] {"TTT", "T T", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(FlashItems.flashChestplate, 1), new Object[] {"T T", "TOT", "TTT", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 1), 'O', FlashItems.theFlashEmblem});
		GameRegistry.addRecipe(new ItemStack(FlashItems.flashLeggings, 1), new Object[] {"TTT", "T T", "T T", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(FlashItems.flashBoots, 1), new Object[] {"T T", "T T", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(FlashItems.reverseFlashHelmet, 1), new Object[] {"TTT", "T T", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 11)});
		GameRegistry.addRecipe(new ItemStack(FlashItems.reverseFlashChestplate, 1), new Object[] {"T T", "TOT", "BBB", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 11), 'B', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 0), 'O', FlashItems.theReverseFlashEmblem});
		GameRegistry.addRecipe(new ItemStack(FlashItems.reverseFlashLeggings, 1), new Object[] {"BBB", "B B", "B B", 'B', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(FlashItems.reverseFlashBoots, 1), new Object[] {"B B", "B B", 'B', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 0)});
		
		for (int i = 0; i <= 15; ++i)
		{
			GameRegistry.addRecipe(new ItemStack(FlashItems.reinforcedTriPolymer, 1, i), new Object[] {"ILI", "LDL", "ILI", 'L', Items.leather, 'I', Items.iron_ingot, 'D', new ItemStack(Items.dye, 1, i)});
		}
		
		GameRegistry.addRecipe(new ItemStack(FlashItems.theFlashEmblem, 1), new Object[] {"TTT", "TGT", "TTT", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 1), 'G', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(FlashItems.theReverseFlashEmblem, 1), new Object[] {"TTT", "TGT", "TTT", 'T', new ItemStack(FlashItems.reinforcedTriPolymer, 1, 0), 'G', Blocks.redstone_block});
		GameRegistry.addRecipe(new ItemStack(FlashItems.theFlashRing, 1), new Object[] {"NGN", "N N", " N ", 'N', Items.gold_nugget, 'G', Items.gold_ingot});
	}
}
