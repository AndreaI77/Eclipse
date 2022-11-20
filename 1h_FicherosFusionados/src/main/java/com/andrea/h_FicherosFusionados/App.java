package com.andrea.h_FicherosFusionados;

import java.io.*;
import java.util.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Escribe el nombre de los ficheros separados por una coma:");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		sc.close();
		String [] nombres = nombre.split(",");
		List<String> lista = new ArrayList<String>();
		BufferedReader file1 = null;
		BufferedReader file2 = null;
		PrintWriter writer = null;
		if(new File(nombres[0]).exists() && new File(nombres[1]).exists()) {
			try {
				file1 = new BufferedReader(new FileReader(new File(nombres[0])));
				String linea = file1.readLine();
				while(linea != null) {
					lista.add(linea);
					linea=file1.readLine();
					
				}
				file1.close();
				file2 = new BufferedReader(new FileReader(new File(nombres[1])));
				linea = file2.readLine();
				while(linea != null) {
					lista.add(linea);
					linea=file2.readLine();
					
				}
				file2.close();
				Collections.sort(lista);
				writer = new PrintWriter("fusionado.txt");
				for(int i=0; i<lista.size(); i++) {
					
					//System.out.println(lista.get(i));
					writer.println(lista.get(i));
				}
				writer.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
