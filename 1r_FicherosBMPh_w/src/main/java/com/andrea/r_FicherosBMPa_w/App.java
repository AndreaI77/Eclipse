package com.andrea.r_FicherosBMPa_w;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
        		int height = getlittleEndian(buffer, 22);
        		int width = getlittleEndian(buffer, 18);
        		System.out.println("Alto: "+height+"\nAncho: "+width);
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
    //esto lo he copiado de las soluciones, ya que no tengo ni idea de cómo interpretar los bytes de la cabecera del bmp
    private static int getlittleEndian(byte[] a ,int offs) {
    	return (a[offs + 3] & 0xff) << 24 | 
				(a[offs + 2] & 0xff) << 16 | 
				(a[offs + 1] & 0xff) << 8 | 
				a[offs] & 0xff;
    }
}
