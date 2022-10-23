package com.hiberus.crudsuperheroes.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeDeleteServiceImpl implements SuperHeroeDeleteService{
	
	@Autowired
	SuperHeroeRepository superHeroeRepository;

	@Override
	public void deleteSuperHeroeById(Long id) {
				
		SuperHeroe result = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));
		
		log.info("[SuperHeroeDeleteServiceImpl] the super hero is going to be erased -> {}" + result.getNombre());
		
		superHeroeRepository.deleteById(id);
		
	}


}
