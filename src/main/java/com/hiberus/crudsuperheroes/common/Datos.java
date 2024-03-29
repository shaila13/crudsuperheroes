package com.hiberus.crudsuperheroes.common;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;
import com.hiberus.crudsuperheroes.model.SuperHeroe;


public class Datos {

	private Datos() {

	}
	
	private static String nombreSuperHeroe="Tormenta";
	private static String superPoder="Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.";
	
    public static final List<SuperHeroe> SUPERHEROES = Arrays.asList(new SuperHeroe(1L,nombreSuperHeroe, superPoder,null),
    		new SuperHeroe(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix",null),
    		new SuperHeroe(3L,"Muerte","Gran poder e influencia en el cosmos.",null) );

    public static final List<SuperHeroeDto> SUPERHEROESDTO = Arrays.asList(new SuperHeroeDto(1L,nombreSuperHeroe, "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente."),
    		new SuperHeroeDto(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix"),
    		new SuperHeroeDto(3L,"Muerte","Gran poder e influencia en el cosmos.") );
   
    public static final Optional<SuperHeroe> SUPERHEROE = Optional.of(new SuperHeroe(1L,nombreSuperHeroe, superPoder,null));

    public static final SuperHeroeDto SUPERHEROEDTO = new SuperHeroeDto(1L,nombreSuperHeroe, superPoder);

}
