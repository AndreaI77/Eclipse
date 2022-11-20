package com.andrea._ConexionJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Para este ejercicio he descargado el jar de JDBC42.5.0 y lo he añadido en librerías.
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	
        Class.forName("org.postgresql.Driver");
        
        String url = "jdbc:postgresql://localhost:5433/dia01";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url,usuario,password);
        
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM articulos ";
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("id"+ "\t"+"nombre"+"\t\t"+"precio");
        System.out.println("------------------------------------------");
        
        while(rs.next()) {
        	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3));
        }
        rs.close();
        con.close();
    }
}
