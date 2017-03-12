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
import br.com.rileyframework.servers.JettyServer;
import br.com.rileyframework.utils.GeneratorRegex;
import br.com.rileyframework.utils.SetupRiley;

/**
 * @author NetoDevel
 */
public class RileyFramework {
	
	private List<HandlerMapping> mappings = new ArrayList<HandlerMapping>();
	private Map<String, HandlerMapping> keyValue = new HashMap<String, HandlerMapping>();
	
	public RileyFramework(){
	}
	
	public RileyFramework(List<HandlerMapping> mappings, Map<String, HandlerMapping> keyValue) {
		super();
		this.mappings = mappings;
		this.keyValue = keyValue;
	}

	public void init(@SuppressWarnings("rawtypes") Class baseClass) {
		try {
			this.mappings = new ArrayList<HandlerMapping>();
			this.keyValue = new HashMap<String, HandlerMapping>();

			SetupRiley.generateSetupRiley(baseClass.getName());
			
			JettyServer.init();
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
					mappings.add(new HandlerMapping(action, clazzAnnoted.getName(), "GET").
							withRegex(GeneratorRegex.generatorRegexFromUrl(action)));
				}
			}
		}
		
		for (HandlerMapping maps : mappings) {
			System.out.println("regex urls: " + maps.getRegex());
		}
		
		for (HandlerMapping mapping : mappings) {
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
	public static boolean matchUrl(String regex, String urlOrigin) {
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(urlOrigin);
		return m.matches();
	}
	
	public static void createTableCli(List<HandlerMapping> mappings) {
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
