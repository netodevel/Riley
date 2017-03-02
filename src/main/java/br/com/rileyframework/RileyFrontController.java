package br.com.rileyframework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.utils.BasePackageMemory;
import br.com.rileyframework.utils.PathVariablesUtil;

public class RileyFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RileyFramework rileyFramework = new RileyFramework();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackage = BasePackageMemory.getPackageInMemory(new File("src/main/resources/basepackage.txt"));

		try {
			rileyFramework.handlerMappings(basePackage);
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

	@SuppressWarnings({"unchecked", "rawtypes" })
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

		final String servletPath = req.getServletPath();

		PathVariablesUtil pathVariable = new PathVariablesUtil();
		String getActionURL = pathVariable.getActionURL(servletPath, rileyFramework.getMappings());
		
		HandlerMapping handlerMapping = rileyFramework.getKeyValue().get(getActionURL);
		if (handlerMapping != null) {
			Class clazzName = Class.forName(handlerMapping.getControllerAction());
			Object obj = createNewInstance(clazzName);
			Class clazz = obj.getClass();
			if (clazz.isAnnotationPresent(Rest.class)) {
				for (Method methods : clazz.getDeclaredMethods()) {
					if (methods.isAnnotationPresent(Get.class) && methods.getAnnotation(Get.class).value().equals(getActionURL)) {
						List<String> parameters = pathVariable.getParameters(servletPath, getActionURL);
						if (parameters != null && parameters.size() > 0) {
							resp.getWriter().println(methods.invoke(obj, parameters.toArray()).toString());
						} else {
							resp.getWriter().println(methods.invoke(obj));
						}
					} 
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
