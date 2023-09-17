package com.hiberus.crudsuperheroes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.crudsuperheroes.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findOneByEmail(String email);
	
}
