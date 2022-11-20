package com.andrea.l_FicherosBMPValidator;

import java.io.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String nombre = "";
        if(args.length ==1) {
        	nombre = args[0];
        }else {
        	System.out.println("Escribe el nombre del fichero:");
        	Scanner sc = new Scanner(System.in);
        	nombre = sc.nextLine();
        	sc.close();
        }
        if(!new File(nombre).exists()) {
        	System.out.println(nombre+" not found");
        	return;
        }
        try {
        	FileInputStream fichero = new FileInputStream(new File(nombre));
        	for (int i = 0; i < 2; i++) {
				
			}
        	
        	int dato = fichero.read();
        	if((char)dato != 'B' ) {
        		System.out.println("El archivo no es un BMP válido");
        	}else {
        		dato= fichero.read();
        		if((char)dato != 'M') {
        			System.out.println("El archivo no es un BMP válido");
        		}else {
        			System.out.println("El archivo es un BMP válido");
        		}
        	}
        	
        	fichero.close();
        	
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }catch(IOException e) {
        	e.printStackTrace();
        }
        
        
    }
}
