package com.profesor.tema3_exercise10esqueleto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Persona {
	private int numero;
	private String nombre;
	private String[]apellidos;
	 private String calle;
	private int num_casa;
	private String ciudad;
	private String provincia;
	private String c_postal;
	private long telefono;
	private LocalDate f_nac;
	
	
	public Persona(int numero, String nombre, String[] apellidos, String calle, int num_casa, String ciudad,
			String provincia, String c_postal, long telefono, LocalDate f_nac) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.calle = calle;
		this.num_casa = num_casa;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.c_postal = c_postal;
		this.telefono = telefono;
		this.f_nac = f_nac;
	}
	
	
	@Override
	public String toString() {
		String ap="";
		for(int i=0; i< apellidos.length;i++) {
			if(apellidos[i].startsWith("\"")) {
				ap+=apellidos[i].substring(1,apellidos[i].length()-1).trim()+" ";
			}else {
				ap+=apellidos[i]+" ";
			}
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "numero: " + numero + ", \nNombre: " + nombre + ", Apellidos: " + ap
				+ "\nDirección: " + calle +" "+  num_casa + ", " + ciudad + ", (" + provincia
				+ ") "+ c_postal + "\nteléfono: " + telefono + ", Fecha de nacimiento: " + f_nac.format(formatter);
	}


	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String[] getApellidos() {
		return apellidos;
	}
	public void setApellidos(String[] apellidos) {
		this.apellidos = apellidos;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNum_casa() {
		return num_casa;
	}
	public void setNum_casa(int num_casa) {
		this.num_casa = num_casa;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getC_postal() {
		return c_postal;
	}
	public void setC_postal(String c_postal) {
		this.c_postal = c_postal;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public LocalDate getF_nac() {
		return f_nac;
	}
	public void setF_nac(LocalDate f_nac) {
		this.f_nac = f_nac;
	}
	
	

}
