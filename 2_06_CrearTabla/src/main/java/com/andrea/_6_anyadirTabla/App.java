package com.andrea._6_anyadirTabla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Si se ejecuta y la tabla ya existe, lanza psqlException.
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
         String sql = "CREATE TABLE proveedores (id varchar(10) PRIMARY KEY,"
         		+ "nombre varchar(100), fecha_alta Date)";
         
         statement.executeUpdate(sql);
         con.close();
    }
}
