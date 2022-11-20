package com.andrea.z_ficherosXMLconDOM3;

public class Coche {
	private String modelo;
	private int cilindrada;
	
	public Coche( int cilindrada, String modelo) {
		super();
		this.modelo = modelo;
		this.cilindrada = cilindrada;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	@Override
	public String toString() {
		return "Modelo: "+modelo+", cilindrada: "+cilindrada;
	}
	

}
