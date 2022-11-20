package com.andrea.y_FicherosXMLconDOM2;

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
public class App 
{
    public static void main( String[] args )
    {
    	try {
        	File file = new File("coches.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc =dBuilder.parse(file);
        	
        	doc.getDocumentElement().normalize();
        	
        	System.out.println(doc.getDocumentElement().getNodeName()+": ");
        	NodeList nList = doc.getElementsByTagName("coche");
        	System.out.println();
        	
        	
        	for(int i=0; i<nList.getLength(); i++) {
        		Node nNode = nList.item(i);
        		if(nNode.getNodeType()== Node.ELEMENT_NODE) {
        			Element el =(Element) nNode;
        			
        			if(el.getElementsByTagName("marca").item(0).getTextContent().equalsIgnoreCase("seat")) {
        				
            			System.out.println(el.getElementsByTagName("marca").item(0).getTextContent()+": "+el.getElementsByTagName("modelo").item(0).getTextContent());
            			
            			
        			}
        			
        			
        		}
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
}
