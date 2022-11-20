package com.andrea.g_FicherosVocales;

import java.io.*;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Escribe el nombre de fichero:");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		sc.close();
		String vocales = "aeiouAEIOU";
		int num = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(nombre)));
			String texto = "";
			String linea = reader.readLine();
			while(linea != null) {
				texto +=linea;
				linea = reader.readLine();
			}
			reader.close();
			for(int i = 0; i<vocales.length(); i++) {
				for( int j=0;j<texto.length();j++) {
					if(vocales.charAt(i) == texto.charAt(j)) {
						num ++;
					}
				}
			}
			System.out.println("En el texto hay "+num+" vocales");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
}
