package modelo;

import java.time.LocalDate;
import java.util.List;

public class Curso {
	int id;
	String nombre;
	LocalDate fechaIni;
	List<Participante> lista;
	
	
	public Curso(int id, String nombre, LocalDate fechaIni, List<Participante> lista) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaIni = fechaIni;
		this.lista = lista;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}
	public List<Participante> getLista() {
		return lista;
	}
	public void setLista(List<Participante> lista) {
		this.lista = lista;
	}
	
	

}
