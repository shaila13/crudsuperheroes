package com.hiberus.crudsuperheroes.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.dto.SuperHeroeRequest;
import com.hiberus.crudsuperheroes.service.SuperHeroeDeleteService;
import com.hiberus.crudsuperheroes.service.SuperHeroeService;
import com.hiberus.crudsuperheroes.service.SuperHeroeUpdateService;

@RestController
@RequestMapping("/api/superheroes")
public class SuperHeroeController {

	 @Autowired
	 private SuperHeroeService superHeroeService;
	 
	 @Autowired
	 private SuperHeroeDeleteService superHeroeDeleteService;
	 
	 @Autowired
	 private SuperHeroeUpdateService superHeroeUpdateService;
	 
	  @GetMapping("/get-superheroes-list")
	  public ResponseEntity<List<SuperHeroeDto>> getAllSuperHeroes() {
	    return ResponseEntity.ok(superHeroeService.getAllSuperHeroes());
	  }

	  @GetMapping("/get-detail-superheroes-by-id/{id}")
	  public ResponseEntity<SuperHeroeDto> getSuperHeroesById(@PathVariable("id") Long id) {
	    return ResponseEntity.ok(superHeroeService.getSuperHeroesById(id));
	  }
	  @GetMapping("/get-detail-superheroe-by-param/{param}")
	  public ResponseEntity<List<SuperHeroeDto>> getSuperHeroesByParam(@PathVariable("param") String param) {
	    return ResponseEntity.ok(superHeroeService.getSuperHeroesByParam(param));
	  }
	  @PutMapping("/update-superheroe/{id}")
	  public ResponseEntity<SuperHeroeDto> updateSuperHeroes(@PathVariable("id") Long id, @RequestBody SuperHeroeRequest superHeroeRequest) {
	    return ResponseEntity.ok(superHeroeUpdateService.updateSuperHeroe(id, superHeroeRequest));
	  }

	  @DeleteMapping("/delete-superheroe-by-id/{id}")
	  public ResponseEntity<Void> deleteSuperHeroe(@PathVariable("id") Long id) {
		  superHeroeDeleteService.deleteSuperHeroeById(id);
	    return ResponseEntity.ok().build();
	  }
	
}
