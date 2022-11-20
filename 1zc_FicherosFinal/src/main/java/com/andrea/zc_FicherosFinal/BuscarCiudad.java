package com.andrea.zc_FicherosFinal;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Medicion;

public class BuscarCiudad {

	final static String apiKey = "fcd9d465ad20815067d4dac2e5c1d73c";
	private static Medicion med;
	
	public static Medicion obtenerCiudad() {
		float lat = 0.0f;
		float lon = 0.0f;
		String name="";
		float temperatura = 0.0f;
		int humidity = 0;
		int id= 0;
		Map tiempo = new HashMap();
		String descripcion;
		String icon;
		String mn;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Escribe el nombre de la ciudad");
		String ciudad = sc.nextLine().trim();
		
		try {
			
			URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+ciudad+"&mode=xml&appid="+apiKey+"&units=metric");
			URLConnection con = url.openConnection();
			InputStream in = new BufferedInputStream(con.getInputStream());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc =dBuilder.parse(in);
			
			doc.getDocumentElement().normalize();
			//System.out.println(doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getChildNodes() ;
			
			for(int i =0;i<nList.getLength();i++) {
			Node nNode = nList.item(i);			
				if(nNode.getNodeType()== Node.ELEMENT_NODE) {
					Element el = (Element) nNode;
					Element city = (Element)el.getElementsByTagName("city").item(0);
					name=city.getAttribute("name");
					System.out.print("Ciudad: "+name);
					
					Element coor = (Element)el.getElementsByTagName("coord").item(0);
					lat = Float.parseFloat(coor.getAttribute("lat"));
					lon = Float.parseFloat(coor.getAttribute("lon"));
					System.out.print(", Coordenadas: longitud: "+lon+", latitud: "+ lat);
					
					Element temp = (Element) el.getElementsByTagName("temperature").item(0);
					temperatura = Float.parseFloat(temp.getAttribute("value"));
					System.out.print(", temperatura: "+temperatura+" Cº ");	
					
					Element hum = (Element) el.getElementsByTagName("humidity").item(0);
					humidity = Integer.parseInt(hum.getAttribute("value"));
					System.out.print(", humedad: "+humidity);
					
					Element weather = (Element) el.getElementsByTagName("weather").item(0);
					descripcion=weather.getAttribute("value");
					icon=weather.getAttribute("icon");
					mn = weather.getAttribute("number");
					
					tiempo.put("id",id);
					tiempo.put("main",mn);
					tiempo.put("description", descripcion);
					System.out.println(", tiempo: id:"+mn+", valor: "+descripcion+", icono: "+icon);
					
					Element fecha = (Element) el.getElementsByTagName("lastupdate").item(0);
					
					String f = fecha.getAttribute("value");
					String []s = f.split("T");
					System.out.print("Última lectura: "+s[0]+" a las "+s[1]);
					System.out.println();
					
					
				}
			}
			LocalDate fecha= LocalDate.now();
			med = new Medicion(fecha,name, lat, lon,tiempo,temperatura,humidity);
		}catch(FileNotFoundException e) {
			System.out.println("No se ha encontrado la ciudad");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return med;
	}
	
}
