package fiskfille.flash.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fiskfille.flash.Flash;

public class FlashNetworkManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;
    
    public static void registerPackets()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Flash.modid);
        
        registerPacket(PacketSetSpeeding.Handler.class, PacketSetSpeeding.class);
        registerPacket(PacketSetSlowMotion.Handler.class, PacketSetSlowMotion.class);
        registerPacket(PacketSetTickspeed.Handler.class, PacketSetTickspeed.class);
    }
    
    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
