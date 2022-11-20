package com.andrea.a_FicherosThrows;

import java.io.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws FileNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cuantos asteriscos desea imprimir?");
        int num = sc.nextInt();
        sc.close();
        PrintWriter writer = new PrintWriter("triangle.txt");
        for(int i=0; i<num; i++) {
        	for(int j= 0; j<= i; j++) {
        		writer.print("*");
        	}
        	writer.println();
        }
        writer.close();
    }
}
