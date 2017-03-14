package br.com.rileyframework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;

/**
 * @author NetoDevel
 */
public class HttpCommands implements HttpActions {

	/**
	 * Invoke action request.
	 * 
	 * @param servletPath
	 * @param req
	 * @param resp
	 * @param urlMapped
	 * @throws Exception
	 */
	public void invokeAction(String servletPath, HttpServletRequest req, HttpServletResponse resp,UrlMapping urlMapped) throws Exception {
		Class<?> clazzName = Class.forName(urlMapped.getControllerAction());
		Object obj = createNewInstance(clazzName);
		Class<?> clazz = obj.getClass();

		switch (urlMapped.getMethod()) {
		case "GET":
			invokeGetAction(resp, servletPath, urlMapped.getAction(), clazz, obj);
			break;

		default:
			break;
		}
	}

	@Override
	public void invokeGetAction(HttpServletResponse resp, final String servletPath, String getActionURL, Class<?> clazz, Object obj) throws Exception {
		try{
			if (clazz.isAnnotationPresent(Rest.class)) {
				for (Method methods : clazz.getDeclaredMethods()) {
					if (methods.isAnnotationPresent(Get.class) && methods.getAnnotation(Get.class).value().equals(getActionURL)) {
						resp.getWriter().println(methods.invoke(obj));
					} 
				}
			}
		} catch (Exception e) {
			throw new Exception("error: invoke action" + e.getMessage());
		}
	}


	/**
	 * Create new instance of class with url invoked
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private static Object createNewInstance(Class<?> clazz) throws Exception {
		try{ 
			Constructor<?> ctor;
			ctor = clazz.getConstructors()[0];
			Object object = ctor.newInstance();
			return object;
		} catch(Exception e) {
			throw new Exception("error: instance class." + e.getMessage());
		}
	}

}
