package com.andrea.v_FicherosXMLconDOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Hello world!
 *
 */
public class readXmlDOM
{
    public static void main( String[] args )
    {
        try {
        	File file = new File("asignaturas.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc =dBuilder.parse(file);
        	
        	doc.getDocumentElement().normalize();
        	
        	System.out.println("elemento base: "+doc.getDocumentElement().getNodeName());
        	NodeList nList = doc.getElementsByTagName("asignatura");
        	System.out.println();
        	
        	System.out.println("Recorriendo asignaturas....");
        	for(int i=0; i<nList.getLength(); i++) {
        		Node nNode = nList.item(i);
        		if(nNode.getNodeType()== Node.ELEMENT_NODE) {
        			Element el =(Element) nNode;
        			
        			if(el.getElementsByTagName("curso").item(0).getTextContent().toLowerCase().equals("segundo")) {
        				System.out.println("Código: "+el.getAttribute("id"));
            			System.out.println("Nombre: "+el.getElementsByTagName("nombre").item(0).getTextContent());
            			System.out.println("Ciclo: "+el.getElementsByTagName("cicloFormativo").item(0).getTextContent());
            			System.out.println("Curso: "+el.getElementsByTagName("curso").item(0).getTextContent());
            			System.out.println();
        			}
        			
        			
        		}
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
