package com.andrea.m_FicherosBinBMPCom;

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
        Scanner sc = new Scanner (System.in);
        System.out.println("Escribe el nombre del fichero BMP:");
        String name = sc.nextLine();
        sc.close();
        try {
        	File file = new File(name);
        	InputStream fichero = new FileInputStream(file);
        	
        	int size=(int)file.length();
        	byte[] buffer = new byte[size];
        	
        	fichero.read(buffer,0,size);
        	
        	if(((char)buffer[0])== 'B' && ((char)buffer[1])== 'M') {
        		//System.out.println(buffer[0]+","+buffer[1]);
        		System.out.println("Es un archivo BMP");
        		if(buffer[30]==0 && buffer[31]==0 && buffer[32]==0 && buffer[33]== 0) {
        			//System.out.println(buffer[30]+","+buffer[31]+","+buffer[32]+","+buffer[33]);
        			System.out.println("El archivo NO está comprimido");
        		}else {
        			System.out.println("El archivo está comprimido");
        		}
        	}else {
        		System.out.println("No es un archivo BMP");
        	}
        	
        	fichero.close();
//        	
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
}
