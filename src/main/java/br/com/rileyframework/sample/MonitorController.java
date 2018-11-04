package br.com.rileyframework.sample;

import br.com.rileyframework.Request;
import br.com.rileyframework.Resource;
import br.com.rileyframework.Response;
import br.com.rileyframework.monitor.HttpTracer;

public class MonitorController extends Resource {

    {
        final HttpTracer httpTracer = new HttpTracer();

        get("/http-tracer", new HttpHandlerRequest() {
            @Override
            public Response handler(Request request, Response response) {
                return response.json(httpTracer.routes()).status(200);
            }
        });
    }

}
