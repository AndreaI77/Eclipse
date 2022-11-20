package com.andrea.zc_FicherosFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Medicion;

public class BuscarCoordenadas {
	final static String apiKey = "fcd9d465ad20815067d4dac2e5c1d73c";
	
	private static Medicion med;
	
	public static boolean comprobarFloat(String cadena) {
		boolean resultado = false;
		try {
			Float.parseFloat(cadena);
			resultado=true;
		}catch(Exception e) {
			resultado= false;
			
		}
		return resultado;
	}
	public static Medicion obtenerLatLong() {
		
		float lat = 0.0f;
		String respuesta ="";
		boolean resultado=false;
		float lon = 0.0f;
		String name="";
		float temp = 0.0f;
		int humidity = 0;
		int id= 0;
		Map weather = new HashMap();
		String descripcion;
		String icon;
		String mn;

		Scanner scan = new Scanner(System.in);
		
		while(resultado == false) {
			System.out.println("Escribe latitud:");
			respuesta = scan.nextLine();
			resultado = comprobarFloat(respuesta);
		}
		lat = Float.parseFloat(respuesta);
		resultado = false;
		while(resultado == false) {
			System.out.println("Escribe longitud:");
			respuesta = scan.nextLine();
			resultado = comprobarFloat(respuesta);
		}
		lon= Float.parseFloat(respuesta);
		
		
		try {
			String json = Conexion.readUrl("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
					+ "&appid=" + apiKey + "&units=metric");
			// System.out.println(json);
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(json).getAsJsonObject();

			name = obj.get("name").getAsString();

			JsonObject js = obj.get("coord").getAsJsonObject();
			lat = js.get("lat").getAsFloat();
			lon = js.get("lon").getAsFloat();
			JsonObject gsonOb = obj.get("main").getAsJsonObject();

			temp = gsonOb.get("temp").getAsFloat();
			humidity = gsonOb.get("humidity").getAsInt();
			JsonArray tiempo = obj.get("weather").getAsJsonArray();

			for (JsonElement el : tiempo) {
				JsonObject gsO = el.getAsJsonObject();
				id = gsO.get("id").getAsInt();
				descripcion = gsO.get("description").getAsString();
				mn = gsO.get("main").getAsString();

				weather.put("id", id);
				weather.put("main", mn);
				weather.put("description", descripcion);
			}
			LocalDate fecha = LocalDate.now();
			med = new Medicion(fecha, name, lat, lon, weather, temp, humidity);
			System.out.println(med);

			if (name.equals("")) {
				System.out.println(
						"No existe ninguna ciudad en estas coordenadas, las mediciones corresponden al punto más cercano");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return med;
	}
}
