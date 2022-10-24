package com.hiberus.crudsuperheroes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.crudsuperheroes.common.Utils;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.dto.SuperHeroeResponse;
import com.hiberus.crudsuperheroes.exception.ValidationException;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

@RestController
@RequestMapping("/api/superheroes")
public class SuperHeroeController {

	 @Autowired
	 private SuperHeroeService  superHeroeService;
	 
	 @Autowired
	 private SuperHeroeDeleteService superHeroeDeleteService;
	 
	 @Autowired
	 private SuperHeroeUpdateService superHeroeUpdateService;

	 
	  @GetMapping("/get-superheroes-list")
	  public ResponseEntity<String> getAllSuperHeroes() {
	    return ResponseEntity.ok(Utils.toJson(superHeroeService.getAllSuperHeroes()));
	  }

	  @GetMapping("/get-detail-superheroes-by-id/{id}")
	  public ResponseEntity<String> getSuperHeroesById(@PathVariable("id") Long id) {
	    return ResponseEntity.ok(Utils.toJson(superHeroeService.getSuperHeroesById(id)));
	  }
	  @GetMapping("/get-detail-superheroe-by-param/{param}")
	  public ResponseEntity<String> getSuperHeroesByParam(@PathVariable("param") String param) {
	    return ResponseEntity.ok(Utils.toJson(superHeroeService.getSuperHeroesByParam(param)));
	  }
	  
	  @PutMapping("/update-superheroe/{id}")
	  public ResponseEntity<Boolean> updateSuperHeroes(@PathVariable("id") Long id, @RequestBody SuperHeroeRequest superHeroeRequest) throws ValidationException {
	    return ResponseEntity.ok(superHeroeUpdateService.updateSuperHeroe(id, superHeroeRequest));
	  }

	  @DeleteMapping("/delete-superheroe-by-id/{id}")
	  public ResponseEntity<String> deleteSuperHeroe(@PathVariable("id") Long id) {
		  superHeroeDeleteService.deleteSuperHeroeById(id);
	    return ResponseEntity.ok().build();
	  }
	
}
