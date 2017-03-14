package br.com.rileyframework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.utils.GeneratorRegex;

/**
 * register all verbs http mapped in project
 * 
 * @author NetoDevel
 */
public abstract class AbstractRegisterMappings implements RegisterMappings {
	
	private List<UrlMapping> mappings = new ArrayList<UrlMapping>();
	
	@Override
	public void scanGet(Class<?> clazzAnnoted, Method methods) {
		if (methods.isAnnotationPresent(Get.class)) {
			String action =  methods.getAnnotation(Get.class).value();
			getMappings().add(new UrlMapping(action, clazzAnnoted.getName(), "GET")
					.withRegex(GeneratorRegex.generatorRegexFromUrl(action)));
		}
	}

	public List<UrlMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<UrlMapping> mappings) {
		this.mappings = mappings;
	}
	
}
