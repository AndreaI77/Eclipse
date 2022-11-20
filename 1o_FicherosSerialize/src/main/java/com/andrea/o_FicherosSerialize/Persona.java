package com.andrea.o_FicherosSerialize;

import java.io.Serializable;


public class Persona implements Serializable{
	private String nombre;
	private String email;
	private String fechaNac;
	
	public Persona() {
		
	}
	public Persona(String nombre, String email, String fechaNac) {
		this.nombre = nombre;
		this.email = email;
		this.fechaNac = fechaNac;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", email=" + email + ", fechaNac=" + fechaNac + "]";
	}
	

}
