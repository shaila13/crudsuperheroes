package com.hiberus.crudsuperheroes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

	@Test(/*expected = SuperHeroeNotFoundException.class*/)
	public void whenFindByIdThrowsExceptionShouldThrowSuperHeroeNotFoundException() throws ValidationException {
		Mockito.doNothing().when(validarDatos).validarIdSuperHeroe(any(Long.class));
		Long id = 1L;
		Mockito.doThrow(new SuperHeroeNotFoundException(id)).when(superHeroeRepository).findById(any(Long.class));
		superHeroeDeleteService.deleteSuperHeroeById(ID);
	}

	@Test
	void whenGivenIdShouldDeleteSuperHeroeIfFound() throws ValidationException {
		SuperHeroe result = new SuperHeroe();
		Mockito.doNothing().when(validarDatos).validarIdSuperHeroe(any(Long.class));
		Mockito.doReturn(result).when(superHeroeRepository).findById(any(Long.class));
		Mockito.doNothing().when(superHeroeRepository).deleteById(any(Long.class));
		superHeroeDeleteService.deleteSuperHeroeById(ID);
		verify(validarDatos, times(1)).validarIdSuperHeroe(any(Long.class));
		verify(superHeroeRepository, times(1)).findById(any(Long.class));

	}

	@Test
	void shouldThrowExceptionWhenSuperHeroeDoesntExist() throws ValidationException {
		Mockito.doThrow(new ValidationException()).when(validarDatos).validarIdSuperHeroe(any(Long.class));
		superHeroeDeleteService.deleteSuperHeroeById(ID);
		verify(validarDatos, times(1)).validarIdSuperHeroe(any(Long.class));
		verify(superHeroeRepository, times(0)).findById(any(Long.class));

	}

}
