package br.com.rileyframework;

import java.util.Map;

import com.google.gson.Gson;

public class Request {

	private Map<String, String> pathVariables;
	private Map<String, String> queryParams;
	private String requestBody;
	
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
	
	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Object body(Class<?> clazz) {
		return new Gson().fromJson(getRequestBody(), clazz);
	}
	
	public String param(String paramName) {
		return getParam(paramName);
	}
	
	public Integer intParam(String paramName) {
		String param = getParam(paramName);
		return Integer.parseInt(param);
	}

	private String getParam(String paramName) {
		String paramFormated = "{" + paramName + "}";
		String param = pathVariables.get(paramFormated);
		return param;
	}
	
}
