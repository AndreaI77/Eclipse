package com.andrea.s_FicherosAmigos;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Hello world!
 *
 */
public class App 
{
	public static Scanner sc = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	 Scanner sc = new Scanner(System.in);
    	 String num = "";
    	 ArrayList<Persona> lista= obtenerLista();
    	 do {
    		 System.out.println("\nElige una opción:\nMenu:\n1. Añadir una persona"
    	        		+ "\n2. Ver nombre de todos\n3. Buscar\n4. Exportar a XML"
    	        		+ "\n5. Importar desdeXML\n0. Salir");
    	       
    	        num = sc.nextLine();
    	        
    	        switch(num) {
    	        case "1": anadirPersona(lista);
    	        		break;
    	        case "2": verTodos(lista);
    	        		break;
    	        case "3": buscar(lista);
    	        		break;
    	        case "4": exportarXML(lista);
    	        		break;
    	        case "5": importarXML(lista);
    	        		break;
    	        case "0": System.out.println("Adios!");
    	        		System.exit(0);
    	        		break;
    	        default: System.out.println("Opción no encontrada.");
    	        }
    	 }while(!num.equals("0"));
       
        sc.close();
    }
    private static void anadirPersona(ArrayList<Persona> lista) {
    	String name = "";
    	int edad = 0;
    	String email = "";
    	String comment = "";
    	String stEdad = "";
    	
    	do {
    		System.out.println("Inserta nombre:");
        	name = sc.nextLine();
    	}while(name.equals(""));
    	
    	do {
    		System.out.println("Inserta edad:");
    		stEdad = sc.nextLine();
    		try {
    			edad = Integer.parseInt(stEdad);
    			if(edad <1 || edad > 120) {
        			System.out.println("La edad debe estar entre 1 y 120 años.");
        		}
    		}catch(Exception e) {
    			System.out.println("La edad debe ser un número.");
    		}
    		
    		
    		
    	}while(edad<1 || edad >120);
  	
    		System.out.println("Inserta email:");
        	email = sc.nextLine();

    	 System.out.println("Inserta comentario:");
    	 comment= sc.nextLine();
    	 
    	 lista.add(new Persona(name, edad, email,comment));
    	 //lista.sort((f1,f2)-> f1.getNombre().compareTo(f2.getNombre()));
    	 Collections.sort(lista, new Comparator<Persona>() {
    		 public int compare(Persona p1, Persona p2) {
    			 return p1.getNombre().compareTo(p2.getNombre());
    		 }
    	 });
    	
    	 System.out.println("Se ha añadido una persona en la posición "+(lista.size()+1)+".");
    	 try {
    		 ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("personas.dat")));
    		 os.writeObject(lista);
    		 os.close();
    	 
	     	}catch(IOException e) {
	     		e.printStackTrace();
	     	}
    }
    private static void exportarXML(ArrayList<Persona> lista) {
    	PrintWriter writer = null;
    	try {
    		writer = new PrintWriter("friends.xml");
    		writer.println("<personas>");
    		for(Persona per : lista) {
    			writer.println("\t<persona>");
    			writer.println("\t\t<nombre>"+per.getNombre()+"</nombre>");
    			writer.println("\t\t<edad>"+per.getEdad()+"</edad>");
    			writer.println("\t\t<email>"+per.getEmail()+"</email>");
    			writer.println("\t\t<comentario>"+per.getComentario()+"</comentario>");
    			writer.println("\t</persona>");
    		}
    		writer.println("</personas>");
    		
    		System.out.println("Exportando...");
    	}catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		if (writer != null) {
    			writer.close();
    		}
    	}
    }
    private static ArrayList<Persona> obtenerLista(){
    	ArrayList<Persona> lista = new ArrayList<Persona>();
    	if(!new File("personas.dat").exists()) {
    		return lista;
    	}
    	try {
    		ObjectInputStream os = new ObjectInputStream(
        			new FileInputStream ( new File("personas.dat")));
        	lista =(ArrayList<Persona>)os.readObject();   	
        	os.close();
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return lista;
    }
    
    private static void verTodos(ArrayList<Persona> lista) {
    	for(Persona per : lista) {
    		System.out.println("Nombre: "+per.getNombre());
    	}
    }
    
    private static void buscar(ArrayList<Persona> lista) {
    	
    		System.out.println("Escribe la palabra a buscar:");
    		String nom=sc.nextLine().toLowerCase();

    	List<Persona> encontrados = lista.stream()
                .filter((f) -> f.getNombre().toLowerCase().contains(nom)
                        || f.getEmail().toLowerCase().contains(nom)
                        || f.getComentario().toLowerCase().contains(nom))
                .collect(Collectors.toList());
    	
    	if(encontrados.size()==0) {
    		System.out.println("No se ha encontrado ningún registro con la palabra especificada.");
    		
    	}else {
//    		for(Persona per : encontrados) {
//    			System.out.println(per.toString());
//    		}
    		encontrados.forEach(System.out::println);
    	}
    }
    private static void importarXML(ArrayList<Persona> lista) {
    	lista.clear();
    	BufferedReader reader = null;
    	ArrayList<String> lineas = new ArrayList<String>();
    	String line, label, endLabel ="";
    	boolean insideLabel = false;
    	int num, indexOpenAngle, indexCloseAngle = 0;
    	Path path = Paths.get("firends.xml");
    	
    	try{
    		reader = new BufferedReader(new FileReader(new File("friends.xml")));
    		line= reader.readLine();
    		while(line != null) {
    			lineas.add(line.trim());
    			line= reader.readLine();
    		}
    		if(lineas.size()== 0) {
    			System.out.println("No se ha podido leer el archivo");
    		}else {
    			num = 1;
    			while(num <lineas.size()-1) {
    				if(!insideLabel) {
    					label = lineas.get(num);
        				endLabel ="</"+ label.substring(1,label.length());
        				insideLabel = true;
    				}else if(lineas.get(num).equals(endLabel)) {
    					insideLabel = false;
    				}else {
    					int edad;
    					String nombre, email, comentario;
    					
    					indexOpenAngle = lineas.get(num).indexOf(">")+1;
                        indexCloseAngle = lineas.get(num).lastIndexOf("</");
                        nombre = lineas.get(num)
                                .substring(indexOpenAngle, indexCloseAngle);
                        
                        indexOpenAngle = lineas.get(++num).indexOf(">")+1;
                        indexCloseAngle = lineas.get(num).lastIndexOf("</");
                        edad = Integer.parseInt(lineas.get(num)
                        		.substring(indexOpenAngle, indexCloseAngle));
                        
                        indexOpenAngle = lineas.get(++num).indexOf(">")+1;
                        indexCloseAngle = lineas.get(num).lastIndexOf("</");
                        email = lineas.get(num)
                                .substring(indexOpenAngle, indexCloseAngle);
                        
                        indexOpenAngle = lineas.get(++num).indexOf(">")+1;
                        indexCloseAngle = lineas.get(num).lastIndexOf("</");
                        comentario = lineas.get(num)
                                .substring(indexOpenAngle, indexCloseAngle);
                        lista.add(new Persona(nombre, edad, email, comentario));
    				}
    				num++;
    			}
    			System.out.println("Se ha importado el archivo");
    		}
    	}catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		if(reader != null) {
    			try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}
