package br.com.rileyframework;

import br.com.rileyframework.servers.ServerFactory;
import br.com.rileyframework.utils.SetupLoader;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Riley {

	private ServerFactory serverFactory;
	private SetupLoader setupLoader;
	private List<Route> listRoutes;

	public Riley() {
		this.serverFactory = new ServerFactory();
		this.setupLoader = new SetupLoader();
	}

	public void init(String typeServer) throws Exception {
		this.serverFactory.create(typeServer);
	}

	@SuppressWarnings("rawtypes")
	public List<Route> registerControllers() throws Exception {
		System.out.println("[INFO] ==> base-package: " + this.setupLoader.basePackage());
		Reflections reflections = new Reflections(this.setupLoader.basePackage().trim());

		// get all controller of application
		Set<Class<? extends Resource>> allControllers = reflections.getSubTypesOf(Resource.class);

		// list to register urls 
		listRoutes = new ArrayList<>();

		for (Class<?> controller : allControllers) {
			Class c = Class.forName(controller.getName());
			Resource controllerIstanced = (Resource) createNewInstance(c);
			for (int j = 0; j < controllerIstanced.getRoutes().size(); j++) {
				listRoutes.add(controllerIstanced.getRoutes().get(j));
				System.out.println("[INFO] ==> mapped route: " +
						"HTTP METHOD ["+controllerIstanced.getRoutes().get(j).getHttpMethod()+"]"+
						", PATH["+ controllerIstanced.getRoutes().get(j).getRoute()+"]");
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
