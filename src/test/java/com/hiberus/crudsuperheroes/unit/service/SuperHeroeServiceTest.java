package com.hiberus.crudsuperheroes.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.mapper.UtilsMapper;
import com.hiberus.crudsuperheroes.model.Datos;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.ValidarDatosService;
import com.hiberus.crudsuperheroes.service.impl.SuperHeroeServiceImpl;

@SpringBootTest
class SuperHeroeServiceTest {

	@InjectMocks
	private SuperHeroeServiceImpl superHeroeService;
	@Mock
	private SuperHeroeRepository superHeroeRepository;
	@Mock
	private ValidarDatosService validarDatos;
	@Mock
	private UtilsMapper utilsMapper;

	private static final Long ID = 1L;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test()
	void getAllSuperHeroesTest() throws ValidationException {
        
        // Configurar comportamiento de los mocks
        when(superHeroeRepository.findAll()).thenReturn(Datos.SUPERHEROES); 
        when(utilsMapper.superHeroeEntitiesToDtos(anyList())).thenReturn(Datos.SUPERHEROESDTO);
        
        // Crear instancia del servicio con los mocks
        superHeroeService = new SuperHeroeServiceImpl(superHeroeRepository, utilsMapper, validarDatos);

        // Llamar al método que queremos probar
        Optional<SuperHeroeResponse> superHeroes = superHeroeService.getAllSuperHeroes();
        
        // Verificar el resultado
        assertTrue(superHeroes.isPresent());
        assertEquals(3, superHeroes.get().getSuperHeroList().size());
		assertFalse(superHeroes.isEmpty());

        // Verificar que los métodos de los mocks fueron llamados correctamente
		verify(superHeroeRepository).findAll();
        verify(superHeroeRepository, times(1)).findAll();
        verify(utilsMapper, times(1)).superHeroeEntitiesToDtos(anyList());

	}
	
	@Test()
	void findAllSuperHeroesByIdTest() throws ValidationException {
	        
        // Configurar comportamiento de los mocks
        when(superHeroeRepository.findById(ID)).thenReturn(Datos.SUPERHEROE); 
        when(utilsMapper.superHeroeEntityToDto(any())).thenReturn(Datos.SUPERHEROEDTO);
        
        try {
        	validarDatos.validarIdSuperHeroe(ID);
        } catch (ValidationException e) {
        	assertTrue(e instanceof ValidationException);
        }
        
        // Crear instancia del servicio con los mocks
        superHeroeService = new SuperHeroeServiceImpl(superHeroeRepository, utilsMapper, validarDatos);
        
        // Llamar al método que queremos probar
        SuperHeroeResponse superHeroes = superHeroeService.getSuperHeroesById(ID);
        
        // Verificar el resultado
        assertEquals(1, superHeroes.getSuperHeroList().get(0).getIdSuperHeroe());
        assertEquals("Tormenta", superHeroes.getSuperHeroList().get(0).getNombre());
        
        // Verificar que no se lanzó ninguna excepción
        assertDoesNotThrow(() -> validarDatos.validarIdSuperHeroe(ID));
        
        // Verificar que los métodos de los mocks fueron llamados correctamente
        verify(superHeroeRepository).findById(anyLong());

	}
	
	@Test()
	void findAllSuperHeroesByParamTest() throws ValidationException {

        // Configurar comportamiento de los mocks
        when(superHeroeRepository.findAll()).thenReturn(Datos.SUPERHEROES); 
        when(utilsMapper.superHeroeEntitiesToDtos(anyList())).thenReturn(Datos.SUPERHEROESDTO);
        
		// Crear instancia del servicio con los mocks
        superHeroeService = new SuperHeroeServiceImpl(superHeroeRepository, utilsMapper, validarDatos);
        
		try {
			validarDatos.validarParametroBusquedaSuperHeroe("menta");
		} catch (ValidationException e) {
            fail("No se esperaba una excepción aquí: " + e.getMessage());
		}
        
        // Llamar al método que queremos probar
        SuperHeroeResponse superHeroes = superHeroeService.getSuperHeroesByParam("menta");
        
        // Verificar el resultado
        assertEquals(1, superHeroes.getSuperHeroList().get(0).getIdSuperHeroe());
        assertEquals("Tormenta", superHeroes.getSuperHeroList().get(0).getNombre());
        // Verificar que no se lanzó ninguna excepción
        assertDoesNotThrow(() -> validarDatos.validarParametroBusquedaSuperHeroe("menta"));
        
        // Verificar que los métodos de los mocks fueron llamados correctamente
        verify(superHeroeRepository, times(1)).findAll();
        verify(utilsMapper, times(1)).superHeroeEntitiesToDtos(anyList());

	}
	
	

}
