package com.hiberus.crudsuperheroes.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
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

	@GetMapping("/superheroes")
	public ResponseEntity<Optional<SuperHeroeResponse>> getAllSuperHeroes() {
		return ResponseEntity.ok(superHeroeService.getAllSuperHeroes());
	}

	@Cacheable("user")
	@GetMapping("/superheroe/{id}")
	public ResponseEntity<SuperHeroeResponse> getSuperHeroesById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(superHeroeService.getSuperHeroesById(id));
	}

	@GetMapping("/superheroes/{param}")
	public ResponseEntity<SuperHeroeResponse> getSuperHeroesByParam(@PathVariable("param") String param) {
		return ResponseEntity.ok(superHeroeService.getSuperHeroesByParam(param));
	}

	@PutMapping("/superheroe/{id}")
	public ResponseEntity<Boolean> updateSuperHeroes(@PathVariable("id") Long id,
			@RequestBody SuperHeroeRequest superHeroeRequest) throws ValidationException {
		return ResponseEntity.ok(superHeroeUpdateService.updateSuperHeroe(id, superHeroeRequest));
	}

	@DeleteMapping("/superheroe/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<String> deleteSuperHeroe(@PathVariable("id") Long id) {
		superHeroeDeleteService.deleteSuperHeroeById(id);
		return ResponseEntity.accepted().build();
	}

	
}
