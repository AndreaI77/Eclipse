package com.andrea._10_2tablas;

import java.util.Scanner;

public class Menu {

	public static int elegirOpcion() {
		 Scanner sc = new Scanner(System.in);
	        int num = 0;
	        boolean res = false;
	        while(!res) {
	        	System.out.println("Elige una opción");
		        System.out.println("1. Añadir curso");
		        System.out.println("2. Añadir participantes a un curso");
		        System.out.println("3. Ver datos de un curso");
		        System.out.println("4. Salir");
		        try {
		        	num = sc.nextInt();
		        	res= true;
		        }catch(Exception e) {
		        	System.out.println("Inserta un número!");
		        	System.out.println();
		        	res = false;
		        }
	        }
	      
	     return num;   
	}
	
	
}
