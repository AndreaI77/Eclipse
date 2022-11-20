package com.andrea.e_FicherosRectangulo;

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
    	Scanner sc = new Scanner(System.in);
        System.out.print("Cuantas líneas desea imprimir?");
        int ladoA = sc.nextInt();
        System.out.print("Cuantas columnas desea imprimir?");
        int ladoB = sc.nextInt();
        sc.close();
        PrintWriter writer = null;
        try {
        	writer = new PrintWriter("rectangulo.txt");
            for(int i=0; i<ladoA; i++) {
            	for(int j= 0; j< ladoB; j++) {
            		writer.print("*");
            	}
            	writer.println();
            }
            writer.close();
        }catch(IOException e) {
        	e.printStackTrace();
        }
        
    }
}
