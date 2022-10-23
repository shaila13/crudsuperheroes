package com.hiberus.crudsuperheroes.service;


import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;


public interface SuperHeroeUpdateService {


	/**
	 * Update the data of a superhero by id.
	 * @param id
	 * @param superHeroeRequest
	 * @return SuperHeroeDto
	 */
	boolean updateSuperHeroe(Long id, SuperHeroeRequest superHeroeRequest);

}
