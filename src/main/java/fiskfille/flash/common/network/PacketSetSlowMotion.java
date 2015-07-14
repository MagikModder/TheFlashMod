package fiskfille.flash.common.network;

import fiskfille.flash.Flash;
import fiskfille.flash.common.data.DataManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSetSlowMotion implements IMessage
{
    public int id;
    private boolean slowMotion;
    
    public PacketSetSlowMotion()
    {
        
    }
    
    public PacketSetSlowMotion(EntityPlayer player, boolean mode)
    {
        id = player.getEntityId();
        slowMotion = mode;
    }
    
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        slowMotion = buf.readBoolean();
    }
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(slowMotion);
    }
    
    public static class Handler implements IMessageHandler<PacketSetSlowMotion, IMessage>
    {
        public IMessage onMessage(PacketSetSlowMotion message, MessageContext ctx)
        {
            boolean isTransformed = message.slowMotion;
            
            if (ctx.side.isClient())
            {
                EntityPlayer player = Flash.proxy.getPlayer();
                EntityPlayer from = null;
                Entity entity = player.worldObj.getEntityByID(message.id);
                
                if (entity instanceof EntityPlayer)
                    from = (EntityPlayer) entity;
                
                if (from != null && from != player)
                {
                	DataManager.setInSlowMotionWithoutNotify(from, isTransformed);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                
                if (player != null)
                {
                    DataManager.setInSlowMotion(player, isTransformed);
                }
            }
            
            return null;
        }
    }
}
