package com.andrea.za_FicherosJSON;

/*
 * Create a program that shows the model of cars of a brand requested to the
 * user by screen, for the file "cars.json", using the Models API, the
 * Streaming API and the GSON API. To do this, it will take out a menu that
 * allows the user which of the 3 to choose API's to use and then the brand
 * that they want to search. The program will run until the user enters an
 * option to exit. In addition, the program must allow the user to put upper
 * or lower case letters and find the mark regardless of this. Sort the output
 * of the results by displacement.
 */





import java.io.*;
import java.nio.file.*;

import java.util.*;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.stream.JsonParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



public class App 
{
	public static void readModel(String inputBrand) {
		boolean carFound = false;
		
		Object obj;
		List<Coche> lista = new ArrayList<>();
		try {
			obj = new JSONParser().parse(new FileReader("coches.json"));
			
			JSONObject jo =(JSONObject) obj;
			JSONObject cars = (JSONObject)jo.get("coches");
			JSONArray car = (JSONArray) cars.get("coche");
			
			Iterator itr1 = car.iterator();
			
			while(itr1.hasNext()) {
				JSONObject c = (JSONObject)itr1.next();
				if(((String) c.get("marca")).equalsIgnoreCase(inputBrand)) {
					
					carFound = true;
					lista.add(new Coche((String)c.get("marca"),(String)c.get("modelo"), Math.toIntExact((Long)c.get("cilindrada"))));
					//System.out.println((String)c.get("modelo"));
					}
				}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}catch (IOException e){
			e.printStackTrace();
			
		}catch(ParseException e) {
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
	
	public static void readStreaming (String inputBrand) {
		
		boolean carFound = false;
		JsonParser parser;
		String brand = "";
		boolean isBrand = false;
		String model = "";
		boolean isModel = false;
		int cilindrada=0;
		boolean isCilindrada = false;
		
		List<Coche> lista = new ArrayList<>();
		
		try {
			parser = Json.createParser(new FileReader("coches.json"));
			while ( parser.hasNext()) {
				JsonParser.Event event = parser.next();
				switch(event) {
				case KEY_NAME:
						switch(parser.getString()) {
							case "marca":
									isBrand = true;
									break;
							case "modelo":
									isModel = true;
									break;
							case "cilindrada":
									isCilindrada = true;
									
						}
						break;
				case VALUE_STRING:
					if(isBrand) {
						brand = parser.getString();
						isBrand = false;
					}else if(isModel) {
						model = parser.getString();
						isModel = false;
					}
					break;
				case VALUE_NUMBER:
					if(isCilindrada) {
						cilindrada = Math.toIntExact(parser.getLong());
						isCilindrada = false;
						System.out.println(cilindrada);
					}
					break;
					
				}
				if(!brand.equals("") && !model.contentEquals("") ) {
					carFound = true;
					if(brand.equalsIgnoreCase(inputBrand)) {
						lista.add(new Coche(brand,model, cilindrada));
						//System.out.println(model);
					}
					brand = "";
					model = "";
					
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			if(!carFound) {
				System.out.println("Coche no encontrado");
			}
		}
		Comparator<Coche> comp = (Coche c1, Coche c2)->{
        	if(c2.getCilindrada() == c1.getCilindrada()) {
        		return c2.getModelo().compareTo(c1.getModelo());
        	}
        	return c2.getCilindrada() - c1.getCilindrada();
        };
        
        lista.stream().sorted(comp).forEach(System.out::println);
				
	}
	
	public static void readGSON(String inputBrand) {
		boolean carFound = false;
		String data = null;
		List<Coche> lista = new ArrayList<>();
		try {
			
			Path path = Paths.get("coches.json");
			data = Files.readAllLines(path).stream().collect(Collectors.joining(""));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		
		com.google.gson.JsonObject gsonOb = parser.parse(data).getAsJsonObject();
		
		com.google.gson.JsonObject gsonOb2 = gsonOb.get("coches").getAsJsonObject();
		
		com.google.gson.JsonArray gsonArr = gsonOb2.get("coche").getAsJsonArray();
		
		for(com.google.gson.JsonElement obj : gsonArr) {
			
			com. google.gson.JsonObject gsonObj = obj.getAsJsonObject();
			if(gsonObj.get("marca").getAsString().equalsIgnoreCase(inputBrand)) {
				carFound = true;
				//System.out.println(gsonObj.get("modelo").getAsString());
				lista.add(new Coche(gsonObj.get("marca").getAsString(),gsonObj.get("modelo").getAsString(), 
						gsonObj.get("cilindrada").getAsInt()));
			}
		}
		if(!carFound) {
			System.out.println("Coche no encontrado");
		}
		Comparator<Coche> comp = (Coche c1, Coche c2)->{
        	if(c2.getCilindrada() == c1.getCilindrada()) {
        		return c2.getModelo().compareTo(c1.getModelo());
        	}
        	return c2.getCilindrada() - c1.getCilindrada();
        };
        
        lista.stream().sorted(comp).forEach(System.out::println);
	}
	
	public static int showMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Elige una opción:\n\n1. API de modelos\n2. API de streaming\n3. GSON\n0. Exit");
		
		
		return sc.nextInt();
	}
	
    public static void main( String[] args )
    {
        int num;
        String modelo = "";
        Scanner sc = new Scanner(System.in);
        do {
        	num = showMenu();
        	
        	if(num != 0) {
        		
        		System.out.println("Choose a brand:");
        		modelo = sc.nextLine();
        		

        	}
        	switch(num) {
        	case 1:readModel(modelo); break;
        	case 2: readStreaming(modelo); break;
        	case 3: readGSON(modelo); break;
        	case 0: System.out.println("Adios");break;
        	default: System.out.println("Opción desconocida");break;
        	}
        	System.out.println();
        }while(num !=0);
        sc.close();
    }
}
