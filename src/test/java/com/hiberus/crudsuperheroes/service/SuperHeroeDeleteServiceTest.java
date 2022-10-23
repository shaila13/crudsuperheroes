package com.hiberus.crudsuperheroes.service;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;

import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeDeleteServiceImpl;

@SpringBootTest
class SuperHeroeDeleteServiceTest {

	@MockBean
	SuperHeroeDeleteServiceImpl superHeroeDeleteService;
	 
	@Mock
	private SuperHeroe superHeroeEntity;
	
    @Mock
    private SuperHeroeRepository superHeroeRepository;
	
	@Test
	void whenGivenIdShouldDeleteSuperHeroeIfFound() {
		
		SuperHeroe superHeroe = SuperHeroe.builder()
											.idSuperHeroe(1L).nombre("Calico Electronico")
											.build();
		
		Mockito.doNothing().when(superHeroeDeleteService).deleteSuperHeroeById(superHeroe.getIdSuperHeroe());
	}

	@Test
	void shouldThrowExceptionWhenSuperHeroeDoesntExist() {
		
		SuperHeroe superHeroe = SuperHeroe.builder()
				.idSuperHeroe(2L).nombre("Superman")
				.build();
		
		Mockito.doThrow(new SuperHeroeNotFoundException(superHeroe.getIdSuperHeroe())).when(superHeroeDeleteService).deleteSuperHeroeById(superHeroe.getIdSuperHeroe());
		
		
	}
	
}
