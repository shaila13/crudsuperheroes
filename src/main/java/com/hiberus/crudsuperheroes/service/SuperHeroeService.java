package com.hiberus.crudsuperheroes.service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;

public interface SuperHeroeService {

	/**
	 * Returns a list of superheroes.
	 * 
	 * @return List<SuperHeroeResponse>
	 */
	SuperHeroeResponse getAllSuperHeroes();

	/**
	 * Returns a superheroe by id.
	 * 
	 * @param id
	 * @return SuperHeroeDto
	 * @throws Exception
	 */
	SuperHeroeResponse getSuperHeroesById(Long id);

	/**
	 * Returns a list of superheroes by a param.
	 * 
	 * @param param
	 * @return List<SuperHeroeResponse>
	 */
	SuperHeroeResponse getSuperHeroesByParam(String param);
}
