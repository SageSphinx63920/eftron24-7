package de.eftron.main;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import de.eftron.litesql.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Shutdown extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		TextChannel ch = e.getChannel();
		if(e.getMessage().getContentDisplay().startsWith(Main.prefix + "shutdown")) {
			if(Main.OwnerIds.contains(e.getAuthor().getIdLong())) {
			
				ch.sendMessage(new EmbedBuilder()
						.setDescription("Bot fährt runter!")
						.setColor(Color.YELLOW)
						.setFooter("Fährt runter")
						.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
				e.getMessage().addReaction("U+1F44C").queue();
				
				
				
				

				LiteSQL.disconnect();
				System.out.println("Bot ist offline");
				
				
				
						
						e.getJDA().shutdownNow();
		
			}else
				ch.sendMessage(Main.noPerm.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
		}
		
	}

}
