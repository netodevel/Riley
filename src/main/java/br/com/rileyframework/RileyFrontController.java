package br.com.rileyframework;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;

public class RileyFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<HandlerMapping> mappings;

	private Map<String, HandlerMapping> keyValue;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			mappings = new ArrayList<HandlerMapping>();
			keyValue = new HashMap<String, HandlerMapping>();
			
			RileyInit.init(mappings);
			for (HandlerMapping mapping : mappings) {
				keyValueMappings(mapping.getAction(), mapping);
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (InstantiationException | IllegalAccessException| IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings({"unchecked", "rawtypes" })
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

		final String servletPath = req.getServletPath();
		HandlerMapping handlerMapping = keyValue.get(servletPath);
		
		if (handlerMapping != null) {
			Class clazzName = Class.forName(handlerMapping.getControllerAction());
			Object obj = createNewInstance(clazzName);
			if (obj == null) {
				System.out.println("error creating object!");
			} else {
				System.out.println("success!" + obj.toString());
			}
			Class clazz = obj.getClass();
			if (clazz.isAnnotationPresent(Rest.class)) {
				for (Method methods : clazz.getDeclaredMethods()) {
					if (methods.isAnnotationPresent(Get.class)) {
						resp.getWriter().println(methods.invoke(obj));
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

	public void keyValueMappings(String action, HandlerMapping handlerMapping) {
		keyValue.put(action, handlerMapping);
	}


}
