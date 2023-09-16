package com.hiberus.crudsuperheroes.integration;
 

 
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.sun.istack.NotNull;

@DataJpaTest
public class IntegracionJpaTest {

	
    @Autowired
    private SuperHeroeRepository superHeroeRepository; 
    
 
    
	@Test()
	void findAllTest()  {
        List<SuperHeroe> superHeroeList = superHeroeRepository.findAll();
        assertFalse(superHeroeList.isEmpty());
        assertEquals(22, superHeroeList.size());
 
    }
    	
	@Test()
	void findByIdTest() {
        Optional<SuperHeroe> superHeroeList = superHeroeRepository.findById(1L);
        assertTrue(superHeroeList.isPresent());
        assertEquals("Tormenta", superHeroeList.orElseThrow().getNombre());
    }
	
	@Test()
	void updateTest(){

        // Given
        SuperHeroe superHeroeShiala = new SuperHeroe(69L, "Shaila", "Reñir mucho.",null);
        // When
        SuperHeroe superHeroe = superHeroeRepository.save(superHeroeShiala);

        // Then
        assertEquals("Shaila", superHeroe.getNombre() );
		
	}
	
	@Test()
	void deleteSuperHeroeTest()  {
		
		SuperHeroe superHeroe = superHeroeRepository.findById(1L).orElseThrow();
        assertEquals("Tormenta", superHeroe.getNombre());

        superHeroeRepository.delete(superHeroe);

        assertThrows(NoSuchElementException.class, () -> {

        	superHeroeRepository.findById(1L).orElseThrow();
        });
        assertEquals(21, superHeroeRepository.findAll().size());
		
	}
	
}
