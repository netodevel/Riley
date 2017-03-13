package br.com.rileyframework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.utils.SetupRiley;

/**
 * handler of all requests
 * 
 * @author neto
 */
public class RileyFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RileyFramework rileyFramework = new RileyFramework();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackage = SetupRiley.getBasePackage(new File("src/main/resources/setup.conf"));

		try {
			rileyFramework.registerUrlMappings(basePackage);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({"rawtypes"})
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		final String servletPath = req.getServletPath();

		for (UrlMapping urlMapped : rileyFramework.getMappings()) {
			if (rileyFramework.matchUrl(urlMapped.getRegex(), servletPath)) {
				Class clazzName = Class.forName(urlMapped.getControllerAction());
				Object obj = createNewInstance(clazzName);
				Class clazz = obj.getClass();
				invokeActionGETRequest(resp, servletPath, urlMapped.getAction(), clazz, obj);
				break;
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	private void invokeActionGETRequest(HttpServletResponse resp, final String servletPath, String getActionURL, @SuppressWarnings("rawtypes") Class clazz, Object obj) throws IOException, IllegalAccessException, InvocationTargetException {
		if (clazz.isAnnotationPresent(Rest.class)) {
			for (Method methods : clazz.getDeclaredMethods()) {
				if (methods.isAnnotationPresent(Get.class) && methods.getAnnotation(Get.class).value().equals(getActionURL)) {
					//TODO: get parameters with regex
//					if (parameters != null && parameters.size() > 0) {
//						resp.getWriter().println(methods.invoke(obj, parameters.toArray()).toString());
//					} else {
						resp.getWriter().println(methods.invoke(obj));
					//}
				} 
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static Object createNewInstance(Class clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<?> ctor;
		ctor = clazz.getConstructors()[0];
		Object object = ctor.newInstance();
		return object;
	}

}
