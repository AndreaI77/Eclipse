package com.andrea._15_Final;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static String readUrl(String urlString) throws Exception{
		
		//lee los datos de la url y devuelve el string
		BufferedReader reader = null;
		
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while((read = reader.read(chars)) != -1) {
				buffer.append(chars,0,read);
			}
			return buffer.toString();
		}finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	public static Connection makeConnection() throws ClassNotFoundException, SQLException {
		// crea y devuelve la conexión a BD
		Class.forName("org.postgresql.Driver");
   	 	String url = "jdbc:postgresql://localhost:5433/jokes";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url,usuario,password);
		return con;
	
	}

}
