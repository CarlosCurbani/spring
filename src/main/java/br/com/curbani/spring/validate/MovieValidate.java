package br.com.curbani.spring.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.curbani.spring.model.Movie;

public class MovieValidate {
	
	private static Logger logger = LoggerFactory.getLogger(MovieValidate.class);
	

	public void validMovie(List<Movie> listMovie) {
		TreeMap<Integer, List<Movie>> mapFilme = new TreeMap<Integer, List<Movie>>();
		for(Movie movie : listMovie) {			
			if(mapFilme.get(movie.getYear()) == null) {
				List<Movie> list = new ArrayList<Movie>();
				mapFilme.put(movie.getYear(), list);
			}			
			if(movie.isWinner()) {				
				mapFilme.get(movie.getYear()).add(movie);				
			}			
		}
		
		for (Entry<Integer, List<Movie>> entry : mapFilme.entrySet()) {
			Integer key = entry.getKey();
			List<Movie> val = entry.getValue();
			if(val == null || val.isEmpty()) {
				logger.warn("Ano sem nenhum vencedor cadastrado: {} !", key);
			}
			if(val != null && val.size() > 1) {
				logger.warn("Existem mais de um filme vencedor para o ano: {} !", key);
				for(Movie movie : val) {
					logger.warn("ano: {}, vencedor: {}",key , movie.getTitle());
				}
			}
		}
		
	}
}
