package com.andrea._8_Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        Scanner sc = new Scanner(System.in);
        String st = "";
        int num = 0;
        String text = "";
        float precio = 0.0f;

        boolean res = false;
        do {
        	while (!res) {
            	System.out.println("Elige una opción: ");
                System.out.println("1. Buscar\n2. Añadir\n3. Salir");
                st = sc.nextLine();
                try {
               	 	num = Integer.parseInt(st);
               	 	res= true;
               }catch(Exception e) {
               		System.out.println("No es un número");
               		res=false;
               }	
            }
        	st= "";
            text= "";
            switch(num) {
	            case 1: 
	    	        	while(text.equals("")) {
	    	        		System.out.println("Inserta el texto a buscar:");
	    	        		text = sc.nextLine();
	    	        	}
	            		if( !text.equals("")) {
	            			buscar(text);
	            		}
	            		break;
	            		
	            case 2: 
	    	        	while(text.equals("")) {
	    	        		System.out.println("Inserta nombre:");
	    	        		text = sc.nextLine();
	    	        	}
	    	        	while(st.equals("")) {
	    	        		System.out.println("Inserta precio: ");
	    	        		st = sc.nextLine();
	    	        		try {
	    	        			precio = Float.parseFloat(st);
	    	        		}catch(Exception e) {
	    	        			st="";
	    	        		}
	    	        	}
	    	        	insertar(text, precio);
	    	        	break;
	    	        	
	            case 3: System.out.println("Adios");
	            		break;
	            default: System.out.println("Opción desconocida");
            }
            res=false;
        }while(num!=3);
        
        sc.close();
    }
    public static void buscar(String text) {
    	try {
			Connection con = Conexion.makeConnection();
			 Statement statement = con .createStatement();
			 String sql = "SELECT * FROM articulos WHERE nombre LIKE '%"+text+"%';";
			 
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
		        System.out.println();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void insertar(String nombre, float precio) {
    	
    	// Ya que el primary key no es serial, primero hay que averiguar el último id
    	int num = obtenerId();
    	num++;
		try {
			Connection con = Conexion.makeConnection();
			Statement statement = con.createStatement();
			String sql = "INSERT INTO articulos (id,nombre,precio) VALUES ("+num+", '"+nombre+"', "+precio+");";

			int filas = statement.executeUpdate(sql);
			System.out.println("filas insertadas: " + filas);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static int obtenerId() {
		int num = 0;
		try {
			Connection con = Conexion.makeConnection();
			Statement statement = con.createStatement();
			String sql = "SELECT id FROM articulos;";

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
}
