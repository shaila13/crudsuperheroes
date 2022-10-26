package com.hiberus.crudsuperheroes.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "SUPER_HERO")
@Entity
public class SuperHeroe implements Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSuperHeroe;

	@NotNull
	@Column(name = "NOMBRE")
	String nombre;
	
	@NotNull
	@Column(name = "SUPER_PODER")
	String superPoder;

	@Column(name = "CREATION_DATE")
	LocalDate creationDate;
	
	
}