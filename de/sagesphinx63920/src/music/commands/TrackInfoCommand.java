package de.eftron.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import de.eftron.main.Main;
import de.eftron.musik.MusicController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TrackInfoCommand extends ListenerAdapter{


	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentDisplay().startsWith(Main.prefix + "stream")) {
		TextChannel channel = e.getChannel();
		
		// !ti 
		
		/**
		 * 			Spielt track -> Es spielt Bla Bla
		 * 
		 * 			Kein Track -> Ich spiel nix
		 * 
		 */
		
		
		MusicController controller = Main.playermanger.getController(channel.getGuild().getIdLong());
		AudioPlayer player = controller.getPlayer();
		
		AudioTrack track;
		if((track = player.getPlayingTrack()) != null) {
			AudioTrackInfo info = track.getInfo();
			
			String author = info.author;
			String title = info.title;
			String url = info.uri;
			boolean isStream = info.isStream;
			long position = track.getPosition();
			long length = track.getDuration();
			
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(author);
			builder.setTitle(title, url);
			
			long curSeconds = position / 1000;
			long curMinutes = curSeconds / 60;
			long curStunden = curMinutes / 60;
			curSeconds %= 60;
			curMinutes %= 60;
			
			long maxSeconds = length / 1000;
			long maxMinutes = maxSeconds / 60;
			long maxStunden = maxMinutes / 60;
			maxSeconds %= 60;
			maxMinutes %= 60;
			
			String time = ((curStunden > 0) ? curStunden + "h " : "") + curMinutes + "min " + curSeconds + "s / " + ((maxStunden > 0) ? maxStunden + "h " : "") + maxMinutes + "min " + maxSeconds + "s";
			
			
			builder.setDescription(isStream ? "-> STREAM" : "-> " + time);
			
			channel.sendMessage(builder.build()).queue();
		}
		else {
			//Nichts
			
			channel.sendMessage(new EmbedBuilder().setDescription("Ich spiel nix").build()).queue();
		}
		
	}
	}
}
