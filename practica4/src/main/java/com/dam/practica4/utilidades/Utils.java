package com.dam.practica4.utilidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dam.practica4.entidades.Categories;
import com.dam.practica4.entidades.Flags;
import com.dam.practica4.entidades.Jokes;
import com.dam.practica4.entidades.Language;
import com.dam.practica4.entidades.Types;

public class Utils {
	
	// El método busca en los campos de las entidades pasadas por parámetro el texto solicitado por consola.
	public static void buscarTexto(String clase) {
		List<?>list=null;
		String st="";
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserta el texto a buscar:");
		String texto = sc.nextLine().trim().toLowerCase();
		HibernateUtils.abrirConexion();
		
		// Según la clase correspondiente, llamo a los métodos, que me devuelven una lista con los objetos que contienen el texto en su descripción.
		if(clase.equals("Categories")){
			list=HibernateUtils.buscarTextoCategories("%"+texto+"%");
			st="categorías";
		}else if( clase.equals("Language")) {
			list=HibernateUtils.buscarTextoLanguage("%"+texto+"%");
			st= "lenguajes";
		}else {
			/*en caso de clases Jokes, Types y Flags obtengo una lista con todos los objetos, 
			 * mediante Stream filtro los que contienen el texto solicitado y los imprimo.
			 */
			List<?>lista=HibernateUtils.devolverListaObjetos(clase);			
			switch(clase) {
				case "Jokes": 
					list=lista.stream().filter(c -> (((Jokes)c).getText1()!= null && ((Jokes)c).getText1().toLowerCase().contains(texto)) 
							|| (((Jokes)c).getText2()!= null && ((Jokes)c).getText2().toLowerCase().contains(texto)))
					.sorted((c1,c2)-> ((Jokes)c1).getId()-((Jokes)c2).getId()).collect(Collectors.toList());
					st="chistes";
					break;
				case "Types":
					list=lista.stream().filter(c ->((Types)c).getType()!= null && ((Types)c).getType().toLowerCase().contains(texto)).collect(Collectors.toList());
					st="tipos";
					break;
				case "Flags":
					list=lista.stream().filter(c ->((Flags)c).getFlag()!= null && ((Flags)c).getFlag().toLowerCase().contains(texto)).collect(Collectors.toList());
					st="flags";
					break;
			}	
		}
		//si la lista no está vacía, la imprimo y en caso contrario aviso.
		if(list.size()>0) {
			list.forEach(System.out::println);
		}else {
			System.out.println("No existen "+st+" con este texto");
		}
		HibernateUtils.cerrarConexion();
	}
	/* según la clase, busco el objeto que más se ha usado en los chistes.
	 * para ello ordeno la lista por el tamaño del set y el primero es el que más se ha usado.
	 */
	public static <T> void buscarMasRepetido(Class<T> clase) {
		List<?> lista=null;
		HibernateUtils.abrirConexion();
		switch(clase.getSimpleName()) {
			case "Flags":
				lista= HibernateUtils.devolverListaObjetos(Flags.class).stream().sorted((c1,c2)->((Flags)c2).getJokeses().size()-((Flags)c1).getJokeses().size())
				.collect(Collectors.toList());
				System.out.println("El flag más utilizado es: \n"+lista.get(0)+"\nSe ha usado en "+((Flags)lista.get(0)).getJokeses().size()+" chistes.");
				break;
			case "Categories":
				lista=HibernateUtils.devolverCategories().stream().sorted((c1,c2) ->((Categories) c2).getJokeses().size()-((Categories)c1).getJokeses().size())
				.collect(Collectors.toList());
				System.out.println("La categoría más repetida es:\n"+lista.get(0)+"\nSe ha usado en "+((Categories)lista.get(0)).getJokeses().size()+" chistes.");
				break;
		}	
		HibernateUtils.cerrarConexion();
	}
	// obtengo una lista con todos los chistes, filtro e imprimo los que no tienen ningún flag en su set.
	public static void buscarJokesSinFlags(){
		HibernateUtils.abrirConexion();
		HibernateUtils.devolverListaObjetos(Jokes.class).stream().filter(c -> ((Jokes)c).getFlagses().size()==0)
		.sorted((c1,c2)-> ((Jokes)c1).getId()-((Jokes)c2).getId()).forEach(System.out::println);
		HibernateUtils.cerrarConexion();
	}
	// obtengo una lista con todos los lenguajes, filtro e imprimo los que no tienen ningún chiste en su set.
	public static void buscarLenguajesSinJokes() {
		HibernateUtils.abrirConexion();
		HibernateUtils.devolverLenguajes().stream().filter(c -> c.getJokeses().size()==0).forEach(System.out::println);
		HibernateUtils.cerrarConexion();
	}
	
