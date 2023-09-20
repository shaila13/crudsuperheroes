package com.hiberus.crudsuperheroes.service;

import com.hiberus.crudsuperheroes.exception.ValidationException;

public interface SuperHeroeDeleteService {

	/**
	 * Delete a superheroes by id.
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException 
	 */
	String deleteSuperHeroeById(Long id) throws ValidationException;

}
