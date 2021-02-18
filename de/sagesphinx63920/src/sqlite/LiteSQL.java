package de.eftron.litesql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LiteSQL {

	private static Connection conn;
	private static Statement stmt;

	public static void connect() {
		conn = null;
		
		try {
			File file = new File("datnbank.db");
			if(!file.exists()) {
				file.createNewFile();
			}
			
			String url = "jdbc:sqlite:" + file.getPath();
			conn = DriverManager.getConnection(url);
			
			System.out.println("Verbindung zur Datenbank Erstellt");
			
			stmt = conn.createStatement();
			
		}catch(SQLException | IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void disconnect() {
		try {
			if(conn!= null) {
				conn.close();
				System.out.println("Verbindung zur Datenbank getrennt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void onUpdate(String sql) {
		try {
			
			stmt.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet onQuery(String sql) {
		
		try {
			
			return stmt.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}