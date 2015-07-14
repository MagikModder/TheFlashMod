//package fiskfille.flash.client.robot;
//
//import com.sun.speech.freetts.Voice;
//import com.sun.speech.freetts.VoiceManager;
//
//public class RobotTalkThread extends Thread
//{
//	private float pitch;
//	private float pitchRange;
//	private float pitchShift;
//	private String sentence;
//
//	public RobotTalkThread(float pitch, float pitchRange, float pitchShift, String message)
//	{
//		this.pitch = pitch;
//		this.pitchRange = pitchRange;
//		this.pitchShift = pitchShift;
//		this.sentence = message;
//
//		this.start();
//	}
//
//	public void run()
//	{
////		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
////
////		try
////		{
////			String voiceName = "kevin16";
////
////			Voice voice;
////			VoiceManager vm = VoiceManager.getInstance();
////			voice = vm.getVoice(voiceName);
////			voice.allocate();
////			voice.setPitch(pitch);
////			voice.setPitchRange(pitchRange);
////			voice.setPitchShift(pitchShift);
////			voice.speak(sentence);
////
////			return;
////		}
////		catch (Exception e)
////		{
////			e.printStackTrace();
////		}
//	}
//}
