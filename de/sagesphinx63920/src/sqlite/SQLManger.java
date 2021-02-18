package de.eftron.litesql;

import de.eftron.litesql.LiteSQL;

@SuppressWarnings("unused")
public class SQLManger {

	public static void onCreate() {
		
		//id  guildid  channelid  messageid  emote  rolleid
		
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS reactroles (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER, messageid INTEGER, emote VARCHAR, rollenid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS econemy(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, userid INTEGER, xp INTEGER, level INTEGER, mute INTEGER, levelxp INTEGER, nextxp INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS mute(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, time INTEGER, userid LONG, guildid LONG)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS musicchannel(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS tempchannels(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS statchannels(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, categoryid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS global(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, channelid INTEGER, guildid INTEGER, muted INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS mute(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, memberid INTEGER, guildid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS countergame(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, channelid INTEGER, guildid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS joinen(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER, ak INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS autofaq(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, channelid INTEGER, guildid INTEGER, prefix STRING, message STRING)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS konto(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, userid INTEGER, geld INTEGER, rabatt INTEGER, bank INTEGER, mute INTEGER)");
		//Bald LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS timeranks(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, userid INTEGER, guildid INTEGER, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, roleid INTEGER)");
		

	}
	
}
