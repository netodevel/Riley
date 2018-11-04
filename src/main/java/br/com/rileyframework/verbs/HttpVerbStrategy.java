package br.com.rileyframework.verbs;

import br.com.rileyframework.Request;
import br.com.rileyframework.Route;

public interface HttpVerbStrategy {

    Request createRequest(final String servletPath, Route route, String body);

}
