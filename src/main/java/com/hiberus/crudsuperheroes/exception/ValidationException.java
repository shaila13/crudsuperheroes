package com.hiberus.crudsuperheroes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException extends Exception {

	/** Numero Serie */
	private static final long serialVersionUID = 3950521534892696583L;

	/** Codigo de mensaje */
	private String codMensaje;
	private String descMensaje;
	
	public ValidationException() {
		super("Los datos son obligatorios.");
	}

}
