package br.com.riley.router;

import com.greghaskins.spectrum.Spectrum;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class RouterTest {{

    describe("Dado uma lista de rotas", () -> {
        List<Route> routes = new ArrayList<>();
        afterEach(routes::clear);

        it("deve ser vazia por padrao", () -> {
            assertEquals(0, routes.size());
        });

        it("deve cadastrar rotas", () -> {
            Route route = Route.builder()
                    .method(HttpConsts.METHOD_GET)
                    .path("/home")
                    .routeAction(() -> "action executed")
                    .build();

            Router router = new Router();
            router.routes.add(route);

            assertEquals(1, router.routes.size());
        });
    });

    describe("dado uma rota get", () -> {
        List<Route> routes = new ArrayList<>();
        Route route = Route.builder()
                .method(HttpConsts.METHOD_GET)
                .path("/")
                .routeAction(() -> "hello world")
                .build();

        routes.add(route);

        it("deve retornar o metodo get", ()-> {
            assertEquals(HttpConsts.METHOD_GET, routes.get(0).getMethod());
        });

        it("deve retornar hello world", ()-> {
            assertEquals("hello world", routes.get(0).getRouteAction().execute());
        });

    });

}}


