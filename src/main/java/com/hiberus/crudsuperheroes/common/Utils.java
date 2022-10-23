package com.hiberus.crudsuperheroes.common;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

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
	 * Averigua si una cadena es vacía o nula
	 *
	 * @param aData cadena a explorar
	 * @return true si la cadena es vacia o nula en caso contrario devuelve false
	 */
	public static boolean isNullOrEmpty(String aData) {

		return (aData == null || aData.length() == 0);

	}

	/**
	 * Get Method Name
	 *
	 * @return method name
	 */
	public static String getCurrentMethodName() {

		final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
		return e.getMethodName();
	}

	/**
	 * Metodo para convertir un objeto en un json
	 * 
	 * @param object Objeto de entrada
	 * @return El json generado
	 */
	public static String toJson(Object object) {
		String gsonString = null;
		try{
		gsonString = new GsonBuilder().setPrettyPrinting().create().toJson(object);
	  }catch(JsonIOException err){
          log.error("JsonIOException : " + err.toString());
      }
		return gsonString;
	}



	/**
	 * Return object valueIfNull if object is null
	 *
	 * @param object
	 * @param valueIfNull
	 * @return
	 */
	public static <T> T valueIfNull(final T object, final T valueIfNull) {

		if (object == null) {
			return valueIfNull;
		} else {
			return object;
		}
	}

	/**
	 * Accessos a get en objetos con proteccion null pointer
	 * 
	 * USO: nullGuard(() -> contract.getContactInfo().getPosition())
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
	 * USO: nullGuard(() -> contract.getContactInfo().getPosition())
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
	 * Null-safe check if the specified collection is empty or null.
	 * <p>
	 * Null returns true.
	 * 
	 * @param coll the collection to check, may be null
	 * @return true if empty or null
	 * @since Commons Collections 3.2
	 */
	public static boolean isEmptyOrNullList(Collection coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * Compara 2 objetos y devuelve trueVal si son iguales o falseVal en caso contrario
	 * @param <T>
	 * @param <U>
	 * @param obj1
	 * @param obj2
	 * @param trueVal
	 * @param falseVal
	 * @return
	 */
	public static <T,U> T ifEquals(U obj1, U obj2, T trueVal, T falseVal) {
		return (Objects.equals(obj1, obj2)?trueVal:falseVal);
	}   

	
}
