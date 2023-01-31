package com.adrea.jokes.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jokes")
public class Jokes implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Categories categories;
	private Language language;
	private Types types;
	private String text1;
	private String text2;
	private Set<Flags> flagses = new HashSet<Flags>(0);

	public Jokes() {
	}

	public Jokes(int id) {
		this.id = id;
	}

	public Jokes(int id, Categories categories, Language language, Types types, String text1, String text2,
			Set<Flags> flagses) {
		this.id = id;
		this.categories = categories;
		this.language = language;
		this.types = types;
		this.text1 = text1;
		this.text2 = text2;
		this.flagses = flagses;
	}

	@Id
	//especifica la columna en la BD
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//refleja la relación con la tabla Categories.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	//refleja la relación con la tabla Language.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	//refleja la relación con la tabla Types
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Types getTypes() {
		return this.types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	@Column(name = "text1")
	public String getText1() {
		return this.text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	@Column(name = "text2")
	public String getText2() {
		return this.text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}
	// refleja la relación con la tabla intermedia
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "jokes_flags", joinColumns = {
			@JoinColumn(name = "joke_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "flag_id", nullable = false, updatable = false) })
	public Set<Flags> getFlagses() {
		return this.flagses;
	}

	public void setFlagses(Set<Flags> flagses) {
		this.flagses = flagses;
	}

	@Override
	public String toString() {
		String texto = this.text1;
		String cat="", idioma ="", tipo="", flags=" no tiene";
		if(this.text2 !=null && !this.text2.equals("null")) {
			texto +="\n"+this.text2;
		}
		if(this.categories != null) {
			cat=this.categories.getCategory();
		}
		if(this.language !=null) {
			idioma=this.language.getCode();
		}
		if(this.types !=null) {
			tipo=this.types.getType();
		}
		if(this.flagses.size()>0) {
			flags = this.flagses.stream().map(c -> c.getFlag()).collect(Collectors.joining(", "));
		}
		return "Joke: " + id + ", categoría: " + cat + ", lenguaje: " + idioma + 
				", tipo: " + tipo+ "\n" + texto +"\nFlags:"+flags+"\n";
	}
}

