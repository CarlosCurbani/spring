package br.com.curbani.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.curbani.spring.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long>{
		
	@Query("SELECT p.id FROM Producer p WHERE p.name = :name")
	Long findProducerByName(@Param("name") String producerName);
	
}
