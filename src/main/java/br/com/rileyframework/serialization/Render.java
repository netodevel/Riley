package br.com.rileyframework.serialization;

import com.google.gson.Gson;

/**
 * @author NetoDevel
 */
public class Render {
	
	public static <T> String toJson(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

}
