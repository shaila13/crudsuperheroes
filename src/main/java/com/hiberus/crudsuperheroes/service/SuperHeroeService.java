package com.hiberus.crudsuperheroes.service;


import java.util.Optional;
 
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;

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
	 */
	SuperHeroeResponse getSuperHeroesById(Long id);

	 /**
	  *  Returns a list of superheroes by a param.
	  * @param param
	  * @return SuperHeroeResponse
	  */
	SuperHeroeResponse getSuperHeroesByParam(String param);
}
