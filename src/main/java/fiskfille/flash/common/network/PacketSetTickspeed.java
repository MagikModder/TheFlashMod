package fiskfille.flash.common.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.flash.common.time.TimeHelper;
import fiskfille.utils.common.time.Tickrate;

public class PacketSetTickspeed implements IMessage
{
    private float tickspeed;
    
    public PacketSetTickspeed()
    {
        
    }
    
    public PacketSetTickspeed(float speed)
    {
        tickspeed = speed;
    }
    
    public void fromBytes(ByteBuf buf)
    {
        tickspeed = buf.readFloat();
    }
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(tickspeed);
    }
    
    public static class Handler implements IMessageHandler<PacketSetTickspeed, IMessage>
    {
        public IMessage onMessage(PacketSetTickspeed message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
//            	if (Tickrate.CLIENT_TICKRATE != message.tickspeed)
//            	{
//            		Tickrate.updateClientTickrate(message.tickspeed);
//            	}
            }
            else
            {
            	if (Tickrate.MILISECONDS_PER_TICK != (long)(1000L / message.tickspeed))
            	{
            		Tickrate.updateServerTickrate(message.tickspeed);
            	}
            }
            
            return null;
        }
    }
}