	//Según la clase indicada en el parámetro, obtengo un objeto de su tabla correspondiente
	
	public static Object obtenerObjeto(String clase) {
		int num = 0, id=0;
		String text="";
		boolean res = false;
		List<?>lista = null;
		List<Integer> listaId = new ArrayList<Integer>();
		Scanner sc = new Scanner (System.in);
		
		//obtengo todos los objetos de la clase
		lista = HibernateUtils.devolverListaObjetos(clase);
		
		/*Según la clase, ordeno la lista por id y copio los id ordenados en otra lista. 
		 * También asigno el texto correspondiente a la variable tipo string, para personalizar el mensaje
		 */
			switch(clase) {
				case "Categories":
					lista.sort((c1,c2)-> ((Categories)c1).getId()-((Categories)c2).getId());
					listaId=lista.stream().map(c-> ((Categories)c).getId()).collect(Collectors.toList());
					text="de la categoría: ";
					break;
				case "Language":
					lista.sort((c1,c2)-> ((Language)c1).getId()-((Language)c2).getId());
					listaId=lista.stream().map(c-> ((Language)c).getId()).collect(Collectors.toList());
					text="del lenguaje: ";
					break;			
				case "Types":
					lista.sort((c1,c2)-> ((Types)c1).getId()-((Types)c2).getId());
					listaId=lista.stream().map(c-> ((Types)c).getId()).collect(Collectors.toList());
					text="del tipo: ";
					break;
				case "Flags":
					lista.sort((c1,c2)-> ((Flags)c1).getId()-((Flags)c2).getId());
					listaId=lista.stream().map(c-> ((Flags)c).getId()).collect(Collectors.toList());
					text="del flag: ";
					break;
				case "Jokes":
					lista.sort((c1,c2)-> ((Jokes)c1).getId()-((Jokes)c2).getId());
					listaId=lista.stream().map(c-> ((Jokes)c).getId()).collect(Collectors.toList());
					text= "del chiste";
					break;		
			}		
		do{
			//imprimo la lista  y solicito que el usuario inserta un id de la lista, comprobando el dato
			lista.forEach(System.out::println);
			System.out.println();
			System.out.println("Inserta el id "+text);
			
			try {
				id= Integer.parseInt(sc.nextLine());
				if(listaId.contains(id)) {				
					num= listaId.indexOf(id);
					res=true;
				}else {
					System.out.println("Id inexistente");
					res=false;
				}				
			}catch(Exception e) {
				System.out.println("No es un número!");
				res = false;
			}			
		}while(!res);
		// devuelvo el objeto elegido
		return lista.get(num);
	}
	
