package com.profesor.tema3_exercise10esqueleto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Funcionario extends Persona{
	

	private String departamento;
	private Grupo grupo;
	private String cuerpo;
	private LocalDate f_ingreso;
	
	public Funcionario(int numero, String nombre, String[] apellidos, String calle, int num_casa, String ciudad,
			String provincia, String c_postal, long telefono, LocalDate f_nac, String departamento, Grupo grupo,
			String cuerpo, LocalDate f_ingreso) {
		super(numero, nombre, apellidos, calle, num_casa, ciudad, provincia, c_postal, telefono, f_nac);
		this.departamento = departamento;
		this.grupo = grupo;
		this.cuerpo = cuerpo;
		this.f_ingreso = f_ingreso;
	}
	
	
	
	public Grupo getGrupo() {
		return grupo;
	}



	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}



	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return super.toString()+"\nTipo de persona: Funcionario \nDepartamento: " + departamento + "\nGrupo: " + grupo + ", Cuerpo: " + cuerpo + "\nFecha de ingreso: "
				+ f_ingreso.format(formatter);
	}



	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public LocalDate getF_ingreso() {
		return f_ingreso;
	}
	public void setF_ingreso(LocalDate f_ingreso) {
		this.f_ingreso = f_ingreso;
	}
	
	

}
