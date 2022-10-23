package com.hiberus.crudsuperheroes.service.impl;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.common.UtilsMapper;
import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;

@Service
public class SuperHeroeServiceImpl implements SuperHeroeService{
	
	  @Autowired
	  SuperHeroeRepository superHeroeRepository;
	    
	  @Autowired
	  UtilsMapper utilsMapper;

	@Override
	public  SuperHeroeResponse getAllSuperHeroes() {
		return SuperHeroeResponse.builder()
				 .superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll())).build();
	}

	@Override
	public SuperHeroeDto getSuperHeroesById(Long id) {
		return utilsMapper.superHeroeEntityToDto(superHeroeRepository.findById(id)
	        .orElseThrow(() -> new SuperHeroeNotFoundException(id)));
	}

	@Override
	public SuperHeroeResponse getSuperHeroesByParam(String param) {
		return SuperHeroeResponse.builder().superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll().stream()
                .filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), param))
                .collect(Collectors.toList()))).build(); 

	}


}
