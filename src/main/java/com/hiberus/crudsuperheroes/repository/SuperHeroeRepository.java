package com.hiberus.crudsuperheroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.crudsuperheroes.model.SuperHeroeEntity;

public interface SuperHeroeRepository extends JpaRepository<SuperHeroeEntity, Long>{
	
	

}
