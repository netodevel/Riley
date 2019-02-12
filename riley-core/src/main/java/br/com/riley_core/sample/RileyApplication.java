package br.com.riley_core.sample;

import br.com.riley_core.Riley;

import static br.com.riley.router.RouteRegistry.get;
import static br.com.riley.router.RouteResponse.json;

public class RileyApplication {

    public static void main(String[] args) throws Exception {
        Riley.getInstance().start();

        get("/index", (ctx) -> json("Hello World"));
    }

}
