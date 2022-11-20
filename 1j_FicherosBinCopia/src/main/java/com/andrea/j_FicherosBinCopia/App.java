package com.andrea.j_FicherosBinCopia;

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
        String nom1 = "";
        String nom2 = "";
        if(args.length>0) {
        	nom1 = args[0];
        	nom2=args[1];
        }else {
        	System.out.println("Escribe el nombre del fichero a copiar");
        	Scanner sc = new Scanner (System.in);
        	nom1 = sc.nextLine();
        	System.out.println("Escribe el nombre del fichero final.");
        	nom2 = sc.nextLine();
        	sc.close();
        	System.out.println("Copiando fichero ....");
        }
        try {
        	File fichero = new File(nom1);
        	InputStream reader = new FileInputStream(fichero);
        	OutputStream writer = new FileOutputStream(new File(nom2));
        	
        	int tamanyo = (int)fichero.length();
        	byte [] datos = new byte[tamanyo];
        	//System.out.println(tamanyo);

        	reader.read(datos, 0, tamanyo); 
        	writer.write(datos, 0, tamanyo);
        	
        	System.out.println("Terminado!");
        	reader.close();
        	writer.close();
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
    }
}
