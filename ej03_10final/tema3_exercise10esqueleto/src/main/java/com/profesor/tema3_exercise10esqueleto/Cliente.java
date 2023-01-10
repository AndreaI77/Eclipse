package com.profesor.tema3_exercise10esqueleto;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Persona {

	private String iban;
	private Estado estado;
	private Tipo tipoCliente;
	private boolean isValid;
	
	
	public Cliente(int numero, String nombre, String[] apellidos, String calle, int num_casa, String ciudad,
			String provincia, String c_postal, long telefono, LocalDate f_nac, String iban, boolean isValid, Estado estado,
			Tipo tipoCliente) {
		super(numero, nombre, apellidos, calle, num_casa, ciudad, provincia, c_postal, telefono, f_nac);
		this.iban = iban;
		this.isValid = isValid;
		this.estado = estado;
		this.tipoCliente = tipoCliente;
	}
	
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Tipo getTipoCliente() {
		return tipoCliente;
	}


	public void setTipoCliente(Tipo tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}


	
	
	
	@Override
	public String toString() {
		return "Cliente: "+super.toString()+ "\nIBAN: " + iban + "\nEstado: " + estado + ", Tipo de Cliente: " + tipoCliente;
	}


	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
}
