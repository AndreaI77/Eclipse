package com.andrea.zb_FicherosJsonInternet;

import java.io.*;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;

/*
 * Given a URL that offers data in JSON format such as:
 *
 * https://jsonplaceholder.typicode.com/todos
 *
 * Investigate how to access that data directly from the web, to do so use
 * the Java API of the views you prefer to read the data from that URL, and
 * load in an ArrayList of objects of type User (class you must create), of
 * those users whose "userId" field is an even number and the "completed"
 * field has a value of true.
 *
 * Subsequently it takes the results of that ArrayList and serialize it.
 */
public class App 
{
	public static String readUrl(String urlString) throws Exception{
		BufferedReader reader = null;
		
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while((read = reader.read(chars)) != -1) {
				buffer.append(chars,0,read);
			}
			return buffer.toString();
		}finally {
			if(reader != null) {
				reader.close();
			}
		}
		
	}
	
	public static void serializeUsers(List<User> users) {
		try {
			ObjectOutputStream objectFile = new ObjectOutputStream(new ObjectOutputStream(
					new FileOutputStream("users.dat")));
			objectFile.writeObject(users);
			objectFile.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static List<User> readFromJson(){
		
		try {
			String json = readUrl("https://jsonplaceholder.typicode.com/todos");
			
			Gson gson = new Gson();
			User[]auxUser = gson.fromJson( json, User[].class);
			List<User> users = new ArrayList<User>();
			
			for(User u: auxUser) {
				if(u.getUserId() %2 == 0 && u.isCompleted()) {
					users.add(u);
				}
			}
			return users;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public static void main( String[] args )
    {
    	List<User> users = readFromJson();
    	users.stream().forEach(System.out::println);
    	//serializeUsers(users);
       
    }
}
