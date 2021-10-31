package br.com.curbani.spring.bean;

import java.io.Serializable;
import java.util.Objects;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import br.com.curbani.spring.util.csv.SampleConverter;

public class MovieCSV implements Serializable {

	private static final long serialVersionUID = 1L;

	@CsvBindByName(column = "year")
	private int year;

	@CsvBindByName(column = "title")
	private String title;

	@CsvBindByName(column = "studios")
	private String studios;

	@CsvBindByName(column = "producers")
	private String producers;

	@CsvCustomBindByName(column = "winner", required = false, converter = SampleConverter.class)
	private boolean winner;

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

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(producers, studios, title, winner, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieCSV other = (MovieCSV) obj;
		return Objects.equals(producers, other.producers) && Objects.equals(studios, other.studios)
				&& Objects.equals(title, other.title) && winner == other.winner && year == other.year;
	}

	@Override
	public String toString() {
		return "CSVFilme [year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers
				+ ", winner=" + winner + "]";
	}

}
