package br.com.curbani.spring.bean;

import java.util.ArrayList;
import java.util.List;

public class ListMovieDTO {

	private List<MovieDTO> min = new ArrayList<MovieDTO>();
	private List<MovieDTO> max = new ArrayList<MovieDTO>();
	
	public ListMovieDTO() {		
	}
	
	public List<MovieDTO> getMin() {
		return min;
	}
	public void setMin(List<MovieDTO> min) {
		this.min = min;
	}
	public List<MovieDTO> getMax() {
		return max;
	}
	public void setMax(List<MovieDTO> max) {
		this.max = max;
	}
	
	
}
