package com.hiberus.crudsuperheroes.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.ValidarDatosService;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeUpdateServiceImpl;

@SpringBootTest
class SuperHeroeUpdateServiceTest {

	@InjectMocks
	private SuperHeroeUpdateServiceImpl superHeroeUpdateService;
	@Mock
	private SuperHeroeRepository superHeroeRepository;
	@Mock
	private ValidarDatosService validarDatos;


	private static final Long ID = 1L;

	@BeforeEach
	public void init() {
        // Crear mocks
		MockitoAnnotations.openMocks(this);
	}
	
	@Test()
	void updateSuperHeroeTest() throws ValidationException {
 
		SuperHeroe superHeroe = SuperHeroe.builder().nombre("Shaila").superPoder("Hablar muy rápido").idSuperHeroe(ID).build();
		SuperHeroeRequest superHeroeRequest = SuperHeroeRequest.builder().nombre("Shaila").superPoder("Hablar muy rápido").build();
		
        // Configurar comportamiento de los mocks
		doNothing().when(validarDatos).validarIdSuperHeroe(any(Long.class));
		doNothing().when(validarDatos).validarDatosUpdateSuperHeroe(any(SuperHeroeRequest.class));
		doThrow(new SuperHeroeNotFoundException(ID)).when(superHeroeRepository).findById(any(Long.class));
		doReturn(Optional.ofNullable(superHeroe)).when(superHeroeRepository).findById(any(Long.class));
		
        when(superHeroeRepository.saveAndFlush(any())).thenReturn(superHeroe);

        // Crear instancia del servicio con los mocks
		superHeroeUpdateService = new SuperHeroeUpdateServiceImpl(superHeroeRepository, validarDatos);
        
        // Llamar al método que queremos probar
        boolean superHeroesCompleted = superHeroeUpdateService.updateSuperHeroe(ID, superHeroeRequest);
        
        // Verificar el resultado
        assertTrue(superHeroesCompleted);
        		
        // Verificar que los métodos de los mocks fueron llamados correctamente
        verify(validarDatos, times(1)).validarIdSuperHeroe(ID);
        verify(validarDatos, times(1)).validarDatosUpdateSuperHeroe(superHeroeRequest);
        verify(superHeroeRepository, times(1)).findById(ID);
        verify(superHeroeRepository, times(1)).saveAndFlush(superHeroe);

	}

 

}
