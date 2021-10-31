package br.com.curbani.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curbani.spring.bean.ListMovieDTO;
import br.com.curbani.spring.bean.MovieDTO;
import br.com.curbani.spring.model.Movie;
import br.com.curbani.spring.model.Producer;
import br.com.curbani.spring.repository.ProducerRepository;

@Service
public class MovieService {	
		
	@Autowired
	private ProducerRepository producerRepository;	
	
	private static Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	public ListMovieDTO findFilme() {
		logger.info("Recenbendo uma chamada para busca dos filmes.");
		ListMovieDTO listFilmeDTO = new ListMovieDTO();
		TreeMap<Integer, List<MovieDTO>> mapFilmeDTO = new TreeMap<Integer, List<MovieDTO>>();		
		
		logger.info("Iniciando busca dos filmes no banco de dados");
		List<Producer> listProducer = producerRepository.findAll();
		logger.info("Finalizou a busca dos filmes no banco de dados");
		logger.info("Iniciando a verificacao do intervalo dos filmes vencedores");
		for(Producer producer : listProducer) {
			for (int i = 1; i < producer.getMovies().size(); i++) {				
				Movie moviePrevious = producer.getMovies().get(i - 1);
				Movie movieFollowing = producer.getMovies().get(i);
				Integer interval = movieFollowing.getYear() - moviePrevious.getYear();
				MovieDTO filmeDTO = new MovieDTO(producer.getName(), interval, moviePrevious.getYear(), movieFollowing.getYear());								
				
				if(mapFilmeDTO.get(interval) != null) {
					mapFilmeDTO.get(interval).add(filmeDTO);
				}else {
					List<MovieDTO> lFilmeDTO = new ArrayList<>();
					lFilmeDTO.add(filmeDTO);
					mapFilmeDTO.put(interval, lFilmeDTO);
				}
			}					
		}
		
		listFilmeDTO.getMin().addAll(mapFilmeDTO.get(mapFilmeDTO.firstKey()));		
		listFilmeDTO.getMax().addAll(mapFilmeDTO.get(mapFilmeDTO.lastKey()));
		logger.info("Finalizou a consulta dos intervalos, retornando objeto para o request");
		return listFilmeDTO;
	}

}
