package com.hiberus.crudsuperheroes.service;


import java.util.Optional;
 
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.ValidationException;

public interface SuperHeroeService {

	/**
	 * Returns a list of superheroes.
	 * 
	 * @return Optional<SuperHeroeResponse>
	 */
	Optional<SuperHeroeResponse> getAllSuperHeroes();

	/**
	 * Returns a superheroe by id.
	 * 
	 * @param id
	 * @return SuperHeroeResponse
	 * @throws ValidationException 
	 */
	SuperHeroeResponse getSuperHeroesById(Long id) throws ValidationException;

	 /**
	  *  Returns a list of superheroes by a param.
	  * @param param
	  * @return SuperHeroeResponse
	 * @throws ValidationException 
	  */
	SuperHeroeResponse getSuperHeroesByParam(String param) throws ValidationException;
}
