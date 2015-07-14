//package fiskfille.flash.client.robot;
//
//import net.minecraft.entity.player.EntityPlayer;
//
//import com.google.code.chatterbotapi.ChatterBot;
//import com.google.code.chatterbotapi.ChatterBotFactory;
//import com.google.code.chatterbotapi.ChatterBotSession;
//import com.google.code.chatterbotapi.ChatterBotThought;
//import com.google.code.chatterbotapi.ChatterBotType;
//
//public class RobotHelper
//{
//	public static ChatterBotFactory factory = new ChatterBotFactory();
//	
//	public static ChatterBotSession chatterSession;
//	public static ChatterBot chatterBot;
//
//	public static void createSession()
//	{
////		System.out.println("------------------------------------- Creating session");
////		
////		try
////		{
////			chatterBot = factory.create(ChatterBotType.JABBERWACKY);
////			chatterSession = chatterBot.createSession();
////			
////			String s = "Hello, my name is Fisk.";
////			System.out.println(chatterSession.think(s));
////		} 
////		catch (Exception e) 
////		{
////			e.printStackTrace();
////		}
//	}
//	
//	public static void think(EntityPlayer player, String message) 
//	{
//		new RobotThinkThread(player, message);
//	}
//}
