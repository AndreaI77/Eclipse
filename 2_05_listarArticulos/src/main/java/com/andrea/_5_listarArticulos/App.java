package com.andrea._5_listarArticulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
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
        String sql = "SELECT * FROM articulos WHERE nombre LIKE 'A%' OR nombre LIKE 'a%' ";
        ResultSet rs = statement.executeQuery(sql);
        
        boolean linea = rs.next();
        if (!linea) {
        	System.out.println("No se encontraron datos");
        }else {
        	System.out.println("id"+ "\t"+"nombre"+"\t\t"+"precio");
            System.out.println("------------------------------------------");
        	while(linea) {
            	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3));
            	linea = rs.next();
            }
        }
        
        rs.close();
        con.close();
    }
}
