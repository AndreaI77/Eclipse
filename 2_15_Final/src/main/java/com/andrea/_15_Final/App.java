package com.andrea._15_Final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	int num = 0;
    	boolean result = false;
    	
    	do {
    		num =Menu.elegirOpcion();
    		
    		switch(num) {
	    		case 1: 
	    			result=false;
	    			
	    	    	List<Joke> jokes = new ArrayList<Joke>();
	    	    	// obtiene las propiedades de los chistes
	    	    	Propiedades prop = Propiedades.obtenerPropiedades();
	    	    	//lee json y devuelve la lista de chistes
	    	    	jokes=Joke.leerDatos(prop);
	    	    	
	    	    	//carga los datos en las tablas
	    	    	//result=BaseDatos.recargarTablas(prop, jokes);
	    			if(result) {
	    				System.out.println("Se han cargado los datos en la BD");
	    			}else {
	    				System.out.println("Ha ocurrido algún error, no se han cargado datos.");
	    			}
	    			System.out.println();
	    			
	    			break;
	    			
	    		case 2: 
	    			result = BaseDatos.insertarChisteStatement(); 
	    			if(result) {
	    				System.out.println("Se ha insertado el chiste");
	    			}else {
	    				System.out.println("Ha ocurrido algún error, no se ha creado el registro.");
	    			}
	    			System.out.println();
	    			break;
	    			
	    		case 3: 
	    			result= BaseDatos.insertarChistePreparedStatement(); 
	    			if(result) {
	    				System.out.println("Se ha insertado el chiste");
	    			}else {
	    				System.out.println("Ha ocurrido algún error, no se ha creado el registro.");
	    			}
	    			System.out.println();
	    			break;
	    		case 4: BaseDatos.buscarTexto(); break;
	    		case 5: BaseDatos.buscarChistesSinFlags(); break;
	    		case 6: System.out.println("Hasta luego");break;
	    		default: System.out.println("Opción desconocida");
    		}
    	}while(num != 6);
       
    }
    	
    	
    
}
