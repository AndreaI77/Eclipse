package com.dam.practica4.utilidades;

import java.util.Scanner;

public class Menu {
	private static int num;
	private static boolean res;

	
	public static int elegirOpcion() {
		
		//devuelve el número de la opción elegida
	        num = 0;
	        Scanner sc = new Scanner(System.in);
	        res = false;
	        while(!res) {
	        	System.out.println();
	        	System.out.println("Menu principal:\nElige una opción:");
		        System.out.println("1. Gestión de jokes");
		        System.out.println("2. Gestión de categories");
		        System.out.println("3. Gestión de lenguajes");
		        System.out.println("4. Gestión de flags");
		        System.out.println("0. Salir");
		        try {
		        	
		        	num=Integer.parseInt(sc.nextLine());
		        	res= true;
		        	
		        }catch(Exception e) {
		        	System.out.println("Inserta un número!");
		        	res = false;
		        }
	        }
	      
	     return num;   
	}
	
	public static int gestionarTablas(String nombre) {
		num=0;
		res=false;
		Scanner sc = new Scanner(System.in);
		/* primer submenú, hay que elegir el tipo de gestión que se quiere hacer con la tabla correspondiente
		 * Devuelve el número de la opción
		 */
		while(!res) {
			System.out.println();
        	System.out.println("Gestión de "+nombre+"\nElige una opción:");
	        System.out.println("1. Consultas");
	        System.out.println("2. Insertar "+nombre);
	        System.out.println("3. Modificar "+nombre);
	        System.out.println("4. Borrar "+nombre);
	        System.out.println("0. Volver al menu principal");
	        try {
	        	num=Integer.parseInt(sc.nextLine());
	        	res= true;
	        }catch(Exception e) {
	        	System.out.println("Inserta un número!");
	        	res = false;
	        }
        }
		return num;
	}
	/* submenú para elegir el tipo de la consulta.
	 * La segunda opción varía según el nombre de la clase.ç
	 * Devuelve el número de la opción
	 */
	public static int elegirConsulta(String nombre) {
		num=0;
		res=false;
		Scanner sc = new Scanner(System.in);
		
		while(!res) {
			System.out.println();
        	System.out.println("CONSULTAS:\nElige una opción:");
	        System.out.println("1. Buscar "+nombre+" por texto");
	        
	        if(nombre.equals("jokes")) {
	        	System.out.println("2. Buscar "+nombre+" sin flags");
	        }else if(nombre.equals("categories")){
	        	System.out.println("2. Buscar la categoría más repetida");
	        }else if(nombre.equals("lenguajes")){
	        	System.out.println("2. Buscar "+nombre+" sin jokes");
	        }else if(nombre.equals("flags")){
	        	System.out.println("2. Buscar el flag más repetido");
	        }
	        System.out.println("0. Volver al menu anterior");
	        try {
	        	num=Integer.parseInt(sc.nextLine());
	        	res= true;
	        }catch(Exception e) {
	        	System.out.println("Inserta un número!");
	        	res = false;
	        }
        }
		
		return num;
	}
	
	/* Menú para elegir el tipo del borrado.
	 * Devuelve la opción elegida
	 */
	public static int elegirBorrado(String clase) {
		int num=0;
		boolean res = false;
		Scanner sc = new Scanner(System.in);
		
		while(!res) {
			System.out.println("Elige una opción:");
			System.out.println("1. Poner el campo a null");
			System.out.println("2. Borrar todos los chistes asociados");
			System.out.println("0. Volver al menú anterior.");
			try {
				num=Integer.parseInt(sc.nextLine());
		    	
		    }catch(Exception e) {
		    	System.out.println("Inserta un número!");
		    	res = false;
		    }
			if(num >= 0 && num<=2) {
				res= true;
			}else {
				System.out.println("Opción desconocida");
				res=false;
			}
		}
		return num;
	}
	
}
