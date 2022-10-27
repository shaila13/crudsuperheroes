package com.hiberus.crudsuperheroes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.mapper.UtilsMapper;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.IValidarDatosService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeServiceImpl implements SuperHeroeService {

	@Autowired
	SuperHeroeRepository superHeroeRepository;

	@Autowired
	UtilsMapper utilsMapper;

	@Autowired
	IValidarDatosService validarDatos;

	@Override
	public SuperHeroeResponse getAllSuperHeroes() {
		return SuperHeroeResponse.builder()
				.superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll())).build();
	}

	@Override
	public SuperHeroeResponse getSuperHeroesById(Long id) {
		try {
			validarDatos.validarIdSuperHeroe(id);
		} catch (ValidationException e) {
			log.error("Los datos son obligatorios.", e.getMessage());
		}
		List<SuperHeroeDto> superHeroList = new ArrayList<>();
		superHeroList.add(utilsMapper.superHeroeEntityToDto(
				superHeroeRepository.findById(id).orElseThrow(() -> new SuperHeroeNotFoundException(id))));
		return SuperHeroeResponse.builder().superHeroList(superHeroList).build();
	}

	@Override
	public SuperHeroeResponse getSuperHeroesByParam(String param) {
		try {
			validarDatos.validarParametroBusquedaSuperHeroe(param);
		} catch (ValidationException e) {
			log.error("Los datos son obligatorios.", e.getMessage());
		}
		return SuperHeroeResponse.builder()
				.superHeroList(utilsMapper.superHeroeEntitiesToDtos(superHeroeRepository.findAll().stream()
						.filter(nombre -> StringUtils.containsIgnoreCase(nombre.getNombre(), param))
						.collect(Collectors.toList())))
				.build();
	}
}
