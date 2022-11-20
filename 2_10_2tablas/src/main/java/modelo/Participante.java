package modelo;

public class Participante {
	
	int id;
	String nombre;
	String curso;
	
	public Participante(int id, String nombre, String curso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
	}
	
	@Override
	public String toString() {
		return "Participante [id=" + id + ", nombre=" + nombre + ", curso=" + curso + "]";
	}

	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
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
}
