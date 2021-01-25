package de.eftron.main;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import de.eftron.litesql.LiteSQL;
import de.eftron.litesql.SQLManger;
import de.eftron.music.commands.Help;
import de.eftron.music.commands.Invite;
import de.eftron.music.commands.NoStopCommand;
import de.eftron.music.commands.PlayCommand;
import de.eftron.music.commands.StopCommand;
import de.eftron.music.commands.TrackInfoCommand;
import de.eftron.musik.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

	//Setzungen
	
	public static JDA jda;
	public static JDABuilder builder;
	public static ArrayList<Long> OwnerIds = new ArrayList<Long>();
	public static String prefix = "24.";
	public static EmbedBuilder noPerm = new EmbedBuilder().setTitle("**:x: Error :x:**").setDescription("Dazu hast du keine Berechtigung").setFooter("Fehler").setColor(Color.RED);
	public static PlayerManager playermanger;
	public static AudioPlayerManager audioplayermanger;

	
	public static void main(String[] args) throws IOException {
		//Sql
    	LiteSQL.connect();
    	SQLManger.onCreate();
		
    	//Online
		System.out.println("+++++\nEftron 24/7 wurde gestartet :) \n+++++\n\n");

		builder = JDABuilder.createDefault("NzkyMTQ4Nzg5MTAzOTUxODgy.X-ZgDA.2KNFu5zrG8Hc26HBLcdsi8RaSOc");

		builder.setActivity(Activity.listening("24/7 Musik"));
		builder.setAutoReconnect(true);
		
		builder.setStatus(OnlineStatus.ONLINE);
		
		audioplayermanger = new DefaultAudioPlayerManager();
	    playermanger = new PlayerManager();
		
		// Event Listener

	    builder.addEventListeners(new StopCommand());
		builder.addEventListeners(new NoStopCommand());
		builder.addEventListeners(new TrackInfoCommand());
		builder.addEventListeners(new Shutdown());
		builder.addEventListeners(new Help());
		builder.addEventListeners(new PlayCommand());
		builder.addEventListeners(new Invite());
	    
	    
		//Owner ID adden
		OwnerIds.add(494546946153906176l);
		OwnerIds.add(660887621169446964l);
		OwnerIds.add(658302673707204627l);

		//Audio
		AudioSourceManagers.registerRemoteSources(audioplayermanger);
        audioplayermanger.getConfiguration().setFilterHotSwapEnabled(true);
        
        //JDA Builder
		try {
			jda = builder.build();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		

	}
	
}