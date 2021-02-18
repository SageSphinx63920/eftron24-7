package de.eftron.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import de.eftron.main.Main;
import de.eftron.musik.MusicController;
import de.eftron.musik.MusicUtil;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class StopCommand extends ListenerAdapter {


	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().startsWith(Main.prefix + "stop")) {
		Member m = e.getMember();
		TextChannel channel = e.getChannel();
		Message message = e.getMessage();
		
		GuildVoiceState state;
		if((state = m.getVoiceState()) != null) {
			VoiceChannel vc;
			if((vc = state.getChannel()) != null) {
				MusicController controller = Main.playermanger.getController(vc.getGuild().getIdLong());
				AudioManager manager = vc.getGuild().getAudioManager();
				AudioPlayer player = controller.getPlayer();
				MusicUtil.updateChannel(channel);

				controller.getQueue().getQueuelist().removeAll(controller.getQueue().getQueuelist());
		
				
				NoStopCommand.noStop.remove(e.getGuild().getIdLong());
				
				player.stopTrack();
				manager.closeAudioConnection();
				message.addReaction("U+1F44C").queue();
			}
		}	
	}
}
}