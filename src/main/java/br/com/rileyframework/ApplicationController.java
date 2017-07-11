package br.com.rileyframework;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.utils.GeneratorRegex;

public abstract class ApplicationController {

	private List<Route> routes = new ArrayList<Route>();
	
	public void get(String route, HttpHandlerRequest handler) {
		Route routeObj = new Route();
		
		routeObj.setRoute(route);
		routeObj.setHandler(handler);
		routeObj.setHttpMethod("GET");
		routeObj.setRouteRegex(GeneratorRegex.generatorRegexFromUrl(route));
		
		getRoutes().add(routeObj);
	}
	
	public ApplicationController(){
		
	}
	
	public interface HttpHandlerRequest {
		void handler(HttpServletRequest request, HttpServletResponse response);
	}
	
	public static void main(String[] args) {
		
		UserController userController = new UserController();
		OtherController otherController = new OtherController();
		
		String path = "/index";
		String other = "/outher";
		
		List<Route> allRoutes = createAllRoutes(userController, otherController);
		

		for (Route route : allRoutes) {
			if (route.getRoute().equals(path)) {
				route.getHandler().handler(null, null);
			}
			if (route.getRoute().equals(other)) {
				route.getHandler().handler(null, null);
			}
		}
		
		//userController.getRoutes().get(0).getHandler().handler(null, null);
		
	}

	private static List<Route> createAllRoutes(UserController userController,
			OtherController otherController) {
		List<Route> allLists = new ArrayList<Route>();

		for (Route route : userController.getRoutes()) {
			allLists.add(route);
		}
		for (Route route : otherController.getRoutes()) {
			allLists.add(route);
		}
		return allLists;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	
	
}

class OtherController extends ApplicationController {
	
	{
		get("/outher", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("other...");
			}
		});
	}
}

class UserController extends ApplicationController {

	public UserController() {
	}
	
	public UserController(HttpHandlerRequest handler) {
		handler.handler(null, null);
	}
	
	{
	
		get("/index", new HttpHandlerRequest() {
			
			public void handler(HttpServletRequest request, HttpServletResponse response) {

				System.out.println("delegate index..");
			}
		});

		get("/show", new HttpHandlerRequest() {
			
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("delegate show..");
			}
		});

		System.out.println("entrou here?" + getRoutes().size());

	}
}