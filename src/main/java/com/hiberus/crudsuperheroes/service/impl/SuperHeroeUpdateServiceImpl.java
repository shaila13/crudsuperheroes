package com.hiberus.crudsuperheroes.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.common.UtilsMapper;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeUpdateServiceImpl implements SuperHeroeUpdateService{
	
	  @Autowired
	  SuperHeroeRepository superHeroeRepository;
	  
	  @Autowired
	  UtilsMapper utilsMapper; 


	@Override
	@Transactional
	public boolean updateSuperHeroe(Long id, SuperHeroeRequest superHeroeRequest) {
		  
		SuperHeroe superHeroe = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));

		superHeroe.setNombre(superHeroeRequest.getNombre());
		superHeroe.setSuperPoder(superHeroeRequest.getSuperPoder());
		
		log.info("The registry "+superHeroe.getNombre()+" will be updated in the database.");
        return superHeroeRepository.saveAndFlush(superHeroe) != null;
		
	}


}
