package br.com.rileyframework;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import br.com.rileyframework.servers.ServerFactory;

public class Riley {

	private static final String BASE_PACKAGE_CONTROLLERS = "controllers";
	private ServerFactory serverFactory;

	public Riley() {
		this.serverFactory = new ServerFactory();
	}
	
	public void init(String typeServer) throws Exception {
		this.serverFactory.create(typeServer);
		for (Route routes : registerControllers()) {
			System.out.println("route: " + routes.getRoute());
			System.out.println("regex: " + routes.getRouteRegex());
		}
		
	}

	@SuppressWarnings("rawtypes")
	public List<Route> registerControllers() throws Exception {
		Reflections reflections = new Reflections(BASE_PACKAGE_CONTROLLERS);
		
		// get all controller of application
		Set<Class<? extends ApplicationController>> allControllers = reflections.getSubTypesOf(ApplicationController.class);

		// list to register urls 
		List<Route> listRoutes = new ArrayList<Route>();
		
		for (Class<?> controller : allControllers) {
		   Class c = Class.forName(controller.getName());
			ApplicationController controllerIstanced = (ApplicationController) createNewInstance(c);
			for (int j = 0; j < controllerIstanced.getRoutes().size(); j++) {
				listRoutes.add(controllerIstanced.getRoutes().get(j));
			}
			
		}
		return listRoutes;
	}
	
	private static Object createNewInstance(Class<?> clazz) throws Exception {
		try{ 
			Constructor<?> ctor;
			ctor = clazz.getConstructor();
			Object object = ctor.newInstance();
			return object;
		} catch(Exception e) {
			throw new Exception("error:" + e.getMessage());
		}
	}

}
