package com.hiberus.crudsuperheroes.model;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hiberus.crudsuperheroes.dto.SuperHeroeDto;

public class Datos {
	
	private Datos() {
	}
	
	public static final SuperHeroe tormenta =  new SuperHeroe(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.",null);
	public static final List<SuperHeroe> SUPERHEROES = Arrays.asList(tormenta,
    		new SuperHeroe(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix",null),
    		new SuperHeroe(3L,"Muerte","Gran poder e influencia en el cosmos.",null) );

    public static final List<SuperHeroeDto> SUPERHEROESDTO = Arrays.asList(new SuperHeroeDto(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente."),
    		new SuperHeroeDto(2L,"Fenix Oscura","Poderosas habilidades como telepata, telekinetica y Fuerza Fenix"),
    		new SuperHeroeDto(3L,"Muerte","Gran poder e influencia en el cosmos.") );
   
    public static final  Optional<SuperHeroe> SUPERHEROE = Optional.of(tormenta);

    public static final SuperHeroeDto SUPERHEROEDTO = new SuperHeroeDto(1L,"Tormenta", "Capaz de dominar el clima, las precipitaciones y la electricidad del ambiente.");

}
