package br.com.rileyframework;

import java.util.ArrayList;
import java.util.List;

import br.com.rileyframework.utils.GeneratorRegex;

public abstract class ApplicationController {

	private List<Route> routes = new ArrayList<Route>();
	
	public void get(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "GET");
		getRoutes().add(routeObj);
	}
	
	public void post(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "POST");
		getRoutes().add(routeObj);
	}
	
	public ApplicationController() {
	}
	
	private Route buildRoute(String route, HttpHandlerRequest handler, String httpMethod) {
		Route routeObj = new Route();
		
		routeObj.setRoute(route);
		routeObj.setHandler(handler);
		routeObj.setHttpMethod(httpMethod);
		routeObj.setRouteRegex(GeneratorRegex.generatorRegexFromUrl(route));
		return routeObj;
	}
	
	public interface HttpHandlerRequest {
		Response handler(Request request, Response response);
	}
	
	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
}
