package com.adrea.jokes.models.entity;

import java.util.HashSet;
import java.util.Set;

import com.adrea.jokes.models.entity.Jokes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

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
	@SequenceGenerator(name="LANGUAGE_SEQ_GEN" ,sequenceName="seq_language", allocationSize=1)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@NotEmpty(message = "El código no puede estar vacío")
	@Column(name = "code", length = 2)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@NotEmpty(message = "el idioma no puede estar vacío")
	@Column(name = "language",nullable = false)
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	// refleja las relaciones con la tabla jokes
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	@JsonIgnore
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
