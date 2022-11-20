package com.andrea._FicherosLectura;

import java.io.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	String nombre = "";
    	if(!new File("log.txt").exists()) {
    		System.out.println("No se ha encontrado el fichero log.txt.\nEscribe el nombre del fichero:");
    		Scanner sc = new Scanner(System.in);
    		nombre = sc.nextLine();
    	}else {
    		nombre = "log.txt";
    	}
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(nombre)));
			String linea = reader.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = reader.readLine();
				}
			reader.close();
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    }
}
