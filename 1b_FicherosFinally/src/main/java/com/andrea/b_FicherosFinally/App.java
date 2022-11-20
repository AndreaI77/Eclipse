package com.andrea.b_FicherosFinally;
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
        System.out.println("Escribe la altura del rectángulo:");
        Scanner sc = new Scanner(System.in);
        int ladoA = sc.nextInt();
        System.out.println("EScribe la anchura del rectángulo:");
        int ladoB = sc.nextInt();
        sc.close();
        PrintWriter writer = null;
        try {
        	writer = new PrintWriter("rectangulo.txt");
        	for(int i=0; i< ladoA; i++) {
        		for( int j = 0; j< ladoB; j++) {
        			if(i == 0 || i == ladoA-1) {
        				writer.print("*");
        			}else {
        				if(j == 0 || j == ladoB-1) {
        					writer.print("*");
        				}else {
        					writer.print(" ");
        				}
        			}
        		}
        		writer.println();
        	}
        	
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }finally {
        	if(writer != null) {
        		writer.close();
        	}
        }
    }
}
