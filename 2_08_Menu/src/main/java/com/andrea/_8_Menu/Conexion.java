package com.andrea._8_Menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
   	 	String url = "jdbc:postgresql://localhost:5433/dia01";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url,usuario,password);
		return con;
	
	}
}
