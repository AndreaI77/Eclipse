package com.andrea.s_FicherosAmigos;

import java.io.Serializable;

public class Persona implements Serializable{
	private String nombre;
	private int edad;
	private String email;
	private String comentario;
	
	public Persona() {
		
	}

	public Persona(String nombre, int edad, String email, String comentario) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		
			this.edad = edad;
				
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", email=" + email + ", comentario=" + comentario + "]";
	}

}
