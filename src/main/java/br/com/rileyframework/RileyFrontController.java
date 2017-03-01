package br.com.rileyframework;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.controller.UserController;

public class RileyFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			doProcess(req, resp);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


		/**
		 * Sample Test
		 */
		UserController user = (UserController) createNewInstance(UserController.class);
		
		if (user == null) {
			System.out.println("erro ao criar objeto");
		} else {
			System.out.println("funfou: " + user.toString());
		}

		Class clazz = user.getClass();
		if (clazz.isAnnotationPresent(Rest.class)) {
			for (Method methods : clazz.getDeclaredMethods()) {
				if (methods.isAnnotationPresent(Get.class)) {
					resp.getWriter().println(methods.invoke(user));
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
