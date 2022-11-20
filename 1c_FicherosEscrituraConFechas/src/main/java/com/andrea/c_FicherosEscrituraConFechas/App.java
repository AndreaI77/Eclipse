package com.andrea.c_FicherosEscrituraConFechas;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Escribe la frase:");
    	Scanner sc = new Scanner(System.in);
    	String text = sc.nextLine();
    	sc.close();
    	PrintWriter writer = null;
    	try {
    		writer = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
    		Date date = new Date();
    		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			writer.println("("+formato.format(date) + ") " + text);
;    	}catch( FileNotFoundException e) {
    		e.printStackTrace();
    	
    	}catch( IOException e) {
    		e.printStackTrace();
    	}finally {
    		if(writer != null) {
    			writer.close();
    		}
    	}
       
    }
}
