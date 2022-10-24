package com.hiberus.crudsuperheroes.service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.ValidationException;

public interface IValidarDatosService {
	

	/**
	 * Se validan los datos Super Heroe.
	 * @param id
	 * @param superHeroeRequest
	 * @throws ValidationException
	 */
	void validarDatosUpdateSuperHeroe(SuperHeroeRequest superHeroeRequest) throws ValidationException;
	
	/**
	 * Se valida el id para realizar las operaciones de busqueda o borrdao de un super heroe.
	 * @param id
	 * @throws ValidationException
	 */
	void validarIdSuperHeroe(Long id) throws ValidationException;
	
	/**
	 * Se valida el parametro para realizar la operacion de busqueda de uno o varios super heroes.
	 * @param parametro
	 * @throws ValidationException
	 */
	void validarParametroBusquedaSuperHeroe(String parametro) throws ValidationException;

}
