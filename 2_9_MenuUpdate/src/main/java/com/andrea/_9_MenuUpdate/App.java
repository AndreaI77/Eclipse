package com.andrea._9_MenuUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
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
                System.out.println("1. Buscar\n2. Añadir\n3. Modificar\n4. Salir");
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
	            case 3: 
	            	System.out.println("Inserta el id del artículo a modificar:");
	            	try {
	            		num = sc.nextInt();
	            		if(buscarId(num)){
	            			do {
	            				System.out.println("Inserta nuevo nombre");
	            				
	            				text = sc.nextLine();
	            				
	            			}while(text.equals(""));
	            			
	            			modificar(num,text);
	            		}else {
	            			System.out.println("Id no válido");
	            		}
	            	}catch(Exception e) {
	            		System.out.println("No es un número");
	            		num=0;
	            	}
	            
	            	break;
	    	        	
	            case 4: System.out.println("Adios");
	            		break;
	            default: System.out.println("Opción desconocida");
            }
            res=false;
        }while(num!=4);
        sc.close();
    }
    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
   	 	String url = "jdbc:postgresql://localhost:5433/dia01";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url,usuario,password);
		return con;
	}
    public static void buscar(String text) {
    	Connection con = null;
		ResultSet rs = null;
    	try {
			con = makeConnection();
			 Statement statement = con .createStatement();
			 String sql = "SELECT * FROM articulos WHERE nombre LIKE '%"+text+"%';";
			 
			 rs = statement.executeQuery(sql);
		        
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
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    public static void insertar(String nombre, float precio) {
    	
    	// Ya que el primary key no es serial, primero hay que averiguar el último id
    	int num = obtenerId();
    	num++;
    	Connection con = null;
		try {
			con = makeConnection();
			Statement statement = con.createStatement();
			String sql = "INSERT INTO articulos (id,nombre,precio) VALUES ("+num+", '"+nombre+"', "+precio+");";

			int filas = statement.executeUpdate(sql);
			System.out.println("filas insertadas: " + filas);
			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
	public static int obtenerId() {
		int num = 0;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = makeConnection();
			Statement statement = con.createStatement();
			String sql = "SELECT id FROM articulos;";

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				num = rs.getInt(1);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return num;
	}
	
	public static boolean buscarId(int num) {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = makeConnection();
			Statement statement = con.createStatement();
			String sql = "SELECT id FROM articulos;";

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				if(num == rs.getInt(1)) {
					rs.close();
					con.close();
					return true;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	public static void modificar(int id, String nombre) {
		Connection con = null;
		try {
			con = makeConnection();
			Statement statement = con.createStatement();
			String sql = "UPDATE articulos SET nombre ='"+nombre+"' WHERE id = "+id+";";

			int filas = statement.executeUpdate(sql);
			System.out.println("Registros modificados: " + filas);
			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
