package com.andrea.f_FicherosInvertidos;

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
    	System.out.println("Escribe el nombre de fichero:");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		sc.close();
		 //String texto = "";
		
		List<String>lista = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(nombre)));
			String linea = reader.readLine();
			while(linea != null) {
				lista.add(linea);
				//texto = linea+"\n"+texto;
				linea = reader.readLine();
			}
			reader.close();
			Collections.reverse(lista);
			PrintWriter writer = new PrintWriter("invertido_"+nombre);
			for(String line : lista) {
				writer.println(line);
			}
			//writer.println(texto);
			writer.close();;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
}
