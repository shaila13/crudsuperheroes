package com.hiberus.crudsuperheroes.service.impl;

import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.common.ConstantsError;
import com.hiberus.crudsuperheroes.common.Utils;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.service.IValidarDatosService;

@Service
public class ValidarDatosServiceImpl implements IValidarDatosService {

	@Override
	public void validarDatosUpdateSuperHeroe(SuperHeroeRequest superHeroeRequest) throws ValidationException {
		if (superHeroeRequest == null) {
			throw new ValidationException(ConstantsError.COD_RESP_ERROR_VALIDACION, "Los datos son obligatorio. ");
		}
		if (Utils.nullGuard(superHeroeRequest::getNombre) == null) {
			throw new ValidationException(ConstantsError.COD_RESP_ERROR_VALIDACION, "El Nombre es obligatorio. ");
		}
	}

	@Override
	public void validarIdSuperHeroe(Long id) throws ValidationException {
		if (id == null) {
			throw new ValidationException(ConstantsError.COD_RESP_ERROR_VALIDACION, "El Id es obligatorio. ");
		}
	}

	@Override
	public void validarParametroBusquedaSuperHeroe(String parametro) throws ValidationException {
		if (Utils.nullGuard(() -> parametro) == null) {
			throw new ValidationException(ConstantsError.COD_RESP_ERROR_VALIDACION,
					"El parametro de busqueda es obligatorio. ");
		}
	}
}
