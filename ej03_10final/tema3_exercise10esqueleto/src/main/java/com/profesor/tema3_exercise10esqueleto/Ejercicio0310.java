package com.profesor.tema3_exercise10esqueleto;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio0310 {
	
	private static int numero, num_casa,c_p;
	private static String nombre,ap,dir, calle, ciudad, provincia, iban, departamento, cuerpo, c_postal, patron;
	private static String[] apellidos, crp,cuenta;
	private static String [] direccion;
	private static LocalDate f_nac, f_ingreso, hoy;
	private static long telefono;
	private static Estado estado;
	private static Tipo tipo;
	private static Grupo grupo;
	private static boolean isValid, res;
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException{
		ManejadorBaseDatos manejador = new ManejadorBaseDatos();
		
		boolean exit = false;
		
		do
		{
			ShowMenu();
			switch (getOption()) {
			case 1:
				addPersona(manejador);
				break;
			case 2:
				addCliente(manejador);		
				break;
			case 3:
				addFuncionario(manejador);			
				break;
			case 4:
				modifyPersona(manejador);			
				break;
			case 5:
				modifyCliente(manejador);			
				break;
			case 6:
				modifyFuncionario(manejador);			
				break;
			case 7:
				showPersonas(manejador);			
				break;
			case 8:
				showClientes(manejador);			
				break;
			case 9:
				showFuncionarios(manejador);			
				break;
			case 0:
			System.out.println("Hasta luego");
		
				exit = true;
				break;
			default:
				break;
			}
		}
		while(!exit);
	}
	
	private static int getOption() {
		System.out.println("Option:");
		int option = sc.nextInt();
		sc.nextLine();
		return option;
	}

	private static void ShowMenu()
	{
		System.out.println();
		System.out.println("1.-Añadir persona");
		System.out.println("2.-Añadir cliente");
		System.out.println("3.-Añadir funcionario");
		System.out.println("4.-Modificar persona");
		System.out.println("5.-Modificar cliente");
		System.out.println("6.-Modificar funcionario");
		System.out.println("7.-Ver personas");
		System.out.println("8.-Ver clientes");
		System.out.println("9.-Ver funcionarios");
		System.out.println("0.-Salir");
	}
	
	
	private static void addPersona(ManejadorBaseDatos manejador) 
			throws ClassNotFoundException, SQLException 
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			res = false;
			nombre = "";
			while( nombre.equals("")) {
				System.out.println("Inserta el nombre:");
				nombre = sc.nextLine();
			}
			ap="";
			while( ap.equals("")) {
				System.out.println("Inserta todos los apellidos separados por comas:");
				ap = sc.nextLine();
				if(ap.length()>0) {
					apellidos= ap.trim().split(",");
				}
			}
			calle = "";
			while( calle.equals("")) {
				System.out.println("Inserta el nombre de la calle:");
				calle = sc.nextLine();
			}
			num_casa = 0;
			res=false;
			while(!res) {
				System.out.println("Escribe el número del domicilio:" );
				 dir = sc.nextLine();
					try {
						num_casa = Integer.parseInt(dir);
						res = true;
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
			}
			ciudad = "";
			while(ciudad.equals("")) {
				System.out.println("Inserta el nombre de la ciudad:");
				ciudad = sc.nextLine();
			}
			provincia = "";
			while(provincia.equals("")) {
				System.out.println("Inserta el nombre de la provincia:");
				provincia = sc.nextLine();
			}
			c_p = 0;
			c_postal="";
			res=false;
			while(!res) {
				System.out.println("Inserta el código postal:" );
				 dir = sc.nextLine();
				try {
					if(dir.trim().length()== 5){
						c_p= Integer.parseInt(dir);
						res= true;
						c_postal = dir;
					}else {
						System.out.println("El código postal debe tener 5 números.");
						res = false;
					}	
					
				}catch(Exception e) {
					System.out.println("No es un número");
					System.out.println();
					res=false;
				}
			}
			telefono = 0;
			res=false;
			while(!res) {
				System.out.println("Inserta el número de teléfono (9 números sin espacios) :" );
				 dir = sc.nextLine();
				try {
					if(dir.trim().length()== 9) {
						if(dir.startsWith("0")) {
							System.out.println("El número no debe empezar por 0");
							res = false;
						}else {
							telefono= Long.parseLong(dir);
							res= true;	
						}
						
					}else {
						System.out.println("El teléfono debe tener 9 números.");
						res = false;
					}	
					
				}catch(Exception e) {
					System.out.println("No es un número");
					System.out.println();
					res=false;
				}
			}
			res=false;
			while (!res) {
				System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
				dir = sc.nextLine();
				try {
					f_nac = LocalDate.parse(dir, formatter);
					hoy = LocalDate.now();
					if(f_nac.isAfter(hoy)) {
						System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
					}else if(f_nac.plus(18,ChronoUnit.YEARS).isAfter(hoy)) {
						System.err.println("No aceptamos menores de 18 años");
					}else if(f_nac.isBefore(hoy.minusYears(110))) {
						System.err.println("No aceptamos mayores de 110 años");
						
					}else {
						res = true;
					}
					
				} catch (Exception e) {
					System.out.println("El formato no es correcto.");
					System.out.println();
					res = false;
				}
			}
			
			ap = String.join("$$, $$", apellidos);
			
			String sql = "INSERT INTO personas(nombre, apellidos, direccion, telefono, fecha_nacimiento) VALUES"
					+ "($a$"+nombre+"$a$,ARRAY[$$"+ap+"$$],($b$"+calle+"$b$, "+num_casa+" ,$c$"+ciudad+"$c$, $d$"+provincia+"$d$,'"+ c_postal+"'), "+telefono+",'"+f_nac+"');";
			manejador.update(sql);

		}

	private static void addCliente(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		res = false;
		nombre = "";
		while( nombre.equals("")) {
			System.out.println("Inserta el nombre:");
			nombre = sc.nextLine();
		}
		ap="";
		while( ap.equals("")) {
			System.out.println("Inserta todos los apellidos separados por comas:");
			ap = sc.nextLine();
			if(ap.length()>0) {
				apellidos= ap.trim().split(",");
			}
		}
		calle = "";
		while( calle.equals("")) {
			System.out.println("Inserta el nombre de la calle:");
			calle = sc.nextLine();
		}
		num_casa = 0;
		res=false;
		while(!res) {
			System.out.println("Escribe el número del domicilio:" );
			 dir = sc.nextLine();
				try {
					num_casa = Integer.parseInt(dir);
					res = true;
				} catch (Exception e) {
					System.out.println("No es un número");
					System.out.println();
					res = false;
				}
		}
		ciudad = "";
		while(ciudad.equals("")) {
			System.out.println("Inserta el nombre de la ciudad:");
			ciudad = sc.nextLine();
		}
		provincia = "";
		while(provincia.equals("")) {
			System.out.println("Inserta el nombre de la provincia:");
			provincia = sc.nextLine();
		}
		c_postal = "";
		c_p=0;
		res=false;
		while(!res) {
			System.out.println("Inserta el código postal:" );
			 dir = sc.nextLine();
			try {
				if(dir.trim().length()== 5){
					c_p= Integer.parseInt(dir);
					res= true;
					c_postal = dir;
				}else {
					System.out.println("El código postal debe tener 5 números.");
					res = false;
				}	
				
			}catch(Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res=false;
			}
		}
		telefono = 0;
		res=false;
		while(!res) {
			System.out.println("Inserta el número de teléfono (9 números sin espacios) :" );
			 dir = sc.nextLine();
			try {
				if(dir.trim().length()== 9){
					if(dir.startsWith("0")) {
						System.out.println("El número no debe empezar por 0");
						res = false;
					}else {
						telefono= Long.parseLong(dir);
						res= true;	
					}
					
				}else {
					System.out.println("El teléfono debe tener 9 números.");
					res = false;
				}	
				
			}catch(Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res=false;
			}
		}
		res=false;
		while (!res) {
			System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
			dir = sc.nextLine();
			try {
				f_nac = LocalDate.parse(dir, formatter);
				hoy = LocalDate.now();
				if(f_nac.isAfter(hoy)) {
					System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
				}else if(f_nac.plus(18,ChronoUnit.YEARS).isAfter(hoy)) {
					System.err.println("No aceptamos menores de 18 años");
				}else if(f_nac.isBefore(hoy.minusYears(110))) {
					System.err.println("No aceptamos mayores de 110 años");
					
				}else {
					res = true;
				}
				
			} catch (Exception e) {
				System.out.println("El formato no es correcto.");
				System.out.println();
				res = false;
			}
		}
		iban = "";
		res=false;
		isValid=false;
		int codAscii=0,resto=0,dc=0, i=2;
		String cadenaDC="";
		BigInteger cuentaNum = new BigInteger("0");
		BigInteger modo = new BigInteger("97");
		while(!res) {
			System.out.println("Inserta número IBAN:");
			iban = sc.nextLine().trim();
			if(iban.substring(0,2).equals("ES") && iban.length()==24){
				res=true;
				//compruebo que los carácteres son numéricos
				do {
					codAscii =iban.codePointAt(i);
					isValid=(codAscii>47 && codAscii<58);
					i++;
				}while(i<iban.length() && isValid);
				if(isValid) {
					cuentaNum = new BigInteger(iban.substring(4,24)+"142800");
					resto = cuentaNum.mod(modo).intValue();
					dc=98-resto;
					cadenaDC = String.valueOf(dc);
				}
				if(iban.substring(2,4).equals(cadenaDC)) {
					
					isValid=true;
				}else {
					isValid=false;
					
					System.out.println("El IBAN no es válido");
				}
				
			}else{
				System.out.println("IBAN español debe tener 24 carácteres y empezar por 'ES'");
				res=false;
			}
		}
			if(isValid==false) {
				System.out.println("Se insertará el cliente con el IBAN incorrecto, pero su estado quedará \"pendiente\".");
				estado = Estado.PENDIENTE;
			}else{
				res=false;
				while(!res) {
					System.out.println("Inserta estado:");
					System.out.println("Inserta el número de la opción:");
					Estado arr[] = Estado.values();
					
					for(Estado est : arr) {
						System.out.println((est.ordinal()+1)+". "+est);
					}
					dir = sc.nextLine();
					try {
						numero = Integer.parseInt(dir);
						if(numero <= arr.length) {
							estado = arr[numero-1];
							res=true;
						}else {
							System.out.println("Opción desconocida.");
							res=false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
						
					}
					
				}
			res=false;	
			while(!res) {
				System.out.println("Inserta el tipo de cliente:");
				System.out.println("Inserta el número de la opción:");
				Tipo arrT[] = Tipo.values();
				
				for(Tipo tp : arrT) {
					System.out.println((tp.ordinal()+1)+". "+tp);
				}
				dir = sc.nextLine();
				try {
					numero = Integer.parseInt(dir);
					if(numero <= arrT.length) {
						tipo = arrT[numero-1];
						
						res=true;
					}else {
						System.out.println("Opción desconocida.");
						res=false;
					}
				} catch (Exception e) {
					System.out.println("No es un número");
					System.out.println();
					res = false;
				}
					
				}
			System.out.println();
			ap = String.join("$$, $$", apellidos);
			
		String sql = "INSERT INTO clientes(nombre, apellidos, direccion, telefono, fecha_nacimiento,iban, estado, tipoCliente) VALUES"
				+ "($a$"+nombre+"$a$,ARRAY[$$"+ap+"$$],($b$"+calle+"$b$, "+num_casa+" ,$c$"+ciudad+"$c$, $d$"+provincia+"$d$,'"+ c_postal+"'), "+telefono+",'"+f_nac+"',('"+iban+"',"+isValid+"),'"+estado+"','"+tipo+"');";
		manejador.update(sql);
		
	}
	
	private static void addFuncionario(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		res = false;
		nombre = "";
		while( nombre.equals("")) {
			System.out.println("Inserta el nombre:");
			nombre = sc.nextLine();
		}
		ap="";
		while( ap.equals("")) {
			System.out.println("Inserta todos los apellidos separados por comas:");
			ap = sc.nextLine();
			if(ap.length()>0) {
				apellidos= ap.trim().split(",");
			}
		}
		calle = "";
		while( calle.equals("")) {
			System.out.println("Inserta el nombre de la calle:");
			calle = sc.nextLine();
		}
		num_casa = 0;
		res=false;
		while (!res) {
			System.out.println("Escribe el número del domicilio:");
			dir = sc.nextLine();
			try {
				num_casa = Integer.parseInt(dir);
				res = true;
			} catch (Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res = false;
			}
		}
		ciudad = "";
		while(ciudad.equals("")) {
			System.out.println("Inserta el nombre de la ciudad:");
			ciudad = sc.nextLine();
		}
		provincia = "";
		while(provincia.equals("")) {
			System.out.println("Inserta el nombre de la provincia:");
			provincia = sc.nextLine();
		}
		c_postal = "";
		c_p = 0;
		res=false;
		while(!res) {
			System.out.println("Inserta el código postal:" );
			 dir = sc.nextLine();
			try {
				if(dir.trim().length()== 5){
					c_p= Integer.parseInt(dir);
					res= true;
					c_postal = dir;
				}else {
					System.out.println("El código postal debe tener 5 números.");
					res = false;
				}					
			}catch(Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res=false;
			}
		}
		telefono = 0;
		res=false;
		while(!res) {
			System.out.println("Inserta el número de teléfono (9 números sin espacios) :" );
			 dir = sc.nextLine();
			try {
				if(dir.trim().length()== 9){
					if(dir.startsWith("0")) {
						System.out.println("El número no debe empezar por 0");
						res = false;
					}else {
						telefono= Long.parseLong(dir);
						res= true;	
					}
					
				}else {
					System.out.println("El teléfono debe tener 9 números.");
					res = false;
				}		
			}catch(Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res=false;
			}
		}
		res=false;
		while (!res) {
			System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
			dir = sc.nextLine();
			try {
				f_nac = LocalDate.parse(dir, formatter);
				hoy = LocalDate.now();
				if(f_nac.isAfter(hoy)) {
					System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
				}else if(f_nac.plus(18,ChronoUnit.YEARS).isAfter(hoy)) {
					System.err.println("No aceptamos menores de 18 años");
				}else if(f_nac.isBefore(hoy.minusYears(110))) {
					System.err.println("No aceptamos mayores de 110 años");					
				}else {
					res = true;
				}				
			} catch (Exception e) {
				System.out.println("El formato no es correcto.");
				System.out.println();
				res = false;
			}
		}
		departamento = "";
		while (departamento.equals("")) {
			System.out.println("Inserta el departamento:");
			departamento = sc.nextLine();
		}

		res = false;
		while (!res) {
			System.out.println("Inserta el número del grupo:");
			Grupo arr[] = Grupo.values();

			for (Grupo gp : arr) {
				System.out.println((gp.ordinal() + 1) + ". " + gp);
			}
			dir = sc.nextLine();
			try {
				numero = Integer.parseInt(dir);
				if (numero <= arr.length) {
					grupo = arr[numero - 1];
					res = true;
				} else {
					System.out.println("Opción desconocida.");
					res = false;
				}
			} catch (Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res = false;
			}
		}
		res = false;
		cuerpo = "";
		patron = "";
		System.out.println(grupo);
		
		switch(grupo) {
			case A1: patron = "A[A-Z]{1,2}[0-9]{2,3}"; break;
			case A2: patron = "B[A-Z]{0,2}[0-9]{2,4}"; break;
			case C1: patron = "C[A-Z]{2}[0-9]{2}"; break;
			case C2: patron = "D[A-Z]{2}[0-9]{2}"; break;
			case AP: patron = "ASP00"; break;
		}
		while (!res) {
			System.out.println("Inserta el código del cuerpo");
			cuerpo = sc.nextLine();
			if (cuerpo.length() == 5) {
				Pattern pat = Pattern.compile(patron);
				Matcher mat = pat.matcher(cuerpo);
				if(mat.matches()) {
					res = true;
				}else {
					res=false;
					System.out.println("El código del cuerpo no es correcto");
				}

			} else {
				System.out.println("El código del cuerpo debe tener 5 carácteres");
				res = false;
			}
		}
		res=false;
		while (!res) {
			System.out.println("Inserta la fecha de ingreso en formato dd/mm/yyyy :");
			dir = sc.nextLine();
			try {
				f_ingreso = LocalDate.parse(dir, formatter);
				
				if(f_ingreso.isAfter(hoy)) {
					System.out.println("La fecha de ingreso no puede ser posterior a hoy.");
				}else if(f_ingreso.isBefore(hoy.minusYears(60))) {
					System.out.println("La fecha de ingreso no es correcta");					
				}else {
					res = true;
				}				
			} catch (Exception e) {
				System.out.println("El formato no es correcto.");
				System.out.println();
				res = false;
			}
		}
		ap = String.join("$$, $$", apellidos);
		
		String sql = "INSERT INTO funcionarios (nombre, apellidos, direccion, telefono, fecha_nacimiento,departamento, cargo, fechaIngreso) VALUES"
				+ "($a$"+nombre+"$a$,ARRAY[$$"+ap+"$$],($b$"+calle+"$b$, "+num_casa+" ,$c$"+ciudad+"$c$, $d$"+provincia+"$d$,'"+ c_postal+"'), "+telefono+",'"+f_nac+"',$e$"+departamento+"$e$,('"+grupo+"','"+cuerpo+"'),'"+f_ingreso+"');";
		
		manejador.update(sql);
	}
	
	private static void modifyPersona(ManejadorBaseDatos manejador) throws ClassNotFoundException, SQLException {
		Persona persona = null;
		res = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while (!res) {
			System.out.println("Escribe el número de la persona a modificar:");
			dir = sc.nextLine();
			try {
				numero = Integer.parseInt(dir);
				res = true;
			} catch (Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res = false;
			}
		}
		String sql = "Select * from only personas where numero =" + numero + ";";
		ResultSet rs = manejador.select(sql);

		while (rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if (nombre.startsWith("\"")) {
				nombre = nombre.substring(1, nombre.length() - 1);
			}
			apellidos = rs.getString(3).substring(1, rs.getString(3).length() - 1).split(",");
			direccion = rs.getString(4).substring(1, rs.getString(4).length() - 1).split(",");
			if (direccion[0].startsWith("\"")) {
				calle = direccion[0].substring(1, direccion[0].length() - 1);
			} else {
				calle = direccion[0];
			}

			num_casa = Integer.parseInt(direccion[1]);
			if (direccion[2].startsWith("\"")) {
				ciudad = direccion[2].substring(1, direccion[2].length() - 1);
			} else {
				ciudad = direccion[2];
			}

			if (direccion[3].startsWith("\"")) {
				provincia = direccion[3].substring(1, direccion[3].length() - 1);
			} else {
				provincia = direccion[3];
			}

			c_postal = direccion[4];
			telefono = rs.getLong(5);

			f_nac = LocalDate.parse(rs.getString(6));

			persona = new Persona(numero, nombre, apellidos, calle, num_casa, ciudad, provincia, c_postal, telefono,
					f_nac);
			System.out.println(persona);
		}
		if (persona == null) {
			System.out.println("No existe persona con este número");
		} else {
			System.out.println();
			System.out.println(
					"Inserta los datos en los campos que quieres modificar, en otro caso, presiona enter para pasar al campo siguiente");

			nombre = "";
			System.out.println("Inserta nombre:");
			nombre = sc.nextLine();
			if (!nombre.equals("")) {
				persona.setNombre(nombre);
			}

			System.out.println("Inserta todos los apellidos separados por comas:");
			ap = sc.nextLine();

			if (ap.length() > 0) {
				apellidos = ap.trim().split(",");
				persona.setApellidos(apellidos);
			}

			System.out.println("Inserta el nombre de la calle:");
			calle = sc.nextLine();
			if (!calle.equals("")) {
				persona.setCalle(calle);
			}

			res = false;
			while (!res) {
				System.out.println("Inserta el número del domicilio:");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						num_casa = Integer.parseInt(dir);
						persona.setNum_casa(num_casa);
						res = true;
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}

			System.out.println("Inserta el nombre de la ciudad:");
			ciudad = sc.nextLine();
			if (!ciudad.equals("")) {
				persona.setCiudad(ciudad);
			}

			System.out.println("Inserta el nombre de la provincia:");
			provincia = sc.nextLine();
			if (!provincia.equals("")) {
				persona.setProvincia(provincia);
			}

			res = false;
			c_postal = "";
			while (!res) {
				System.out.println("Inserta el código postal:");
				c_postal = sc.nextLine();
				if (!c_postal.equals("")) {
					try {
						if (c_postal.trim().length() == 5) {
							c_p = Integer.parseInt(c_postal);
							res = true;
							persona.setC_postal(c_postal);
						} else {
							System.out.println("El código postal debe tener 5 números.");
							res = false;
						}

					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			telefono = 0;
			res = false;
			while (!res) {
				System.out.println("Inserta el número de teléfono (9 números sin espacios) :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						if (dir.trim().length() == 9) {
							if (dir.startsWith("0")) {
								System.out.println("El número no debe empezar por 0");
								res = false;
							} else {
								telefono = Long.parseLong(dir);
								res = true;
								persona.setTelefono(telefono);
							}
						} else {
							System.out.println("El teléfono debe tener 9 números.");
							res = false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			res = false;
			while (!res) {
				System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						f_nac = LocalDate.parse(dir, formatter);
						hoy = LocalDate.now();
						if (f_nac.isAfter(hoy)) {
							System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
							res = false;
						} else if (f_nac.plus(18, ChronoUnit.YEARS).isAfter(hoy)) {
							System.err.println("No aceptamos menores de 18 años");
							res = false;
						} else if (f_nac.isBefore(hoy.minusYears(110))) {
							System.err.println("No aceptamos mayores de 110 años");
							res = false;
						} else {
							persona.setF_nac(f_nac);
							res = true;
						}
					} catch (Exception e) {
						System.out.println("El formato no es correcto.");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			ap = String.join("$$, $$", persona.getApellidos());
			sql = "UPDATE personas SET nombre =$a$" + persona.getNombre() + "$a$, apellidos =ARRAY[$$" + ap
					+ "$$],direccion = ($b$" + persona.getCalle() + "$b$, " + persona.getNum_casa() + " ,$c$"
					+ persona.getCiudad() + "$c$, $d$" + persona.getProvincia() + "$d$,'" + persona.getC_postal()
					+ "'),telefono=" + persona.getTelefono() + ",fecha_nacimiento ='" + persona.getF_nac()
					+ "' WHERE numero = " + persona.getNumero() + " ;";

			manejador.update(sql);
		}
	}
	
	private static void modifyCliente(ManejadorBaseDatos manejador) throws ClassNotFoundException, SQLException {
		Cliente cliente = null;
		res = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while (!res) {
			System.out.println("Escribe el número del cliente a modificar:");
			dir = sc.nextLine();
			try {
				numero = Integer.parseInt(dir);
				res = true;
			} catch (Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res = false;
			}
		}
		String sql = "Select * from clientes where numero =" + numero + ";";
		ResultSet rs = manejador.select(sql);

		while (rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if (nombre.startsWith("\"")) {
				nombre = nombre.substring(1, nombre.length() - 1);
			}
			apellidos = rs.getString(3).substring(1, rs.getString(3).length() - 1).split(",");
			direccion = rs.getString(4).substring(1, rs.getString(4).length() - 1).split(",");
			if (direccion[0].startsWith("\"")) {
				calle = direccion[0].substring(1, direccion[0].length() - 1);
			} else {
				calle = direccion[0];
			}

			num_casa = Integer.parseInt(direccion[1]);
			if (direccion[2].startsWith("\"")) {
				ciudad = direccion[2].substring(1, direccion[2].length() - 1);
			} else {
				ciudad = direccion[2];
			}

			if (direccion[3].startsWith("\"")) {
				provincia = direccion[3].substring(1, direccion[3].length() - 1);
			} else {
				provincia = direccion[3];
			}

			c_postal = direccion[4];
			telefono = rs.getLong(5);

			f_nac = LocalDate.parse(rs.getString(6));
			cuenta = rs.getString(7).substring(1, rs.getString(7).length() - 1).split(",");
			iban = cuenta[0];
			isValid = Boolean.parseBoolean(cuenta[1]);
			estado = Estado.valueOf(rs.getString(8).toUpperCase());
			tipo = Tipo.valueOf(rs.getString(9).toUpperCase());
			cliente = new Cliente(numero, nombre, apellidos, calle, num_casa, ciudad, provincia, c_postal, telefono,
					f_nac, iban, isValid, estado, tipo);
			
			if (!isValid) {
				System.err.println("Código IBAN del cliente no es válido");
			}
			System.out.println(cliente);
		}
		System.out.println();
		if (cliente == null) {
			System.out.println("No existe cliente con este número");
		} else {
			System.out.println();
			System.out.println(
					"Inserta los datos en los campos que quieres modificar, en otro caso, presiona enter para pasar al campo siguiente");

			nombre = "";
			System.out.println("Inserta nombre:");
			nombre = sc.nextLine();
			if (!nombre.equals("")) {
				cliente.setNombre(nombre);
			}

			System.out.println("Inserta todos los apellidos separados por comas:");
			ap = sc.nextLine();

			if (ap.length() > 0) {
				apellidos = ap.trim().split(",");
				cliente.setApellidos(apellidos);
			}

			System.out.println("Inserta el nombre de la calle:");
			calle = sc.nextLine();
			if (!calle.equals("")) {
				cliente.setCalle(calle);
			}

			res = false;
			while (!res) {
				System.out.println("Inserta el número del domicilio:");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						num_casa = Integer.parseInt(dir);
						cliente.setNum_casa(num_casa);
						res = true;
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}

			System.out.println("Inserta el nombre de la ciudad:");
			ciudad = sc.nextLine();
			if (!ciudad.equals("")) {
				cliente.setCiudad(ciudad);
			}

			System.out.println("Inserta el nombre de la provincia:");
			provincia = sc.nextLine();
			if (!provincia.equals("")) {
				cliente.setProvincia(provincia);
			}

			res = false;
			c_postal = "";
			while (!res) {
				System.out.println("Inserta el código postal:");
				c_postal = sc.nextLine();
				if (!c_postal.equals("")) {
					try {
						if (c_postal.trim().length() == 5) {
							c_p = Integer.parseInt(c_postal);
							res = true;
							cliente.setC_postal(c_postal);
						} else {
							System.out.println("El código postal debe tener 5 números.");
							res = false;
						}

					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			telefono = 0;
			res = false;
			while (!res) {
				System.out.println("Inserta el número de teléfono (9 números sin espacios) :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						if (dir.trim().length() == 9) {
							if (dir.startsWith("0")) {
								System.out.println("El número no debe empezar por 0");
								res = false;
							} else {
								telefono = Long.parseLong(dir);
								res = true;
								cliente.setTelefono(telefono);
							}
						} else {
							System.out.println("El teléfono debe tener 9 números.");
							res = false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			res = false;
			while (!res) {
				System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						f_nac = LocalDate.parse(dir, formatter);
						hoy = LocalDate.now();
						if (f_nac.isAfter(hoy)) {
							System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
							res = false;
						} else if (f_nac.plus(18, ChronoUnit.YEARS).isAfter(hoy)) {
							System.err.println("No aceptamos menores de 18 años");
							res = false;
						} else if (f_nac.isBefore(hoy.minusYears(110))) {
							System.err.println("No aceptamos mayores de 110 años");
							res = false;
						} else {
							cliente.setF_nac(f_nac);
							res = true;
						}
					} catch (Exception e) {
						System.out.println("El formato no es correcto.");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			iban = "";
			res = false;
			isValid = false;
			int codAscii = 0, resto = 0, dc = 0, i = 2;
			String cadenaDC = "";
			BigInteger cuentaNum = new BigInteger("0");
			BigInteger modo = new BigInteger("97");
			while (!res) {
				System.out.println("Inserta número IBAN:");
				iban = sc.nextLine().trim();
				if (!iban.equals("")) {
					if (iban.substring(0, 2).equals("ES") && iban.length() == 24) {
						res = true;
						// compruebo que los carácteres son numéricos
						do {
							codAscii = iban.codePointAt(i);
							isValid = (codAscii > 47 && codAscii < 58);
							i++;
						} while (i < iban.length() && isValid);
						if (isValid) {
							cuentaNum = new BigInteger(iban.substring(4, 24) + "142800");
							resto = cuentaNum.mod(modo).intValue();
							dc = 98 - resto;
							cadenaDC = String.valueOf(dc);
						}
						if (iban.substring(2, 4).equals(cadenaDC)) {
							isValid = true;

						} else {
							isValid = false;
							System.out.println("El IBAN no es válido");
						}
						cliente.setIban(iban);
						cliente.setValid(isValid);
					} else {
						System.out.println("IBAN español debe tener 24 carácteres y empezar por 'ES'");
						res = false;
					}
				} else {
					res = true;
				}
			}
			if (cliente.isValid() == false && !iban.equals("")) {
				System.out.println(
						"Se insertará el cliente con el IBAN incorrecto, pero su estado quedará \"pendiente\".");
				cliente.setEstado(Estado.PENDIENTE);
			} else if (cliente.isValid() == true) {
				res = false;
				while (!res) {
					System.out.println("Inserta estado:");
					System.out.println("Inserta el número de la opción:");
					Estado arr[] = Estado.values();

					for (Estado est : arr) {
						System.out.println((est.ordinal() + 1) + ". " + est);
					}
					dir = sc.nextLine();
					if (!dir.equals("")) {
						try {
							numero = Integer.parseInt(dir);
							if (numero <= arr.length) {
								estado = arr[numero - 1];
								cliente.setEstado(estado);
								res = true;
							} else {
								System.out.println("Opción desconocida.");
								res = false;
							}
						} catch (Exception e) {
							System.out.println("No es un número");
							System.out.println();
							res = false;
						}
					} else {
						res = true;
					}
				}
			}
			res = false;
			while (!res) {
				System.out.println("Inserta el tipo de cliente:");
				System.out.println("Inserta el número de la opción:");
				Tipo arrT[] = Tipo.values();

				for (Tipo tp : arrT) {
					System.out.println((tp.ordinal() + 1) + ". " + tp);
				}
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						numero = Integer.parseInt(dir);
						if (numero <= arrT.length) {
							tipo = arrT[numero - 1];
							cliente.setTipoCliente(tipo);
							res = true;
						} else {
							System.out.println("Opción desconocida.");
							res = false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			System.out.println();

			ap = String.join("$$, $$", cliente.getApellidos());
			sql = "UPDATE clientes SET nombre =$a$" + cliente.getNombre() + "$a$, apellidos =ARRAY[$$" + ap
					+ "$$],direccion = ($b$" + cliente.getCalle() + "$b$, " + cliente.getNum_casa() + " ,$c$"
					+ cliente.getCiudad() + "$c$, $d$" + cliente.getProvincia() + "$d$,'" + cliente.getC_postal()
					+ "'),telefono=" + cliente.getTelefono() + ",fecha_nacimiento ='" + cliente.getF_nac()
					+ "', iban =('" + cliente.getIban() + "'," + cliente.isValid() + "), estado = '"
					+ cliente.getEstado() + "', tipoCliente='" + cliente.getTipoCliente() + "' WHERE numero = "
					+ cliente.getNumero() + " ;";

			manejador.update(sql);
		}
	}
	
	private static void modifyFuncionario(ManejadorBaseDatos manejador) throws ClassNotFoundException, SQLException {
		Funcionario func = null;
		res = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while (!res) {
			System.out.println("Escribe el número del funcionario a modificar:");
			dir = sc.nextLine();
			try {
				numero = Integer.parseInt(dir);
				res = true;
			} catch (Exception e) {
				System.out.println("No es un número");
				System.out.println();
				res = false;
			}
		}
		String sql = "Select * from funcionarios where numero =" + numero + ";";
		ResultSet rs = manejador.select(sql);

		while (rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if (nombre.startsWith("\"")) {
				nombre = nombre.substring(1, nombre.length() - 1);
			}
			apellidos = rs.getString(3).substring(1, rs.getString(3).length() - 1).split(",");
			direccion = rs.getString(4).substring(1, rs.getString(4).length() - 1).split(",");
			if (direccion[0].startsWith("\"")) {
				calle = direccion[0].substring(1, direccion[0].length() - 1);
			} else {
				calle = direccion[0];
			}

			num_casa = Integer.parseInt(direccion[1]);
			if (direccion[2].startsWith("\"")) {
				ciudad = direccion[2].substring(1, direccion[2].length() - 1);
			} else {
				ciudad = direccion[2];
			}

			if (direccion[3].startsWith("\"")) {
				provincia = direccion[3].substring(1, direccion[3].length() - 1);
			} else {
				provincia = direccion[3];
			}

			c_postal = direccion[4];
			telefono = rs.getLong(5);
			f_nac = LocalDate.parse(rs.getString(6));
			departamento = rs.getString(7);
			crp = rs.getString(8).substring(1, rs.getString(8).length() - 1).split(",");
			grupo = Grupo.valueOf(crp[0].toUpperCase());
			cuerpo = crp[1];
			f_ingreso = LocalDate.parse(rs.getString(9));

			func = new Funcionario(numero, nombre, apellidos, calle, num_casa, ciudad, provincia, c_postal, telefono,
					f_nac, departamento, grupo, cuerpo, f_ingreso);
			System.out.println(func);

		}
		if (func == null) {
			System.out.println("No existe funcionario con este número");
		} else {
			System.out.println();
			System.out.println(
					"Inserta los datos en los campos que quieres modificar, en otro caso, presiona enter para pasar al campo siguiente");

			nombre = "";
			System.out.println("Inserta nombre:");
			nombre = sc.nextLine();
			if (!nombre.equals("")) {
				func.setNombre(nombre);
			}

			System.out.println("Inserta todos los apellidos separados por comas:");
			ap = sc.nextLine();

			if (ap.length() > 0) {
				apellidos = ap.trim().split(",");
				func.setApellidos(apellidos);
			}

			System.out.println("Inserta el nombre de la calle:");
			calle = sc.nextLine();
			if (!calle.equals("")) {
				func.setCalle(calle);
			}

			res = false;
			while (!res) {
				System.out.println("Inserta el número del domicilio:");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						num_casa = Integer.parseInt(dir);
						func.setNum_casa(num_casa);
						res = true;
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}

			System.out.println("Inserta el nombre de la ciudad:");
			ciudad = sc.nextLine();
			if (!ciudad.equals("")) {
				func.setCiudad(ciudad);
			}

			System.out.println("Inserta el nombre de la provincia:");
			provincia = sc.nextLine();
			if (!provincia.equals("")) {
				func.setProvincia(provincia);
			}

			res = false;
			c_postal = "";
			while (!res) {
				System.out.println("Inserta el código postal:");
				c_postal = sc.nextLine();
				if (!c_postal.equals("")) {
					try {
						if (c_postal.trim().length() == 5) {
							c_p = Integer.parseInt(c_postal);
							res = true;
							func.setC_postal(c_postal);
						} else {
							System.out.println("El código postal debe tener 5 números.");
							res = false;
						}

					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			telefono = 0;
			res = false;

			while (!res) {
				System.out.println("Inserta el número de teléfono (9 números sin espacios) :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						if (dir.trim().length() == 9) {
							if (dir.startsWith("0")) {
								System.out.println("El número no debe empezar por 0");
								res = false;
							} else {
								telefono = Long.parseLong(dir);
								res = true;
								func.setTelefono(telefono);
							}
						} else {
							System.out.println("El teléfono debe tener 9 números.");
							res = false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			res = false;
			while (!res) {
				System.out.println("Inserta la fecha de nacimiento en formato dd/mm/yyyy :");
				dir = sc.nextLine();
				if (!dir.equals("")) {
					try {
						f_nac = LocalDate.parse(dir, formatter);
						hoy = LocalDate.now();
						if (f_nac.isAfter(hoy)) {
							System.err.println("La fecha de nacimiento no puede ser posterior a hoy.");
							res = false;
						} else if (f_nac.plus(18, ChronoUnit.YEARS).isAfter(hoy)) {
							System.err.println("No aceptamos menores de 18 años");
							res = false;
						} else if (f_nac.isBefore(hoy.minusYears(70))) {
							System.err.println("No aceptamos mayores de 70 años");
							res = false;
						} else {
							func.setF_nac(f_nac);
							res = true;
						}
					} catch (Exception e) {
						System.out.println("El formato no es correcto.");
						System.out.println();
						res = false;
					}
				} else {
					res = true;
				}
			}
			departamento = "";
			System.out.println("Inserta el departamento:");
			departamento = sc.nextLine();
			if (!departamento.equals("")) {
				func.setDepartamento(departamento);
			}
			res = false;
			while (!res) {
				System.out.println("Inserta el número del grupo:");
				Grupo arr[] = Grupo.values();

				for (Grupo gp : arr) {
					System.out.println((gp.ordinal() + 1) + ". " + gp);
				}
				dir = sc.nextLine();
				if(dir.equals("")) {
					res=true;
				}else {
					
					try {
						numero = Integer.parseInt(dir);
						if (numero <= arr.length) {
							grupo = arr[numero - 1];
							func.setGrupo(grupo);
							res = true;
						} else {
							System.out.println("Opción desconocida.");
							res = false;
						}
					} catch (Exception e) {
						System.out.println("No es un número");
						System.out.println();
						res = false;
					}
				}
			}
			res = false;
			cuerpo = "";
			patron = "";
			
			switch(func.getGrupo()) {
				case A1: patron = "A[A-Z]{1,2}[0-9]{2,3}"; break;
				case A2: patron = "B[A-Z]{0,2}[0-9]{2,4}"; break;
				case C1: patron = "C[A-Z]{2}[0-9]{2}"; break;
				case C2: patron = "D[A-Z]{2}[0-9]{2}"; break;
				case AP: patron = "ASP00"; break;
			}
			while (!res) {
				System.out.println("Inserta el código del cuerpo");
				cuerpo = sc.nextLine();
				if(cuerpo.equals("")) {
					cuerpo= func.getCuerpo();
				}
				if (cuerpo.length() == 5) {
					Pattern pat = Pattern.compile(patron);
					Matcher mat = pat.matcher(cuerpo);
					if(mat.matches()) {
						res = true;
						func.setCuerpo(cuerpo);
					}else {
						res=false;
						System.out.println("El código del cuerpo no es correcto");
					}

				} else {
					System.out.println("El código del cuerpo debe tener 5 carácteres");
					res = false;
				}
			}
//			res = false;
//			boolean result = false;
//			while (!res) {
//				while (!result) {
//					System.out.println("Inserta el número del grupo:");
//					Grupo arr[] = Grupo.values();
//
//					for (Grupo gp : arr) {
//						System.out.println((gp.ordinal() + 1) + ". " + gp);
//					}
//					dir = sc.nextLine();
//					if (dir.equals("")) {
//						res = true;
//						result = true;
//					} else {
//						try {
//							numero = Integer.parseInt(dir);
//							if (numero <= arr.length) {
//								grupo = arr[numero - 1];
//								func.setGrupo(grupo);
//								result = true;
//
//							} else {
//								System.out.println("Opción desconocida.");
//								res = false;
//								result = false;
//							}
//						} catch (Exception e) {
//							System.out.println("No es un número");
//							System.out.println();
//							res = false;
//							result = false;
//						}
//					}
//				}
//				switch (func.getGrupo()) {
//				case A1:
//					patron = "A[A-Z]{1,2}[0-9]{2,3}";
//					break;
//				case A2:
//					patron = "B[A-Z]{0,2}[0-9]{2,4}";
//					break;
//				case C1:
//					patron = "C[A-Z]{2}[0-9]{2}";
//					break;
//				case C2:
//					patron = "D[A-Z]{2}[0-9]{2}";
//					break;
//				case AP:
//					patron = "ASP00";
//					break;
//				}
//				if(!res) {
//					System.out.println("Inserta el código del cuerpo");
//					cuerpo = sc.nextLine();
//					if (cuerpo.length() == 5) {
//						Pattern pat = Pattern.compile(patron);
//						Matcher mat = pat.matcher(cuerpo);
//						if (mat.matches()) {
//							res = true;
//							func.setCuerpo(cuerpo);
//						} else {
//							res = false;
//							result=false;
//							System.out.println("El código del cuerpo no es correcto");
//						}
//	
//					} else {
//						System.out.println("El código del cuerpo debe tener 5 carácteres");
//						res = false;
//						result=false;
//					}
//				}
//			}

			res = false;
			while (!res) {
				System.out.println("Inserta la fecha de ingreso en formato dd/mm/yyyy :");
				dir = sc.nextLine();
				if (dir.equals("")) {
					res = true;
				} else {
					try {
						f_ingreso = LocalDate.parse(dir, formatter);

						if (f_ingreso.isAfter(hoy)) {
							System.out.println("La fecha de ingreso no puede ser posterior a hoy.");
						} else if (f_ingreso.isBefore(hoy.minusYears(60))) {
							System.out.println("La fecha de ingreso no es correcta");
						} else {
							func.setF_ingreso(f_ingreso);
							res = true;
						}
					} catch (Exception e) {
						System.out.println("El formato no es correcto.");
						System.out.println();
						res = false;
					}
				}
			}

			ap = String.join("$$, $$", func.getApellidos());
			sql = "UPDATE funcionarios SET nombre =$a$" + func.getNombre() + "$a$, apellidos =ARRAY[$$" + ap
					+ "$$],direccion = ($b$" + func.getCalle() + "$b$, " + func.getNum_casa() + " ,$c$"
					+ func.getCiudad() + "$c$, $d$" + func.getProvincia() + "$d$,'" + func.getC_postal()
					+ "'),telefono=" + func.getTelefono() + ",fecha_nacimiento ='" + func.getF_nac()
					+ "', departamento =$e$" + func.getDepartamento() + "$e$, cargo = ('" + func.getGrupo() + "','"
					+ func.getCuerpo() + "'), fechaIngreso ='" + func.getF_ingreso() + "' WHERE numero = "
					+ func.getNumero() + " ;";

			manejador.update(sql);
		}
	}

	private static void showPersonas(ManejadorBaseDatos manejador) 
		throws SQLException, ClassNotFoundException 
	{

		String sql = "Select * from only personas"; 
		ResultSet rs = manejador.select(sql);
		
		while(rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if(nombre.startsWith("\"")) {
				nombre = nombre.substring(1,nombre.length()-1);
			}
			apellidos=rs.getString(3).substring(1, rs.getString(3).length()-1).split(",");
			direccion = rs.getString(4).substring(1,rs.getString(4).length()-1).split(",");
			if( direccion[0].startsWith("\"")) {
				calle= direccion[0].substring(1, direccion[0].length()-1);
			}else {
				calle = direccion[0];
			}
			
			num_casa = Integer.parseInt(direccion[1]);
			if( direccion[2].startsWith("\"")) {
				ciudad= direccion[2].substring(1, direccion[2].length()-1);
			}else {
				ciudad = direccion[2];
			}
			
			if( direccion[3].startsWith("\"")) {
				provincia= direccion[3].substring(1, direccion[3].length()-1);
			}else {
				provincia= direccion[3];
			}
			
			c_postal= direccion[4];
			telefono = rs.getLong(5);
			
			f_nac = LocalDate.parse(rs.getString(6));

			Persona persona = new Persona(numero, nombre, apellidos, calle,num_casa, ciudad, provincia,c_postal, telefono, f_nac);	
			System.out.println(persona);
			System.out.println();
		}
	
	}
	
	private static void showClientes(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

		String sql = "Select * from clientes"; 
		ResultSet rs = manejador.select(sql);
		
		while(rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if(nombre.startsWith("\"")) {
				nombre = nombre.substring(1,nombre.length()-1);
			}
			apellidos=rs.getString(3).substring(1, rs.getString(3).length()-1).split(",");
			
			direccion = rs.getString(4).substring(1,rs.getString(4).length()-1).split(",");
			if( direccion[0].startsWith("\"")) {
				calle= direccion[0].substring(1, direccion[0].length()-1);
			}else {
				calle = direccion[0];
			}
			
			num_casa = Integer.parseInt(direccion[1]);
			if( direccion[2].startsWith("\"")) {
				ciudad= direccion[2].substring(1, direccion[2].length()-1);
			}else {
				ciudad = direccion[2];
			}
			
			if( direccion[3].startsWith("\"")) {
				provincia= direccion[3].substring(1, direccion[3].length()-1);
			}else {
				provincia= direccion[3];
			}
			
			c_postal= direccion[4];
			telefono = rs.getLong(5);
			f_nac = LocalDate.parse(rs.getString(6));
			cuenta= rs.getString(7).substring(1,rs.getString(7).length()-1).split(",");
			iban = cuenta[0];
			isValid = Boolean.parseBoolean(cuenta[1]);
			estado = Estado.valueOf(rs.getString(8).toUpperCase());
			tipo = Tipo.valueOf(rs.getString(9).toUpperCase());
			Cliente cliente = new Cliente(numero, nombre, apellidos, calle,num_casa, ciudad, provincia,c_postal, telefono, f_nac, iban,isValid, estado, tipo);	
			if(!cliente.isValid()) {
				System.err.println("Código IBAN del cliente no es válido");
			}
			System.out.println(cliente);
			
			System.out.println();
		}
	
	}
	
	private static void showFuncionarios(ManejadorBaseDatos manejador) 
		throws ClassNotFoundException, SQLException 
	{

		String sql = "Select * from funcionarios"; 
		ResultSet rs = manejador.select(sql);
		
		while(rs.next()) {
			numero = rs.getInt(1);
			nombre = rs.getString(2);
			if(nombre.startsWith("\"")) {
				nombre = nombre.substring(1,nombre.length()-1);
			}
			apellidos=rs.getString(3).substring(1, rs.getString(3).length()-1).split(",");
			direccion = rs.getString(4).substring(1,rs.getString(4).length()-1).split(",");
			if( direccion[0].startsWith("\"")) {
				calle= direccion[0].substring(1, direccion[0].length()-1);
			}else {
				calle = direccion[0];
			}
			
			num_casa = Integer.parseInt(direccion[1]);
			if( direccion[2].startsWith("\"")) {
				ciudad= direccion[2].substring(1, direccion[2].length()-1);
			}else {
				ciudad = direccion[2];
			}
			
			if( direccion[3].startsWith("\"")) {
				provincia= direccion[3].substring(1, direccion[3].length()-1);
			}else {
				provincia= direccion[3];
			}
			
			c_postal= direccion[4];
			telefono = rs.getLong(5);
			f_nac = LocalDate.parse(rs.getString(6));
			departamento = rs.getString(7);
			crp = rs.getString(8).substring(1, rs.getString(8).length()-1).split(",");
			grupo = Grupo.valueOf(crp[0].toUpperCase());
			cuerpo =crp[1];
			f_ingreso = LocalDate.parse(rs.getString(9));

			Funcionario funcionario = new Funcionario(numero, nombre, apellidos, calle,num_casa, ciudad, provincia,c_postal, telefono, f_nac, departamento, grupo, cuerpo, f_ingreso );	
			System.out.println(funcionario);
			System.out.println();
			
		}
	}
	



}