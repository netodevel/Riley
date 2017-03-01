package br.com.rileyframework.generics;

import com.google.gson.Gson;

public class JsonReturn {
	
	public static <T> String toJson(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

}
