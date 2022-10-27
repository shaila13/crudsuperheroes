package com.hiberus.crudsuperheroes.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeDeleteServiceImpl;

@SpringBootTest
class SuperHeroeDeleteServiceTest {

	@InjectMocks
	private SuperHeroeDeleteServiceImpl superHeroeDeleteService;
	@Mock
	private SuperHeroeRepository superHeroeRepository;
	@Mock
	private IValidarDatosService validarDatos;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	private static final Long ID = 123L;

	@Test()
	void whenFindByIdThrowsExceptionShouldThrowSuperHeroeNotFoundException() throws ValidationException {
		Mockito.doNothing().when(validarDatos).validarIdSuperHeroe(any(Long.class));
		Mockito.doThrow(new SuperHeroeNotFoundException(ID)).when(superHeroeRepository).findById(any(Long.class));
		try {
			superHeroeDeleteService.deleteSuperHeroeById(ID);
		} catch (SuperHeroeNotFoundException e) {
			assertTrue(e instanceof SuperHeroeNotFoundException);
		}

	}

	@Test
	void whenGivenIdShouldDeleteSuperHeroeIfFound() {
		SuperHeroe result = SuperHeroe.builder().nombre("Test").superPoder("Test").idSuperHeroe(ID).build();
		try {
			Mockito.doNothing().when(validarDatos).validarIdSuperHeroe(any(Long.class));
			Mockito.doReturn(Optional.ofNullable(result)).when(superHeroeRepository).findById(any(Long.class));
			Mockito.doNothing().when(superHeroeRepository).deleteById(any(Long.class));
			superHeroeDeleteService.deleteSuperHeroeById(ID);
			verify(validarDatos, times(1)).validarIdSuperHeroe(any(Long.class));
			verify(superHeroeRepository, times(1)).findById(any(Long.class));
		} catch (ValidationException e) {
			assertTrue(e instanceof ValidationException);
		}catch (SuperHeroeNotFoundException es) {
			assertTrue(es instanceof SuperHeroeNotFoundException);
		}

	}

	@Test
	void shouldThrowExceptionWhenSuperHeroeDoesntExist(){
		try {
			Mockito.doThrow(new ValidationException()).when(validarDatos).validarIdSuperHeroe(any(Long.class));
			superHeroeDeleteService.deleteSuperHeroeById(ID);
			verify(validarDatos, times(1)).validarIdSuperHeroe(any(Long.class));
			verify(superHeroeRepository, times(0)).findById(any(Long.class));
		} catch (ValidationException e) {
			assertTrue(e instanceof ValidationException);
		}catch (SuperHeroeNotFoundException es) {
			assertTrue(es instanceof SuperHeroeNotFoundException);
		}
	}

}
