package br.com.curbani.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int year;
	
	private String title;
	
	private String studios;
	
	@ManyToMany
	@JoinTable(name = "movie_producer", 
		joinColumns = @JoinColumn(name = "producer_id"), 
		inverseJoinColumns = @JoinColumn(name = "movie_id"))
	@OrderBy("name ASC")	
	private List<Producer> producers;
	
	private boolean winner;

	public Movie() {
	}
	
	public Movie( int year, String title, String studios, List<Producer> producers, boolean winner) {		
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", year=" + year + ", title=" + title + ", studios=" + studios + ", winner=" + winner + "]";
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

}
