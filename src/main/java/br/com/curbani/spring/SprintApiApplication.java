package br.com.curbani.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.curbani.spring.model.Movie;
import br.com.curbani.spring.model.Producer;
import br.com.curbani.spring.repository.MovieRepository;
import br.com.curbani.spring.repository.ProducerRepository;
import br.com.curbani.spring.util.csv.ManipulateCSV;

@SpringBootApplication
@EnableJpaRepositories
public class SprintApiApplication implements CommandLineRunner{

	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Value( "${csv.name.file}" )
	private String nameCSV;
	
	private static Logger logger = LoggerFactory.getLogger(SprintApiApplication.class);
	
	
	public static void main(String[] args) {		
		SpringApplication.run(SprintApiApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Iniciando leitura de arquivo CSV.");
		ManipulateCSV csvService = new ManipulateCSV();		
		List<Movie> listFilmes = csvService.readCSVtoList(nameCSV);
		logger.info("Finalizou a leitura do arquivo CSV.");
		logger.info("Iniciando persistencia das informacoes no banco de dados.");
		for (Movie filme : listFilmes) {
			for(Producer producer : filme.getProducers()) {
				Long producerID =  producerRepository.findProducerByName(producer.getName());
				if(producerID != null) {
					producer.setId(producerID);
				}else {
					producerRepository.save(producer);					
				}				
			}
			movieRepository.save(filme);			
		}
		logger.info("Finalizou a persistencia no banco de dados.");	
		
	}
	
	

}
