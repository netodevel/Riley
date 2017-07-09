package br.com.rileyframework;

import br.com.rileyframework.RileyFrameworkBuilder.HttpHandlerRequest;

public class Route {

	private String route;
	
	private HttpHandlerRequest handler;
	
	private String httpMethod;

	private String routeRegex;

	public Route() {
		super();
	}

	public Route(String route, HttpHandlerRequest handler, String httpMethod,
			String routeRegex) {
		super();
		this.route = route;
		this.handler = handler;
		this.httpMethod = httpMethod;
		this.routeRegex = routeRegex;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public HttpHandlerRequest getHandler() {
		return handler;
	}

	public void setHandler(HttpHandlerRequest handler) {
		this.handler = handler;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRouteRegex() {
		return routeRegex;
	}

	public void setRouteRegex(String routeRegex) {
		this.routeRegex = routeRegex;
	}
	
	
	
	
}
