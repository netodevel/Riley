package br.com.rileyframework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handler request.
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String servletPath = req.getServletPath();
		for (UrlMapping urlMapped : rileyFramework.getMappings()) {
			if (rileyFramework.matchUrl(urlMapped.getRegex(), servletPath)) {
				Class<?> clazzName = Class.forName(urlMapped.getControllerAction());
				Object obj = createNewInstance(clazzName);
				Class<?> clazz = obj.getClass();
				invokeActionGETRequest(resp, servletPath, urlMapped.getAction(), clazz, obj);
				break;
			}
		}
	}

	private void invokeActionGETRequest(HttpServletResponse resp, final String servletPath, String getActionURL, Class<?> clazz, Object obj) throws Exception  {
		try{
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
