package com.hiberus.crudsuperheroes.exception;

public class SuperHeroeNotFoundException  extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuperHeroeNotFoundException(Long id) {
        super("Could not find super heroe with id " + id + ".");
    }
    
    public SuperHeroeNotFoundException() {
        super("Could not find any super heroe.");
    }
}
