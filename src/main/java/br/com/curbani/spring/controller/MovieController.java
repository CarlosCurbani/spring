package br.com.curbani.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curbani.spring.bean.ListMovieDTO;
import br.com.curbani.spring.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public ListMovieDTO listar() {
		return this.movieService.findFilme();
	}

}
