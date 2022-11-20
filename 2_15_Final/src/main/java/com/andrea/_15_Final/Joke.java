package com.andrea._15_Final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Joke {
	String category,type, text, text2, lang;
	
	Map<String, Boolean> flags;
	int id;
	
	
	public Joke(String category, String type, String text, String text2, String lang,
			Map<String, Boolean> flags, int id) {
		super();
		this.category = category;
		this.type = type;
		this.text = text;
		this.text2 = text2;
		this.lang = lang;
		this.flags = flags;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Joke [categoría: " + category + ", tipo: " + type + ", texto: " + text + ", texto2: " + text2 + ", "
				+ ", idioma: " + lang + ", flags: " + flags + ", id: " + id + "]";
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Map<String, Boolean> getFlags() {
		return flags;
	}
	public void setFlags(Map<String, Boolean> flags) {
		this.flags = flags;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public static List<Joke> leerDatos(Propiedades prop) {
		//lee los datos del json 
    	
    	int contador=0, id=0;
    	String category = "";
    	String type = "", text = "", text2 = "", lang = "";
    	Map<String, Boolean> flags = new HashMap<String, Boolean>();
    	Joke jk;
    	List<Joke> jokes = new ArrayList<Joke>();
    	
    	// obtengo la cantidad de chistes de cada idioma
    	List<Range> lista = prop.getRange();
    	
    	try {
    		//en el range están los idiomas y la cantidad de chistes de cada idioma
    		// el método obtiene los datos de cada joke, lo crea y lo añade a la lista
    		for(Range r : lista) {
    			System.out.println("Cargando chistes en idioma '"+r.getIdioma()+"' .........");
    			for(int i =0; i< r.getCantidad();i++) {
    				String texto = Conexion.readUrl("https://v2.jokeapi.dev/joke/Any?idRange="+i+"&lang="+r.getIdioma());
    				System.out.println(texto +contador);
    				JsonParser parser = new JsonParser();
    				JsonObject obj = parser.parse(texto).getAsJsonObject();
    				category = obj.get("category").getAsString();
    				type = obj.get("type").getAsString();
    				if(type.equals("single")) {
    					text=obj.get("joke").getAsString();
    					text2="";
    					
    				}else {
    					
    					text = obj.get("setup").getAsString();
    					text2 = obj.get("delivery").getAsString();
    				}
    				JsonObject jo = obj.get("flags").getAsJsonObject();
    				flags = new Gson().fromJson(jo.toString(), Map.class);
    				//System.out.println(flags);
    				id = obj.get("id").getAsInt();
    				lang = obj.get("lang").getAsString();
    				jk= new Joke(category, type, text, text2, lang, flags, id);
    				jokes.add(jk);
    				Thread.sleep(500);
    				contador++;
    			}
    			
    		}} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return null;
    		}
    	return jokes;
	}
}
