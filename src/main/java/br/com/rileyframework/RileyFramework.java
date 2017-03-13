package br.com.rileyframework;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.reflections.Reflections;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.servers.ServerFactory;
import br.com.rileyframework.utils.GeneratorRegex;
import br.com.rileyframework.utils.SetupRiley;

/**
 * Main Class RileyFramework
 * 
 * @author NetoDevel
 */
public class RileyFramework {
	
	private List<UrlMapping> mappings = new ArrayList<UrlMapping>();
	private Map<String, UrlMapping> keyValue = new HashMap<String, UrlMapping>();
	private ServerFactory serverFactory;
	
	public RileyFramework(){
	}
	
	public RileyFramework(List<UrlMapping> mappings, Map<String, UrlMapping> keyValue) {
		super();
		this.mappings = mappings;
		this.keyValue = keyValue;
	}

	/**
	 * start your application
	 * 
	 * @param baseClass
	 * @param server
	 */
	public void init(@SuppressWarnings("rawtypes") Class baseClass, String server) {
		try {
			this.mappings = new ArrayList<UrlMapping>();
			this.keyValue = new HashMap<String, UrlMapping>();
			serverFactory = new ServerFactory();

			SetupRiley.generateSetupRiley(baseClass.getName());
			
			serverFactory.create(server);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void registerUrlMappings(String baseClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

		Class clazz = Class.forName(baseClass);
		Reflections reflections = new Reflections(clazz.getPackage());

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Rest.class);
		for (Class<?> clazzAnnoted : annotated) {
			for (Method methods : clazzAnnoted.getDeclaredMethods()) {
				if (methods.isAnnotationPresent(Get.class)) {
					String action =  methods.getAnnotation(Get.class).value();
					mappings.add(new UrlMapping(action, clazzAnnoted.getName(), "GET").
							withRegex(GeneratorRegex.generatorRegexFromUrl(action)));
				}
			}
		}
		
		for (UrlMapping mapping : mappings) {
			keyValueMappings(mapping.getAction(), mapping);
		}
		
		createTableCli(mappings);
	}
	
	/**
	 * 
	 * @param regex
	 * @param urlOrigin
	 * @return boolean
	 */
	public boolean matchUrl(String regex, String urlOrigin) {
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(urlOrigin);
		return m.matches();
	}
	
	public static void createTableCli(List<UrlMapping> mappings) {
		String leftAlignFormat = "| %-15s | %-12s |%n";
		System.out.println("\n");
		System.out.println("\n");
		System.out.format("+-----------------+--------------+%n");
		System.out.format("| method          | path         |%n");
		System.out.format("+-----------------+--------------+%n");
		for (int i = 0; i < mappings.size(); i++) {
		    System.out.format(leftAlignFormat, mappings.get(i).getMethod(), mappings.get(i).getAction());
		}
		System.out.format("+-----------------+--------------+%n");
		System.out.println("\n");
	}
	
	public void keyValueMappings(String action, UrlMapping handlerMapping) {
		this.keyValue.put(action, handlerMapping);
	}

	public List<UrlMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<UrlMapping> mappings) {
		this.mappings = mappings;
	}

	public Map<String, UrlMapping> getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Map<String, UrlMapping> keyValue) {
		this.keyValue = keyValue;
	}
	
}
