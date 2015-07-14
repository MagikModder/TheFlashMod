//package fiskfille.flash.asm;
//
//import java.io.File;
//import java.util.Map;
//
//import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
//import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
//import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
//import fiskfille.flash.Flash;
//
//@MCVersion("1.7.10")
//@TransformerExclusions({"fiskfille.flash.asm"})
//public class FlashPlugin implements IFMLLoadingPlugin
//{
//	public static File modFile;
//	
//	@Override
//	public String[] getASMTransformerClass()
//	{
//		return new String[] {FlashClassTransformer.class.getName()};
//	}
//
//	@Override
//	public String getModContainerClass()
//	{
//		return null;
//	}
//
//	@Override
//	public String getSetupClass()
//	{
//		return null;
//	}
//
//	@Override
//	public void injectData(Map<String, Object> data)
//	{
//		
//	}
//
//	@Override
//	public String getAccessTransformerClass()
//	{
//		return null;
//	}
//}