	//creo un objeto de la clase pasada por parámetro y llamo al metodo correspondiente para insertarlo en la BD
	public static void insertar(String clase){		
		Categories category= null;
		Language lang = null;
		Types tipo=null;
		String text1 = null, text2=null, st="";
		Set<Flags> flags=new HashSet<Flags>();
		Scanner sc = new Scanner(System.in);
		List<Flags>listaFl= new ArrayList<Flags>();
		List<?>lista= null;
		boolean res=false;
		int id= 0, lastIndex=0;
		
		HibernateUtils.abrirConexion();
		
		//Obtengo los objetos del tipo solicitado
		lista=HibernateUtils.devolverListaObjetos(clase);	
		switch(clase) {
			case "Jokes":					
				//obtengo la categoría
				category=(Categories)obtenerObjeto("Categories");

				//obtengo el lenguaje
				lang = (Language)obtenerObjeto("Language");
				//obtengo el tipo
				tipo=(Types) obtenerObjeto("Types");
				
				//según el tipo indicado, solicito los textos correspondientes
				if(tipo.getType().equals("twopart")) {
					System.out.println("Inserta primer texto:");
					text1=sc.nextLine();
					System.out.println("Inserta segundo texto:");
					text2=sc.nextLine();
				}else {
					System.out.println("Inserta texto del chiste:");
					text1=sc.nextLine();
				}
				//obtengo una lista de los flags, la recorro y pregunto si el usuario los quiere asignar al chiste
				listaFl=HibernateUtils.devolverListaObjetos(Flags.class);
				
				for(Flags fl:listaFl) {
					res=false;
					do {
						System.out.println("Asignar el flag '"+fl.getFlag()+ "' al chiste? (s/n)");
						st=sc.nextLine().toLowerCase();
						if(st.equalsIgnoreCase("s")) {
							flags.add(fl);
							res=true;
						}else if(st.equalsIgnoreCase("n")){
							res=true;
							
						}else {
							System.out.println("Respuesta incorrecta.");
							res=false;
						}
					}while(!res);	 
				}
				//ordeno la lista de objetos por id y obtengo el último.
				lista.sort((c1,c2)-> ((Jokes)c1).getId()-((Jokes)c2).getId());
				lastIndex=lista.size()-1;
				id=((Jokes)lista.get(lastIndex)).getId();
				
				//llamo al método para guardar el chiste que le paso por parámetro y si me devuelve true, confirmo la inserción.
				if(HibernateUtils.save(new Jokes((id+1), category,lang, tipo,text1, text2, flags))){
					System.out.println("Se ha insertado el registro");
					
				}
				break;
			case "Categories":	
				//ordeno la lista de objetos por id y obtengo el último.
				lista.sort((c1,c2)-> ((Categories)c1).getId()-((Categories)c2).getId());
				lista.forEach(System.out::println);
				lastIndex=lista.size()-1;
				id=((Categories)lista.get(lastIndex)).getId();
				res=false;
				//solicito el texto de la categoría, no permito valores nulos, ni strings vacíos
				do {
					 System.out.println("Inserta la categoría:");
					 st=sc.nextLine().trim();
					 if(st.equals("")|| st ==null) {
						 res=false;

					 }else {
						//llamo al método para guardar la categoría que le paso por parámetro y si me devuelve true, confirmo la inserción.
						 if(HibernateUtils.save(new Categories((id+1),st))) {
							 System.out.println("Se ha insertado el registro");			
						 }
						 res=true;
					 }	 
				}while(!res);
				 break;
			case "Language":
				//ordeno la lista de objetos por id y obtengo el último.
				lista.sort((c1,c2)-> ((Language)c1).getId()-((Language)c2).getId());
				lista.forEach(System.out::println);
				lastIndex=lista.size()-1;
				id=((Language)lista.get(lastIndex)).getId();
				 do {
					//solicito el texto del lenguaje, no permito valores nulos, ni strings vacíos
					 System.out.println("Inserta el lenguaje:");
					 st=sc.nextLine().trim();
					 if(st.equals("")|| st ==null) {
						 res=false;

					 }else {
						 //solicito el código de 2 letras
						 System.out.println("Inserta el código:");
						 text1=sc.nextLine().trim();
						 if(text1.length()==2) {
							 res=true;
						 }else {
							 System.out.println("El código debe tener 2 letras.");
							 res=false;
						 }
					 }

				 }while(!res);
				//llamo al método para guardar el lenguaje que le paso por parámetro y si me devuelve true, confirmo la inserción.
				 if(HibernateUtils.save(new Language((id+1),text1,st))) {
					 System.out.println("Se ha insertado el registro");
				 }
				 break;
			case "Flags":	
				//ordeno la lista por id y obtengo el último
				lista.sort((c1,c2)-> ((Flags)c1).getId()-((Flags)c2).getId());
				lista.forEach(System.out::println);
				lastIndex=lista.size()-1;
				id=((Flags)lista.get(lastIndex)).getId();
				res=false;
				do {
					//solicito el texto del flag, no permito valores nulos, ni strings vacíos
					 System.out.println("Inserta el flag:");
					 st=sc.nextLine().trim();
					 if(st.equals("")|| st ==null) {
						 res=false;
	
					 }else {
						//llamo al método para guardar el flag que le paso por parámetro y si me devuelve true, confirmo la inserción.
						 if(HibernateUtils.save(new Flags((id+1),st))) {
							 System.out.println("Se ha insertado el registro");
							 res=true;
						 }
					 }
				}while(!res);
				 break;
		}
		HibernateUtils.cerrarConexion();
	}
	// método que modifica un objeto según el nombre de la clase pasado por parámetro.
	public static void update(String clase) {
		String text1=null, text2=null, st="", str="";
		boolean res = false, rs=false;
		Jokes joke = null;
		Categories cat = null;
		Flags flag=null;
		Types tipo= null;
		Language lang= null;
		List<Flags> flags=null;
		Set<Flags> flg = new HashSet<Flags>();
		
		Scanner sc = new Scanner(System.in);
		HibernateUtils.abrirConexion();
		
		switch(clase) {
			case "Jokes":
				// obtengo el objeto a modificar
				joke =(Jokes)obtenerObjeto(clase);
				System.out.println(joke);
				
				/* pregunto si quiere cambiar la categoría del chiste, en caso afirmativo, 
				 * obtengo el objeto de la clase Categories y cambio la categoría del chiste
				 */
				while(!res) {
					System.out.println("¿Quieres cambiar la categoría? (s/n)");
					st=sc.nextLine().trim();
					if(st.equalsIgnoreCase("s")) {
						joke.setCategories((Categories)obtenerObjeto("Categories"));
						res= true;
					}else if(st.equalsIgnoreCase("n")) {
						System.out.println("No se ha cambiado la categoría");
						res= true;
					}else {
						System.out.println("Opción desconocida");
						res= false;
					}
				}
				/* pregunto si quiere cambiar el lenguaje del chiste, en caso afirmativo, 
				 * obtengo el objeto de la clase Language y cambio el llenguaje del chiste
				 */
				res= false;
				while(!res) {
					System.out.println("¿Quieres cambiar el lenguaje? (s/n)");
					st=sc.nextLine().trim();
					if(st.equalsIgnoreCase("s")) {
						joke.setLanguage((Language)obtenerObjeto("Language"));
						res= true;
					}else if(st.equalsIgnoreCase("n")){
						System.out.println("No se ha cambiado el lenguaje");
						res= true;
					}else {
						System.out.println("Opción desconocida");
						res= false;
					}
				}
				/* pregunto si quiere cambiar el tipo del chiste, en caso afirmativo, 
				 * obtengo el objeto de la clase Types y cambio el tipo del chiste
				 * 
				 */
				res= false;
				rs=false;
				while(!res) {
					System.out.println("¿Quieres cambiar el tipo? (s/n)");
					st=sc.nextLine().trim();
					if(st.equalsIgnoreCase("s")) {
						tipo= (Types)obtenerObjeto("Types");
						
						/* en caso de que  se elige el tipo "single", compruebo el tipo del chiste.
						 * Si coincide, pregunto si quiere cambiar el texto. 
						 * Al final actualizo el tipo del chiste,por si estaba nulo. 
						 */
						if( tipo.getType().equals("single")) {
							if(joke.getTypes() == null || joke.getTypes().getType().equals("single")){
								while(!rs) {
									System.out.println("¿Quieres cambiar el texto? (s/n)");
									str=sc.nextLine().trim();
									if(str.equalsIgnoreCase("s")) {
										System.out.println("Inserta el texto:");
										text1=sc.nextLine();
										if(!text1.equals("") && text1!=null) {
											joke.setText1(text1);
											rs=true;
										}else {
											System.out.println("No se ha cambiado el texto.");
										}
										
									}else if(str.equalsIgnoreCase("n")) {
										System.out.println("No se ha cambiado el texto");
										rs= true;
									}else {
										System.out.println("Opción desconocida");
										rs=false;
									}	
								}	
								joke.setTypes(tipo);
							}else {
								// en caso de que el chiste es "twopart", aviso que si cambia el tipo, se elimina el segundo texto del chiste.
								rs=false;
								System.out.println("Si cambias el tipo a single, se eliminará el segundo texto. ¿Quieres seguir? (s/n)");
								str= sc.nextLine().trim();
								while(!rs) {
									if(str.equalsIgnoreCase("s")) {
										// En caso afirmativo doy una opción para cambiar primer texto y quito el segundo texto
										System.out.println("Inserta primer texto:");
										text1=sc.nextLine();
										if(!text1.equals("") && text1!=null) {
											joke.setText1(text1);
											joke.setText2(null);
											rs=true;
										}
										joke.setTypes(tipo);
									
									}else if(str.equalsIgnoreCase("n")) {
										System.out.println("No se ha cambiado el tipo");
										rs= true;
									}else {
										System.out.println("Opción desconocida");
										rs=false;
									}
								}
							}
							// si se elige cambiar el tipo a twopart, doy la opción de cambiar el primer texto
						}else if( tipo.getType().equals("twopart")) {
							rs=false;
							
								while(!rs) {
									System.out.println("¿Quieres cambiar el  primer texto? (s/n)");
									str=sc.nextLine().trim();
									if(str.equalsIgnoreCase("s")) {
										System.out.println("Inserta el texto:");
										text1=sc.nextLine().trim();
										if(!text1.equals("") && text1!=null) {
											joke.setText1(text1);
											rs=true;
										}else {
											System.out.println("No se ha cambiado el texto.");
										}
										
									}else if(str.equalsIgnoreCase("n")) {
										System.out.println("No se ha cambiado el texto");
										rs= true;
									}else {
										System.out.println("Opción desconocida");
										rs=false;
									}								
								}
								//En caso de que el tipo del chiste era "single", obligo a insertar segundo texto
								if(joke.getTypes().getType().equals("single")) {
									rs=false;
									while(!rs) {
										System.out.println("Inserta segundo texto");
										text2=sc.nextLine().trim();
										if(text2 != null && !text2.equals("")) {
											joke.setText2(text2);
											rs=true;
										}else {
											
											rs=false;
										}
									}
								}else {
									//En caso de que el chiste ya era del tipo "twopart" doy la opción de cambiar segundo texto
									System.out.println("¿Quieres cambiar segundo texto? (s/n)");
									str=sc.nextLine().trim();
									if(str.equalsIgnoreCase("s")) {
										System.out.println("Inserta el texto:");
										text2=sc.nextLine().trim();
										if(!text2.equals("") && text2!=null) {
											joke.setText2(text2);
											rs=true;
										}else {
											System.out.println("No se ha cambiado el texto.");
										}
										
									}else if(str.equalsIgnoreCase("n")) {
										System.out.println("No se ha cambiado el texto");
										rs= true;
									}else {
										System.out.println("Opción desconocida");
										rs=false;
									}
								}
	
								joke.setTypes(tipo);
						}
							
						res=true;
					}else if(st.equalsIgnoreCase("n")){
						System.out.println("No se ha cambiado el tipo");
						res= true;
					}else {
						System.out.println("Opción desconocida");
						res= false;
					}
				}
				// imprimo los flags del chiste y pregunto si los quiere cambiar
				rs=false;
				while(!rs) {
					System.out.println("El chiste contiene los sigüientes flags:");
					joke.getFlagses().forEach(System.out::println);
					System.out.println("¿Quieres cambiar los flags? (s/n)");
					str=sc.nextLine().trim();
					if(str.equalsIgnoreCase("s")) {
						/* en caso afirmativo obtengo el listado de los flags, 
						 * lo recorro y pregunto si quiere asignar los flags al chiste.
						 */
						flags=HibernateUtils.devolverListaObjetos(Flags.class);
						for(Flags fl: flags) {
							res=false;
							do {
								System.out.println("Asignar el flag '"+fl.getFlag()+ "' al chiste? (s/n)");
								st=sc.nextLine().toLowerCase();
								if(st.equalsIgnoreCase("s")) {
									//añado el flag a un set
									flg.add(fl);
									res=true;
								}else if(st.equalsIgnoreCase("n")){
									res=true;
									
								}else {
									System.out.println("Respuesta incorrecta.");
									res=false;
								}
							}while(!res);
						}
						//actualizo el set del chiste
						joke.setFlagses(flg);
						rs=true;
					}else if(str.equalsIgnoreCase("n")) {
						System.out.println("No se han cambiado los flags");
						rs= true;
					}else {
						System.out.println("Opción desconocida");
						rs=false;
					}
				}

				str="id="+joke.getId();
				// llamo al método update para actualizar el chiste en la BD y si me devuelve true, confirmo la actualización
				if(HibernateUtils.update(Jokes.class, str, joke)) {
					System.out.println("Se ha actualizado el chiste");
				}
				break;
			case "Categories":
				res = false;
				// obtengo el objeto a modificar
				cat=(Categories)obtenerObjeto(clase);
				System.out.println(cat);
				// solicito la nueva descripción, aviso de que se cambiará en todos los jokes afectados y pido una confirmación
				System.out.println("Inserta la categoría:");
				st=sc.nextLine().trim();
				if(st != null && !st.equals("")) {
					while(!res) {
						System.out.println("¿Quieres cambiar '"+cat.getCategory()+"' por '"+st+"'?\nSe cambiará la categoría en todos los chistes asociados. (s/n) ");
						str=sc.nextLine().trim();
						if(str.equalsIgnoreCase("s")) {
							cat.setCategory(st);
							st ="id="+cat.getId();
							// llamo al método update para actualizar la categoría en la BD y si me devuelve true, confirmo la actualización
							if(HibernateUtils.update(Categories.class, st,cat)) {
								System.out.println("Se ha actualizado el registro");
							}
							res=true;
						}else if(str.equalsIgnoreCase("n")){
							System.out.println("No se ha hecho ningún cambio");
							res=true;
						}else {
							System.out.println("Opción desconocida");
							res=false;
						}
					}
				}else {
					System.out.println("No se ha insertado ningún valor. \nNo se ha actualizado la categoría");
				}
				break;
			case "Language":
				res = false;
				rs=false;
				// obtengo el objeto a modificar
				lang= (Language)obtenerObjeto(clase);
				System.out.println(lang);
				// solicito la nueva descripción, aviso de que se cambiará en todos los jokes afectados y pido una confirmación
				System.out.println("Inserta el lenguaje:");
				st=sc.nextLine().trim();
				if(st != null && !st.equals("")) {
					while(!res) {
						System.out.println("¿Quieres cambiar '"+lang.getLanguage()+"' por '"+st+"'?\\nSe cambiará el lenguaje en todos los chistes asociados.(s/n) ");
						str=sc.nextLine().trim();
						if(str.equalsIgnoreCase("s")) {
							lang.setLanguage(st);
							while(!rs) {
								System.out.println("Inserta el código del lenguaje:");
								str=sc.nextLine().trim();
								if(str.length()==2) {
									lang.setCode(str);
									st ="id="+lang.getId();
									// llamo al método update para actualizar el chiste en la BD y si me devuelve true, confirmo la actualización
									if(HibernateUtils.update(Language.class, st,lang)) {
										System.out.println("Se ha actualizado el registro");
									}
									rs=true;
								}else {
									System.out.println("El código debe tener 2 letras");
									rs=false;
								}
							}
							res=true;
						}else if(sc.nextLine().equalsIgnoreCase("n")){
							System.out.println("No se ha hecho ningún cambio");
							res=true;
						}else {
							System.out.println("Opción desconocida");
							res=false;
						}
					}
				}else {
					System.err.println("No se ha insertado ningún valor. \nNo se ha actualizado el registro");
				}
				
				break;
			case "Flags":
				res = false;
				// obtengo el objeto a modificar
				flag=(Flags)obtenerObjeto(clase);
				System.out.println(flag);
				// solicito la nueva descripción, aviso de que se cambiará en todos los jokes afectados y pido una confirmación
				System.out.println("Inserta la descripción del flag:");
				st=sc.nextLine().trim();
				if(st != null && !st.equals("")) {
					while(!res) {
						System.out.println("¿Quiere cambiar '"+flag.getFlag()+"' por '"+st+"'? \\nSe cambiará el flag en todos los chistes asociados.(s/n) ");
						str=sc.nextLine().trim();
						if(str.equalsIgnoreCase("s")) {
							flag.setFlag(st);
							st ="id="+flag.getId();
							// llamo al método update para actualizar el chiste en la BD y si me devuelve true, confirmo la actualización
							HibernateUtils.update(Flags.class, st,flag);
							System.out.println("Se ha actualizado el registro");
							res=true;
						}else if(str.equalsIgnoreCase("n")){
							System.out.println("No se ha hecho ningún cambio");
							res=true;
						}else {
							System.out.println("Opción desconocida");
							res=false;
						}
					}
				}else {
					System.err.println("No se ha insertado ningún valor.\nNo se ha actualizado el registro");
				}
				
				break;		
		}

		HibernateUtils.cerrarConexion();		
	}
	// método para borrar un objeto
	public static<T>  void borrarObjeto(Class<T> clase) {
		boolean res=false, result=false, salir = false;
		String str="", st="",text="",text2="";
		int num=0, opt=0;
		Categories cat = null;
		Jokes joke=null;
		Language lang=null;
		Flags flag=null;
		Set<Jokes>lista=null;
		
		Scanner sc = new Scanner(System.in);
		HibernateUtils.abrirConexion();
		// según la clase pasada por parámetro, obtengo el objeto, su id y un texto personalizado para imprimir luego
		switch(clase.getSimpleName()) {
			case "Jokes":
				joke=(Jokes)obtenerObjeto(clase.getSimpleName());
				st="id="+joke.getId();
				text="el chiste";
				num=0;
				break;
			case "Categories":
				cat=(Categories)obtenerObjeto(clase.getSimpleName());
				//compruebo el tamaño del set y aviso.
				lista=cat.getJokeses();
				num=lista.size();
				st="id="+cat.getId();
				text="la categoría";
				if(num>0) {
					System.out.println("La categoría tiene "+num+" chistes asociados.");
				}
				break;
			case "Language":
				lang=(Language)obtenerObjeto(clase.getSimpleName());
				//compruebo el tamaño del set y aviso.
				lista=lang.getJokeses();
				num=lista.size();
				st="id="+lang.getId();
				text="el lenguaje";
				if(num>0) {
					System.out.println("El lenguaje tiene "+num+" chistes asociados.");
				}
				break;
			case "Flags":
				flag=(Flags)obtenerObjeto(clase.getSimpleName());
				//compruebo el tamaño del set y aviso.
				lista=flag.getJokeses();
				num=lista.size();
				st="id="+flag.getId();
				text="el flag";
				if(num>0) {
					System.out.println("El flag tiene "+num+" chistes asociados.");
				}
				break;
		}
		while(!res) {
			//pido una confirmación del borrado
			System.out.println("¿Seguro que quieres borrar "+text+"? (s/n)");
			str=sc.nextLine().trim();
			if(str.equalsIgnoreCase("s")) {
				// si el objeto tiene chistes asociados, llamo a un método del menú para elegir el tipo del borrado.
				if(num>0) {
					do {
						opt=Menu.elegirBorrado(clase.getSimpleName());
						switch(opt) {
						/* en caso de elegir poner los valores nulos en los chistes asociados, modifico el chiste. 
						 * En caso de los flags no hace falta, ya que no afectan directamente a los chistes y al haber sido borrado, 
						 * se borran los registros en la tabla intermedia.
						 */
							case 1:
								
								if(cat !=null || lang !=null) {
									for(Jokes jk : lista) {
										if(cat!=null) {
											jk.setCategories(null);
										}else if(lang!=null) {
											jk.setLanguage(null);
										}
										//lamo al método update para modificar el chiste en la BD
										if(HibernateUtils.update(Jokes.class,"id="+jk.getId(),jk)) {
											result=true;
										}
									}
								}else {
									
									result=true;
								}
								if(result) {
									System.out.println("Se han actualizado los chistes correspondientes");
								}else {
									System.out.println("No se han actualizado los chistes");
								}
								salir = false;
								break;
							case 2:
								// en caso de elegir borrar los chistes correspondientes, borro los chistes del set
								for(Jokes jk : lista) {
									HibernateUtils.deleteById(Jokes.class,"id = "+jk.getId());
								}
								System.out.println("Se han borrado los chistes correspondientes.");
								result=true;
								salir = false;
								break;
							case 0: 
								result = true; 
								salir = true;	
								break;
						}
						
					}while(!result);
				}
				if(!salir) {
					//si la opción no es 0, llamo el método deleteById, borro el registro correspondiente en la BD y confirmo el borrado
					opt=HibernateUtils.deleteById(clase, st);
					if(opt== -1) {
						System.out.println("No se ha eliminado el registro. Algo ha salido mal.");
					}else {
						System.out.println("Se ha borrado el registro");
					}
				}
				res=true;
		
			}else if(str.equalsIgnoreCase("n")){
				System.out.println("No se ha hecho ningún cambio");
				res=true;
			}else {
				System.out.println("Opción desconocida");
				res=false;
			}
		}
		HibernateUtils.cerrarConexion();	
	}
	

}
