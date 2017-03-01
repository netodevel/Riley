package br.com.rileyframework;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.run.JettyEmbedded;
import br.com.rileyframework.utils.BasePackageMemory;

/**
 * @author NetoDevel
 */
public class RileyFramework {
	
	private List<HandlerMapping> mappings = new ArrayList<>();
	private Map<String, HandlerMapping> keyValue = new HashMap<>();
	
	public RileyFramework(){
	}
	
	public RileyFramework(List<HandlerMapping> mappings, Map<String, HandlerMapping> keyValue) {
		super();
		this.mappings = mappings;
		this.keyValue = keyValue;
	}

	public void init(@SuppressWarnings("rawtypes") Class baseClass, String [] args) {
		try {
			this.mappings = new ArrayList<HandlerMapping>();
			this.keyValue = new HashMap<String, HandlerMapping>();

			BasePackageMemory.savePackageInMemory(baseClass.getName());
			
			JettyEmbedded.init(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void handlerMappings(String baseClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

		Class clazz = Class.forName(baseClass);
		Reflections reflections = new Reflections(clazz.getPackage());

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Rest.class);
		for (Class<?> clazzAnnoted : annotated) {
			for (Method methods : clazzAnnoted.getDeclaredMethods()) {
				if (methods.isAnnotationPresent(Get.class)) {
					String action =  methods.getAnnotation(Get.class).value();
					mappings.add(new HandlerMapping(action, clazzAnnoted.getName(), "GET"));
				}
			}
		}
		
		for (HandlerMapping mapping : mappings) {
			keyValueMappings(mapping.getAction(), mapping);
		}
		
		createTableCli(mappings);
	}
	
	public static void createTableCli(List<HandlerMapping> mappings) {
		String leftAlignFormat = "| %-15s | %-12s |%n";
		System.out.println("%n");
		System.out.format("+-----------------+--------------+%n");
		System.out.format("| method          | path         |%n");
		System.out.format("+-----------------+--------------+%n");
		for (int i = 0; i < mappings.size(); i++) {
		    System.out.format(leftAlignFormat, mappings.get(i).getMethod(), mappings.get(i).getAction());
		}
		System.out.format("+-----------------+--------------+%n");
	}
	
	public void keyValueMappings(String action, HandlerMapping handlerMapping) {
		this.keyValue.put(action, handlerMapping);
	}

	public List<HandlerMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<HandlerMapping> mappings) {
		this.mappings = mappings;
	}

	public Map<String, HandlerMapping> getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Map<String, HandlerMapping> keyValue) {
		this.keyValue = keyValue;
	}
	
}
