package com.hiberus.crudsuperheroes.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;
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
		
		log.info("[SuperHeroeDeleteServiceImpl] deleteSuperHeroeById -> INIT");
		
		SuperHeroeEntity result = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));
		
		log.info("[SuperHeroeDeleteServiceImpl] se va a proceder a borrar al super heroe -> {}" + result.getNombre());
		
		superHeroeRepository.deleteById(id);
		
	}


}
