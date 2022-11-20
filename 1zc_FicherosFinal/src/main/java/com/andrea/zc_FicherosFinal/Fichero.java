package com.andrea.zc_FicherosFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.Medicion;

public class Fichero {

	public static void guardarDatos(Medicion med) {
		List<Medicion> mediciones = new ArrayList<>();
		
		mediciones = deserializarDatos();
		if(med !=null) {
			
		
    	boolean contiene = false;
    	if(!mediciones.isEmpty()) {
    		for(Medicion md : mediciones) {
        		if(md.getFecha().isEqual(med.getFecha()) 
        				&&  md.getCiudad().equals(med.getCiudad())){
        			contiene = true;
        			md=med;
        		}
        	}
        	if( !contiene) {
        		mediciones.add(med);
        		System.out.println("Se ha guardado la medición: \n"+med);
        		
        	}else {
        		System.out.println("Ya existe una medición de esta ciudad en esta misma fecha");
        	}
    	}else {
    		mediciones.add(med);
    	}
    	
		try {
			
			FileOutputStream file = new FileOutputStream(new File("mediciones.dat"));
			ObjectOutputStream os = new ObjectOutputStream(file);
	        os.writeObject(mediciones);
	        os.close();
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("No hay ninguna medicion par guardar");
		}
	}
	public static List<Medicion>  deserializarDatos() {
		List<Medicion> mediciones = new ArrayList();
		ObjectInputStream is= null;
		try {
			is = new ObjectInputStream(
					new FileInputStream ( new File("mediciones.dat")));
			mediciones =(List<Medicion>) is.readObject();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se han guardado ningunos datos todavía");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}	
		return mediciones;
	}
	
	public static void verDatos() {
		List<Medicion> mediciones = new ArrayList();
		mediciones = deserializarDatos();
		
		for(Medicion med : mediciones) {
			System.out.println(med);
		}	    	
	}
}
