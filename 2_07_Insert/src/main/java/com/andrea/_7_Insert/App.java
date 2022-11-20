package com.andrea._7_Insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * si se vuelve a ejecutar y la llave primaria ya existe, 
 * salta PSQLeXception de llave duplicada
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	Class.forName("org.postgresql.Driver");
   	 	String url = "jdbc:postgresql://localhost:5433/dia02";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url,usuario,password);
        
        Statement statement = con .createStatement();
        String sql = "INSERT INTO proveedores VALUES ('p1', 'frutas sl', now()), ('p2', 'quesos sl', now());";
        int filas = statement.executeUpdate(sql);
        System.out.println("filas insertadas: "+filas);
        con.close();
    }
}
