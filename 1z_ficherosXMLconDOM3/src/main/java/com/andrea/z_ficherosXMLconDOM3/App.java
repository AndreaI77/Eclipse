package com.andrea.z_ficherosXMLconDOM3;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class App 
{
    public static void main( String[] args )
    {
    	String marca;
    	List<Coche> lista = new ArrayList<>();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Introduce la marca:");
    	marca = sc.nextLine();
        try {
        	File file = new File("coches.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(file);
        	doc.getDocumentElement().normalize();
        	
        	
        	NodeList nList = doc.getElementsByTagName("coche");
        	
        	for ( int i=0; i<nList.getLength();i++) {
        		Node nNode = nList.item(i);
        		
        		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
        			
        			Element eElement = (Element) nNode;
        			if(eElement.getElementsByTagName("marca")
        					.item(0).getTextContent().equalsIgnoreCase(marca)) {
        				lista.add(new Coche(Integer.parseInt(eElement
        						.getElementsByTagName("cilindrada").item(0).getTextContent()),
        						eElement.getElementsByTagName("modelo").item(0).getTextContent()));
        			}
        		}
        	}
        	
       
        }catch(Exception e) {
        	e.printStackTrace();
        }
        Comparator<Coche> comp = (Coche c1, Coche c2)->{
        	if(c2.getCilindrada() == c1.getCilindrada()) {
        		return c2.getModelo().compareTo(c1.getModelo());
        	}
        	return c2.getCilindrada() - c1.getCilindrada();
        };
        
        lista.stream().sorted(comp).forEach(System.out::println);
    }
}
