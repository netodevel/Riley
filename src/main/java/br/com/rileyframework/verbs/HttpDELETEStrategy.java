package br.com.rileyframework.verbs;

import br.com.rileyframework.Request;
import br.com.rileyframework.Route;
import br.com.rileyframework.helpers.RequestHelper;

public class HttpDELETEStrategy implements HttpVerbStrategy {

    @Override
    public Request createRequest(String servletPath, Route route, String body) {
        return Request.builder()
                .pathVariables(RequestHelper.getPathVariables(route.getRoute(), servletPath))
                .build();
    }
}
