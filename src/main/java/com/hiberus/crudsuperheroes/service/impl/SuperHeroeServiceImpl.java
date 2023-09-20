package com.hiberus.crudsuperheroes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.mapper.UtilsMapper;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.ValidarDatosService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeServiceImpl implements SuperHeroeService {
 

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private final ValidarDatosService validarDatos;
    private final SuperHeroeRepository superHeroeRepository;
    private final UtilsMapper utilsMapper;

    public SuperHeroeServiceImpl(SuperHeroeRepository superHeroeRepository, UtilsMapper utilsMapper,ValidarDatosService validarDatos) {
        this.superHeroeRepository = superHeroeRepository;
        this.utilsMapper = utilsMapper;
        this.validarDatos = validarDatos;
    }


	@Override
	public Optional<SuperHeroeResponse> getAllSuperHeroes() {
 
    Optional<SuperHeroeResponse> superHeroResponseOptional = Optional.of(SuperHeroeResponse.builder()
    		.superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll())).build());
    
		return Optional.of(superHeroResponseOptional.orElseThrow(() -> new SuperHeroeNotFoundException()));
	}

	@Override
	@Cacheable("superheroCache")
	public SuperHeroeResponse getSuperHeroesById(Long id) throws ValidationException{
 		
		validarDatos.validarIdSuperHeroe(id);
 
		List<SuperHeroeDto> superHeroList = new ArrayList<>();
		superHeroList.add(utilsMapper.superHeroeEntityToDto(
				superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id))));
		return SuperHeroeResponse.builder().superHeroList(superHeroList).build();
	}

	@Override
	public SuperHeroeResponse  getSuperHeroesByParam(String param) throws ValidationException{
 
		validarDatos.validarParametroBusquedaSuperHeroe(param);
 
		return SuperHeroeResponse.builder()
				.superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll().stream()
						.filter(nombre -> StringUtils.containsIgnoreCase(nombre.getNombre(), param))
						.collect(Collectors.toList())))
				.build();
	}
}
