package br.com.rileyframework;

import br.com.rileyframework.utils.SetupLoader;
import com.riley.server.ConfigureServerAdapter;
import com.riley.server.JettyServer;
import com.riley.server.RileyServerException;
import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Riley {

	@Getter @Setter
	private List<Route> routes;
	private ConfigureServerAdapter configureServerAdapter;
	private static Riley instance;
	private SetupLoader setupLoader;

	public static synchronized Riley getInstance(){
		if (instance == null){
			instance = new Riley();
		}
		return instance;
	}

	public Riley() {
		this.setupLoader = new SetupLoader();
	}

	public List<Route> registerControllers() {
		try {
			System.out.println("[INFO] ==> base-package: " + this.setupLoader.basePackage());
			Reflections reflections = new Reflections(this.setupLoader.basePackage().trim());

			// get all controller of application
			Set<Class<? extends Resource>> allControllers = reflections.getSubTypesOf(Resource.class);

			// list to register urls
			routes = new ArrayList<>();

			for (Class<?> controller : allControllers) {
				Class c = Class.forName(controller.getName());
				Resource controllerIstanced = (Resource) createNewInstance(c);
				for (int j = 0; j < controllerIstanced.getRoutes().size(); j++) {
					routes.add(controllerIstanced.getRoutes().get(j));
					System.out.println("[INFO] ==> mapped route: " +
							"HTTP METHOD ["+controllerIstanced.getRoutes().get(j).getHttpMethod()+"]"+
							", PATH["+ controllerIstanced.getRoutes().get(j).getRoute()+"]");
				}
			}
		} catch (Exception e) {
			System.out.println("[ERROR] ==> location: Riley.registerControllers(), error: ".concat(e.getMessage()));
		}

		return routes;
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

	public void registerController(Route route) {
		routes = registerControllers();
		routes.add(route);
	}

	public void configureServer(ConfigureServerAdapter configureServerAdapter) {
		this.configureServerAdapter = configureServerAdapter;
	}

	public void start() throws Exception {
		if (this.configureServerAdapter == null) {
			this.configureServerAdapter = getServerDefault();
		}
		validateServer();
		this.configureServerAdapter.start();
	}

	public void validateServer() {
		if (this.configureServerAdapter.port() == null){
			this.configureServerAdapter = null;
			throw new IllegalArgumentException("Port can not be null");
		}
		if (this.configureServerAdapter.servlet() == null){
			this.configureServerAdapter = null;
			throw new IllegalArgumentException("Servlet can not be null");
		}
	}

	protected ConfigureServerAdapter getServerDefault() {
		return JettyServer.builder()
				.servlet(new RileyServlet(Riley.getInstance()))
                .jettyPort(3000)
                .build();
	}

	public void shutDown() throws Exception {
		if (isNotValidToShutdownServer()) throw new RileyServerException("Server not started");
		this.configureServerAdapter.shutDown();
		this.configureServerAdapter = null;
	}

	private boolean isNotValidToShutdownServer() {
		if (this.configureServerAdapter == null || !configureServerAdapter.isStarted()) return true;
		return false;
	}

	public Server getServer() {
		Server server = new Server();
		server.setPort(this.configureServerAdapter.port());
		server.setIsStaterd(this.configureServerAdapter.isStarted());
		server.setServlet(this.configureServerAdapter.servlet());
		return server;
	}

}
