package com.andrea.Hibernate1;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner sc;
    public static void mostrarLibros( )
    {  
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
         Session session = sessionFactory.openSession();
         if(session != null) {
        	 System.out.println("Sesión abierta");
         }else {
        	 System.out.println("fallo en la sesión");
         }
         Query<Libros> consulta = session.createQuery("from Libros");
         List<Libros> resultados = consulta.list();
         for(Object resultado : resultados) {
        	 Libros libro = (Libros) resultado;
        	 System.out.println(libro.getId()+ ": "+libro.getTitulo()
        	 +", de "+libro.getAutores().getNombre());
         }
         session.close();
    }
    public static void anyadir() {
    	System.out.println("Introduzca el código del libro: ");
    	int id = Integer.parseInt(sc.nextLine());
    	System.out.print("Introduzca el título: ");
    	String titulo = sc.nextLine();
    	System.out.print("Introduzca el autor: ");
    	String autor = sc.nextLine();
    	SessionFactory sessionFactory = new
    	Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction trans = session.beginTransaction();
    	Libros libro = new Libros(id, new Autores(autor), titulo);
    	session.save(libro);
    	trans.commit();
    	session.close();
    }
    public static void main(String[]args) {
    	@SuppressWarnings("unused")
    	org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	
    	boolean terminado = false;
    	sc = new Scanner(System.in);
    	do {
    	System.out.println("Escoja una opción:");
    	System.out.println("1. Añadir un nuevo dato");
    	System.out.println("2. Ver todos los datos existentes");
    	System.out.println("0. Salir");
    	String opcion = sc.nextLine();
    	if (opcion.equals("1"))
    		anyadir();
    	else if (opcion.equals("2"))
    		mostrarLibros();
    	else if (opcion.equals("0"))
    		terminado = true;
    	} while (!terminado);
    		sc.close();
    }
}
