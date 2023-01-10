package com.dam.practica4;

import com.dam.practica4.entidades.Language;
import com.dam.practica4.entidades.Categories;
import com.dam.practica4.entidades.Jokes;
import com.dam.practica4.entidades.Flags;
import com.dam.practica4.utilidades.Menu;
import com.dam.practica4.utilidades.Utils;

public class App 
{
	
	public static void main( String[] args )
    {
    	int num = 0, num2=0, num3 = 0;
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	
    	do {
    		num =Menu.elegirOpcion();
    		switch(num) {
	    		case 1:
	    			do {
	    				num2 =Menu.gestionarTablas("jokes");
		    			switch(num2) {
			    			case 1:
			    				do {
			    					num3=Menu.elegirConsulta("jokes");
				    				switch(num3 ) {
				    					case 1: Utils.buscarTexto("Jokes");
				    						break;
				    					case 2: Utils.buscarJokesSinFlags();
				    						break;
				    					case 0: break;
				    					default: System.out.println("Opción desconocida");break;
				    				}
			    				}while(num3 !=0);
			    				break;
			    			case 2: Utils.insertar("Jokes");
			    			
			    			
				    			break;
				    		case 3: Utils.update("Jokes");
				    			break;
				    		case 4: Utils.borrarObjeto(Jokes.class);
				    			break;
				    		case 0: break;
				    		default: System.out.println("Opción desconocida");break;
		    			}
	    			}while(num2 !=0);
	    			break;
	    		
	    		case 2: 
	    			do {
	    				num2=Menu.gestionarTablas("categories");
			    		switch(num2) {
			    			case 1:
			    				do {
			    					num3=Menu.elegirConsulta("categories");
				    				switch(num3) {
				    					case 1: Utils.buscarTexto("Categories"); 
				    						break;
				    					case 2: Utils.buscarMasRepetido(Categories.class); 
				    						break;
				    					case 0: break;
							    		default: System.out.println("Opción desconocida");break;
				    				}
			    				}while(num3!=0);
			    				break;
			    			case 2: Utils.insertar("Categories");
				    			break;
				    		case 3: Utils.update("Categories");
				    			break;
				    		case 4: Utils.borrarObjeto(Categories.class);
				    			break;
				    		case 0: break;
				    		default: System.out.println("Opción desconocida");break;
			    		}
	    			}while(num2 !=0);
		    		break;
	    		case 3: 
	    			do {
	    				
		    			num2=Menu.gestionarTablas("lenguajes");
			    		switch(num2) {
			    			case 1: 
			    				do {
			    					num3=Menu.elegirConsulta("lenguajes");
				    				switch(num3) {
				    					case 1: Utils.buscarTexto("Language"); 
				    						break;
				    					case 2: Utils.buscarLenguajesSinJokes();
				    						break;
				    					case 0: break;
							    		default: System.out.println("Opción desconocida");break;
				    				}
			    				}while(num3 !=0);
			    				break;
			    			case 2: Utils.insertar("Language");
				    			break;
				    		case 3: Utils.update("Language");
				    			break;
				    		case 4: Utils.borrarObjeto(Language.class);
				    			break;
				    		case 0: break;
				    		default: System.out.println("Opción desconocida");break;
			    		}
	    			}while(num2 !=0);
		    		break;
	    		case 4:
	    			do {
		    			num2=Menu.gestionarTablas("flags");
			    		switch(num2) {
			    			case 1: 
			    				do {
				    				num3=Menu.elegirConsulta("flags");
				    				switch(num3) {
				    					case 1: Utils.buscarTexto("Flags");
				    						break;
				    					case 2: Utils.buscarMasRepetido(Flags.class);
				    						break;
				    					case 0: break;
							    		default: System.out.println("Opción desconocida");break;
				    				}
			    				}while(num3 !=0);
			    				break;
			    			case 2: Utils.insertar("Flags");
				    			break;
				    		case 3: Utils.update("Flags");
				    			break;
				    		case 4: Utils.borrarObjeto(Flags.class);
				    			break;
				    		case 0: break;
				    		default: System.out.println("Opción desconocida");break;
				    		}
	    			}while(num2 !=0);
	    			break;
	    		case 0: System.out.println("Hasta luego");
	    				
	    		default:System.out.println("Opción desconocida"); 		
    		}
    		
    	}while(num!=0);
    }
	

}
