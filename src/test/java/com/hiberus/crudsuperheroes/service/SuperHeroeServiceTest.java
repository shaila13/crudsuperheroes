package com.hiberus.crudsuperheroes.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;

import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeServiceImpl;

@SpringBootTest
class SuperHeroeServiceTest {

	@MockBean
	SuperHeroeServiceImpl superHeroeService;
	 
	@Mock
	private SuperHeroe superHeroeEntity;
	
    @Mock
    private SuperHeroeRepository superHeroeRepository;
	
    @Test
    public void shouldReturnAllUsers() {
    	
        List<SuperHeroe> superHeroe = new ArrayList<>();
        
        superHeroe.add(new SuperHeroe());

        given(superHeroeRepository.findAll()).willReturn(superHeroe);

        SuperHeroeResponse expected = superHeroeService.getAllSuperHeroes();

        assertEquals(expected, superHeroe);
        verify(superHeroeRepository).findAll();
    }
	
}
