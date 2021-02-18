package de.eftron.music.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import de.eftron.main.Main;
import de.eftron.musik.AudioLoadResult;
import de.eftron.musik.MusicController;
import de.eftron.musik.MusicUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class NoStopCommand extends ListenerAdapter{

	public static ArrayList<Long> noStop = new ArrayList<Long>();
	

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().startsWith(Main.prefix + "radio")) {
			
			System.out.println("play");
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			List<String> RadioList = Arrays.asList("http://mp3.ffh.de/ffhchannels/hqsummerfeeling.mp3", "http://swr-swr3-live.cast.addradio.de/swr/swr3/live/mp3/128/stream.mp3", "http://addrad.io/4WRDh8", "http://hr-hr3-live.cast.addradio.de/hr/hr3/live/mp3/128/stream.mp3", "http://stream.jam.fm/jamfm-live/mp3-128/vtuner", "http://mp3.ffh.de/ffhchannels/hqsummerfeeling.mp3");
			String Radio_RANDOM = RadioList.get(new Random().nextInt(RadioList.size()));
		
		Message message = e.getMessage();
		TextChannel channel = e.getChannel();
		Member m = e.getMember();
		
		String[] args = message.getContentDisplay().split(" ");
		
		if(args.length == 2) {
			
			GuildVoiceState state;
			if((state = m.getVoiceState()) != null) {
				VoiceChannel vc;
				if((vc = state.getChannel()) != null) {
					MusicController controller = Main.playermanger.getController(vc.getGuild().getIdLong());
					AudioPlayerManager apm = Main.audioplayermanger;
					AudioManager manager = vc.getGuild().getAudioManager();
					
					AudioPlayer player = controller.getPlayer();
					System.out.println();
					if(controller.getQueue().getQueuelist().size() == 0 && controller.getPlayer().getPlayingTrack() == null) {
						
						MusicUtil.updateChannel(channel);
						player.stopTrack();
						manager.openAudioConnection(vc);
						
						 String url;
						
						if(args[1].equalsIgnoreCase("ffh")) {
							url = "http://mp3.ffh.de/ffhchannels/hqsummerfeeling.mp3";
							apm.loadItem(url, new AudioLoadResult(controller, url));
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt FFH").setColor(Color_RANDOM).build()).queue();		
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("swr3")) {
							url = "http://swr-swr3-live.cast.addradio.de/swr/swr3/live/mp3/128/stream.mp3";
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt SWR 3").setColor(Color_RANDOM).build()).queue();						
							apm.loadItem(url, new AudioLoadResult(controller, url));
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("bayern2")) {
							url = "http://addrad.io/4WRDh8";
							apm.loadItem(url, new AudioLoadResult(controller, url));
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt Bayern 2").setColor(Color_RANDOM).build()).queue();
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("hr3")) {
							url = "http://hr-hr3-live.cast.addradio.de/hr/hr3/live/mp3/128/stream.mp3";
							apm.loadItem(url, new AudioLoadResult(controller, url));
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt Hr 3").setColor(Color_RANDOM).build()).queue();
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("jam")) {
							url = "http://stream.jam.fm/jamfm-live/mp3-128/vtuner";
							apm.loadItem(url, new AudioLoadResult(controller, url));
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt Jam FM").setColor(Color_RANDOM).build()).queue();
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("zufall")) {
							url = Radio_RANDOM;
							apm.loadItem(url, new AudioLoadResult(controller, url));
							e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich spiele jetzt Zufall").setColor(Color_RANDOM).build()).queue();
							noStop.add(e.getGuild().getIdLong());
						}else if(args[1].equalsIgnoreCase("stop")) {
							player.stopTrack();
							manager.closeAudioConnection();
							noStop.remove(e.getGuild().getIdLong());
							
						}else {
							e.getChannel().sendMessage(new EmbedBuilder()
									.setTitle("Fehler")
									.setColor(Color.red)
									.setDescription("Bitte nutze e.24 ffh / swr3 / hr3 / jam / bayern2 / zufall")
									.build()).queue();
						}

							
						
					}else {
						e.getChannel().sendMessage(new EmbedBuilder()
								.setColor(Color.yellow)
								.setTitle("24/7 blockiert!")
								.setDescription("Bitte warte bis alle Lieder aus der Queue sind. Oder stoppe sie mit e.stop")
								.build()).queue();
					}
					

					
				}
				
				
				
				else {
					EmbedBuilder builder = new EmbedBuilder();
					builder.setDescription("Huch? Du bist wohl in keinem VoiceChannel.");
					builder.setColor(Color.decode("#f22613"));
					channel.sendMessage(builder.build()).queue();
				}
			}
			else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setDescription("Huch? Du bist wohl in keinem VoiceChannel.");
				builder.setColor(Color.decode("#f22613"));
				channel.sendMessage(builder.build()).queue();
			}
		}else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setDescription("Bitte benutze e.24 <Radio Sender>");
			builder.setColor(Color.decode("#f22613"));
			channel.sendMessage(builder.build()).queue();
		}
	}
}}