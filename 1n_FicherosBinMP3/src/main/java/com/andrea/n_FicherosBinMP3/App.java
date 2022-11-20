package com.andrea.n_FicherosBinMP3;

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
        System.out.println("escribe el nombre del fichero:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        sc.close();
        File file = new File(name);
		if (!file.exists()) {
			System.out.println("File not found!");
			return;
		}
		InputStream fichero = null;
		try {
			fichero = new FileInputStream(file);
			int size = (int)file.length();
			byte[] buffer = new byte[size];
			fichero.read(buffer, 0, size);
//			System.out.println(buffer[size -128]+","+buffer[size -127]+","+buffer[size -126]);
//			System.out.println(size);
			if((char)buffer[size -128] =='T' && (char)buffer[size -127] =='A' 
					&& (char)buffer[size-126] =='G') {
				System.out.println("Título: ");
				for( int i = size -125; i< size - 95; i++) {
					System.out.print((char)buffer[i]);
				}
				System.out.println("\nArtista: ");
				for( int i = size -95; i< size - 65; i++) {
					System.out.print((char)buffer[i]);
				}
				System.out.println("\nAlbum: ");
				for( int i = size -65; i< size - 35; i++) {
					System.out.print((char)buffer[i]);
				}
				System.out.println("\nAño: ");
				for( int i = size -35; i< size - 31; i++) {
					System.out.print((char)buffer[i]);
				}
				System.out.println("\nComentario: ");
				for( int i = size -31; i< size - 1; i++) {
					System.out.print((char)buffer[i]);
				}
				System.out.println("\nGénero: "+(char)buffer[size-31]);
				
			}else {
				System.out.println("No es un archivo MP3");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Algo ha salido mal: " + e.getMessage());
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
    }
}
