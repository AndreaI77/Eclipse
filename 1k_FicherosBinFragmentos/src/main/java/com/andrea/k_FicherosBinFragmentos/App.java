package com.andrea.k_FicherosBinFragmentos;

import java.io.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        int size = 5*1024;
        System.out.println("Escribe el nombre del fichero:");
        Scanner sc = new Scanner(System.in);
        String nom1 =sc.nextLine();
        sc.close();
        String nom2 = "fragmento_"+nom1;
        try {
        	InputStream fichero1 = new FileInputStream(new File(nom1));
        	byte[]buffer = new byte[size];
        	fichero1.read(buffer, 0 ,size);
        	
        	OutputStream fichero2 = new FileOutputStream(new File(nom2));
        	fichero2.write(buffer,0,size);
        	
        	fichero1.close();
        	fichero2.close();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
    }
}
