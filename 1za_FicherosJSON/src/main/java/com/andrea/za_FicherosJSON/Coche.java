package com.andrea.za_FicherosJSON;

public class Coche {
	String marca;
	String modelo;
	int cilindrada;
	
	public Coche(String marca, String modelo, int cilindrada) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.cilindrada = cilindrada;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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
		return "marca=" + marca + ", modelo=" + modelo + ", cilindrada=" + cilindrada ;
	}
	
	
	

}
