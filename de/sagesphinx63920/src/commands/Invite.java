package de.eftron.music.commands;

import java.awt.Color;

import de.eftron.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

	public class Invite extends ListenerAdapter {

	    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
	                
	        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "invite")) {
	        	
	          	       event.getChannel().sendMessage(new EmbedBuilder()
	                        
	                            .setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl())
	                            .setColor(Color.CYAN)
	                            .setDescription("**Invite mich:** \n Klicke [hier](https://discord.com/api/oauth2/authorize?client_id=792148789103951882&permissions=36785416&scope=bot%20applications.commands) um mich zu deinem Server einzuladen. \n \n **Support Server** \n Klicke [hier](https://discord.gg/j8emH5ap3k) um zu meinem Discord zu kommen. \n \n**Meine Website** \nKlicke [hier](https://eftron.de) um zu meiner Website zu kommen. \n \n * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *")
	                            .setFooter("Invite Info")
	                          .build()).queue();
	          	        		
	          	    };
	        	}


	        }


