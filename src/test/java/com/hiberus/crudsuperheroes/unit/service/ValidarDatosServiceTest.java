package com.hiberus.crudsuperheroes.unit.service;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.service.impl.ValidarDatosServiceImpl;

@SpringBootTest
class ValidarDatosServiceTest {

	@InjectMocks
	private ValidarDatosServiceImpl validarDatos;

	@BeforeEach
	public void init() {
		validarDatos = new ValidarDatosServiceImpl();
	}
	
	@Test()
	void validarDatosUpdateSuperHeroeRequestNullTest() throws ValidationException {
		
        assertThrows(ValidationException.class, () -> validarDatos.validarDatosUpdateSuperHeroe(null));

	}
	
	@Test()
	void validarDatosUpdateSuperHeroeNombreNullTest() throws ValidationException {
 
		SuperHeroeRequest superHeroeRequest = SuperHeroeRequest.builder().nombre(null).superPoder("Hablar muy rápido").build();
        assertThrows(ValidationException.class, () -> validarDatos.validarDatosUpdateSuperHeroe(superHeroeRequest));
  
	}
	
	@Test()
	void validarIdSuperHeroeTest() throws ValidationException {
 
        assertThrows(ValidationException.class, () -> validarDatos.validarIdSuperHeroe(null));
        
	}
	
	@Test()
	void validarParametroBusquedaSuperHeroeTest() throws ValidationException {

        assertThrows(ValidationException.class, () -> validarDatos.validarParametroBusquedaSuperHeroe(null));

	}
	
	

}
