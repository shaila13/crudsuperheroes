package com.hiberus.crudsuperheroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.crudsuperheroes.model.SuperHeroe;

public interface SuperHeroeRepository extends JpaRepository<SuperHeroe, Long>{
	
}
