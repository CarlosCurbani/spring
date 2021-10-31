package br.com.curbani.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.WhereJoinTable;


@Entity
public class Producer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
		
	@ManyToMany(mappedBy = "producers")
	@OrderBy("year ASC")
	@WhereJoinTable(clause = "movie1_.winner=true")
	private List<Movie> movies;
	
	public Producer() {		
	}	
		
	public Producer(String name, List<Movie> movies) {
		this.name = name.trim();
		this.movies = movies;
	}
	
	public Producer(String name) {
		this.name = name.trim();		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Producer [id=" + id + ", nome=" + name + ", movies=" + movies + "]";
	}
	
}
