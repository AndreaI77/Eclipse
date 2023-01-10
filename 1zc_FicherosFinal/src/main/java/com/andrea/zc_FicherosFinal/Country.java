package com.andrea.zc_FicherosFinal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Country {
	
	public static void obtenerPaises(String region) {
		String url="https://restcountries.com/v2/all?fields=name,capital,region";
		String json = null;
		String name;
		String capital = null;
		String pais;
		System.out.println(region+" contiene estos países:");
		try {
			json = Conexion.readUrl(url);
			//System.out.println(json);
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(json).getAsJsonArray();
			
			for (JsonElement el : array) {
				JsonObject obj = el.getAsJsonObject();
				name = obj.get("name").getAsString();
				try {
					//hay registros como por ejemplo Antarctica, que no tienen capital
					capital = obj.get("capital").getAsString();
				}catch(NullPointerException e) {
					capital = "No tiene capital";
				}
				
				pais = obj.get("region").getAsString();
				
				if (pais.equals(region)) {
					System.out.println(name + ", capital: " + capital);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
