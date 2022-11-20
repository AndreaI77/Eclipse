package com.andrea.zc_FicherosFinal;
import modelo.Tiempo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;



public class Rango {
	public static void obtenerRango(){
		
		LocalDate fecha1 = null;
		String st = "";
		LocalDate fecha2 = null;
		boolean respuesta = false;
		Scanner sc = new Scanner(System.in);
		List<Tiempo> lista = new ArrayList<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while (!respuesta) {
			try {
				while (!respuesta) {
					System.out.println("Introduce primera fecha en formato dd/mm/yyy:");
					st = sc.nextLine();

					fecha1 = LocalDate.parse(st, formatter);
					//System.out.println(formatter.format(fecha1).toString());

					respuesta = true;
				}
				respuesta = false;
				while (!respuesta) {
					System.out.println("Introduce segunda fecha en formato dd/mm/yyy:");
					st = sc.nextLine();

					fecha2 = LocalDate.parse(st, formatter);
					//System.out.println(formatter.format(fecha2).toString());

					if (fecha2.isEqual(fecha1) || fecha2.isAfter(fecha1)) {
						respuesta = true;
					} else {
						
						System.out.println("La segunda fecha debe ser posterior a la primera");
						respuesta = false;
					}

				}

			} catch (DateTimeParseException e) {
				System.out.println("Respete el formato solicitado e introduzca una fecha válida");
				respuesta = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		if(!new File("datos.csv").exists() ) {
			System.out.println("No se ha encontrado el archivo.");
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("datos.csv")));
			String linea = reader.readLine();
			linea=reader.readLine();
			int num=0;
			String[] array= null;
			String[] fh= null;			
			LocalDate fecha = null;
			String hora = null;
			Float temp = null;
			Tiempo t = null;
			boolean contiene = false;
			while(linea != null) {
				array = linea.split(",");
				fh=array[5].split(" ");
				fecha = LocalDate.parse(fh[0]);
				hora = fh[1];
				temp =Float.parseFloat(array[6]);
				t = new Tiempo(fecha, hora, temp);

					for(Tiempo tm : lista) {
						if(tm.getFecha().isEqual(t.getFecha()) && tm.getHora().equals(t.getHora())) {
							contiene = true;
							
						}
					}
					if( !contiene) {
						if((fecha1.isEqual(t.getFecha()) || fecha1.isBefore(t.getFecha()))
								&& (fecha2.isEqual(t.getFecha()) || fecha2.isAfter(t.getFecha())))
						lista.add(t);
					}
					contiene = false;
				linea = reader.readLine();

			}
			
			LocalTime time = null;
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
			LocalDate fc = null;
			try {
				fc = lista.get(0).getFecha();
				System.out.println();System.out.println("Resultado: ");
				System.out.print(formatter.format(fc)+" ");
				for(Tiempo tmp: lista) {
					if(!fc.isEqual(tmp.getFecha())) {
						System.out.println();
						System.out.print(formatter.format(tmp.getFecha()).toString()+" ");
						fc = tmp.getFecha();
					}
					time = LocalTime.parse(tmp.getHora());
					System.out.print(formato.format(time)+"->"+tmp.getTemperatura()+" ");
				}
				
			}catch(IndexOutOfBoundsException e) {
				System.out.println("No hay registros para este rango");
			}
			
			
			System.out.println();
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
