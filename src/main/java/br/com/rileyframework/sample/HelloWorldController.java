package br.com.rileyframework.sample;

import br.com.rileyframework.Request;
import br.com.rileyframework.Resource;
import br.com.rileyframework.Response;

public class HelloWorldController extends Resource {

    {
        get("/", new HttpHandlerRequest() {
            @Override
            public Response handler(Request req, Response res) {
                return res.json("hello world").status(200);
            }
        });

        get("/home", new HttpHandlerRequest() {
            @Override
            public Response handler(Request request, Response response) {
                return response.json("home").status(200);
            }
        });
    }

}
