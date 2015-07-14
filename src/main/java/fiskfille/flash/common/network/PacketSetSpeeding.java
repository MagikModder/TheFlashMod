package fiskfille.flash.common.network;

import fiskfille.flash.Flash;
import fiskfille.flash.common.data.DataManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSetSpeeding implements IMessage
{
    public int id;
    private boolean speeding;
    
    public PacketSetSpeeding()
    {
        
    }
    
    public PacketSetSpeeding(EntityPlayer player, boolean mode)
    {
        id = player.getEntityId();
        speeding = mode;
    }
    
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        speeding = buf.readBoolean();
    }
    
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeBoolean(speeding);
    }
    
    public static class Handler implements IMessageHandler<PacketSetSpeeding, IMessage>
    {
        public IMessage onMessage(PacketSetSpeeding message, MessageContext ctx)
        {
            boolean isTransformed = message.speeding;
            
            if (ctx.side.isClient())
            {
                EntityPlayer player = Flash.proxy.getPlayer();
                EntityPlayer from = null;
                Entity entity = player.worldObj.getEntityByID(message.id);
                
                if (entity instanceof EntityPlayer)
                    from = (EntityPlayer) entity;
                
                if (from != null && from != player)
                {
                	DataManager.setIsSpeedingWithoutNotify(from, isTransformed);
                }
            }
            else
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                
                if (player != null)
                {
                    DataManager.setIsSpeeding(player, isTransformed);
                }
            }
            
            return null;
        }
    }
}
