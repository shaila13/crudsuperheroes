package com.hiberus.crudsuperheroes.common;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase de utilidades
 *
 * @author Shaila
 *
 */
@Slf4j
@UtilityClass
public class Utils {

	/**
	 * Averigua si una cadena es vacia o nula
	 *
	 * @param aData cadena a explorar
	 * @return true si la cadena es vacia o nula en caso contrario devuelve false
	 */
	public static boolean isNullOrEmpty(String aData) {
		return (aData == null || aData.length() == 0);
	}

	/**
	 * Metodo para convertir un objeto en un json
	 * 
	 * @param object Objeto de entrada
	 * @return El json generado
	 */
	public static String toJson(Object object) {
		String gsonString = null;
		try {
			gsonString = new GsonBuilder().setPrettyPrinting().create().toJson(object);
		} catch (JsonIOException err) {
			log.error("JsonIOException : " + err.toString());
		}
		return gsonString;
	}

	/**
	 * Accessos a get en objetos con proteccion null pointer
	 * 
	 * 
	 * @param <T>
	 * @param supplier
	 * @return
	 */
	public static <T> String nullGuardString(Supplier<T> supplier) {
		try {
			return (String) supplier.get();
		} catch (NullPointerException ignored) {
			return null;
		}
	}

	/**
	 * Accessos a get en objetos con proteccion null pointer
	 *
	 * @param <T>
	 * @param supplier
	 * @return
	 */
	public static <T> T nullGuard(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (NullPointerException ignored) {
			return null;
		}
	}

	/**
	 * Comprueba que una lista sea nula o vacia.
	 * <p>
	 * Null devuelve true.
	 * 
	 * @param lista a comprobar
	 * @return true si es vacia o nula
	 */
	public static boolean isEmptyOrNullList(Collection coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * Compara 2 objetos y devuelve trueVal si son iguales o falseVal en caso
	 * contrario
	 * 
	 * @param <T>
	 * @param <U>
	 * @param obj1
	 * @param obj2
	 * @param trueVal
	 * @param falseVal
	 * @return
	 */
	public static <T, U> T ifEquals(U obj1, U obj2, T trueVal, T falseVal) {
		return (Objects.equals(obj1, obj2) ? trueVal : falseVal);
	}

	/**
	 * Metodo para convertir un objeto en un json
	 * 
	 * @param object
	 * @return String
	 */
	public static String jsonInString(Object object) {
		String jsonInString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonInString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException err) {
			log.error("JsonIOException : " + err.toString());
		}
		return jsonInString;
	}
}
