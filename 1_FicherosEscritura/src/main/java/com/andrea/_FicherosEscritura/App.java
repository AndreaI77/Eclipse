package com.andrea._FicherosEscritura;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int num = 1;
        PrintWriter writer;
        try {
        	writer = new PrintWriter("impares.txt");
        	while(num<= 10) {
            	if(num%2 == 1) {
            		writer.println(num);
            	}
            	num++;
            }
        	writer.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }    
    }
}
