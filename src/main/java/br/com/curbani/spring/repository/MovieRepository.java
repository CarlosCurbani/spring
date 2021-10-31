package br.com.curbani.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curbani.spring.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
