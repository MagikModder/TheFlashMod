//package fiskfille.flash.client.robot;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.ChatComponentText;
//
//public class RobotThinkThread extends Thread
//{
//	public EntityPlayer player; 
//	public String message;
//
//	public RobotThinkThread(EntityPlayer player, String message)
//	{
//		this.player = player;
//		this.message = message;
//
//		this.setName("Gideon Think Thread");
//		this.start();
//	}
//
//	public void run()
//	{
//		String answer = "";
//
//		try
//		{
//			answer = RobotHelper.chatterSession.think(message);
//			player.addChatMessage(new ChatComponentText("<Gideon> " + answer));
//			new RobotTalkThread(1, 4, 2, answer);
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}	
//}
