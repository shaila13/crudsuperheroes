package com.hiberus.crudsuperheroes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.crudsuperheroes.anotacion.CustomTimer;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.SuperHeroeNotFoundException;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SuperHeroeController {

	
	@Autowired
	private SuperHeroeService superHeroeService;

	@Autowired
	private SuperHeroeDeleteService superHeroeDeleteService;

	@Autowired
	private SuperHeroeUpdateService superHeroeUpdateService;

	@CustomTimer
	@GetMapping("/superheroes")
	public ResponseEntity<Optional<SuperHeroeResponse>> getAllSuperHeroes(){
		return ResponseEntity.ok(superHeroeService.getAllSuperHeroes());
	}

    @GetMapping("/superheroe/{id}")
    public ResponseEntity<SuperHeroeResponse> getSuperHeroesById(@PathVariable("id") Long id) throws SuperHeroeNotFoundException, ValidationException {
        return Optional.ofNullable(superHeroeService.getSuperHeroesById(id))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    log.error("SuperHeroe not found with id: {}", id);
                    return new SuperHeroeNotFoundException(id);
                });
    }
 
//@ControllerAdvice
	@GetMapping("/superheroes/{param}")
	public ResponseEntity<SuperHeroeResponse> getSuperHeroesByParam(@PathVariable("param") String param) throws SuperHeroeNotFoundException, ValidationException {
		return Optional.ofNullable(superHeroeService.getSuperHeroesByParam(param))
		        .map(ResponseEntity::ok)
		        .orElseThrow(() -> new SuperHeroeNotFoundException(param));
	}

	@PutMapping("/superheroe/{id}")
	public ResponseEntity<Boolean> updateSuperHeroes(@PathVariable("id") Long id,
			@RequestBody SuperHeroeRequest superHeroeRequest) throws ValidationException {
		return ResponseEntity.ok(superHeroeUpdateService.updateSuperHeroe(id, superHeroeRequest));
	}

	@DeleteMapping("/superheroe/{id}")
	public ResponseEntity<String> deleteSuperHeroe(@PathVariable("id") Long id) throws ValidationException {
		superHeroeDeleteService.deleteSuperHeroeById(id);
		return ResponseEntity.accepted().build();
	}

    @ExceptionHandler(SuperHeroeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSuperHeroeNotFoundException(SuperHeroeNotFoundException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }
    
	
}
