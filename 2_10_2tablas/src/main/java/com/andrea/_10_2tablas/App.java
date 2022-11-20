package com.andrea._10_2tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	int num = 0;
    	
    	
    	do {
    		num =Menu.elegirOpcion();
    		
    		switch(num) {
    		case 1: crearCurso();break;
    		case 2: anyadirParticipantes(); break;
    		case 3: verCurso(); break;
    		case 4: System.out.println("Hasta luego");break;
    		default: System.out.println("Opción desconocida");
    		}
    	}while(num != 4);
       
    }
    public static void crearCurso() {
    	Scanner sc = new Scanner(System.in);
    	String nombre = "";
    	String st = "";
    	boolean res= false;
    	LocalDate fecha = null;
    	
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while(nombre.equals("")) {
			System.out.println("Inserta nombre:");
			
				nombre = sc.nextLine();	
		}
		
		while(!res) {
			System.out.print("Inserta fecha de inicio en formato dd/mm/yyyy");
			
				st = sc.nextLine();
			try {
				fecha = LocalDate.parse(st, formatter);
				res= true;
			}catch (DateTimeParseException e) {
				System.out.println("Respete el formato solicitado e introduzca una fecha válida");
				res = false;
			}
		}
    	try {
			Connection con = Conexion.makeConnection();
			Statement statement = con.createStatement();
			String sql = "INSERT INTO cursos (titulo, fechaini) VALUES ('"+nombre+"', '"+fecha+"');";

			int filas = statement.executeUpdate(sql);
			System.out.println("Registros insertados: " + filas);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    public static void anyadirParticipantes() {
    	Scanner sc = new Scanner(System.in);
    	int id= 0, num=0;
    	
    	 boolean res = false;
    	 String nombre = "";
    	 
    	 while(res = false) {
    		 System.out.println("Inserta id");
    			try {
    				id = sc.nextInt();
    				res = true;
    			}catch(Exception e) {
    				System.out.println("El id debe ser un número");
    			}
    	 }
		while(nombre.equals("")) {
			System.out.println("Inserta nombre");
			nombre = sc.nextLine();
		}
		num= elegirCurso();
		
		
		String sql="";
		Connection con = null;
		
		try {
			con = Conexion.makeConnection();
			Statement statement = con.createStatement();
			
			//sql = "INSERT INTO participantes (id, nombre) VALUES ('"+nombre+"', "+fecha+");";

			int filas = statement.executeUpdate(sql);
			System.out.println("Registros insertados: " + filas);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public static int elegirCurso() {
    	Scanner sc = new Scanner(System.in);
		boolean res = false;
		int num = 0;
		List<Integer> lista= new ArrayList<Integer>();
		Connection con = null;
		ResultSet rs = null;
		 try {
			con = Conexion.makeConnection();
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM cursos;";
			 rs = statement.executeQuery(sql);
		        
		        boolean linea = rs.next();
		        if (!linea) {
		        	System.out.println("No se encontraron datos");
		        }else {
		        	System.out.println("id"+ "\t"+"título"+"\t\t"+"fecha inicio");
		            System.out.println("------------------------------------------");
		        	while(linea) {
		        		num = rs.getInt(1);
		        		lista.add(num);
		            	System.out.println(num+"\t"+rs.getString(2)+"\t\t"+rs.getString(3));
		            	linea = rs.next();
		            }
		        }
		        System.out.println();
		        while(!res) {
		        	System.out.println("Inserta el id del curso elegido:");
			        try {
			        	num = sc.nextInt();
			        	res = true;
			        }catch(Exception e) {
			        	System.out.println("no es un número.");
			        	res = false;
			        }
			        res = false;
			        for( int i : lista) {
			        	if(num == i) {
			        		res=true;
			        	}
			        }
			        
		        }
		        
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return num;
	}
    public static void verCurso() {
    	
    }
}
