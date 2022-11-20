package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tiempo {
	private LocalDate fecha;
	private String hora;
	private Float temperatura;
	public Tiempo(LocalDate fecha, String hora, Float temperatura) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.temperatura = temperatura;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}
	@Override
	public String toString() {
		return fecha + " " + hora + "->" + temperatura ;
	}
	
	

}
