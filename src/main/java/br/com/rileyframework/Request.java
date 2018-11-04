package br.com.rileyframework;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Request {

	private Map<String, String> pathVariables;
	private Map<String, String> queryParams;
	private String requestBody;

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

	private String getParam(String parameterName) {
		String formattedParameter = "{" + parameterName + "}";
		String param = pathVariables.get(formattedParameter);
		return param;
	}
	
}
