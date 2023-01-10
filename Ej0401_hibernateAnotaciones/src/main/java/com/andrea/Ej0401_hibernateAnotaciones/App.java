package com.andrea.Ej0401_hibernateAnotaciones;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	@SuppressWarnings("unused")
    	org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null) {
       	 System.out.println("Sesión abierta");
        }else {
       	 System.out.println("fallo en la sesión");
        }
    }
}
