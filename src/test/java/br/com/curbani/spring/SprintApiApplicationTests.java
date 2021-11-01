package br.com.curbani.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.curbani.spring.bean.ListMovieDTO;
import br.com.curbani.spring.bean.MovieDTO;
import br.com.curbani.spring.model.Movie;
import br.com.curbani.spring.model.Producer;
import br.com.curbani.spring.repository.MovieRepository;
import br.com.curbani.spring.repository.ProducerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class SprintApiApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MovieRepository filmeRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void testServiceOk() throws Exception {
		mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	void testNewMaxInterval() throws Exception {
		MvcResult result = mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
		ListMovieDTO responseListFilmeDTO = objectMapper.readValue(content, ListMovieDTO.class);
		String nameNewMaxProducer = "TestNewMaxInterval";
		
		Producer producer = producerRepository.save(new Producer(nameNewMaxProducer));
		
		int interval = 9999;
		if(responseListFilmeDTO != null && responseListFilmeDTO.getMax() != null && !responseListFilmeDTO.getMax().isEmpty()) {
			interval = responseListFilmeDTO.getMax().get(0).getInterval() + 1;
		}
		
		
		filmeRepository.save(new Movie(2000, "Filme Teste Max", "Studios Teste", Arrays.asList(producer), true));
		filmeRepository.save(new Movie(interval, "Filme Teste Max 2", "Studios Teste", Arrays.asList(producer), true));
		
		MvcResult result2 = mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
	            .andReturn();

		ListMovieDTO responseListFilmeDTO2 = objectMapper.readValue(result2.getResponse().getContentAsString(), ListMovieDTO.class);
		if(responseListFilmeDTO != null && responseListFilmeDTO.getMax() != null && !responseListFilmeDTO.getMax().isEmpty()) {
			assertNotEquals(responseListFilmeDTO.getMax().get(0).getProducer(), responseListFilmeDTO2.getMax().get(0).getProducer());
		}		
		assertEquals(responseListFilmeDTO2.getMax().get(0).getProducer(), nameNewMaxProducer);
		
	}
	
	@Test
	void testAddMinInterval() throws Exception {
		MvcResult result = mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
		ListMovieDTO responseListFilmeDTO = objectMapper.readValue(content, ListMovieDTO.class);
		String nameNewMinProducer = "TestNewMinInterval";		
		Producer producer = producerRepository.save(new Producer(nameNewMinProducer));	
		
		int interval = 1;
		if(responseListFilmeDTO != null && responseListFilmeDTO.getMin() != null && !responseListFilmeDTO.getMin().isEmpty()) {
			interval =  responseListFilmeDTO.getMin().get(0).getInterval();
		}
		
		filmeRepository.save(new Movie(2000, "Filme Teste Min", "Studios Teste", Arrays.asList(producer), true));
		filmeRepository.save(new Movie(2000 + interval, "Filme Teste Min 2", "Studios Teste", Arrays.asList(producer), true));
		MvcResult result2 = mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
	            .andReturn();

		ListMovieDTO responseListFilmeDTO2 = objectMapper.readValue(result2.getResponse().getContentAsString(), ListMovieDTO.class);
		assertTrue(responseListFilmeDTO2 != null && responseListFilmeDTO2.getMin() != null && !responseListFilmeDTO2.getMin().isEmpty());
		
		if(responseListFilmeDTO != null && responseListFilmeDTO.getMin() != null && !responseListFilmeDTO.getMin().isEmpty()) {
			assertTrue(responseListFilmeDTO.getMin().size() < responseListFilmeDTO2.getMin().size());
			boolean producerFound = false;
			for(MovieDTO filmeDTO : responseListFilmeDTO2.getMin()) {
				if(filmeDTO.getProducer().equalsIgnoreCase(nameNewMinProducer)) {
					producerFound = true; 
					break;
				}
			}
			assertTrue(producerFound);
		}		
				
	}
	

}
