package br.com.curbani.spring.util.csv;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.curbani.spring.SprintApiApplication;
import br.com.curbani.spring.bean.MovieCSV;
import br.com.curbani.spring.model.Movie;
import br.com.curbani.spring.model.Producer;

public class ManipulateCSV {	
	
	private static Logger logger = LoggerFactory.getLogger(ManipulateCSV.class);
	
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
		} catch (Exception exc) {
			logger.error("Erro ao tentar efetuar o carregamento do CSV: {}", exc.getMessage());
			logger.error("Possivel causa:", exc.getCause());			
		} 
		return listFilmes;

	}
	
	private List<Producer> splitProducer(String producers){
		if(producers == null || producers.isEmpty()) {
			return null;
		}
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
