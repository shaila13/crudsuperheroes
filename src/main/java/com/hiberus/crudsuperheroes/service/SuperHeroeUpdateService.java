package com.hiberus.crudsuperheroes.service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.ValidationException;

public interface SuperHeroeUpdateService {

	/**
	 * Update the data of a superhero by id.
	 * 
	 * @param id
	 * @param superHeroeRequest
	 * @return SuperHeroeResponse
	 * @throws ValidationException
	 */
	boolean updateSuperHeroe(Long id, SuperHeroeRequest superHeroeRequest) throws ValidationException;
}
