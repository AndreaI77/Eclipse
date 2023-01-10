package com.profesor.tema3_exercise10esqueleto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManejadorBaseDatos {
	Scanner sc = new Scanner(System.in);
	String url = "jdbc:postgresql://localhost:5433/gestionpersonal";
	String usuario = "postgres";
	String password = "admin";
	
	public ManejadorBaseDatos() 
		throws ClassNotFoundException, SQLException
	{
		checkDataBaseIsCreated();
	}
	
	private void checkDataBaseIsCreated() throws 
		ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5433/";
		String usuario = "postgres";
		String password = "admin";
		
		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement statement = con.createStatement();
		
		String sentenciaSQL = 
				"SELECT * FROM pg_database WHERE datname LIKE 'gestionpersonal';";
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		
		if (!rs.next()) {
			boolean exit = false;
			System.out.println("No se ha encontrado base de datos ");
			do
			{
				System.out.println("Crear base de datos automaticamente? (si/no)");
				String answer = sc.nextLine();
				
				if (answer.equals("si")) 
				{
					createDataBase();
					exit = true;
				}
				else if(answer.equals("no"))
				{
					System.out.println("No se creó la base de datos");
					System.exit(0);
				}
				else
				{
					System.out.println("Error de escritura prueba con (si/no)");
				}
			}
			while(!exit);
		}
		else
		{
			System.out.println("Se ha encontrado la base de datos ");
			boolean exit = false;
			do
			{
				System.out.println("Borrar y crear un nueva base de datos "
						+ "automaticamente? (si/no)");
				String answer = sc.nextLine();
				
				if (answer.equals("si")) 
				{
					createDataBase();
					exit = true;
				}
				else if(answer.equals("no"))
				{
					checkTablesAreCreated();
					exit = true;
				}
				else
				{
					System.out.println("Error de escritura prueba con (si/no)");
				}
			}
			while(!exit);
			
		}
	}
	
	private void checkTablesAreCreated() 
		throws ClassNotFoundException, SQLException {
		Connection con = conection();
		Statement statement = con.createStatement();
		
		String sentenciaSQL = 
				"SELECT count(*) " + 
				"FROM information_schema.tables " + 
				"WHERE table_type = 'BASE TABLE' " + 
				"AND table_name = 'personas' " + 
				"OR table_name = 'clientes' " + 
				"OR table_name = 'funcionarios';";
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		rs.next();
		if(rs.getInt(1) != 3)
		{
			System.out.println("No se ha encontrado tablas ");
			System.out.println("Crear tablas automaticamente? (si/no)");
			String answer = sc.nextLine();
			
			if (answer.equals("si")) {
				createTables();
			}
			else
			{
				System.out.println("No se crearon las tablas");
				System.exit(0);
			}
		}
		else {
			System.out.println("Tablas cargadas con exito");
		}
		
	}
	
	public void createTables() 
			throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		/* En el siguiente String tendremos que concatenar
		 * las diferentes instrucciones de borrado de tablas y su creación
		 * los tipos de datos que queramos hacer y las tablas heredadas
		 * Es el Script de creación de nuestra base de datos
		 */
		
		String sentenciaSQL = "DROP TABLE IF EXISTS funcionarios; \r\n"
				+ "DROP TABLE IF EXISTS clientes;\n"
				+ "DROP TABLE IF EXISTS personas;\n"	
				+ "CREATE TYPE address AS(calle varchar(50), numero int, ciudad varchar(50), provincia varchar (50), c_postal varchar(5));\n" //no he cosegu8ido validar el length de cod postal ni con el domain, por lo tanto, lo valido desde java
				+ "CREATE DOMAIN rango_fechas as DATE check(EXTRACT(YEAR from VALUE) <= (EXTRACT(YEAR FROM CURRENT_DATE)-18) AND EXTRACT(YEAR from VALUE) >(EXTRACT(YEAR FROM CURRENT_DATE)-110));\n"
				+ "CREATE TABLE personas (	numero serial PRIMARY KEY,nombre varchar(50),apellidos varchar(50)[],direccion address,"
				+ "	telefono numeric (9) CHECK(length(telefono::varchar(9)) = 9),fecha_nacimiento rango_fechas);\n"
				+ "CREATE TYPE est AS ENUM('ACTIVO','PENDIENTE','INACTIVO');\n"
				+ "CREATE TYPE tipo AS ENUM('NORMAL','PREMIUM');\n"
				+ "CREATE TYPE cuenta AS(NroCuenta varchar(34), valid boolean);"
				+ "CREATE TABLE clientes( iban cuenta,estado est ,tipoCliente tipo)inherits (personas);\n"
				+ "ALTER TABLE clientes ADD constraint cp_clientes PRIMARY KEY( numero);\n"
				+ "CREATE TYPE grupo AS ENUM('A1','A2','C1','C2', 'AP');\r\n"
				+ "CREATE TYPE cgo AS(codigo grupo,cuerpo varchar(5));\r\n"
				+ "CREATE TABLE funcionarios(departamento varchar(250),cargo cgo, fechaIngreso Date check(fechaIngreso <= CURRENT_DATE))inherits (personas);\n"
				+ "ALTER TABLE funcionarios add constraint cp_funcionarios PRIMARY KEY( numero);\n";
 

		try {
			statement.executeUpdate(sentenciaSQL);
			
		} catch (Exception e) {
			System.out.println("Problemas creando las tablas");
		} finally {
			con.close();
		}
		
		/*
		 * Aquí crearemos los datos de prueba iniciales que queramos insertar
		 * en nuestra base de datos
		 */
		String pruebaPersonas = "INSERT INTO personas(nombre, apellidos, direccion, telefono, fecha_nacimiento) VALUES\r\n"
				+ "		('juan',ARRAY['martinez'],('primera avenida', 12 ,'altea', 'Alicante', '03585'), 987456321, '1999-10-25');\r\n";
		String pruebaCliente = "INSERT INTO clientes(nombre, apellidos, direccion, telefono, fecha_nacimiento, iban, estado, tipoCliente) VALUES\r\n"
				+ "('ana',ARRAY['perez', 'lopez'],('calle mayor', 45,'Benidorm', 'Alicante', '03503'), 123456789, '1978-01-25', ('ES123654789006',false), 'PENDIENTE', 'NORMAL');";
				
		String pruebaFuncionario = "INSERT INTO funcionarios(nombre, apellidos, direccion, telefono, fecha_nacimiento, departamento, cargo, fechaIngreso) VALUES\r\n"
				+ "('Eva',ARRAY['Gonzalez', 'Delgado'],('calle del mar', 257,'Valencia', 'Valencia', '12345'), 789456321, '1985-07-13', 'Contabilidad', ('C1','CFX25'), '2020-08-12');";
				
		update(pruebaPersonas);
		update(pruebaCliente);
		update(pruebaFuncionario);
	}

	private void createDataBase() throws 
	ClassNotFoundException, SQLException 
	{
		System.out.println("Creando base de datos GestionPersonal");
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5433/";
		String usuario = "postgres";
		String password = "admin";
		
		Connection con = DriverManager.getConnection(url, usuario, password);
		Statement statement = con.createStatement();
		
		String sentenciaSQL = "DROP DATABASE IF EXISTS gestionpersonal; "
				+ "CREATE DATABASE gestionpersonal;";
		
		try {
			int errorCode = statement.executeUpdate(sentenciaSQL);
			
			if (errorCode == 0) {
				System.out.println("Se ha creado con exito la base de datos");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			con.close();
		}
		
		createTables();
	}
	
	private Connection conection() throws
	ClassNotFoundException, SQLException
	{
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url, usuario, password);
	}
	
	
	public void update(String sentenceSQL) 
		throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		try {
			statement.executeUpdate(sentenceSQL);
			if(sentenceSQL.startsWith("INS")) {
				System.out.println("Se ha insertado el registro.");
			}else {
				System.out.println("Se ha modificado el registro");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			con.close();
		}
	}
	
	public ResultSet select(String sentenciaSQL) 
		throws ClassNotFoundException, SQLException
	{
		Connection con = conection();
		Statement statement = con.createStatement();
		
		ResultSet rs = statement.executeQuery(sentenciaSQL);
		
		con.close();
		
		return rs;
	}
}
                           
                           
  