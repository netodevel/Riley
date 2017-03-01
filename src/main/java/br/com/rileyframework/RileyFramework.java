package br.com.rileyframework;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rileyframework.run.JettyEmbedded;

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
			RileyInit.init(baseClass, mappings);
			JettyEmbedded.init(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void handlerMappings(String baseClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		Class clazz = Class.forName(baseClass);
		RileyInit.init(clazz, mappings);
		for (HandlerMapping mapping : mappings) {
			keyValueMappings(mapping.getAction(), mapping);
		}
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
