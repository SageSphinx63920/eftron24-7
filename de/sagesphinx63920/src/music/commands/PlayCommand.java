package de.eftron.music.commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

public class PlayCommand extends ListenerAdapter{


	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().startsWith(Main.prefix + "play")) {
			System.out.println("play");
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
		
		Message message = e.getMessage();
		TextChannel channel = e.getChannel();
		Member m = e.getMember();
		
		String[] args = message.getContentDisplay().split(" ");
		if(!NoStopCommand.noStop.contains(e.getGuild().getIdLong())) {
			if(args.length > 1) {
				GuildVoiceState state;
				if((state = m.getVoiceState()) != null) {
					VoiceChannel vc;
					if((vc = state.getChannel()) != null) {
						MusicController controller = Main.playermanger.getController(vc.getGuild().getIdLong());
						AudioPlayerManager apm = Main.audioplayermanger;
						AudioManager manager = vc.getGuild().getAudioManager();
						manager.openAudioConnection(vc);
						
						MusicUtil.updateChannel(channel);
						
						StringBuilder strBuilder = new StringBuilder();
						for(int i = 1; i < args.length; i++) strBuilder.append(args[i] + " ");
						
						String url = strBuilder.toString().trim();
						if(!url.startsWith("http")) {
							url = "ytsearch: " + url;
						}
						
						e.getChannel().sendMessage(new EmbedBuilder().setDescription("Ich habe " + url.replace("ytsearch:", "" + " zur Playlist hinzugefügt.")).setColor(Color_RANDOM).build()).queue();
						
						apm.loadItem(url, new AudioLoadResult(controller, url));
						
						System.out.println("load übergabe");
						
						
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
			}
			else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setDescription("Bitte benutze e.play <url/search query>");
				builder.setColor(Color.decode("#f22613"));
				channel.sendMessage(builder.build()).queue();
			}
		}else {
			e.getChannel().sendMessage(new EmbedBuilder()
					.setColor(Color.yellow)
					.setTitle("24/7 Modus aktiv")
					
					.setDescription("Bitte deaktiviere erst den 24/7 Modus mit 24.stop oder 24.stop")
					.build()).queue();		
			}

	}
}}
