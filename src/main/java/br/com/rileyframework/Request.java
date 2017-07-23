package br.com.rileyframework;

import java.util.Map;

public class Request {

	private Map<String, String> pathVariables;
	private Map<String, String> queryParams;
	private Object requestBody;
	
	public Map<String, String> getPathVariables() {
		return pathVariables;
	}
	
	public void setPathVariables(Map<String, String> pathVariables) {
		this.pathVariables = pathVariables;
	}
	
	public Map<String, String> getQueryParams() {
		return queryParams;
	}
	
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	
	public String param(String paramName) {
		String paramFormated = "{" + paramName + "}";
		return pathVariables.get(paramFormated);
	}
	
}
