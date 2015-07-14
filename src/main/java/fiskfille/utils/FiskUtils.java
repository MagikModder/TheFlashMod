package fiskfille.utils;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import fiskfille.utils.common.time.Tickrate;

public class FiskUtils extends DummyModContainer
{
	public static final String modid = "fiskutils";
	public static final String version = "${version}";
	
	public static FiskUtils instance;
	
	public FiskUtils()
	{
		super(createMetadata());
		instance = this;
	}
	
	private static ModMetadata createMetadata()
	{
        ModMetadata meta = new ModMetadata();
        meta.modId = modid;
        meta.name = "FiskUtils";
        meta.version = version;
        meta.authorList = Arrays.asList("FiskFille");
        meta.description = "Utility mod required for some of FiskFille's mods.";
        return meta;
    }
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
}