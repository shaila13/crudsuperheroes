package com.hiberus.crudsuperheroes.service;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeUpdateServiceImpl;

@SpringBootTest
class SuperHeroeUpdateServiceTest {

	@MockBean
	SuperHeroeUpdateServiceImpl superHeroeUpdateService;
	 
	@Mock
	private SuperHeroeEntity superHeroeEntity;
	
    @Mock
    private SuperHeroeRepository superHeroeRepository;
	
	@Test
	public void updateUserWhenPutUser() throws Exception {
 
		SuperHeroeEntity superHeroe = SuperHeroeEntity.builder()
				.idSuperHeroe(1L).nombre("Calico Electronico").superPoder("No cuenta con ningun superpoder")
				.build();
		
		SuperHeroeRequest newSuperHeroe = SuperHeroeRequest.builder().superPoder("Salva al mundo con su barriga").build();
				
        given(superHeroeRepository.findById(superHeroe.getIdSuperHeroe())).willReturn(Optional.of(superHeroe));
        superHeroeUpdateService.updateSuperHeroe(superHeroe.getIdSuperHeroe(), newSuperHeroe);

        verify(superHeroeRepository).save(superHeroe);
        verify(superHeroeRepository).findById(superHeroe.getIdSuperHeroe());
 
	}

	@Test
	public void shouldThrowExceptionWhenUserDoesntExist() throws Exception {
 
		SuperHeroeEntity superHeroe = SuperHeroeEntity.builder()
				.idSuperHeroe(99L).nombre("Test Name").superPoder("Test SuperPower")
				.build();
		
		SuperHeroeRequest newSuperHeroe = SuperHeroeRequest.builder().superPoder("Salva al mundo con su barriga").build();

		Mockito.doThrow(new SuperHeroeNotFoundException(superHeroe.getIdSuperHeroe())).when(superHeroeUpdateService).updateSuperHeroe(superHeroe.getIdSuperHeroe(), newSuperHeroe);
 

	}
	
}
