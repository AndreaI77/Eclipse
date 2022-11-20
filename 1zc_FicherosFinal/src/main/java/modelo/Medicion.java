package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Medicion implements Serializable {
	
	LocalDate fecha;
	String ciudad;
	float latitud;
	float longitud;
	Map weather;
	float temperatura;
	int humidity;
	
	
	
	public Medicion(LocalDate fecha, String ciudad, float latitud, float longitud, Map weather, float temperatura,
			int humidity) {
		super();
		this.fecha = fecha;
		this.ciudad = ciudad;
		this.latitud = latitud;
		this.longitud = longitud;
		this.weather = weather;
		this.temperatura = temperatura;
		this.humidity = humidity;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public Map getWeather() {
		return weather;
	}
	public void setWeather(Map weather) {
		this.weather = weather;
	}
	public float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "Medicion [fecha=" + fecha + ", ciudad=" + ciudad + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", weather=" + weather + ", temperatura=" + temperatura + ", humidity=" + humidity + "]";
	}
	

}
