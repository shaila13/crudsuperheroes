package com.hiberus.crudsuperheroes.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.model.SuperHeroe;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UtilsMapper {

	/**
	 * Metodo para mapear una lista de SuperHeroeEntity a SuperHeroe dto.
	 *
	 * @param entity
	 * @return
	 * 
	 */
	public List<SuperHeroeDto> superHeroeEntitiesToDtos(List<SuperHeroe> entities) {
		return entities.stream().map(this::superHeroeEntityToDto).collect(Collectors.toList());
	}

	/**
	 * Metodo para mapear una SuperHeroeEntity a SuperHeroeDto
	 *
	 * @param entity
	 * @return
	 * 
	 */
	public SuperHeroeDto superHeroeEntityToDto(SuperHeroe entity) {
		return SuperHeroeDto.builder().idSuperHeroe(entity.getIdSuperHeroe()).nombre(entity.getNombre())
				.superPoder(entity.getSuperPoder()).build();
	}
}
