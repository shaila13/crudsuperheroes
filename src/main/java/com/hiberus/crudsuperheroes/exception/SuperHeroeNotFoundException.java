package com.hiberus.crudsuperheroes.exception;

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
