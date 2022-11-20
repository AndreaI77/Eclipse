package com.andrea.p_FicherosDeserializar;


import java.io.*;



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
    	
        try {
        	ObjectInputStream os = new ObjectInputStream(
        			new FileInputStream ( new File("personas.dat")));
        	Persona p= null;
        	for(int i=0; i<3; i++) {
        		p = (Persona)os.readObject();
            	System.out.println(p.toString());
        	}
        	        	
        	os.close();
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }catch(IOException ex) {
        	ex.printStackTrace();
        }catch(ClassNotFoundException exc) {
        	exc.printStackTrace();
        }
    }
}

