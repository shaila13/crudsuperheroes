package com.hiberus.crudsuperheroes.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.model.SuperHeroe;
import com.hiberus.crudsuperheroes.repository.SuperHeroeRepository;
import com.hiberus.crudsuperheroes.service.ValidarDatosService;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SuperHeroeUpdateServiceImpl implements SuperHeroeUpdateService {
	
	private final ValidarDatosService validarDatos;
    private final SuperHeroeRepository superHeroeRepository; 

    public SuperHeroeUpdateServiceImpl(SuperHeroeRepository superHeroeRepository, ValidarDatosService validarDatos) {
        this.superHeroeRepository = superHeroeRepository; 
        this.validarDatos = validarDatos;
    }

	
	@Override
	@Transactional
	public boolean updateSuperHeroe(Long id, SuperHeroeRequest superHeroeRequest) throws ValidationException {
 
			validarDatos.validarIdSuperHeroe(id);
			validarDatos.validarDatosUpdateSuperHeroe(superHeroeRequest);
 
		Optional<SuperHeroeRequest> optSuperHeroe = Optional.ofNullable(superHeroeRequest);
		SuperHeroe superHeroe = superHeroeRepository.findById(id)
				.orElseThrow(() -> new SuperHeroeNotFoundException(id));

		superHeroe.setNombre(superHeroeRequest.getNombre());
		superHeroe.setCreationDate(LocalDate.now());
		if(optSuperHeroe.isPresent()) {
			superHeroe.setSuperPoder(optSuperHeroe.get().getSuperPoder());
		}
	
		log.info("El registro " + superHeroe.getNombre() + " sera actualizado en Base de Datos.");
		return superHeroeRepository.saveAndFlush(superHeroe) != null;
	}
}
