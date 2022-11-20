package com.andrea.zc_FicherosFinal;

import java.util.Scanner;

public class Menu {

public static int showMenu() {
		
		boolean resultado = false;
		String cadena;
		int num=0;
		Scanner sc = new Scanner(System.in);
		
		while(resultado == false) {
			
			System.out.println("Elige una opción:");
			System.out.println("1. Obtener datos con latitud y longitud");
			System.out.println("2. Obtener datos por nombre de la ciudad");
			System.out.println("3. Obtener temperatura en un rango de fechas");
			System.out.println("4. Guardar la  medición obtenida en el punto 1 o 2");
			System.out.println("5. Ver datos guardados");
			System.out.println("6. Obtener países por continente");
			System.out.println("0. Exit");
			
			cadena = sc.nextLine();
			
			try {
				num=Integer.parseInt(cadena);
				resultado=true;
				
			}catch(Exception e) {
				resultado= false;
				System.out.println("No es un número.");
			}
		}
		
		return num;			
	}
	public static String elegirContinente() {
		boolean resultado = false;
		String cadena;
		int num=0;
		Scanner sc = new Scanner(System.in);
		String pais="";
		while(resultado == false) {
			
			System.out.println("Elige una opción:");
			System.out.println("1. Europa");
			System.out.println("2. Asia");
			System.out.println("3. Africa");
			System.out.println("4. Americas");
			System.out.println("5. Oceania");
			System.out.println("0. Exit");
			
			cadena = sc.nextLine();
			
			try {
				num=Integer.parseInt(cadena);
				resultado=true;
				
			}catch(Exception e) {
				resultado= false;
				System.out.println("No es un número.");
			}
		}
			
			
			switch(num) {
	    	case 1: pais = "Europe"; break;
	    	case 2: pais = "Asia"; break;
	    	case 3: pais = "Africa"; break;
	    	case 4: pais = "Americas"; break;
	    	case 5: pais = "Oceania";break; 
	    	case 0: System.out.println("Vuelves al menú principal:");break;
	    	default: System.out.println("Opción desconocida");
	    				//num = 0;
	    				break;
			}
		return pais;
	
	}
}
