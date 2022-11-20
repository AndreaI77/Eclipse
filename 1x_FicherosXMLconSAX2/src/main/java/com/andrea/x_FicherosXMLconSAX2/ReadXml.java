package com.andrea.x_FicherosXMLconSAX2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXml extends DefaultHandler {
	public void procesarXml() {
    	try {
    		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    		SAXParser saxParser = saxParserFactory.newSAXParser();
    		DefaultHandler manejadorEventos = new DefaultHandler() {
    			String etiquetaActual = "";
    			String contenido = "";
    			String marca = "", modelo = "", cilindrada = "";
    			
    			
    			// Método que se llama al encontrar inicio de etiqueta: '<'
    			public void startElement(String uri, String localName, String qName,
    					Attributes attributes) throws SAXException{
    				etiquetaActual = qName;
    				if (etiquetaActual.equals("coche")) {
						marca = "";
						modelo = "";
						cilindrada = "";
					}
    			}
    			
    			 // Obtiene los datos entre '<' y '>'
    			public void characters(char ch[], int start, int length) throws SAXException{
    				contenido = new String(ch, start, length);
    			}
    			
    			// Llamado al encontrar un fin de etiqueta: '>'
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (!etiquetaActual.equals("")) {

						if (etiquetaActual.equals("marca")) {
							marca = contenido;
						}
							
						if (etiquetaActual.equals("modelo")) {
							modelo = contenido;
						}
						if (etiquetaActual.equals("cilindrada")) {
							cilindrada = contenido;
						}
							

						if (!marca.equals("") && !modelo.equals("") && !cilindrada.equals("")) {
							if (marca.equalsIgnoreCase("seat"))
								System.out.println(marca+": "+ modelo );
						}

						etiquetaActual = "";
					}

				}
    			
    		};
    		
    		// Cuerpo de la función: trata de analizar el fichero deseado
            // Llamará a startElement(), endElement() y character()
    		saxParser.parse("coches.xml", manejadorEventos);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
