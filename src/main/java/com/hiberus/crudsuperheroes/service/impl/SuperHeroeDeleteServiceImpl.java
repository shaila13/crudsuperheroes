package com.hiberus.crudsuperheroes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.IValidarDatosService;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeDeleteServiceImpl implements SuperHeroeDeleteService {

	@Autowired
	SuperHeroeRepository superHeroeRepository;

	@Autowired
	IValidarDatosService validarDatos;

	@Override
	public String deleteSuperHeroeById(Long id) {
		String mesajeDetele;
		try {
			validarDatos.validarIdSuperHeroe(id);
		} catch (ValidationException e) {
			log.error("Los datos son obligatorios.", e.getMessage());

		}
		SuperHeroe result = superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id));

		log.info("El super heroe " + result.getNombre() + " va a ser borrado de Base de Datos.");

		superHeroeRepository.deleteById(id);
		mesajeDetele = "El super heroe " + result.getNombre() + " ha sido borrado de Base de Datos.";
		return mesajeDetele;
	}
}
