package br.com.rileyframework;

import javax.servlet.http.HttpServletResponse;

/**
 * @author NetoDevel
 */
public interface HttpActions {

	public void invokeGetAction(HttpServletResponse resp, final String servletPath, String getActionURL, Class<?> clazz, Object obj) throws Exception ;
	
}
