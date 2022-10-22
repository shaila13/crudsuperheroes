package com.hiberus.crudsuperheroes.service;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeDeleteServiceImpl;

@SpringBootTest
class SuperHeroeServiceTest {

	@MockBean
	SuperHeroeDeleteServiceImpl superHeroeDeleteService;
	 
	@Mock
	private SuperHeroeEntity superHeroeEntity;
	
	@Test
	void whenGivenId_shouldDeleteSuperHeroe_ifFound() {
		
		SuperHeroeEntity superHeroe = SuperHeroeEntity.builder()
											.idSuperHeroe(1L).nombre("Calico Electronico")
											.build();
		
		Mockito.doNothing().when(superHeroeDeleteService).deleteSuperHeroeById(superHeroe.getIdSuperHeroe());
	}

	@Test
	void should_throw_exception_when_superHeroe_doesnt_exist() {
		
		SuperHeroeEntity superHeroe = SuperHeroeEntity.builder()
				.idSuperHeroe(2L).nombre("Superman")
				.build();
		
		Mockito.doThrow(new SuperHeroeNotFoundException(superHeroe.getIdSuperHeroe())).when(superHeroeDeleteService).deleteSuperHeroeById(superHeroe.getIdSuperHeroe());
		
		
	}
	
}
