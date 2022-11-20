package com.andrea.zc_FicherosFinal;

import modelo.Medicion;

public class App 
{
	private static Medicion med;

    public static void main( String[] args )
    {
    	int num;
      String pais;
        do {
        	num = Menu.showMenu();      	
        	switch(num) {
	        	case 1: med = BuscarCoordenadas.obtenerLatLong(); break;
	        	case 2: med = BuscarCiudad.obtenerCiudad(); break;
	        	case 3: Rango.obtenerRango(); break;
	        	case 4: Fichero.guardarDatos(med); break;
	        	case 5: Fichero.verDatos(); break;
	        	case 6: pais = Menu.elegirContinente();
	        			if(!pais.equals("")) {
	        				Country.obtenerPaises(pais);
	        			}
	        			break;
	        		 
	        	case 0: System.out.println("Adios");break;
	        	default: System.out.println("Opción desconocida");break;
        	}
        	System.out.println();
        	
        }while(num !=0);	
    }
}
