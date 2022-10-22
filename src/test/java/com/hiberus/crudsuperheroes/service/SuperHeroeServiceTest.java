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

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeServiceImpl;

@SpringBootTest
class SuperHeroeServiceTest {

	@MockBean
	SuperHeroeServiceImpl superHeroeService;
	 
	@Mock
	private SuperHeroeEntity superHeroeEntity;
	
    @Mock
    private SuperHeroeRepository superHeroeRepository;
	
    @Test
    public void shouldReturnAllUsers() {
    	
        List<SuperHeroeEntity> users = new ArrayList();
        
        users.add(new SuperHeroeEntity());

        given(superHeroeRepository.findAll()).willReturn(users);

        List<SuperHeroeResponse> expected = superHeroeService.getAllSuperHeroes();

        assertEquals(expected, users);
        verify(superHeroeRepository).findAll();
    }
	
}
