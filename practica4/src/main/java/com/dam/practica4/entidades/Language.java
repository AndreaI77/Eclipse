package com.dam.practica4.entidades;

// default package
// Generated 20 dic 2022 19:49:13 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Language generated by hbm2java
 */
//
//Agrupación de las NamedQueries definidas para esta clase
@NamedQueries({
	@NamedQuery(name= "Query_get_text", query= "from Language where lower(language) like :text"),
	@NamedQuery(name= "Query_get_lenguajes", query= "from Language")
})

@Entity
@Table(name = "language")
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String language;
	private Set<Jokes> jokeses = new HashSet<Jokes>(0);

	public Language() {
	}

	public Language(int id) {
		this.id = id;
	}
	
	public Language(int id, String code, String language) {
		this.id = id;
		this.code = code;
		this.language = language;
	}

	public Language(int id, String code, String language, Set<Jokes> jokeses) {
		this.id = id;
		this.code = code;
		this.language = language;
		this.jokeses = jokeses;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//especificación de la columna en la tabla
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", length = 2)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "language")
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	// refleja las relaciones con la tabla jokes
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	public Set<Jokes> getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes> jokeses) {
		this.jokeses = jokeses;
	}

	@Override
	public String toString() {
		return id + " - "+ language+", código: " + code ;
	}
	
}
