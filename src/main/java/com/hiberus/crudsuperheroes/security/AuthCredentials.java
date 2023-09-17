package com.hiberus.crudsuperheroes.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthCredentials {
 
	String nombre;
	String email;
	String password;
 
}
