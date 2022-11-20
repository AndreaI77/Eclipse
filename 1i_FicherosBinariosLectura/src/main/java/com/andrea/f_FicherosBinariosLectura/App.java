package com.andrea.f_FicherosBinariosLectura;

import java.io.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	
    	String nombre="";
    	String nom = "";
        if(args.length == 1) {
        	nombre = args[0];
        }else if(args.length == 2) {
        	nombre = args[0];
        	nom = args[1];
        }else {
        	System.out.println("Escribe el nombre del archivo");
        	Scanner sc = new Scanner(System.in);
        	nombre = sc.nextLine();
        	sc.close();
        }
        try {
        	
        	if(nom.equals("")) {
        		nom = nombre;
        	}
        	FileInputStream fichero = new FileInputStream(new File(nombre));
        	PrintWriter writer = new PrintWriter(nom+".txt");
        	int dato=fichero.read();
        	while(dato>=32 && dato<= 127) {
        		writer.print((char)dato);
        		dato=fichero.read();
        	}
        	fichero.close();
        	writer.close();
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
        
        
    }
}
