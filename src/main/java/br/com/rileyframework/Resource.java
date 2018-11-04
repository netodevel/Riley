package br.com.rileyframework;

import java.util.ArrayList;
import java.util.List;

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
		String finalRoute = this.baseUrl != null ? this.baseUrl.concat(route) : route;
		return Route.builder()
				.route(finalRoute)
				.handler(handler)
				.httpMethod(httpMethod)
				.routeRegex(finalRoute)
				.build();
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
