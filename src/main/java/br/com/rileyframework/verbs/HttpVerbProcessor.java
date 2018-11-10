package br.com.rileyframework.verbs;

import br.com.rileyframework.Request;
import br.com.rileyframework.Response;
import br.com.rileyframework.Route;
import br.com.rileyframework.exceptions.RileyException;
import br.com.rileyframework.ui.RileyTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public void makeResponse(Route route, Request request, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Response response = Response.builder().printWriter(resp.getWriter()).build();
        if (route.getHttpMethod().equals(req.getMethod())) {
            Response responseOfRoute = route.getHandler().handler(request, response);
            resp.setContentType("application/json");
            resp.setStatus(responseOfRoute.getCode());
        }
    }

    public void makeResponseHtml(Route route, Request request, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Response response = Response.builder().printWriter(resp.getWriter()).build();
        if (route.getHttpMethod().equals(req.getMethod())) {
            Response responseOfRoute = route.getHandler().handler(request, response);

            String htmlGerado = new RileyTemplateEngine()
                    .modelAndView(response.getModelAndView())
                    .format(response.getHtml());

            resp.getWriter().print(htmlGerado);
            resp.setStatus(responseOfRoute.getCode());
        }
    }

    public List<HttpGETStrategy> strategies(){
        return Arrays.asList(new HttpGETStrategy());
    }
}
