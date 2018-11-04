package br.com.rileyframework.monitor;

import br.com.rileyframework.Riley;
import br.com.rileyframework.Route;

import java.util.Arrays;
import java.util.List;

public class HttpTracer {

    private Riley riley;

    public HttpTracer() {
        riley = new Riley();
    }

    public List<Route> routes() {
        try {
            return riley.registerControllers();
        } catch (Exception e) {
            System.out.println("[ERROR] ==> location: HttpTracer.routes(), error: " + e.getMessage());
        }
        return Arrays.asList();
    }

}
