package br.com.rileyframework.verbs;

import br.com.rileyframework.Request;
import br.com.rileyframework.Route;
import br.com.rileyframework.exceptions.RileyException;

import java.util.Arrays;
import java.util.List;

public class HttpVerbProcessor {

    public Request execute(String httpVerb, String servletPath, Route route, String body){
        for (HttpVerbStrategy strategy: strategies()) {
            if (strategy.getClass().getSimpleName().contains(httpVerb)) {
                return strategy.createRequest(servletPath, route, body);
            }
        }
        System.out.println("[ERROR] ==> location: HttpVerbProcessor.execute, error: ".concat("verb ".concat(httpVerb).concat("not implemented yet")));
        throw new RileyException("Not Found", "verb ".concat(httpVerb).concat("not implemented yet"));
    }

    public List<HttpGETStrategy> strategies(){
        return Arrays.asList(new HttpGETStrategy());
    }
}
