package com.andrea.o_FicherosSerialize;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
		try {
			FileOutputStream file = new FileOutputStream(new File("personas.dat"));
			ObjectOutputStream os = new ObjectOutputStream(file);
	        Persona p= new Persona("Maria", "maria@gmail.com","20/12/1956");
	        os.writeObject(p);
	        p= new Persona("Pepe", "pepe@gmail.com","09/09/1979");
	        os.writeObject(p);
	        p= new Persona("Juan", "juan@gmail.com","02/01/1982");
	        os.writeObject(p);
	        os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}

