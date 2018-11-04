package br.com.rileyframework;

import br.com.rileyframework.Resource.HttpHandlerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Route {

	private String route;
	private HttpHandlerRequest handler;
	private String httpMethod;
	private String routeRegex;

}
