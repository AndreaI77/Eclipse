package com.adrea.jokes.models.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.adrea.jokes.models.entity.Categories;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String category;
	private Set<Jokes> jokeses = new HashSet<Jokes>(0);

	public Categories() {
	}
	
	public Categories(int id) {
		this.id = id;
	}

	public Categories(int id, String category) {
		this.id = id;
		this.category = category;
	}

	public Categories(int id, String category, Set<Jokes> jokeses) {
		this.id = id;
		this.category = category;
		this.jokeses = jokeses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "category", nullable = false)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	//refleja las relaciones entre las tablas.
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	public Set<Jokes> getJokeses() {
		return this.jokeses;
	}

	public void setJokeses(Set<Jokes> jokeses) {
		this.jokeses = jokeses;
	}

	@Override
	public String toString() {
		
		return id+" - "+category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		return id == other.id;
	}
}