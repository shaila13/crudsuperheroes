package com.hiberus.crudsuperheroes.service.impl;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

@Service
public class SuperHeroeUpdateServiceImpl implements SuperHeroeUpdateService{
	
	  @Autowired
	  SuperHeroeRepository superHeroeRepository;

	@Override
	@Transactional
	public boolean updateSuperHeroe(Long id, SuperHeroeRequest superHeroeRequest) {

		SuperHeroeEntity superHeroe = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));

		superHeroe.setNombre(superHeroeRequest.getNombre());
		superHeroe.setSuperPoder(superHeroeRequest.getSuperPoder());
		
        superHeroeRepository.saveAndFlush(superHeroe);
        
        return true;
		
		
	}


}
