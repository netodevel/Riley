package br.com.rileyframework;

import java.util.ArrayList;
import java.util.List;

import br.com.rileyframework.utils.GeneratorRegex;

public abstract class Resource implements RileyAdapter {

	private List<Route> routes = new ArrayList<Route>();
	private String baseUrl;
	
	public Resource() {
	}
	
	@Override
	public void main() {
	}
	
	public void get(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "GET");
		getRoutes().add(routeObj);
	}
	
	public void post(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "POST");
		getRoutes().add(routeObj);
	}
	
	public void put(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "PUT");
		getRoutes().add(routeObj);
	}
	
	public void delete(String route, HttpHandlerRequest handler) {
		Route routeObj = buildRoute(route, handler, "DELETE");
		getRoutes().add(routeObj);
	}
	
	public void baseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
		
	private Route buildRoute(String route, HttpHandlerRequest handler, String httpMethod) {
		Route routeObj = new Route();
		String routeFromBase = this.baseUrl != null ? this.baseUrl + route : route;

		routeObj.setRoute(routeFromBase);
		routeObj.setHandler(handler);
		routeObj.setHttpMethod(httpMethod);
		routeObj.setRouteRegex(GeneratorRegex.generatorRegexFromUrl(routeFromBase));
		return routeObj;
	}
	
	public interface HttpHandlerRequest {
		Response handler(Request request, Response response) throws InterruptedException;
	}
	
	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
}
