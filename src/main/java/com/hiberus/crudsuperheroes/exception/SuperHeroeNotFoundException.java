package com.hiberus.crudsuperheroes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SuperHeroeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuperHeroeNotFoundException(Long id) {
		super("No se ha podido recuperar super heroe con id " + id + ".");
	}
	public SuperHeroeNotFoundException(String param) {
		super("No se ha podido recuperar super heroe que contiene " + param + " en su nombre.");
	}
	public SuperHeroeNotFoundException() {
		super("No se ha podido recuperar ningun super heroe.");
	}
}
