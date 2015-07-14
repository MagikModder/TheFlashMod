package fiskfille.flash.common.item.armor;

import fiskfille.flash.SpeedsterManager;
import fiskfille.flash.common.item.FlashItems;
import fiskfille.flash.common.speedster.Speedster;

public class ItemReverseFlashArmor extends ItemSpeedsterArmor
{
    public ItemReverseFlashArmor(int armorPiece)
    {
        super(FlashItems.FLASHMATERIAL, 4, armorPiece);
    }
    
    @Override
    public Speedster getSpeedster()
    {
        return SpeedsterManager.speedsterReverseFlash;
    }
}