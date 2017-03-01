package br.com.rileyframework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.utils.BasePackageMemory;

public class RileyInit {
	
	public static void init(Class baseClass, List<HandlerMapping> mappings) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		
		Reflections reflections = new Reflections(baseClass.getPackage());
		
		BasePackageMemory.savePackageInMemory(baseClass.getName());
		
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Rest.class);

		for (Class<?> clazz : annotated) {
			for (Method methods : clazz.getDeclaredMethods()) {
				if (methods.isAnnotationPresent(Get.class)) {
					String action =  methods.getAnnotation(Get.class).value();
					mappings.add(new HandlerMapping(action, clazz.getName(), "GET"));
				}
			}
		}
		
		RileyInit.createTableCli(mappings);
	}
	
	public static void createTableCli(List<HandlerMapping> mappings) {
		String leftAlignFormat = "| %-15s | %-12s |%n";
		System.out.format("+-----------------+--------------+%n");
		System.out.format("| method          | path         |%n");
		System.out.format("+-----------------+--------------+%n");
		for (int i = 0; i < mappings.size(); i++) {
		    System.out.format(leftAlignFormat, mappings.get(i).getMethod(), mappings.get(i).getAction());
		}
		System.out.format("+-----------------+--------------+%n");
	}
	
	
}
