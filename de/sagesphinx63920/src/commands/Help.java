package de.eftron.music.commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.eftron.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter{

	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "help")) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Guild g = event.getGuild();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().split(" ");
			int length = args.length;
			 StringBuilder strbuild = new StringBuilder();
																								     //1 	2
			for(int i = 2; i < length; i++) strbuild.append(args[i] + " "); //i = anzahl von suvfix: e.ichbincool
				
			String argsstring = strbuild.toString().trim();
			
			//Hier gehts los
          	  ch.sendMessage(new EmbedBuilder()
          			  .setTitle("Help Menü")
          			  .setColor(Color_RANDOM)
          			  .setDescription("Hi " + m.getAsMention() + "\n das ist mein Help Menü \n \n 24.radio ffh / swr3 / hr3 / jam / bayern2 / zufall / stop >> Spielt einen Sender im 24/7 Modus \n 24.stop >> Stoppt den Sender \n 24.stream >> Zeigt dir den aktuellen Stream \n 24.play [link/Name] >> Spielt ein Lied / Playlist \n 24.invite >> Gibt dir die Invite Info \n 24.help >> Zeigt dieses Menü \n \n [] Bitte ausfüllen")
          			  .setFooter("24/7 Help Menü")
          			  .build()).queue();      
        }
    }
}
