package com.hiberus.crudsuperheroes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.ValidarDatosService;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeDeleteServiceImpl implements SuperHeroeDeleteService {

	@Autowired
	SuperHeroeRepository superHeroeRepository;

	@Autowired
	ValidarDatosService validarDatos;

	@Override
	public String deleteSuperHeroeById(Long id) throws ValidationException {

		validarDatos.validarIdSuperHeroe(id);

		SuperHeroe result = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));

		log.info("El super heroe " + result.getNombre() + " va a ser borrado de Base de Datos.");

		superHeroeRepository.deleteById(id);

		return "El super heroe " + result.getNombre() + " ha sido borrado de Base de Datos.";
	}
}
