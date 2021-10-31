package br.com.curbani.spring.util.csv;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.curbani.spring.bean.MovieCSV;
import br.com.curbani.spring.model.Movie;
import br.com.curbani.spring.model.Producer;

public class ManipulateCSV {	
	
	
	public List<Movie> readCSVtoList(String nameCSV) {
		Reader reader;
		List<Movie> listFilmes = new ArrayList<Movie>();
		try {
			reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(nameCSV).toURI()));
						
			CsvToBean<MovieCSV> csvToBean = new CsvToBeanBuilder<MovieCSV>(reader)
					.withType(MovieCSV.class)
					.withIgnoreEmptyLine(true)					
					.withSeparator(';')					
					.build();			
	
			List<MovieCSV> listFilmesCSV = csvToBean.parse();
			
			for (MovieCSV filmeCSV : listFilmesCSV) {				
				listFilmes.add(new Movie(filmeCSV.getYear(),
						filmeCSV.getTitle(), 
						filmeCSV.getStudios(),
						splitProducer(filmeCSV.getProducers()),					
						filmeCSV.isWinner()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return listFilmes;

	}
	
	private List<Producer> splitProducer(String producers){	
		List<Producer> listProducers  = new ArrayList<>();
		String[] arrayProducer = producers.replace(" and ", ",").split(",");
		for (int i = 0; i < arrayProducer.length; i++) {
			String nameProducer = arrayProducer[i].trim();
			if(!nameProducer.isEmpty()) {				
				listProducers.add(new Producer(arrayProducer[i], null));				
			}
		}
		return listProducers;
	}
}
