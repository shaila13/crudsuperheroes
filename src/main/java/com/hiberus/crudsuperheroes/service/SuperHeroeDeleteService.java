package com.hiberus.crudsuperheroes.service;

import java.util.List;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;


public interface SuperHeroeDeleteService {

	/**
	 * Delete a superheroes by id.
	 * @param id
	 */
	void deleteSuperHeroeById(Long id);

}
