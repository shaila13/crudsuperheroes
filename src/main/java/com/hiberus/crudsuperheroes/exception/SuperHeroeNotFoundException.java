package com.hiberus.crudsuperheroes.exception;

public class SuperHeroeNotFoundException  extends RuntimeException {

    public SuperHeroeNotFoundException(Long id) {
        super("Could not find super heroe with id " + id + ".");
    }
}
