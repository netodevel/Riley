package br.com.rileyframework;

import java.lang.reflect.Method;

public interface RegisterMappings {

	public void scanGet(Class<?> clazzAnnoted, Method methods);
	
}
