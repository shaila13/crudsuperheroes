package com.hiberus.crudsuperheroes.unit.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.crudsuperheroes.controller.SuperHeroeController;
import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.mapper.UtilsMapper;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

@WebMvcTest(SuperHeroeController.class)
class SuperHeroControllerTest {

	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SuperHeroeService superHeroeService;

    @MockBean
    private SuperHeroeDeleteService superHeroeDeleteSService;
    
    @MockBean
    private SuperHeroeUpdateService superHeroeUpdateService;
    
    private UtilsMapper utilsMapper;
    
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        utilsMapper = new UtilsMapper();
    }
	
	@Test()
	void getAllSuperHeroesTest() throws JsonProcessingException, Exception {
   
		// Given
        List<SuperHeroe> superHeroeList = Arrays.asList(new SuperHeroe(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.",null),
        		new SuperHeroe(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix",null),
        		new SuperHeroe(3L,"Muerte","Gran poder e influencia en el cosmos.",null) );
        
        doReturn(Optional.of(superHeroeList)).when(superHeroeService).getAllSuperHeroes();
        
        // When
        mvc.perform(get("/api/v1/superheroes").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idSuperHeroe").value(1L))
                .andExpect(jsonPath("$[1].idSuperHeroe").value(2L))
                .andExpect(jsonPath("$[2].idSuperHeroe").value(3L))
                .andExpect(jsonPath("$[0].nombre").value("Tormenta"))
                .andExpect(jsonPath("$[1].nombre").value("Fenix Oscura"))
                .andExpect(jsonPath("$[2].nombre").value("Muerte"))
                .andExpect(jsonPath("$[0].superPoder").value("Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente."))
                .andExpect(jsonPath("$[1].superPoder").value("Poderosas habilidades como telepata, telekinetica y Fuerza Fenix"))
                .andExpect(jsonPath("$[2].superPoder").value("Gran poder e influencia en el cosmos."))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(content().json(objectMapper.writeValueAsString(superHeroeList)));

        verify(superHeroeService).getAllSuperHeroes();
    }
    	
	@Test()
	void getAllSuperHeroesTest2() throws JsonProcessingException, Exception {
   
		// Given
        List<SuperHeroe> superHeroeList = Arrays.asList(new SuperHeroe(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.",null),
        		new SuperHeroe(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix",null),
        		new SuperHeroe(3L,"Muerte","Gran poder e influencia en el cosmos.",null) );
        
        doReturn(Optional.of(superHeroeList)).when(superHeroeService).getAllSuperHeroes();
        
        // When
        mvc.perform(get("/api/v1/superheroes").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[*].idSuperHeroe").isNotEmpty())
        .andExpect(content().json(objectMapper.writeValueAsString(superHeroeList)));

        verify(superHeroeService).getAllSuperHeroes();
    }
	
	@Test()
	void getSuperHeroesByIdTest() throws JsonProcessingException, Exception {
		
		List<SuperHeroeDto> superHeroList =	Arrays.asList(utilsMapper.superHeroeEntityToDto(
				new SuperHeroe(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.", null)));
		
	    SuperHeroeResponse superHeroeResponse = SuperHeroeResponse.builder()
	            .superHeroList(superHeroList)
	            .build();
		
		doReturn(superHeroeResponse).when(superHeroeService).getSuperHeroesById(1L);
        		
        Long id = 1L;
        // When
        mvc.perform(get("/api/v1/superheroe/{id}",id).contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.superHeroList").isArray())
                .andExpect(jsonPath("$.superHeroList[*].idSuperHeroe").isNotEmpty())
                .andExpect(jsonPath("$.superHeroList[*].idSuperHeroe").exists())
                .andExpect(jsonPath("$.superHeroList[*].nombre").value("Tormenta"))
                .andExpect(jsonPath("$.superHeroList[*].superPoder").value("Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente."))
                .andExpect(jsonPath("$.superHeroList", hasSize(1)))
                .andExpect(content().json(objectMapper.writeValueAsString(superHeroeResponse)));

        verify(superHeroeService).getSuperHeroesById(id);
        
		
	}
	
	@Test()
	void getSuperHeroesByParamTest() throws JsonProcessingException, Exception {
		
		List<SuperHeroeDto> superHeroList =	Arrays.asList(utilsMapper.superHeroeEntityToDto(
				new SuperHeroe(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.", null)));
		
	    SuperHeroeResponse superHeroeResponse = SuperHeroeResponse.builder()
	            .superHeroList(superHeroList)
	            .build();
		
		doReturn(superHeroeResponse).when(superHeroeService).getSuperHeroesByParam("menta");
        		
        String params = "menta";
        // When
        mvc.perform(get("/api/v1/superheroes/{params}",params).contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.superHeroList").isArray())
                .andExpect(jsonPath("$.superHeroList[*].idSuperHeroe").isNotEmpty())
                .andExpect(jsonPath("$.superHeroList[*].idSuperHeroe").exists())
                .andExpect(jsonPath("$.superHeroList[*].nombre").value("Tormenta"))
                .andExpect(jsonPath("$.superHeroList[*].superPoder").value("Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente."))
                .andExpect(jsonPath("$.superHeroList", hasSize(1)))
                .andExpect(content().json(objectMapper.writeValueAsString(superHeroeResponse)));

        verify(superHeroeService).getSuperHeroesByParam(params);
		
		
	}


	@Test()
	void updateSuperHeroesTest() throws JsonProcessingException, Exception {

 		SuperHeroeRequest superHeroRequest = new SuperHeroeRequest("Shaila", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.");
        Long id = 1L;
        
         doReturn(true).when(superHeroeUpdateService).updateSuperHeroe(id, superHeroRequest);
        
         mvc.perform(put("/api/v1/superheroe/{id}", id)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(superHeroRequest)))
                 .andExpect(status().isOk());
        
        verify(superHeroeUpdateService).updateSuperHeroe(id, superHeroRequest);
	}

	
	@Test()
	void deleteSuperHeroeTest() throws JsonProcessingException, Exception {
		
		 Long id = 1L;
		 
	     mvc.perform(delete("/api/v1/superheroe/{id}",id).contentType(MediaType.APPLICATION_JSON))
	     		.andExpect(status().isAccepted());
	        
	        verify(superHeroeDeleteSService).deleteSuperHeroeById(id);
		
	}
	
}
