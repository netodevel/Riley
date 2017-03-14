package br.com.rileyframework;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.reflections.Reflections;

import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.servers.ServerFactory;
import br.com.rileyframework.utils.SetupRiley;

/**
 * Main class RileyFramework
 * 
 * @author NetoDevel
 */
public class RileyFramework extends AbstractRegisterMappings {

	/**
	 * Define engine for server
	 */
	private ServerFactory serverFactory;

	/**
	 * start your application
	 * 
	 * @param baseClass
	 * @param server
	 */
	public void init(Class<?> baseClass, String server) {
		try {
			serverFactory = new ServerFactory();
			SetupRiley.generateSetupRiley(baseClass.getName());

			serverFactory.create(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Scan all files project and register url mappings
	 * 
	 * @param basePackage
	 * @throws Exception
	 */
	public void registerUrlMappings(String basePackage) throws Exception {
		try {
			Class<?> clazz = Class.forName(basePackage);
			Reflections reflections = new Reflections(clazz.getPackage());

			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Rest.class);
			for (Class<?> clazzAnnoted : annotated) {
				for (Method methods : clazzAnnoted.getDeclaredMethods()) {
					super.scanGet(clazzAnnoted, methods);
				}
			}
			
			createTableCli(super.getMappings());
			
		} catch(Exception e) {
			throw new Exception("error: scan url mappings" + e.getMessage());
		}
	}
	
	/**
	 * match request url
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
	
	/**
	 * Create table cli for urls mapped
	 * 
	 * @param mappings
	 */
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
	
}
